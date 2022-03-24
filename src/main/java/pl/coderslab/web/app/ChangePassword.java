package pl.coderslab.web.app;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangePassword", value = "/app/user/changePassword")
public class ChangePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");

        request.getServletContext().getRequestDispatcher("/user_change_password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDao adminDao = new AdminDao();
        Integer id = Integer.valueOf(request.getParameter("id"));
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");

        if (pass1.equals(pass2)) {
            Admin adminToEdit = adminDao.read(id);
            adminToEdit.setPassword(pass1);
            adminDao.update(adminToEdit);
            response.sendRedirect("/app/dashboard");
        }
    }
}
