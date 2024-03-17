package com.example.hogwarts.servlet;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.dto.StudentDTO;
import com.example.hogwarts.dto.StudentRespDTO;
import com.example.hogwarts.model.repository.FacultyRepo;
import com.example.hogwarts.model.repository.StudentRepo;
import com.example.hogwarts.model.repository.impl.FacultyRepoImpl;
import com.example.hogwarts.model.repository.impl.StudentRepoImpl;
import com.example.hogwarts.service.FacultyService;
import com.example.hogwarts.service.StudentService;
import com.example.hogwarts.service.impl.FacultyServiceImpl;
import com.example.hogwarts.service.impl.StudentServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.stream.Collectors;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {
    private final StudentRepo studentRepo = new StudentRepoImpl();
    private final StudentService studentService = new StudentServiceImpl(studentRepo);
    private final FacultyRepo facultyRepo = new FacultyRepoImpl();
    private final FacultyService facultyService = new FacultyServiceImpl(facultyRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        PrintWriter printWriter = resp.getWriter();

        String id = req.getParameter("id");

        if (id != null) {
            Integer studentID = Integer.parseInt(id);
            StudentDTO studentDTO = studentService.getByID(studentID);
            if (studentDTO != null) {
                FacultyDTO facultyDTO = facultyService.getByID(studentDTO.getFacultyId());
                StudentRespDTO studentRespDTO = StudentRespDTO.builder()
                        .id(studentDTO.getId())
                        .firstName(studentDTO.getFirstName())
                        .lastName(studentDTO.getLastName())
                        .faculty(facultyDTO.getTitle())
                        .build();
                printWriter.print(new Gson().toJson(studentRespDTO));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().print("Student is not found!");
            }

        } else {
            printWriter.print(studentService.getALl().stream()
                    .map(studentDTO -> new Gson().toJson(studentDTO))
                    .collect(Collectors.toList()));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Scanner scanner = new Scanner(req.getInputStream(), StandardCharsets.UTF_8);
        String jsonData = scanner.useDelimiter("\\A").next();
        scanner.close();

        StudentDTO studentDTO = new Gson().fromJson(jsonData, StudentDTO.class);

        if (studentService.create(studentDTO)) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().print("Student is create!");
        } else {
            resp.getWriter().print("Student is not create!");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Scanner scanner = new Scanner(req.getInputStream(), StandardCharsets.UTF_8);
        String jsonData = scanner.useDelimiter("\\A").next();
        scanner.close();

        StudentDTO studentDTO = new Gson().fromJson(jsonData, StudentDTO.class);

        if (studentService.update(studentDTO)) {
            resp.getWriter().print("Student is update!");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().print("Student is not found!");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Scanner scanner = new Scanner(req.getInputStream(), StandardCharsets.UTF_8);
        String jsonData = scanner.useDelimiter("\\A").next();
        scanner.close();

        StudentDTO studentDTO = new Gson().fromJson(jsonData, StudentDTO.class);

        if (studentService.remove(studentDTO.getId())) {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            resp.getWriter().print("Student is remove!");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().print("Student is not found!");
        }
    }
}
