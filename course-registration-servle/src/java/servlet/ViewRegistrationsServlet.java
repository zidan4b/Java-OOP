package servlet;

import dto.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DBConnection;

/**
 * Displays all course registrations.
 */
@WebServlet("/viewRegistrations")
public class ViewRegistrationsServlet extends HttpServlet {

    /**
     * Displays all registrations in a table.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if servlet error occurs
     * @throws IOException if I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        List<RegistrationDTO> registrations = new ArrayList<>();

        String sql = "SELECT sc.registration_id, s.student_id, s.student_name, "
                + "c.course_code, c.course_name, sc.semester "
                + "FROM student_courses sc "
                + "JOIN students s ON sc.student_id = s.student_id "
                + "JOIN courses c ON sc.course_code = c.course_code "
                + "ORDER BY sc.registration_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RegistrationDTO dto = new RegistrationDTO(
                        rs.getInt("registration_id"),
                        rs.getString("student_id"),
                        rs.getString("student_name"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getString("semester")
                );
                registrations.add(dto);
            }

        } catch (SQLException e) {
            showError(response, e.getMessage());
            return;
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'>");
            out.println("<title>All Registrations</title>");
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("</head><body>");
            out.println("<div class='table-container'>");
            out.println("<h2>All Course Registrations</h2>");

            if (registrations.isEmpty()) {
                out.println("<p class='error'>No registrations found.</p>");
            } else {
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>Registration ID</th>");
                out.println("<th>Student ID</th>");
                out.println("<th>Student Name</th>");
                out.println("<th>Course Code</th>");
                out.println("<th>Course Name</th>");
                out.println("<th>Semester</th>");
                out.println("</tr>");

                for (RegistrationDTO dto : registrations) {
                    out.println("<tr>");
                    out.println("<td>" + dto.getRegistrationId() + "</td>");
                    out.println("<td>" + escapeHtml(dto.getStudentId()) + "</td>");
                    out.println("<td>" + escapeHtml(dto.getStudentName()) + "</td>");
                    out.println("<td>" + escapeHtml(dto.getCourseCode()) + "</td>");
                    out.println("<td>" + escapeHtml(dto.getCourseName()) + "</td>");
                    out.println("<td>" + escapeHtml(dto.getSemester()) + "</td>");
                    out.println("</tr>");
                }

                out.println("</table>");
            }

            out.println("<div class='center'>");
            out.println("<a class='link-btn' href='index.html'>Back to Registration Form</a>");
            out.println("</div>");

            out.println("</div></body></html>");
        }
    }

    /**
     * Shows an error page.
     */
    private void showError(HttpServletResponse response, String message) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html><head><meta charset='UTF-8'>");
            out.println("<title>Error</title>");
            out.println("<link rel='stylesheet' href='style.css'>");
            out.println("</head><body>");
            out.println("<div class='table-container'>");
            out.println("<h2>Error</h2>");
            out.println("<p class='error'>" + escapeHtml(message) + "</p>");
            out.println("<div class='center'>");
            out.println("<a class='link-btn' href='index.html'>Back to Registration Form</a>");
            out.println("</div>");
            out.println("</div></body></html>");
        }
    }

    /**
     * Escapes basic HTML characters.
     */
    private static String escapeHtml(String text) {
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
