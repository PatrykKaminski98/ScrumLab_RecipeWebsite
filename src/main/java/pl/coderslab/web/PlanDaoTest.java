package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlanDaoTest", value = "/PlanDaoTest")
public class PlanDaoTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();

        System.out.println(planDao.read(1)); //read
        List<Plan> all = planDao.findAll();//findall
        for (Plan plan : all) {
            System.out.println(plan);
        }
       // planDao.create(all.get(1)); //create
        Plan plan = planDao.read(10);
        plan.setDescription("23");
        planDao.update(plan);

        //planDao.delete(5);



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
