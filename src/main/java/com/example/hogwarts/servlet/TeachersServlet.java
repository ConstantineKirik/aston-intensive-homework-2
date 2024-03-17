package com.example.hogwarts.servlet;

import com.example.hogwarts.dto.TeacherDTO;
import com.example.hogwarts.dto.TeacherRespDTO;
import com.example.hogwarts.model.repository.TeacherRepo;
import com.example.hogwarts.model.repository.impl.TeacherRepoImpl;
import com.example.hogwarts.service.TeacherService;
import com.example.hogwarts.service.impl.TeacherServiceImpl;
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

@WebServlet("/teachers")
public class TeachersServlet extends HttpServlet {
    private final TeacherRepo teacherRepo = new TeacherRepoImpl();
    private final TeacherService teacherService = new TeacherServiceImpl(teacherRepo);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        PrintWriter printWriter = resp.getWriter();

        String id = req.getParameter("id");

        if (id != null) {
            TeacherDTO teacherDTO = teacherService.getByID(Integer.parseInt(id));
            if (teacherDTO != null) {
                TeacherRespDTO teacherRespDTO = TeacherRespDTO.builder()
                        .id(teacherDTO.getId())
                        .firstName(teacherDTO.getFirstName())
                        .lastName(teacherDTO.getLastName())
                        .faculties(teacherService.getFacultiesByTeacher(teacherDTO))
                        .build();
                printWriter.print(new Gson().toJson(teacherRespDTO));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().print("Teacher is not found!");
            }

        } else {
            printWriter.print(teacherService.getALl().stream()
                    .map(teacherDTO -> new Gson().toJson(teacherDTO))
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

        TeacherDTO teacherDTO = new Gson().fromJson(jsonData, TeacherDTO.class);

        if (teacherService.create(teacherDTO)) {
            resp.setStatus(HttpServletResponse.SC_CREATED);
            resp.getWriter().print("Teacher is create!");
        } else {
            resp.getWriter().print("Teacher is not create!");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Scanner scanner = new Scanner(req.getInputStream(), StandardCharsets.UTF_8);
        String jsonData = scanner.useDelimiter("\\A").next();
        scanner.close();

        TeacherDTO teacherDTO = new Gson().fromJson(jsonData, TeacherDTO.class);

        if (teacherService.update(teacherDTO)) {
            resp.getWriter().print("Teacher is update!");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().print("Teacher is not found!");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Scanner scanner = new Scanner(req.getInputStream(), StandardCharsets.UTF_8);
        String jsonData = scanner.useDelimiter("\\A").next();
        scanner.close();

        TeacherDTO teacherDTO = new Gson().fromJson(jsonData, TeacherDTO.class);

        if (teacherService.remove(teacherDTO.getId())) {
            resp.getWriter().print("Teacher is remove!");
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().print("Teacher is not found!");
        }
    }
}
