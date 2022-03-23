package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDao adminDao = new AdminDao();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Admin admin = adminDao.loginAuthorization(email, password);
        if(admin != null){
            HttpSession session = request.getSession();
            session.setAttribute("admin", admin);

            response.sendRedirect("/app/dashboard");
        } else {
            request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }
}
