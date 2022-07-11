package dao;

import connect_MySQL.Connect_MySQL;
import model.Department;
import model.Staff;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffDao implements CRUD<Staff> {
    DepartmentDao departmentDao = new DepartmentDao();

    @Override
    public List<Staff> getAll() {
        String sql = "select * from nhanvien";
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("idnv");
                String name = resultSet.getString("tennv");
                Date date = resultSet.getDate("ngaysinh");
                LocalDate birth = ((java.sql.Date) date).toLocalDate();
                String address = resultSet.getString("diachi");
                String phoneNumber = resultSet.getString("sdt");
                String email = resultSet.getString("email");
                Department department = departmentDao.findById(resultSet.getInt("idpb"));

                staffList.add(new Staff(id, name, birth, address, phoneNumber,email, department));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return staffList;
    }

    @Override
    public boolean create(Staff staff) {
        String sql = "insert into nhanvien value (?,?,?,?,?,?,?)";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, staff.getId());
            preparedStatement.setString(2, staff.getName());
            preparedStatement.setString(3, String.valueOf(staff.getBirth()));
            preparedStatement.setString(4, staff.getAddress());
            preparedStatement.setString(5, staff.getPhone());
            preparedStatement.setString(6, staff.getMail());
            preparedStatement.setInt(7, staff.getDepartment().getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(int id, Staff staff) {
        String sql = "UPDATE nhanvien SET tennv = ?,ngaysinh = ?, " +
                "diachi = ?,sdt = ?,email = ?, idpb =? WHERE (idnv = ?)";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(7, staff.getId());
            preparedStatement.setString(1, staff.getName());
            preparedStatement.setString(2, String.valueOf(staff.getBirth()));
            preparedStatement.setString(3, staff.getAddress());
            preparedStatement.setString(4, staff.getPhone());
            preparedStatement.setString(5, staff.getMail());
            preparedStatement.setInt(6, staff.getDepartment().getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from nhanvien WHERE idnv = ?";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Staff findById(int id) {
        String sql = "select * from nhanvien where idnv = " + id;
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.next();
            int idz = resultSet.getInt("idnv");
            String name = resultSet.getString("tennv");
            Date date = resultSet.getDate("ngaysinh");
            LocalDate birth = ((java.sql.Date) date).toLocalDate();
            String address = resultSet.getString("diachi");
            String phoneNumber = resultSet.getString("sdt");
            String email = resultSet.getString("email");
            Department department = departmentDao.findById(resultSet.getInt("idpb"));

            return new Staff(idz, name, birth, address, phoneNumber, email, department);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public List<Staff> getSearch(String searchName){
        String sql = "select * from nhanvien where tennv like concat('%',?,'%')";
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, searchName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("idnv");
                String name = resultSet.getString("tennv");
                Date date = resultSet.getDate("ngaysinh");
                LocalDate birth = ((java.sql.Date) date).toLocalDate();
                String address = resultSet.getString("diachi");
                String phoneNumber = resultSet.getString("sdt");
                String email = resultSet.getString("email");
                Department department = departmentDao.findById(resultSet.getInt("idpb"));
                staffList.add(new Staff(id, name, birth, address,  phoneNumber,email, department));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return staffList;
    }
}
