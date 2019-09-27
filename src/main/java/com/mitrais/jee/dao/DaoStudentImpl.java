package com.mitrais.jee.dao;

import com.mitrais.jee.model.Students;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of DAO
 */
public class DaoStudentImpl implements StudentDao{
    private DaoStudentImpl() {
    }

    private static class SingletonHelper {
        private static final DaoStudentImpl INSTANCE = new DaoStudentImpl();
    }

    public static DaoStudentImpl getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Students> find(String s) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";
        int id = Integer.parseInt(s);
        String name = "", grade = "", studentId = "";
        double balance = 0.0;
        Date created = new Date();
        Date modified = new Date();
        Connection conn = DataSourceFactory.getConnection();

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            grade = resultSet.getString("grade");
            studentId = resultSet.getString("student_id");
            balance = resultSet.getDouble("balance");
            created = resultSet.getDate("created");
            modified = resultSet.getDate("modified");
        }
        return Optional.of(new Students(id, name, grade, balance, studentId, created));
    }

    @Override
    public List<Students> findAll() throws SQLException {
        List<Students> studentsList = new ArrayList<>();
        String sql = "SELECT * FROM students";
        Connection conn = DataSourceFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String grade = resultSet.getString("grade");
            String studentId = resultSet.getString("student_id");
            double balance = resultSet.getDouble("balance");
            Date created = resultSet.getDate("created");
            Date modified = resultSet.getDate("modified");
            Students students = new Students(id, name, grade, balance, studentId, created);
            studentsList.add(students);
        }
        return studentsList;
    }

    @Override
    public boolean save(Students students) throws SQLException {
        String sql = "INSERT into students (name, grade, student_id, balance, created) VALUES (?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, students.getName());
        statement.setString(2, students.getGrade());
        statement.setString(3, students.getStudentId());
        statement.setDouble(4, students.getBalance());
        statement.setDate(5, new java.sql.Date(students.getCreated().getTime()));
        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Students students) throws SQLException {
        String sql = "UPDATE students SET name = ?, grade = ?, student_id = ?, balance = ?, modified = ?  WHERE id = ?";
        boolean rowUpdate = false;
        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, students.getName());
        statement.setString(2, students.getGrade());
        statement.setString(3, students.getStudentId());
        statement.setDouble(4, students.getBalance());
        statement.setDate(5, new java.sql.Date(students.getModified().getTime()));
        statement.setInt(6, students.getId());
        rowUpdate = statement.executeUpdate() > 0;
        return rowUpdate;
    }

    @Override
    public boolean delete(Students students) throws SQLException {
        String sql = "DELETE FROM students where id = ?";
        boolean rowDelete = false;

        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, students.getId());
        rowDelete = statement.executeLargeUpdate() > 0;
        return rowDelete;
    }
}
