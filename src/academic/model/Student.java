package academic.model;

public class Student extends Person {
    private int year;
    private String major;

    // Constructor
    public Student(String id, String name, int year, String major) {
        super(id, name);
        this.year = year;
        this.major = major;
    }

    // Getters
    public int getYear() {
        return year;
    }

    public String getMajor() {
        return major;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMajor(String major) {
        this.major = major;
    }

}