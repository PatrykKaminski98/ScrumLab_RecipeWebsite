package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "register", value = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = new Admin();
        AdminDao adminDao = new AdminDao();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String pass1 = request.getParameter("password1");
        String pass2 = request.getParameter("password2");

        //Miało nie byc walidacji powtórzenia hasła wiec ignoruje pass2

        admin.setFirstName(name);
        admin.setLastName(surname);
        admin.setEmail(email);
        admin.setPassword(pass1);
        if(adminDao.registerValidate(admin)){
            adminDao.create(admin);
            response.sendRedirect("/login");
        } else {
            request.getServletContext().getRequestDispatcher("/badregistration.jsp").forward(request, response);

        }





    }
}

