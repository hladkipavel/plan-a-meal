package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.readLoginAdmin(email, password);
        if(admin == null){
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }else{
            session.setAttribute("admin", admin);
            getServletContext().getRequestDispatcher("/app/dashboard").forward(req,resp);
        }
    }
}

