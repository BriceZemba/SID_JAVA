package services;

import mediatek2021.Document;
import mediatek2021.Mediatek;
import persistance.Session;
import persistance.modèle.PDocument;
import persistance.modèle.filters.IdSorter;

import java.io.IOException;
import java.io.Serial;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet de la page d'accueil
 * Panel avec toutes les informations nécessaires
 */
@WebServlet(name="index", urlPatterns = {"/"})
public class IndexServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public IndexServlet() {
        super();
    }

    public static <T> List<T> cast(List l) {return l;}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(Session.isStarted(request.getSession())) {

            List<PDocument> list = cast(Mediatek.getInstance().catalogue(0));
            list.sort(new IdSorter());
            request.setAttribute("list", list);

            int emprunts = 0;
            for (Document doc : list) {
                if(((PDocument) doc).estEmprunté())
                    emprunts++;
            }
            request.setAttribute("emprunts", emprunts);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/indexView.jsp");
            dispatcher.forward(request, response);
        } else
            response.sendRedirect(request.getContextPath() + "/login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}