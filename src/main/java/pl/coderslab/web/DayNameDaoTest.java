package pl.coderslab.web;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.model.DayName;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DayNameDaoTest", value = "/dayNameDaoTest")
public class DayNameDaoTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DayNameDao dayNameDao = new DayNameDao();
        List<DayName> daysNames = dayNameDao.findAll();
        for (DayName daysName : daysNames) {
            System.out.println(daysName.toString());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
