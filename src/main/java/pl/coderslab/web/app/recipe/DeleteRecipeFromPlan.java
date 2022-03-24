package pl.coderslab.web.app.recipe;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeletePlan", value = "/app/recipe/delete")
public class DeleteRecipeFromPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        int planId = Integer.parseInt(request.getParameter("planId"));
        String mealName = request.getParameter("mealName");
        request.setAttribute("recipeId", recipeId);
        request.setAttribute("planId", planId);
        getServletContext().getRequestDispatcher("/confirmDeleteRecipeFromPlan.jsp")
                .forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeID = Integer.parseInt(request.getParameter("recipeId"));
        int planId = Integer.parseInt(request.getParameter("planId"));
        String mealName = request.getParameter("mealName");
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.deleteRecipeFromPlan(recipeID, planId, mealName);
        response.sendRedirect("/app/plan/details?id=" + planId);

    }
}
