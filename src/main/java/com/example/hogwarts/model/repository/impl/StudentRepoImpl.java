package com.example.hogwarts.model.repository.impl;

import com.example.hogwarts.model.entity.Student;
import com.example.hogwarts.model.repository.StudentRepo;
import com.example.hogwarts.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepoImpl implements StudentRepo {

    @Override
    public Student get(Integer id) {

        Student student = null;

        String selectQuery = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DataSource.getInstance().getConnection()) {

            try(PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    resultSet.next();
                    student = Student.builder()
                            .id(resultSet.getInt("id"))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .facultyId(resultSet.getInt("faculty_id"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> getALl() {

        List<Student> students = new ArrayList<>();

        String selectQuery = "SELECT * FROM students";

        try (Connection connection = DataSource.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                Student student = Student.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .facultyId(resultSet.getInt("faculty_id"))
                        .build();
                students.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public void save(Student entity) {

        String insertQuery = "INSERT INTO students (first_name, last_name, faculty_id) VALUES (?, ?, ?)";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery)) {

            insertPreparedStatement.setString(1, entity.getFirstName());
            insertPreparedStatement.setString(2, entity.getLastName());
            insertPreparedStatement.setInt(3, entity.getFacultyId());
            insertPreparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Student entity) {

        String updateQuery = "UPDATE students SET first_name=?, last_name=?, faculty_id=? WHERE id=?";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement updatePreparedStatement = connection.prepareStatement(updateQuery)) {

            updatePreparedStatement.setString(1, entity.getFirstName());
            updatePreparedStatement.setString(2, entity.getLastName());
            updatePreparedStatement.setInt(3, entity.getFacultyId());
            updatePreparedStatement.setInt(4, entity.getId());
            updatePreparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {

        String deleteQuery = "DELETE FROM students WHERE id=?";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement deletePreparedStatement = connection.prepareStatement(deleteQuery)) {

            deletePreparedStatement.setInt(1, id);
            deletePreparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
