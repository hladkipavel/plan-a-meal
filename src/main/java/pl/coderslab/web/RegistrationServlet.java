package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       getServletContext().getRequestDispatcher("/registrationForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Admin admin = new Admin();
        admin.setFirstName(req.getParameter("name"));
        admin.setLastName(req.getParameter("surname"));
        admin.setEmail(req.getParameter("email"));
        admin.setPassword(req.getParameter("password"));
        AdminDao adminDao = new AdminDao();
        adminDao.create(admin);
        resp.sendRedirect("/login");
    }
}
