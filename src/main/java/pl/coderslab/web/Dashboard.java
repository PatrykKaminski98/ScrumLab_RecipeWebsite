package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.PlanDetails;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

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
            request.setAttribute("planQty", numberOfPlans);

            RecipeDao recipeDao = new RecipeDao();
            int numberOfRecipes = recipeDao.numberOfRecipes(admin);
            request.setAttribute("recipe", numberOfRecipes);

            List<PlanDetails> lastPlan = planDao.getLastPlan(admin);

            if (lastPlan.size() > 0) {
                request.setAttribute("plan", lastPlan);
                request.setAttribute("planDays", getPlanDays(lastPlan));
                request.setAttribute("planName", getPlanName(lastPlan));
            }

            request.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
        }
    }

    public HashSet<String> getPlanDays(List<PlanDetails> plan) {
        HashSet<String> planDays = new HashSet<>();
        for (PlanDetails planDetails : plan) {
            planDays.add(planDetails.getDayName());
        }
        return planDays;
    }

    public String getPlanName(List<PlanDetails> plan) {
        return plan.get(0).getPlanName();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

