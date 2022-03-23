package pl.coderslab.web.app;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "dashboard", value = "/app/dashboard")
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("admin") != null) {
            Admin admin = (Admin) session.getAttribute("admin");
            request.setAttribute("adminName", admin.getFirstName());
            PlanDao planDao = new PlanDao();

            int numberOfPlans = planDao.numberOfPlans(admin);
            request.setAttribute("planQty",numberOfPlans);

            RecipeDao recipeDao = new RecipeDao();
            int numberOfRecipes = recipeDao.numberOfRecipes(admin);
            request.setAttribute("recipe",numberOfRecipes);


            request.getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
