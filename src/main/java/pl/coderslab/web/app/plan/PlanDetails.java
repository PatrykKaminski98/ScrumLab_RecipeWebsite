package pl.coderslab.web.app.plan;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

@WebServlet(name = "planDetails", value = "/app/plan/details")
public class PlanDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();

        Plan plan = planDao.read(Integer.parseInt(request.getParameter("id")));
        List<pl.coderslab.model.PlanDetails> planDetails = planDao.getPlanDetails(Integer.parseInt(request.getParameter("id")));


        if (planDetails.size() > 0) {
            request.setAttribute("plan", plan);
            request.setAttribute("details", planDetails);
            request.setAttribute("planDays", getPlanDays(planDetails));

        }

        request.getServletContext().getRequestDispatcher("/planDetails.jsp").forward(request, response);

    }

    public HashSet<String> getPlanDays(List<pl.coderslab.model.PlanDetails> plan) {
        HashSet<String> planDays = new HashSet<>();
        for (pl.coderslab.model.PlanDetails planDetails : plan) {
            planDays.add(planDetails.getDayName());
        }
        return planDays;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
