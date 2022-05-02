package pl.coderslab.web.app.plan;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddPlan", value = "/app/plan/add")
public class AddPlan extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            getServletContext().getRequestDispatcher("/WEB-INF/plan/add_plan.jsp")
                    .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession();
            Admin admin = (Admin) session.getAttribute("admin");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            PlanDao planDao = new PlanDao();
            Plan plan = new Plan();
            plan.setName(name);
            plan.setDescription(description);
            plan.setAdmin_id(admin.getId());
            planDao.create(plan);

            response.sendRedirect("/app/plan/list");
    }
}
