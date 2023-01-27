
package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.LatestPlan;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlanDao planDao = new PlanDao();
        RecipeDao recipeDao = new RecipeDao();
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        Integer id = admin.getId();
        String countPlans = planDao.countPlansForAdmin(id);
        String countRecipe = recipeDao.countRecipeForAdmin(id);
        req.setAttribute("name", admin.getFirstName());
        req.setAttribute("countPlans", countPlans);
        req.setAttribute("countRecipe", countRecipe);
        String planName = planDao.getPlanName(id);
        req.setAttribute("planName", planName);
        List<LatestPlan> latestPlans = planDao.getLatestPlanForAdmin(id);
        req.setAttribute("latestPlans", latestPlans);
        getServletContext().getRequestDispatcher("/app/dashboard.jsp").forward(req, resp);
    }
}
