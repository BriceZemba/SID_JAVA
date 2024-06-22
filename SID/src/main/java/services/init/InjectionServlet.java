package services.init;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

/*
 * Servlet d'initialisation
 */
@WebServlet(value="/initializeResources", loadOnStartup=1)
public class InjectionServlet extends HttpServlet {

    /*
     * Permet d'éxecuter le bloc static de la classe MediatekData
     * @see persistance.modèle.MediatekData
     */
    @Override
    public void init() {
        try {
            Class.forName("persistance.modèle.MediatekData");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}