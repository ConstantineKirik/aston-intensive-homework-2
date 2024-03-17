package com.example.hogwarts.servlet;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.dto.FacultyRespDTO;
import com.example.hogwarts.model.repository.FacultyRepo;
import com.example.hogwarts.model.repository.impl.FacultyRepoImpl;
import com.example.hogwarts.service.FacultyService;
import com.example.hogwarts.service.impl.FacultyServiceImpl;
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

@WebServlet("/faculties")
public class FacultiesServlet extends HttpServlet {
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
            FacultyDTO facultyDTO = facultyService.getByID(Integer.parseInt(id));
            if (facultyDTO != null) {
                FacultyRespDTO facultyRespDTO = FacultyRespDTO.builder()
                        .id(facultyDTO.getId())
                        .title(facultyDTO.getTitle())
                        .teachers(facultyService.getTeachersByFaculty(facultyDTO))
                        .students(facultyService.getStudentsByFaculty(facultyDTO))
                        .build();
                printWriter.print(new Gson().toJson(facultyRespDTO));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().print("Faculty is not found!");
            }
        } else {
            printWriter.print(facultyService.getALl().stream()
                    .map(facultyDTO -> new Gson().toJson(facultyDTO))
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

        FacultyDTO facultyDTO = new Gson().fromJson(jsonData, FacultyDTO.class);

        if (facultyService.create(facultyDTO)) {
            resp.getWriter().print("Faculty is create!");
        } else {
            resp.getWriter().print("Faculty with the name " + facultyDTO.getTitle() + " already exists!");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Scanner scanner = new Scanner(req.getInputStream(), StandardCharsets.UTF_8);
        String jsonData = scanner.useDelimiter("\\A").next();
        scanner.close();

        FacultyDTO facultyDTO = new Gson().fromJson(jsonData, FacultyDTO.class);

        if (facultyService.update(facultyDTO)) {
            resp.getWriter().print("Faculty is update!");
        } else {
            resp.getWriter().print("Faculty with the name " + facultyDTO.getTitle() + " not found!");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Scanner scanner = new Scanner(req.getInputStream(), StandardCharsets.UTF_8);
        String jsonData = scanner.useDelimiter("\\A").next();
        scanner.close();

        FacultyDTO facultyDTO = new Gson().fromJson(jsonData, FacultyDTO.class);

        if (facultyService.remove(facultyDTO.getId())) {
            resp.getWriter().print("Faculty is remove!");
        } else {
            resp.getWriter().print("Faculty with the name " + facultyDTO.getTitle() + " not found!");
        }
    }
}
