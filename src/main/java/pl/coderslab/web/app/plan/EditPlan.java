package pl.coderslab.web.app.plan;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditPlan", value = "/app/plan/edit")
public class EditPlan extends HttpServlet {
    private final String charsetEncoding = "utf-8";
    private final String contentType = "text/html";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(charsetEncoding);
        response.setContentType(contentType);
        response.setCharacterEncoding(charsetEncoding);

        Integer id = Integer.valueOf(request.getParameter("id"));
        PlanDao planDao = new PlanDao();

        Plan planToEdit = planDao.read(id);
        request.setAttribute("plan", planToEdit);



        request.getServletContext().getRequestDispatcher("/plan_edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding(charsetEncoding);
        response.setContentType(contentType);
        response.setCharacterEncoding(charsetEncoding);

        PlanDao planDao = new PlanDao();

        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String desc = request.getParameter("desc");

        Plan planToEdit = planDao.read(id);
        planToEdit.setName(name);
        planToEdit.setDescription(desc);

        planDao.update(planToEdit);

        response.sendRedirect("/app/plan/list");
    }
}
