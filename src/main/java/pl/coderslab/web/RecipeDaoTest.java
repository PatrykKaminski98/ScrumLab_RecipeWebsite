package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RecipeDaoTest", value = "/recipeDaoTest")
public class RecipeDaoTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //read
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipeRead = recipeDao.read(5);
        System.out.println(recipeRead.toString());
        //findAll
        List<Recipe> recipes = recipeDao.findAll();
        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
        /*  create
        Recipe create = recipes.get(8);
        recipeDao.create(create);
        */
        // delete
        // recipeDao.delete(11);

        //  update
        //recipeDao.update(recipes.get(0));


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
