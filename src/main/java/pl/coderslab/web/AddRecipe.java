package pl.coderslab.web;


import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Admin;
import pl.coderslab.model.Recipe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;



@WebServlet("/app/recipe/add")
public class AddRecipe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app/addRecipe.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Recipe recipe = new Recipe();
        RecipeDao recipeDao = new RecipeDao();

        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");


        recipe.setName(req.getParameter("name"));
        recipe.setIngredients(req.getParameter("ingredients"));
        recipe.setDescription(req.getParameter("description"));
        recipe.setCreated(String.valueOf(LocalDateTime.now()));
        recipe.setUpdated(req.getParameter(String.valueOf(LocalDateTime.now())));
        recipe.setPreparationTime(Integer.parseInt(req.getParameter("preparationTime")));
        recipe.setPreparation(req.getParameter("preparation"));
        recipe.setAdminId(admin.getId());
        recipeDao.create(recipe);

        resp.sendRedirect("/app/recipe/list");
    }
}
