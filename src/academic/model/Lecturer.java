package academic.model;

public class Lecturer extends Person {
    private String initial;
    private String studyProgram;

    // Constructor
    public Lecturer(String id, String name, String initial, String email, String studyProgram) {
        super(id, name, email);
        this.initial = initial;
        this.studyProgram = studyProgram;
    }
    // Getters
    public String getInitial() {
        return initial;
    }

    public String getStudyProgram() {
        return studyProgram;
    }
}