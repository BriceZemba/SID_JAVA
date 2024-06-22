package persistance;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import persistance.modèle.LoggedUser;

/*
 * Classe utilitaire pour la gestion de Session
 * Lorsqu'un utilisateur se connecte, une session propre lui est crée
 */
public class Session {

    /*
     * Début une session sur le site lorsque l'un utilisateur se connecte
     */
    public static void startSession(HttpServletRequest request, LoggedUser user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
    }

    /*
     * Met fin à la session en cours lorsqu'un utilisateur se déconnecte
     */
    public static void endSession(HttpSession session) {
        session.invalidate();
    }

    /*
     * Permet de vérifier qu'une session est bien en cours avec un utilisateur connecté
     */
    public static boolean isStarted(HttpSession session) {
        return session != null && session.getAttribute("user") != null && session.getAttribute("user") != "";
    }
}