package persistance.modèle;

import mediatek2021.Utilisateur;

/*
 * Implémentation d'un Utilisateur de l'application
 * Les utilisateurs sont forcément connectés
 * On peut implémenter ici de futur rôles si nécessaires
 */
public class LoggedUser implements Utilisateur {

    private String username;
    private String password;

    public LoggedUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String login() {
        return this.username;
    }

    @Override
    public String password() {
        return this.password;
    }

    @Override
    public Object[] data() {
        return new Object[0];
    }

    @Override
    public String toString() {
        return this.username;
    }
}
