package pl.coderslab.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "testapp", value = "/app/test")
public class LoginSessionTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Do wywalenia potem
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("admin")); //co widzi server
        response.getWriter().println("Dziala"); // wskazuje na dostep do strony
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
