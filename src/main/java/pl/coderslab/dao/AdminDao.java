package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.err;
import static pl.coderslab.utils.DbUtil.hashPassword;

public class AdminDao {

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name,last_name,email,password) VALUES (?,?,?,?);";

    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id = ?;";

    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";

    private static final String READ_ADMIN_QUERY = "SELECT * from admins where id = ?;";

    private static final String UPDATE_ADMIN_QUERY = "UPDATE	admins SET firs_name = ? , last_name = ?, email = ?, password = ? WHERE	id = ?;";
    private static final String QUERY_LOGIN_ADMIN = "SELECT * FROM admins WHERE email = ? ";

    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_ADMIN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, admin.getFirstName());
            insertStm.setString(2, admin.getLastName());
            insertStm.setString(3, admin.getEmail());
            insertStm.setString(4, hashPassword(admin.getPassword()));
//            insertStm.setInt(5, admin.getSuperAdmin());
//            insertStm.setInt(6, admin.getEnable());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admin.setId(generatedKeys.getInt(1));
                    return admin;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void delete(Integer adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, adminId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Admin not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMINS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin adminToAdd = new Admin();
                adminToAdd.setId(resultSet.getInt("id"));
                adminToAdd.setFirstName(resultSet.getString("firstName"));
                adminToAdd.setLastName(resultSet.getString("lastName"));
                adminToAdd.setEmail(resultSet.getString("email"));
                adminToAdd.setPassword(resultSet.getString("password"));
                adminToAdd.setSuperAdmin(resultSet.getInt("superAdmin"));
                adminToAdd.setEnable(resultSet.getInt("enable"));
                adminList.add(adminToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;

    }

    public Admin read(Integer adminId) {
        Admin admin = new Admin();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("firstName"));
                    admin.setLastName(resultSet.getString("lastName"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperAdmin(resultSet.getInt("superAdmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;

    }

    public void update(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            statement.setInt(7, admin.getId());
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPassword());
            statement.setInt(5, admin.getSuperAdmin());
            statement.setInt(6, admin.getEnable());

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Admin readLoginAdmin(String email, String password){
        Admin admin = null;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN_ADMIN)
        ) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                
                while (resultSet.next()) {
                    String passwordBD = resultSet.getString("password");
                    if(BCrypt.checkpw(password, passwordBD)) {
                        admin = new Admin();
                        admin.setId(resultSet.getInt("id"));
                        admin.setFirstName(resultSet.getString("firstName"));
                        admin.setLastName(resultSet.getString("lastName"));
                        admin.setEmail(resultSet.getString("email"));
                        admin.setPassword(passwordBD);
                    }
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
}