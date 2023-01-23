package pl.coderslab;

import pl.coderslab.dao.AdminDao;

public class main {
    public static void main(String[] args) {
        AdminDao adminDao = new AdminDao();
        System.out.println(adminDao.findAll());
    }
}
