package dto;

/**
 * Data Transfer Object for course registration data.
 */
public class RegistrationDTO {

    private int registrationId;
    private String studentId;
    private String studentName;
    private String courseCode;
    private String courseName;
    private String semester;

    public RegistrationDTO() {
    }

    public RegistrationDTO(int registrationId, String studentId, String studentName,
            String courseCode, String courseName, String semester) {
        this.registrationId = registrationId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.semester = semester;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}