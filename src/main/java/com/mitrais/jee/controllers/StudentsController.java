package com.mitrais.jee.controllers;

import com.mitrais.jee.dao.DaoStudentImpl;
import com.mitrais.jee.dao.StudentDao;
import com.mitrais.jee.model.Students;
import com.mitrais.jee.utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
@WebServlet("/")
public class StudentsController extends HttpServlet {

    private static final long serVersionUID = 1L;
    private StudentDao studentDao = DaoStudentImpl.getInstance();
    private static final Logger LOGGER = Logger.getLogger(StudentsController.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try {
            switch (action) {
                case "/students/new":
                    newForm(req, resp);
                    break;
                case "/students/insert":
                    insertStudent(req, resp);
                    break;
                case "/students/edit":
                    editForm(req, resp);
                    break;
                case "/students/update":
                    updateStudent(req, resp);
                    break;
                case "/students/delete":
                    deleteStudent(req, resp);
                    break;
                default:
                    listStudent(req, resp);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Error", e);
            e.printStackTrace();
        }
    }

    private static List<String> listOfGrade() {
        List<String> gradeList = new ArrayList<>();
        gradeList.add("Freshmen");
        gradeList.add("Sophomore");
        gradeList.add("Senior");
        return gradeList;
    }

    private static Date setDate() {
        LocalDate localDate = LocalDate.now();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private void editForm(HttpServletRequest req, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String id = req.getParameter("id");

        Optional<Students> existingStudent = studentDao.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/studentForm.jsp");
        req.setAttribute("gradeList", listOfGrade());
        existingStudent.ifPresent(s -> req.setAttribute("student", s));
        dispatcher.forward(req, response);
    }

    private void newForm(HttpServletRequest req, HttpServletResponse res) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/studentForm.jsp");
        req.setAttribute("gradeList", listOfGrade());
        dispatcher.forward(req, res);

    }

    private void insertStudent(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
        String name = req.getParameter("name");
        String grade = req.getParameter("grade");
        String balance = req.getParameter("balance");
        String studentId = AppUtils.generateNumber("000");
        Students student = new Students(name, grade, Double.parseDouble(balance), studentId, setDate());
        studentDao.save(student);
        res.sendRedirect("/servletjsp/students");
    }

    private void listStudent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/studentList.jsp");
        List<Students> studentsList = studentDao.findAll();
        req.setAttribute("studentList", studentsList);
        dispatcher.forward(req, resp);
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String grade = req.getParameter("grade");
        String balance = req.getParameter("balance");
        String studentId = req.getParameter("studentId");
        Students students = new Students(id, name, grade, Double.parseDouble(balance), studentId, setDate());
        studentDao.update(students);
        res.sendRedirect("/servletjsp/students");
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse res) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Students students = new Students(id);
        studentDao.delete(students);
        res.sendRedirect("/servletjsp/students");
    }
}
