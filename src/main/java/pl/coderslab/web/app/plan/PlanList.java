package pl.coderslab.web.app.plan;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlanList", value = "/app/plan/list")
public class PlanList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        HttpSession session = request.getSession();
        if(session.getAttribute("admin") == null) {
            response.sendRedirect("/login");
        }
            List<Plan> allPlans = planDao.findAll((Admin) session.getAttribute("admin"));

        request.setAttribute("plans", allPlans);
        getServletContext().getRequestDispatcher("/WEB-INF/plan/plan_list.jsp")
                    .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
