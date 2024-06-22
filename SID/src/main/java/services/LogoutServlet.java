package services;

import persistance.Session;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.Serial;

/*
 * Servlet de déconnexion d'un utilisateur
 * Lorsqu'un utilisateur visite cet url, il est automatiquement déconnecté et sa session détruite
 */
@WebServlet(name="logout", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session.endSession(request.getSession());
        response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
