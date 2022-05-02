package pl.coderslab.web.app.user;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "blockUser", value = "/app/blockUser")
public class BlockUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            AdminDao adminDao = new AdminDao();
            Admin adminUpt = adminDao.read(id);
            if(adminUpt.getEnable() == 1) adminUpt.setEnable(0);
            else if(adminUpt.getEnable() == 0) adminUpt.setEnable(1);
            adminDao.update(adminUpt);
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
