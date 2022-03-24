package pl.coderslab.web.app.recipe;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "editRecipe", value = "/app/recipe/edit")
public class EditRecipe extends HttpServlet {
    private final String charsetEncoding = "utf-8";
    private final String contentType = "text/html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(charsetEncoding);
        response.setContentType(contentType);
        response.setCharacterEncoding(charsetEncoding);

        RecipeDao recipeDao = new RecipeDao();
        Integer id = Integer.valueOf(request.getParameter("id"));
        Recipe recipe = recipeDao.read(id);
        request.setAttribute("recipe", recipe);

        request.getServletContext().getRequestDispatcher("/recipe_edit.jsp").forward(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(charsetEncoding);
        response.setContentType(contentType);
        response.setCharacterEncoding(charsetEncoding);

        String name = request.getParameter("name");
        Integer id = Integer.valueOf(request.getParameter("id"));
        String desc = request.getParameter("desc");
        Integer time = Integer.valueOf(request.getParameter("time"));
        String prepDesc = request.getParameter("prepDesc");
        String ingredients = request.getParameter("ingredients");

        RecipeDao recipeDao = new RecipeDao();

        Recipe recipeToEdit = recipeDao.read(id);
        recipeToEdit.setName(name);
        recipeToEdit.setDescription(desc);
        recipeToEdit.setPreparationTime(time);
        recipeToEdit.setPreparation(prepDesc);
        recipeToEdit.setIngredients(ingredients);
        recipeDao.update(recipeToEdit);

        response.sendRedirect("/app/recipe/list");
    }
}
