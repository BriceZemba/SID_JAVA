package persistance.modèle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mediatek2021.*;
import persistance.DatabaseConnection;

/*
 * La classe implémentant Mediatek
 * Celle-ci est injecté dans l'instance unique de Mediatek lors de l'initialisation
 * @see services.init.InjectionServlet
 */
public class MediatekData implements PersistentMediatek {

	//Injection de la dépendance
	static {
		Mediatek.getInstance().setData(new MediatekData());
	}

	private static Object newLock = new Object();
	private static Object delLock = new Object();

	private MediatekData() {}

	/*
	 * Permet d'obtenir une liste de tous les documents
	 * Par types :
	 * - 0 : Tous
	 * - 1 : Livres
	 * - 2 : DVD
	 * - 3 : CD
	 */
	@Override
	public List<Document> catalogue(int type) {
		Connection db = DatabaseConnection.getConnection();
		List<Document> documents = new ArrayList<>();

		String sql = "SELECT * FROM DOCUMENTS";
		if(type > 0) {
			sql += " WHERE type = ?";
		}

		try {
			PreparedStatement statement = db.prepareStatement(sql);
			if(type > 0)
				statement.setInt(1, type);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				int id = rs.getInt("id");
				int typedoc = rs.getInt("type");
				String titre = rs.getString("titre");
				String autheur = rs.getString("auteur");
				boolean emprunt = rs.getBoolean("emprunt");
				PDocument doc = new PDocument(id, typedoc, titre, autheur, emprunt);
				documents.add(doc);
			}
		} catch (SQLException ignored) {}

		DatabaseConnection.close(db);
		return documents;
	}

	/*
	 * Retourne l'utilisateur uniquement si le bon mot de passe est fournis
	 * Permet de valider une connexion d'un utilisateur
	 */
	@Override
	public Utilisateur getUser(String username, String password) {
		Connection db = DatabaseConnection.getConnection();
		String sql = "SELECT identifiant, motdepasse " +
					 "FROM UTILISATEURS " +
					 "WHERE identifiant = ? AND motdepasse = ?";

		try {
			PreparedStatement statement = db.prepareStatement(sql);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				LoggedUser user = new LoggedUser(username, password);
				DatabaseConnection.close(db);
				return user;
			}
			DatabaseConnection.close(db);
		} catch (SQLException ignored) {}
		return null;
	}

	/*
	 * Trouve et renvoie un Document avec son id
	 */
	@Override
	public Document getDocument(int numDocument) {
		Connection db = DatabaseConnection.getConnection();
		String sql = "SELECT * " +
					 "FROM DOCUMENTS " +
					 "WHERE id = ?";

		try {
			PreparedStatement statement = db.prepareStatement(sql);
			statement.setInt(1, numDocument);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				int typedoc = rs.getInt("type");
				String titre = rs.getString("titre");
				String autheur = rs.getString("auteur");
				boolean emprunt = rs.getBoolean("emprunt");

				DatabaseConnection.close(db);
				return new PDocument(id, typedoc, titre, autheur, emprunt);
			}
		} catch (SQLException ignored) {}

		DatabaseConnection.close(db);
		return null;
	}

	/*
	 * Insère un nouveau Document dans la base de donnée
	 * Pour l'instant, il faut passer en arguments les informations suivantes
	 * type :
	 * - 1 : Livres
	 * - 2 : DVD
	 * - 3 : CD
	 * String titre, String auteur
	 */
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		Connection db = DatabaseConnection.getConnection();
		String sql = "INSERT INTO DOCUMENTS (TYPE, TITRE, AUTEUR) VALUES (?,?,?)";

		synchronized (newLock) {
			try {
				PreparedStatement statement = db.prepareStatement(sql);
				statement.setInt(1, type);
				statement.setString(2, (String) args[0]);
				statement.setString(3, (String) args[1]);

				statement.executeUpdate();
			} catch (Exception e) {
				throw new NewDocException("Impossible d'ajouter le document");
			}
		}
		DatabaseConnection.close(db);
	}

	/*
	 * Permet de supprimer un document de la base par son id
	 * @throw SuppressException si le document est en cours d'emprunt
	 */
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		Connection db = DatabaseConnection.getConnection();
		PDocument doc = (PDocument) getDocument(numDoc);

		if(doc == null)
			throw new SuppressException("Document " + doc + " inexistant ou déjà supprimé.");
		if(doc.estEmprunté())
			throw new SuppressException("Impossible de supprimer le document " + doc + " car il est actuellement emprunté.");

		String sql = "DELETE FROM DOCUMENTS WHERE ID = ?";

		synchronized (delLock) {
			try {
				PreparedStatement statement = db.prepareStatement(sql);
				statement.setInt(1, numDoc);
				statement.executeUpdate();
			} catch (Exception e) {
				throw new SuppressException("Impossible de supprimer le document");
			}
		}
		DatabaseConnection.close(db);
	}
}
