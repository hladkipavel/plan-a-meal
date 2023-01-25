package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminDao adminDao = new AdminDao();
        String mail = req.getParameter("mail");
        String password = req.getParameter("password");
        Admin admin = adminDao.readLoginAdmin(mail, password);
        if(admin.getId() != 0){
            resp.sendRedirect("/home");
        }else {
            getServletContext().getRequestDispatcher("/home.jsp").forward(req,resp);
        }
    }
}

