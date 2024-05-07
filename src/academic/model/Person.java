package academic.model;


public class Person {
    private String id;
    private String name;
    private String email;

    // Constructors
    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}