package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter("/login")
public class AuthorizationFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = httpRequest.getSession();
        if (session != null && email != null && password != null) {
            httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest, httpResponse);
        } else {
            AdminDao adminDao = new AdminDao();
            Admin admin = adminDao.readLoginAdmin(email, password);
            if(admin == null){
                httpRequest.getRequestDispatcher("/login.jsp").forward(httpRequest, httpResponse);
            }else {
                httpRequest.getRequestDispatcher("/dashboard.jsp").forward(httpRequest, httpResponse);
            }
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}