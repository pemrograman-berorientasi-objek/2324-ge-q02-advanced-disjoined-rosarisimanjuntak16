package academic.model;

/**
 * 12S22046 Difya Ambarita
 */
import java.util.List;

public class Course {
    private String code;
    private String name;
    private int credits;
    private String passingGrade;
    private List<Lecturer> lecturers; // Perubahan: menambahkan atribut lecturers

    
    public Course(String code, String name, int credits, String passingGrade, List<Lecturer> lecturers) {
        this.code = code;
        this.name = name;
        this.credits = credits;
        this.passingGrade = passingGrade;
        this.lecturers = lecturers; // Perubahan: inisialisasi atribut lecturers
    }

    // Getter untuk atribut lecturers
    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    // Getter untuk atribut lainnya
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public String getPassingGrade() {
        return passingGrade;
    }
}
