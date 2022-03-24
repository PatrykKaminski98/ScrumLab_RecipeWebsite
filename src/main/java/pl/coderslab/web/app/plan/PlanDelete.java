package pl.coderslab.web.app.plan;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.PlanDetails;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "planDelete", value = "/app/plan/delete")
public class PlanDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
         Integer id = Integer.valueOf(request.getParameter("id"));

        List<PlanDetails> planDetails = planDao.getPlanDetails(id);
        for (PlanDetails planDetail : planDetails) {
            recipeDao.deleteRecipeFromPlan(planDetail.getRecipeId(),id,planDetail.getMealName());
        }
        planDao.delete(id);
        response.sendRedirect("/app/plan/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
