package academic.model;

public class Enrollment {
    private String courseId;
    private String studentId;
    private String academicYear;
    private String semester;
    private String grade;
    private String previousGrade="";
    private String sementara="sementara";
    // Constructor
    public Enrollment(String courseId, String studentId, String academicYear, String semester2, String grade, String previousGrade) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.academicYear = academicYear;
        this.semester = semester2;
        this.grade = grade;
        this.previousGrade = previousGrade;
    }

    public Enrollment(String courseId2, String studentId2, String academicYear2, String semester2, Object grade2,
            Object previousGrade2) {
    }

    // Getters and setters
    public String getCourseId() {
        return courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public String getPreviousGrade() {
        return previousGrade;
    }

    public void setPreviousGrade(String previousGrade) {
        this.previousGrade = previousGrade;
    }

    public String getSementara() {
        return sementara;
    }

}
