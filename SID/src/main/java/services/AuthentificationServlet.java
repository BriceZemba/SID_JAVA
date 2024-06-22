package services;

import java.io.IOException;
import java.io.Serial;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mediatek2021.Mediatek;
import persistance.Session;
import persistance.modèle.LoggedUser;

/*
 * Servlet de connexion
 * Automatiquement redirigé sur cette page lors de l'accès à un url restreint (actuellement toutes les url sauf cette page)
 */
@WebServlet(name="login", urlPatterns = { "/login" })
public class AuthentificationServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public AuthentificationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        if(Session.isStarted(request.getSession()))
            dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/indexView.jsp");
        else
            dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //formulaire envoyé
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoggedUser user = null;
        boolean invalid = false;
        String error = null;

        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            invalid = true;
            error = "Veuillez indiquer votre nom d'utilisateur et votre mot de passe!";
        } else {
            user = (LoggedUser) Mediatek.getInstance().getUser(username, password);
            if (user == null) {
                invalid = true;
                error = "Utilisateur ou mot de passe non valide";
            }
        }

        if (invalid) {
            request.setAttribute("error", error);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);
        }
        // Connexion avec succès, redirection vers la page d'accueil
        else {
            Session.startSession(request, user);
            request.setAttribute("success", "Connexion avec succès ! Bienvenue " + user.login() +".");
            response.sendRedirect(request.getContextPath());
        }
    }
}
