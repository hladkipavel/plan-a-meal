package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.LatestPlan;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/plan/details")
public class PlanDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PlanDao planDao = new PlanDao();
        Plan plan = planDao.read(id);
        List<LatestPlan> detailsPlan = planDao.getDetailsForPlan(id);
        req.setAttribute("plan", plan);
        req.setAttribute("detailsPlan", detailsPlan);
        getServletContext().getRequestDispatcher("/app/detailsPlan.jsp").forward(req, resp);
    }
}
