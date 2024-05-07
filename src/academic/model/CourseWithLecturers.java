package academic.model;

import java.util.List;
public class CourseWithLecturers extends Course {
    private List<Lecturer> lecturers;

    // Constructor
    public CourseWithLecturers(String code, String name, int credits, String passingGrade, List<Lecturer> lecturers) {
        super(code, name, credits, passingGrade, lecturers);
        this.lecturers = lecturers;
    }

    // Getter
    public List<Lecturer> getLecturers() {
        return lecturers;
    }
    
}

