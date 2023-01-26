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
///        AdminDao adminDao = new AdminDao();
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
////        Admin admin = adminDao.readLoginAdmin(email, password);
//        HttpSession session = req.getSession();
//        if (session != null && email != null && password != null) {
//            req.getRequestDispatcher("/login.jsp").forward(req, resp);
//        } else {
//            AdminDao adminDao = new AdminDao();
//            Admin admin = adminDao.readLoginAdmin(email, password);
//            if(admin == null){
//                httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest, httpResponse);
//            }else {
//                httpRequest.getRequestDispatcher("/app/dashboard").forward(httpRequest, httpResponse);
//            }
//        }
//        if(admin != null){
//            Cookie cookie = new Cookie("email", email);
//            getServletContext().getRequestDispatcher("/app/dashboard").forward(req, resp);
//        }else {
//            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
//        }
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

