package services;

import mediatek2021.Mediatek;
import persistance.Session;
import persistance.modèle.PDocument;
import persistance.modèle.filters.IdSorter;

import java.io.IOException;
import java.io.Serial;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Servlet de la page permettant d'afficher la liste de tous les documents
 */
@WebServlet(name="liste", urlPatterns = {"/list"})
public class ListeServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    public ListeServlet() {
        super();
    }

    public static <T> List<T> cast(List l) {return l; }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Session.isStarted(request.getSession())) {
            List<PDocument> list;

            //Afin de filtrer les documents par type
            String type = request.getParameter("type");
            if(type != null && Pattern.compile("\\d+").matcher(type).matches() && Integer.parseInt(type) <= 3)
                list = cast(Mediatek.getInstance().catalogue(Integer.parseInt(type)));
            else
                list = cast(Mediatek.getInstance().catalogue(0));

            //Futur implémentation de différents tris possible ici (@see persistance.modèle.filters)
            list.sort(new IdSorter());
            request.setAttribute("list", list);

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/listView.jsp");
            dispatcher.forward(request, response);
        }
        else
            response.sendRedirect(request.getContextPath() + "/login");
    }
}