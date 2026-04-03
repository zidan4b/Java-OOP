package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBConnection;

/**
 * Handles course registration requests.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    /**
     * Processes course registration form submission.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet error occurs
     * @throws IOException if I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String studentName = request.getParameter("studentName");
        String studentId = request.getParameter("studentId");
        String courseCode = request.getParameter("courseCode");
        String semester = request.getParameter("semester");

        if (studentName == null || studentId == null || courseCode == null || semester == null
                || studentName.trim().isEmpty() || studentId.trim().isEmpty()
                || courseCode.trim().isEmpty() || semester.trim().isEmpty()) {
            showMessagePage(response, "Error", "All fields are required.", false);
            return;
        }

        studentName = studentName.trim();
        studentId = studentId.trim();
        courseCode = courseCode.trim().toUpperCase();
        semester = semester.trim();

        String courseName = null;

        String checkCourseSql = "SELECT course_name FROM courses WHERE course_code = ?";
        String checkStudentSql = "SELECT student_id FROM students WHERE student_id = ? AND student_name = ?";
        String insertStudentSql = "INSERT INTO students (student_id, student_name) VALUES (?, ?)";
        String checkDuplicateSql = "SELECT registration_id FROM student_courses "
                + "WHERE student_id = ? AND course_code = ? AND semester = ?";
        String insertRegistrationSql = "INSERT INTO student_courses (student_id, course_code, semester) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {

            // 1) Check course exists
            try (PreparedStatement ps = conn.prepareStatement(checkCourseSql)) {
                ps.setString(1, courseCode);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        courseName = rs.getString("course_name");
                    } else {
                        showMessagePage(response, "Registration Error",
                                "Course code " + courseCode + " does not exist.", false);
                        return;
                    }
                }
            }

            // 2) Check whether student exists
            boolean studentExists = false;
            try (PreparedStatement ps = conn.prepareStatement(checkStudentSql)) {
                ps.setString(1, studentId);
                ps.setString(2, studentName);
                try (ResultSet rs = ps.executeQuery()) {
                    studentExists = rs.next();
                }
            }

            // 3) Insert student if missing
            if (!studentExists) {
                try (PreparedStatement ps = conn.prepareStatement(insertStudentSql)) {
                    ps.setString(1, studentId);
                    ps.setString(2, studentName);
                    ps.executeUpdate();
                }
            }

            // 4) Check duplicate registration
            try (PreparedStatement ps = conn.prepareStatement(checkDuplicateSql)) {
                ps.setString(1, studentId);
                ps.setString(2, courseCode);
                ps.setString(3, semester);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        showMessagePage(response, "Registration Error",
                                "This student is already registered in " + courseCode
                                        + " for " + semester + ".", false);
                        return;
                    }
                }
            }

            // 5) Insert registration
            try (PreparedStatement ps = conn.prepareStatement(insertRegistrationSql)) {
                ps.setString(1, studentId);
                ps.setString(2, courseCode);
                ps.setString(3, semester);
                ps.executeUpdate();
            }

            // 6) Show success response
            showSuccessTable(response, studentName, studentId, courseCode, courseName, semester);

        } catch (SQLException e) {
            showMessagePage(response, "Database Error", e.getMessage(), false);
        }
    }

    /**
     * Displays a success page in a tabular format.
     */
    private void showSuccessTable(HttpServletResponse response, String studentName,
            String studentId, String courseCode, String courseName, String semester)
            throws IOException {

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'>");
            out.println("<title>Registration Result</title>");
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("</head><body>");
            out.println("<div class='table-container'>");
            out.println("<h2>Course Registration Result</h2>");
            out.println("<p class='success'>Registration Successful!</p>");

            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Student Name</th>");
            out.println("<th>Student ID</th>");
            out.println("<th>Course Code</th>");
            out.println("<th>Course Name</th>");
            out.println("<th>Semester</th>");
            out.println("<th>Registration Status</th>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>" + escapeHtml(studentName) + "</td>");
            out.println("<td>" + escapeHtml(studentId) + "</td>");
            out.println("<td>" + escapeHtml(courseCode) + "</td>");
            out.println("<td>" + escapeHtml(courseName) + "</td>");
            out.println("<td>" + escapeHtml(semester) + "</td>");
            out.println("<td>Registered</td>");
            out.println("</tr>");
            out.println("</table>");

            out.println("<div class='center'>");
            out.println("<a class='link-btn' href='index.html'>Back to Registration Form</a>");
            out.println("</div>");

            out.println("</div></body></html>");
        }
    }

    /**
     * Displays a message page.
     */
    private void showMessagePage(HttpServletResponse response, String title,
            String message, boolean success) throws IOException {

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'>");
            out.println("<title>" + escapeHtml(title) + "</title>");
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("</head><body>");
            out.println("<div class='table-container'>");
            out.println("<h2>" + escapeHtml(title) + "</h2>");

            if (success) {
                out.println("<p class='success'>" + escapeHtml(message) + "</p>");
            } else {
                out.println("<p class='error'>" + escapeHtml(message) + "</p>");
            }

            out.println("<div class='center'>");
            out.println("<a class='link-btn' href='index.html'>Back to Registration Form</a>");
            out.println("</div>");

            out.println("</div></body></html>");
        }
    }

    /**
     * Escapes basic HTML characters.
     */
    private String escapeHtml(String text) {
        if (text == null) {
            return "";
        }
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
}
