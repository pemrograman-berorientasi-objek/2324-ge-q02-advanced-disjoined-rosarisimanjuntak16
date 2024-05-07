package academic.driver;

/**
 * @author 12S22046 Difya Laurensya Ambarita
 */

import academic.model.*;

import java.util.*;

public class Driver1 {
    private static List<CourseWithLecturers> courses = new ArrayList<>();
    private static Object lecturdetail;
    // private static void sortCoursesBySemesterDescending(List<CourseWithLecturers> courses) {
    //     Collections.sort(courses, Comparator.comparing(CourseWithLecturers::getSemester).reversed());
    // }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        List<Enrollment> enrollments = new ArrayList<>();
        List<Lecturer> lecturers = new ArrayList<>();
        List<CourseOpening> CourseOpenings = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("---")) break;
            String[] parts = line.split("#");
            if (parts.length < 2) {
                continue;
            }
            String command = parts[0];
            switch (command) {
                case "course-add":
                if (parts.length < 5) {
                    continue;
                }
                String code = parts[1];
                String name = parts[2];
                int credits;
                try {
                    credits = Integer.parseInt(parts[3]);
                } catch (NumberFormatException e) {
                    continue;
                }
                String passingGrade = parts[4];
                // String lecturerInitialList = parts[5];
                //     List<Lecturer> courseLecturers = new ArrayList<>();
                //     String[] lecturerInitials = lecturerInitialList.split(",");
                //     for (String initial : lecturerInitials) {
                //         Lecturer lecturer = findLecturer(lecturers, initial);
                //         if (lecturer != null) {
                //             courseLecturers.add(lecturer);
                //         } else {
                //             System.out.println("Invalid lecturer initial for course " + code + "; " + initial);
                //         }
                //     }
                CourseWithLecturers course = new CourseWithLecturers(code, name, credits, passingGrade, new ArrayList<>());
                courses.add(course);
                break;
            

                case "student-add":
                    if (parts.length < 5) {
                        continue;
                    }
                    String id = parts[1];
                    String studentName = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    String major = parts[4];
                    Student student = new Student(id, studentName, year, major);

                    // Prevent duplication
                    if (findStudent(students, id) != null) {
                        continue; // Continue to the next input line
                    }
                    students.add(student);
                    break;

                case "enrollment-add":
                    if (parts.length < 5) {
                        continue;
                    }
                    String courseId = parts[1];
                    String studentId = parts[2];
                    String academicYear = parts[3];
                    String semester = parts[4];

                    CourseWithLecturers courseForEnrollment = findCourse(courses, courseId);
                    Student studentForEnrollment = findStudent(students, studentId);

                    if (courseForEnrollment == null) {
                        continue;
                    }

                    if (studentForEnrollment == null) {
                        continue;
                    }

                    Enrollment enrollment = new Enrollment(courseId, studentId, academicYear, semester, null, null);
                    enrollments.add(enrollment);
                    break;

                case "enrollment-grade":
                    if (parts.length < 6) {
                        continue;
                    }
                    String courseCode = parts[1];
                    String studentID = parts[2];
                    String academicYearGrade = parts[3];
                    String semesterGrade = parts[4];
                    String grade = parts[5];
                    CourseWithLecturers courseForEnrollmentGrade = findCourse(courses, courseCode);
                    Student studentForEnrollmentGrade = findStudent(students, studentID);

                    if (courseForEnrollmentGrade == null) {
                        continue;
                    }

                    if (studentForEnrollmentGrade == null) {
                        continue;
                    }

                    Enrollment enrollmentGrade = findEnrollment(enrollments, courseCode, studentID, academicYearGrade, semesterGrade);
                    if (enrollmentGrade == null) {
                        continue;
                    }

                    enrollmentGrade.setGrade(grade); // Set the grade for the enrollment
                    break;
                    
                    case "enrollment-remedial":
                    if (parts.length < 6) {
                        continue;
                    }
                    String courseCodeRemedial = parts[1];
                    String studentIDRemedial = parts[2];
                    String academicYearRemedial = parts[3];
                    String semesterRemedial = parts[4];
                    String gradeRemedial = parts[5];
                    CourseWithLecturers courseForEnrollmentRemedial = findCourse(courses, courseCodeRemedial);
                    Student studentForEnrollmentRemedial = findStudent(students, studentIDRemedial);
                
                    if (courseForEnrollmentRemedial == null) {
                        continue;
                    }
                
                    if (studentForEnrollmentRemedial == null) {
                        continue;
                    }
                
                    Enrollment enrollmentRemedial = findEnrollment(enrollments, courseCodeRemedial, studentIDRemedial, academicYearRemedial, semesterRemedial);
                    if (enrollmentRemedial == null) {
                        continue;
                    }
                
                    // Check if a previous grade exists
                    if (enrollmentRemedial.getPreviousGrade() != null) {
                        continue;
                    }
                
                    // Update the grade for the enrollment
                    enrollmentRemedial.setPreviousGrade(enrollmentRemedial.getGrade());
                    enrollmentRemedial.setGrade(gradeRemedial);
                    break;
                

                    case "lecturer-add":
                    if (parts.length < 6) {
                        continue;
                    }
                    String lecturerId = parts[1];
                    String lecturerName = parts[2];
                    String lecturerInitial = parts[3];
                    String email = parts[4];
                    String studyProgram = parts[5];
                    Lecturer lecturer = new
                    Lecturer(lecturerId, lecturerName, lecturerInitial, email, studyProgram);

                    // Prevent duplicate lecturer entries
                    boolean isDuplicateLecturer = lecturers.stream().anyMatch(l -> l.getId().equals(lecturer.getId()));
                    if (isDuplicateLecturer) {
                        continue; // Continue to the next input line
                    }

                    lecturers.add(lecturer);
                    break;


                case "student-details":
                    if (parts.length < 2) {
                        continue;
                    }
                    String studentIdDetails = parts[1];
                    Student studentDetails = findStudent(students, studentIdDetails);
                    if (studentDetails == null) {
                        continue;
                    }
                    for(Student student1 : students){
                        if(student1.getId().equals(studentIdDetails)){
                            double gpa = calculateGPA(enrollments, student1.getId());
                            int totalCredits = calculateTotalCredits(enrollments, student1.getId());
                            System.out.println(student1.getId() + "|" + student1.getName() + "|" + student1.getYear() + "|" + student1.getMajor() + "|" + String.format("%.2f", gpa) + "|" + totalCredits);
                            break;
                           
                        }
                    }
                    case "course-open":
                    if (parts.length < 5) {
                        continue;
                    }
                    String courseCodeOpen = parts[1];
                    String academicYearOpen = parts[2];
                    String semesterOpen = parts[3];
                    String[] lecturerInitialList = parts[4].split(",");
                        StringBuilder lecturerInfo = new StringBuilder();
                        for (String lecturerdetail : lecturerInitialList) {
                            // Find lecturer by initial
                            Lecturer lecturerFound = lecturers.stream()
                                    .filter(l -> l.getInitial().equals( lecturerdetail))
                                    .findFirst()
                                    .orElse(null);
                            if (lecturerFound != null) {
                                if (lecturerInfo.length() > 0) {
                                    lecturerInfo.append(";");
                                }
                                lecturerInfo.append( lecturerdetail)
                                        .append(" (")
                                        .append(lecturerFound.getEmail())
                                        .append(")");
                            }
                        }
                    boolean haslecturer = false;
                    for (Lecturer lec : lecturers) {
                        if (lec.getInitial().equals(lecturerInitialList)) {
                            haslecturer = true;
                            break;
                        }
                    }

                    for (Course courseOpen : courses) {
                        if (courseOpen.getCode().equals(courseCodeOpen)) {
                            haslecturer = true;
                        }
                        
                        if (haslecturer) {
                            CourseOpening courseOpening = new CourseOpening(courseCodeOpen, semesterOpen, academicYearOpen, lecturerInfo.toString());
                            CourseOpenings.add(courseOpening);
                        }
                    }
                        break;

                        //berikan kode dengan mengikuti imputan find-the-best-student#2020/2021#odd find-the-best-student#2020/2021#even agar dapat mengeluarkan output 12S20002|B/A 12S20002|B/A
                        case "find-the-best-student":
                        if (parts.length < 3) {
                            continue;
                        }
                        String academicYearBest = parts[1];
                        String semesterBest = parts[2];
                        double bestGPA = 0;
                        String bestStudentId = "";
                        for (Student std : students) {
                            double gpa = calculateGPA(enrollments, std.getId());
                            if (gpa > bestGPA) {
                                bestGPA = gpa;
                                bestStudentId = std.getId();
                            }
                        }
                        System.out.println(bestStudentId + "|" + String.format("%.2f", bestGPA));
                        break;

                        

                    
                case "course-history":
                CourseOpenings.sort(Comparator.comparing(CourseOpening::getSemester).reversed());
                if (parts.length < 2) {
                        continue;
                    }
                    String courseCodeHistory = parts[1];
                    for (CourseOpening opening : CourseOpenings) {
                        if (opening.getCourseCode().equals(courseCodeHistory)) {
                            String courseName = findCourseName(courses, courseCodeHistory);
                            String credit = Integer.toString(findCourseCredits(courses, courseCodeHistory));
                            String opengrade = findPassingGrade(courses, courseCodeHistory);
                            // String lecturerInfo = getLecturerInfo(opening.getLecturers());
                            System.out.println(opening.getCourseCode() + "|" + courseName + "|" + credit + "|" + opengrade + "|" + opening.getAcademicYear() + "|" + opening.getSemester() + "|" + opening.getLecturers());
                        }
                        for (Enrollment enroll : enrollments) {
                            if (enroll.getCourseId().equals(opening.getCourseCode()) && enroll.getAcademicYear().equals(opening.getAcademicYear()) && enroll.getSemester().equals(opening.getSemester())) {
                                
                            if (enroll.getCourseId().equals(courseCodeHistory)) {
                                String grades = enroll.getGrade() != null ? enroll.getGrade() : "None";
                                String previousGrade = enroll.getPreviousGrade() != null ? "(" + enroll.getPreviousGrade() + ")" : "";
                                System.out.println(enroll.getCourseId() + "|" + enroll.getStudentId() + "|" + enroll.getAcademicYear() + "|" + enroll.getSemester() + "|" + grades + previousGrade);
                            }
                        }
                    }
                }
                break;
            }
        }

        scanner.close();

        // Print lecturers
        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.getId() + "|" + lecturer.getName() + "|" + lecturer.getInitial() + "|" + lecturer.getEmail() + "|" + lecturer.getStudyProgram());
        }

        // Print courses with assigned lecturers
        for (CourseWithLecturers course : courses) {
            StringBuilder lecturerInfo = new StringBuilder();
            List<Lecturer> courseLecturers = course.getLecturers();
            for (int i = 0; i < courseLecturers.size(); i++) {
                Lecturer lecturer = courseLecturers.get(i);
                lecturerInfo.append(lecturer.getInitial()).append(" (").append(lecturer.getEmail()).append(")");
                if (i < courseLecturers.size() - 1) {
                    lecturerInfo.append(";");
                }
            }
            System.out.println(course.getCode() + "|" + course.getName() + "|" + course.getCredits() + "|" + course.getPassingGrade());
        }

        // Print students
        for (Student student : students) {
            System.out.println(student.getId() + "|" + student.getName() + "|" + student.getYear() + "|" + student.getMajor());
        }

        // Mencetak detail enrollments
        for (Enrollment enrollment : enrollments) {
            String grade = enrollment.getGrade() != null ? enrollment.getGrade() : "None";
            String previousGrade = enrollment.getPreviousGrade() != null ? "(" + enrollment.getPreviousGrade() + ")" : "";
            System.out.println(enrollment.getCourseId() + "|" + enrollment.getStudentId() + "|" + enrollment.getAcademicYear() + "|" + enrollment.getSemester() + "|" + grade + previousGrade);
        }
    }

    private static CourseWithLecturers findCourse(List<CourseWithLecturers> courses, String courseId) {
        for (CourseWithLecturers course : courses) {
            if (course.getCode().equals(courseId)) {
                return course;
            }
        }
        return null;
    }

    private static Student findStudent(List<Student> students, String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    private static Enrollment findEnrollment(List<Enrollment> enrollments, String courseId, String studentId, String academicYear, String semester) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseId().equals(courseId) && enrollment.getStudentId().equals(studentId)
                    && enrollment.getAcademicYear().equals(academicYear) && enrollment.getSemester().equals(semester)) {
                return enrollment;
            }
        }
        return null;
    }
    private static double calculateGPA(List<Enrollment> enrollments, String studentId) {
    Map<String, List<Enrollment>> enrollmentsByStudent = new HashMap<>();
    for (Enrollment enrollment : enrollments) {
        if (enrollment.getStudentId().equals(studentId) && enrollment.getGrade() != null) {
            String studentKey = enrollment.getStudentId() + "-" + enrollment.getCourseId();
            enrollmentsByStudent.putIfAbsent(studentKey, new ArrayList<>());
            enrollmentsByStudent.get(studentKey).add(enrollment);
        }
    }

    if (enrollmentsByStudent.isEmpty()) {
        return 0.0; // If no data is available, return 0.0
    }

    double totalGradePoints = 0;
    int totalCredits = 0;

    for (List<Enrollment> studentEnrollments : enrollmentsByStudent.values()) {
        // Find the latest enrollment for each course
        Enrollment latestEnrollment = studentEnrollments.stream()
                .max(Comparator.comparing(Enrollment::getAcademicYear)
                        .thenComparing(Enrollment::getSemester))
                .orElse(null);

        if (latestEnrollment != null) {
            double gradePoints = convertGradeToPoints(latestEnrollment.getGrade());
            int credits = findCourseCredit(courses, latestEnrollment.getCourseId());
            totalGradePoints += gradePoints * credits;
            totalCredits += credits;
        }
    }

    double gpa = totalCredits == 0 ? 0 : totalGradePoints / totalCredits;
    return gpa;
}

    
private static int calculateTotalCredits(List<Enrollment> enrollments, String studentId) {
    Map<String, List<Enrollment>> enrollmentsByStudent = new HashMap<>();
    for (Enrollment enrollment : enrollments) {
        if (enrollment.getStudentId().equals(studentId) && enrollment.getGrade() != null) {
            String studentKey = enrollment.getStudentId() + "-" + enrollment.getCourseId();
            enrollmentsByStudent.putIfAbsent(studentKey, new ArrayList<>());
            enrollmentsByStudent.get(studentKey).add(enrollment);
        }
    }

    if (enrollmentsByStudent.isEmpty()) {
        return 0; // Jika tidak ada data yang tersedia, kembalikan nilai 0
    }

    int totalCredits = 0;
    for (List<Enrollment> studentEnrollments : enrollmentsByStudent.values()) {
        // Temukan pendaftaran terbaru untuk setiap kursus
        Enrollment latestEnrollment = studentEnrollments.stream()
                .max(Comparator.comparing(Enrollment::getAcademicYear)
                        .thenComparing(Enrollment::getSemester))
                .orElse(null);

        if (latestEnrollment != null) {
            int credits = findCourseCredit(courses, latestEnrollment.getCourseId());
            totalCredits += credits;
        }
    }

    return totalCredits;
}

    
    private static int findCourseCredit(List<CourseWithLecturers> courses, String courseId) {
        for (CourseWithLecturers course : courses) {
            if (course.getCode().equals(courseId)) {
                return course.getCredits();
            }
        }
        return 0; 
    }
    
    private static double convertGradeToPoints(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "AB":
                return 3.5;
            case "B":
                return 3.0;
            case "BC":
                return 2.5;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "E":
                return 0.0;
            default:
                return 0.0;
        }

    }

    private static Lecturer findLecturer(List<Lecturer> lecturers, String initial) {
        for (Lecturer lecturer : lecturers) {
            if (lecturer.getInitial().equals(initial)) {
                return lecturer;
            }
        }
        return null;
    }
    
    private static String findCourseName(List<CourseWithLecturers> courses, String courseId) {
        for (CourseWithLecturers course : courses) {
            if (course.getCode().equals(courseId)) {
                return course.getName();
            }
        }
        return "null";
    }
    
    private static int findCourseCredits(List<CourseWithLecturers> courses, String courseId) {
        for (CourseWithLecturers course : courses) {
            if (course.getCode().equals(courseId)) {
                return course.getCredits();
            }
        }
        return 0;
    }
    
    private static String findPassingGrade(List<CourseWithLecturers> courses, String courseId) {
        for (CourseWithLecturers course : courses) {
            if (course.getCode().equals(courseId)) {
                return course.getPassingGrade();
            }
        }
        return "null";
    }

    // private static String getLecturerInfo(List<Lecturer> lecturers) {
    //     StringBuilder lecturerInfo = new StringBuilder();
    //     for (Lecturer lecturer : lecturers) {
    //         if (lecturerInfo.length() > 0) {
    //             lecturerInfo.append(",");
    //         }
    //         lecturerInfo.append(lecturer.getInitial()).append(" (").append(lecturer.getEmail()).append(")");
    //     }
    //     return "[" + lecturerInfo.toString() + "]";
    // }    
    
}  
 