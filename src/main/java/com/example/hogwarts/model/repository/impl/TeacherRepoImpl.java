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

        String selectQuery = "SELECT * FROM techers WHERE id = ?";

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

            try(PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
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
    public void save(Teacher entity) {

        String insertQuery = "INSERT INTO teachers (first_name, last_name) VALUES (?, ?)";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery)) {

            insertPreparedStatement.setString(1, entity.getFirstName());
            insertPreparedStatement.setString(2, entity.getLastName());
            insertPreparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addTeacherToFaculty(Faculty faculty, Teacher teacher) {

        String insertQuery = "INSERT INTO faculties_teachers (faculty_id, teacher_id) VALUES (?, ?)";

        try(Connection connection = DataSource.getInstance().getConnection();
        PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery)){

            insertPreparedStatement.setInt(1, faculty.getId());
            insertPreparedStatement.setInt(2, teacher.getId());
            insertPreparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Teacher entity) {

        String updateQuery = "UPDATE teachers SET first_name=?, last_name=? WHERE id=?";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement updatePreparedStatement = connection.prepareStatement(updateQuery)) {

            updatePreparedStatement.setString(1, entity.getFirstName());
            updatePreparedStatement.setString(2, entity.getLastName());
            updatePreparedStatement.setInt(4, entity.getId());
            updatePreparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {

        String deleteQuery = "DELETE FROM teachers WHERE id=?";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement deletePreparedStatement = connection.prepareStatement(deleteQuery)) {

            deletePreparedStatement.setInt(1, id);
            deletePreparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
