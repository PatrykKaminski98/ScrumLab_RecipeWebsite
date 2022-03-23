package pl.coderslab.web.app.recipe;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "AddRecipe", value = "/app/recipe/add")
public class AddRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/add_recipe.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        try{
                String name = request.getParameter("name");
                String ingredients = request.getParameter("ingredients");
                String description = request.getParameter("description");
                int preparationTime = Integer.parseInt(request.getParameter("preparationTime"));
                String preparation = request.getParameter("preparation");
                Recipe recipe = new Recipe();
                recipe.setName(name);
                recipe.setIngredients(ingredients);
                recipe.setDescription(description);
                recipe.setPreparationTime(preparationTime);
                recipe.setPreparation(preparation);
                recipe.setAdminId(admin.getId());
                RecipeDao recipeDao = new RecipeDao();
                recipeDao.create(recipe);

            } catch (NumberFormatException e){
                e.printStackTrace();
            }
        response.sendRedirect("/app/recipe/list");

    }
}
