package com.example.hogwarts.model.repository.impl;

import com.example.hogwarts.model.entity.Student;
import com.example.hogwarts.model.repository.StudentRepo;
import com.example.hogwarts.config.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepoImpl implements StudentRepo {

    @Override
    public Student findByID(Integer id) {

        Student student = null;

        String selectQuery = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DataSource.getInstance().getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

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
    public List<Student> findAll() {

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
    public boolean save(Student entity) {

        int count = 0;

        String insertQuery = "INSERT INTO students (first_name, last_name, faculty_id) VALUES (?, ?, ?)";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getFacultyId());

            count = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count > 0;
    }

    @Override
    public boolean update(Student entity) {

        int count = 0;

        String updateQuery = "UPDATE students SET first_name=?, last_name=?, faculty_id=? WHERE id=?";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getFacultyId());
            preparedStatement.setInt(4, entity.getId());

            count = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count > 0;
    }

    @Override
    public boolean delete(Integer id) {

        int count = 0;

        String deleteQuery = "DELETE FROM students WHERE id=?";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, id);

            count = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count > 0;
    }
}
