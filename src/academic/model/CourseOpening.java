package academic.model;

import java.util.List;
/**
 * @author 12S22052 Rosari Simanjuntak
 * @author 12S22046 Difya Ambarita 
 */
public class CourseOpening {
    private String courseCode;
    private String courseName;
    private int credits;
    private String passingGrade;
    private String academicYear;
    private String semester;
    private String lecturers; // Perubahan: menambahkan atribut lecturers

    public CourseOpening(String courseCode, String semester , String academicYear,  String lecturers) {
        this.courseCode = courseCode;
        this.courseName = "";
        this.credits = 0;
        this.passingGrade = "";
        this.academicYear = academicYear;
        this.semester = semester;
        this.lecturers = lecturers;
    }

    public String getCourseCode() {
        return courseCode;
    }


    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }


    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }


    public String getLecturers() {
        return lecturers;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setLecturers(String lecturers) {
        this.lecturers = lecturers;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

}
