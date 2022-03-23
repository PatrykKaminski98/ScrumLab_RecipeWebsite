package pl.coderslab.web.app.recipe;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RecipeDetails", value = "/app/recipe/details")
public class RecipeDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            RecipeDao recipeDao = new RecipeDao();
            Recipe recipe = recipeDao.read(id);
            request.setAttribute("recipe", recipe);
            getServletContext().getRequestDispatcher("/recipe_details.jsp")
                    .forward(request, response);


        }catch(NumberFormatException e){
            e.printStackTrace();
        }




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
