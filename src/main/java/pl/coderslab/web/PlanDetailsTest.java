package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.PlanDetails;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlanDetailsTest", value = "/PlanDetailsTest")
public class PlanDetailsTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        AdminDao adminDao = new AdminDao();
        List<PlanDetails> lastPlan = planDao.getLastPlan(adminDao.read(1));
        for (PlanDetails planDetails : lastPlan) {
            System.out.println(planDetails.getDayName() + " ");
            System.out.println(planDetails.getMealName() + " ");
            System.out.println(planDetails.getRecipeName() + " ");
            System.out.println(planDetails.getRecipeDescription());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
