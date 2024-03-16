package com.example.hogwarts.model.repository.impl;

import com.example.hogwarts.model.entity.Student;
import com.example.hogwarts.model.entity.Teacher;
import com.example.hogwarts.config.DataSource;
import com.example.hogwarts.model.repository.FacultyRepo;
import com.example.hogwarts.model.entity.Faculty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyRepoImpl implements FacultyRepo {

    @Override
    public Faculty findByID(Integer id) {

        Faculty faculty = null;

        String selectQuery = "SELECT * FROM faculties WHERE id = ?";

        try (Connection connection = DataSource.getInstance().getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    resultSet.next();
                    faculty = Faculty.builder()
                            .id(resultSet.getInt("id"))
                            .title(resultSet.getString("title"))
                            .build();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return faculty;
    }

    @Override
    public List<Faculty> findAll() {

        List<Faculty> faculties = new ArrayList<>();

        String selectQuery = "SELECT * FROM faculties";

        try (Connection connection = DataSource.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                Faculty faculty = Faculty.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .build();

                faculties.add(faculty);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return faculties;
    }

    @Override
    public List<Teacher> findTeachersByFaculty(Faculty faculty) {

        List<Teacher> teachers = new ArrayList<>();

        String selectQuery = "SELECT * FROM teachers t JOIN faculties_teachers ft ON t.id = ft.teacher_id WHERE ft.faculty_id = ?";

        try (Connection connection = DataSource.getInstance().getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, faculty.getId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        Teacher teacher = Teacher.builder()
                                .id(resultSet.getInt("id"))
                                .firstName(resultSet.getString("first_name"))
                                .lastName(resultSet.getString("last_name"))
                                .build();
                        teachers.add(teacher);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return teachers;
    }

    @Override
    public List<Student> findStudentsByFaculty(Faculty faculty) {

        List<Student> students = new ArrayList<>();

        String selectQuery = "SELECT * FROM students s JOIN faculties f ON s.faculty_id = f.id WHERE f.id = ?";

        try (Connection connection = DataSource.getInstance().getConnection()) {

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, faculty.getId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    while (resultSet.next()) {
                        Student student = Student.builder()
                                .id(resultSet.getInt("id"))
                                .firstName(resultSet.getString("first_name"))
                                .lastName(resultSet.getString("last_name"))
                                .facultyId(resultSet.getInt("faculty_id"))
                                .build();
                        students.add(student);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public boolean save(Faculty entity) {

        int count = 0;

        String insertQuery = "INSERT INTO faculties (title) VALUES (?)";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, entity.getTitle());

            count = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count > 0;
    }

    @Override
    public boolean update(Faculty entity) {

        int count = 0;

        String updateQuery = "UPDATE faculties SET title = ? WHERE id = ?";

        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setInt(2, entity.getId());

            count = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return count > 0;
    }

    @Override
    public boolean delete(Integer id) {

        int count = 0;

        String deleteQuery = "DELETE FROM faculties WHERE id = ?";

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
