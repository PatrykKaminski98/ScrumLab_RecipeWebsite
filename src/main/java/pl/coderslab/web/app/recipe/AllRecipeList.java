package pl.coderslab.web.app.recipe;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AllRecipeList", value = "/allRecipesList")
public class AllRecipeList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> allRecipes = recipeDao.findAbsoluteAll();
        request.setAttribute("recipes", allRecipes);
        getServletContext().getRequestDispatcher("/WEB-INF/recipe/allRecipe.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String search = request.getParameter("search");
            String regex = ".*".concat(search).concat(".*");


            RecipeDao recipeDao = new RecipeDao();
            List<Recipe> allRecipes = recipeDao.findAbsoluteAll();
            allRecipes.removeIf(recipe -> !recipe.getName().matches(regex));
            request.setAttribute("recipes", allRecipes);
                getServletContext().getRequestDispatcher("/WEB-INF/recipe/allRecipe.jsp")
                        .forward(request, response);

    }
}
