package com.example.hogwarts.servlet;

import com.example.hogwarts.model.repository.TeacherRepo;
import com.example.hogwarts.model.repository.impl.TeacherRepoImpl;
import com.example.hogwarts.service.TeacherService;
import com.example.hogwarts.service.impl.TeacherServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/facultiesAndTeachers")
public class FacultyTeacherServlet extends HttpServlet {
    private final TeacherRepo teacherRepo = new TeacherRepoImpl();
    private final TeacherService teacherService = new TeacherServiceImpl(teacherRepo);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter printWriter = resp.getWriter();

        String facultyId = req.getParameter("facultyId");
        String teacherId = req.getParameter("teacherId");

        if (facultyId != null && teacherId != null){
            teacherService.addTeacherToFaculty(Integer.parseInt(facultyId),Integer.parseInt(teacherId));
        } else{
            printWriter.print("Invalid data!");
        }
    }
}
