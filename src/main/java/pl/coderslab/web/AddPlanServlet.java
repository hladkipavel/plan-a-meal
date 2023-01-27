package pl.coderslab.web;

import com.mysql.fabric.xmlrpc.base.Data;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/app/plan/add")
public class AddPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app/addPlan.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Plan plan = new Plan();
        String planName = req.getParameter("planName");
        String planDescription = req.getParameter("planDescription");
        plan.setName(planName);
        plan.setDescription(planDescription);
        plan.setCreated(String.valueOf(LocalDateTime. now()));
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        plan.setAdminId(admin.getId());
        PlanDao planDao = new PlanDao();
        planDao.create(plan);
        getServletContext().getRequestDispatcher("/app/dashboard").forward(req, resp);
    }
}
