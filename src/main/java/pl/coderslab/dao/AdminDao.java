package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("DuplicatedCode")
public class AdminDao {

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins (first_name, last_name, email, password, superadmin, enable) VALUES (?,?,?,?,?,?);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins where id = ?;";
    private static final String FIND_ALL_ADMIN_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins where id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins set first_name = ?, last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? WHERE id = ?;";
    private static final String UPDATE_ADMIN_DATA_QUERY = "UPDATE admins set first_name = ?, last_name = ?, email = ? WHERE id = ?;";

    public Admin read(Integer adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_ADMIN_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Admin admin = new Admin();
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                    return admin;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Admin create(Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_ADMIN_QUERY, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, hashPassword(admin.getPassword()));
            statement.setInt(5, 0);
            statement.setInt(6, 1);
            statement.executeUpdate();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    admin.setId(resultSet.getInt(1));
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

    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ADMIN_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin adminToAdd = new Admin();
                adminToAdd.setId(resultSet.getInt("id"));
                adminToAdd.setFirstName(resultSet.getString("first_name"));
                adminToAdd.setLastName(resultSet.getString("last_name"));
                adminToAdd.setEmail(resultSet.getString("email"));
                adminToAdd.setPassword(resultSet.getString("password"));
                adminToAdd.setSuperadmin(resultSet.getInt("superadmin"));
                adminToAdd.setEnable(resultSet.getInt("enable"));
                adminList.add(adminToAdd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adminList;
    }

    public void delete(Integer adminId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY)) {
            statement.setInt(1, adminId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update (Admin admin) {
        try (Connection connection = DbUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_QUERY)) {
            statement.setInt(7,admin.getId());
            statement.setString(1,admin.getFirstName());
            statement.setString(2,admin.getLastName());
            statement.setString(3,admin.getEmail());
            statement.setString(4,hashPassword(admin.getPassword()));
            statement.setInt(5,admin.getSuperadmin());
            statement.setInt(6,admin.getEnable());

            statement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUserData (Admin admin) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_ADMIN_DATA_QUERY)) {
            statement.setInt(4,admin.getId());
            statement.setString(1,admin.getFirstName());
            statement.setString(2,admin.getLastName());
            statement.setString(3,admin.getEmail());

            statement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String hashPassword (String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Admin loginAuthorization(String email, String password){
        List<Admin> adminList = findAll();
        for (Admin admin : adminList) {
            if(admin.getEmail().equals(email) && BCrypt.checkpw(password, admin.getPassword())){
                return admin;
            }
        }
        return null;
    }

    public boolean registerValidate(Admin possibleNewAdmin){
        List<Admin> adminList = findAll();
        for (Admin existingAdmin : adminList) {
            if(existingAdmin.getEmail().equals(possibleNewAdmin.getEmail())){
                System.out.println("Ten email jest już przypisany do innego konta");
                return false;
            }
        }
        return true;
    }


}
