package pl.coderslab.web.app.recipe;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.DayName;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddRecipeToPlan", value = "/app/recipe/plan/add")
public class AddRecipeToPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        DayNameDao dayNameDao = new DayNameDao();
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        List<Plan> allPlans = planDao.findAll(admin);
        List<Recipe> allRecipes = recipeDao.findAll(admin);
        List<DayName> allDays = dayNameDao.findAll();

        request.setAttribute("days", allDays);
        request.setAttribute("recipes", allRecipes);
        request.setAttribute("plans", allPlans);
        getServletContext().getRequestDispatcher("/add_recipe_to_plan.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        response.setContentType("text/html");
        response.setCharacterEncoding("utf8");
        try{
            String planName = request.getParameter("planName");
            String mealName = request.getParameter("mealName");
            int displayOrder = Integer.parseInt(request.getParameter("numberMeal"));
            String recipeName = request.getParameter("recipeName");
            String dayName = request.getParameter("day");
            PlanDao planDao = new PlanDao();
            RecipeDao recipeDao = new RecipeDao();
            DayNameDao dayNameDao = new DayNameDao();


            int recipeId = recipeDao.getRecipeId(recipeName);
            int dayId = dayNameDao.getDayId(dayName);
            int planId = planDao.getPlanId(planName);

            recipeDao.addRecipeToPlan(recipeId, mealName, displayOrder, dayId, planId);
            response.sendRedirect("/app/recipe/plan/add");
        } catch (NumberFormatException e){
            e.printStackTrace();
        }

    }
}
