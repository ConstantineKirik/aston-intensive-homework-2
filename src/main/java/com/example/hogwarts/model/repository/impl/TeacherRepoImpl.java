package com.example.hogwarts.model.repository.impl;

import com.example.hogwarts.model.entity.Faculty;
import com.example.hogwarts.model.entity.Teacher;
import com.example.hogwarts.model.repository.TeacherRepo;
import com.example.hogwarts.util.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepoImpl implements TeacherRepo {

    @Override
    public Teacher findByID(Integer id) {

        Teacher teacher = null;

        String selectQuery = "SELECT * FROM teachers WHERE id = ?";

        try (Connection connection = DataSource.getInstance().getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    resultSet.next();
                    teacher = Teacher.builder()
                            .id(resultSet.getInt("id"))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    @Override
    public List<Teacher> findAll() {

        List<Teacher> teachers = new ArrayList<>();

        String selectQuery = "SELECT * FROM teachers";

        try (Connection connection = DataSource.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                Teacher teacher = Teacher.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .build();
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teachers;
    }

    @Override
    public List<Faculty> findFacultiesByTeacher(Teacher teacher) {

        List<Faculty> faculties = new ArrayList<>();

        String selectQuery = "SELECT * FROM faculties f JOIN faculties_teachers ft ON f.id = ft.faculty_id WHERE ft.teacher_id = ?";

        try (Connection connection = DataSource.getInstance().getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, teacher.getId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        Faculty faculty = Faculty.builder()
                                .id(resultSet.getInt("id"))
                                .title(resultSet.getString("title"))
                                .build();
                        faculties.add(faculty);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return faculties;
    }

    @Override
    public boolean save(Teacher entity) {

        int count = 0;

        String insertQuery = "INSERT INTO teachers (first_name, last_name) VALUES (?, ?)";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());

            count = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count > 0;
    }

    @Override
    public void addTeacherToFaculty(Integer facultyID, Integer teacherID) {

        String insertQuery = "INSERT INTO faculties_teachers (faculty_id, teacher_id) VALUES (?, ?)";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setInt(1, facultyID);
            preparedStatement.setInt(2, teacherID);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean update(Teacher entity) {

        int count = 0;

        String updateQuery = "UPDATE teachers SET first_name=?, last_name=? WHERE id=?";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setInt(3, entity.getId());

            count = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count > 0;
    }

    @Override
    public boolean delete(Integer id) {

        int count = 0;

        String deleteQuery = "DELETE FROM teachers WHERE id=?";

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
