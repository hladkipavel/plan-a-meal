package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/app/recipe/list")
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecipeDao recipeDao = new RecipeDao();
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        List<Recipe> recipeList = recipeDao.findAll(admin.getId());
        req.setAttribute("recipeList", recipeList);

        getServletContext().getRequestDispatcher("/app/list.jsp").forward(req,resp);
    }
}
