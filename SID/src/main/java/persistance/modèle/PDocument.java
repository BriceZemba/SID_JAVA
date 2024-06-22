package persistance.modèle;

import mediatek2021.Document;

import java.util.HashMap;
import java.util.Map;

/*
 * La classe implémentant Document,
 * afin de pouvoir réaliser toutes les opérations de persistances vers la base de données
 */
public class PDocument implements Document {

    /*
     * la Map data permet de rapidement ajouter, supprimer, et obtenir les attributs d'un fichier
     */
    private Map<String, Object> data = new HashMap<String, Object>();

    public PDocument(int id, int type, String titre, String auteur, boolean emprunt) {
        data.put("id", id);
        data.put("type", type);
        data.put("titre", titre);
        data.put("auteur", auteur);
        data.put("emprunt", emprunt);
    }

    /*
     * Permet de retourner un tableau associatif (key -> value) représentant les attributs du document
     * @return Map.Entry<String, Object>[] / Set<Map.Entry<String, Object>>
     */
    @Override
    public Object[] data() {
        return data.entrySet().toArray();
    }

    public int getId() {
        return (int) data.get("id");
    }

    public String getType() {
        switch((int)data.get("type")) {
            case 1:
                return "Livre";
            case 2:
                return "DVD";
            case 3:
                return "CD";
            default:
                break;
        }
        return "invalide";
    }

    public String getTitre() {
        return (String) data.get("titre");
    }

    public String getAuteur() {
        return (String) data.get("auteur");
    }

    public boolean estEmprunté() {
        return (boolean) data.get("emprunt");
    }

    @Override
    public String toString() {
        return "#" + data.get("id") + "\"" + data.get("titre") + "\" (" + this.getType() + ")";
    }
}
