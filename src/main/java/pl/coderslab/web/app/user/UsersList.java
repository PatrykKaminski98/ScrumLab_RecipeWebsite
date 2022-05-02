package pl.coderslab.web.app.user;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UsersList", value = "/app/usersList")
public class UsersList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminDao adminDao = new AdminDao();
        List<Admin> allUsers = adminDao.findAll();
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        request.setAttribute("current", admin.getSuperadmin());
        request.setAttribute("users", allUsers);
        getServletContext().getRequestDispatcher("/WEB-INF/user/users_list.jsp")
                .forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
