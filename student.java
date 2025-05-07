
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Course {
    private String courseName;
    private Map<String, Integer> studentMarks; // studentName -> marks

    public Course(String courseName) {
        this.courseName = courseName;
        this.studentMarks = new HashMap<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void addStudentMark(String studentName, int mark) {
        studentMarks.put(studentName, mark);
    }

    public Map<String, Integer> getStudentMarks() {
        return studentMarks;
    }

    public List<String> getStudentsBelowMark(int threshold) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : studentMarks.entrySet()) {
            if (entry.getValue() < threshold) {
                result.add(entry.getKey() + " scored " + entry.getValue() + " in " + courseName);
            }
        }
        return result;
    }
}

class Student {
    private String name;
    private String program;
    private String semester;
    private List<Course> registeredCourses;

    public Student(String name, String program, String semester) {
        this.name = name;
        this.program = program;
        this.semester = semester;
        this.registeredCourses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getProgram() {
        return program;
    }

    public String getSemester() {
        return semester;
    }

    public void registerCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
        }
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void printDetails() {
        System.out.println("Name: " + name);
        System.out.println("Program: " + program);
        System.out.println("Semester: " + semester);
        System.out.print("Registered Courses: ");
        for (int i = 0; i < registeredCourses.size(); i++) {
            System.out.print(registeredCourses.get(i).getCourseName());
            if (i < registeredCourses.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n");
    }
}

public class Student {
    public static void main(String[] args) {
        // Create courses
        Course math = new Course("Mathematics");
        Course physics = new Course("Physics");
        Course chemistry = new Course("Chemistry");

        // Create students
        Student alice = new Student("Alice", "BSc Computer Science", "3");
        Student bob = new Student("Bob", "BSc Physics", "2");
        Student charlie = new Student("Charlie", "BSc Chemistry", "1");

        // Register courses for students
        alice.registerCourse(math);
        alice.registerCourse(physics);

        bob.registerCourse(physics);
        bob.registerCourse(chemistry);

        charlie.registerCourse(chemistry);
        charlie.registerCourse(math);

        // Add marks
        math.addStudentMark("Alice", 35);
        math.addStudentMark("Charlie", 45);

        physics.addStudentMark("Alice", 50);
        physics.addStudentMark("Bob", 25);

        chemistry.addStudentMark("Bob", 30);
        chemistry.addStudentMark("Charlie", 60);

        // List of students and courses
        List<Student> students = new ArrayList<>();
        students.add(alice);
        students.add(bob);
        students.add(charlie);

        List<Course> courses = new ArrayList<>();
        courses.add(math);
        courses.add(physics);
        courses.add(chemistry);

        // 1. Retrieve and display student details
        System.out.println("Student Details:");
        for (Student s : students) {
            s.printDetails();
        }

        // 2. Retrieve marks of students scoring less than 40 and respective course
        System.out.println("Students scoring less than 40 in courses:");
        for (Course c : courses) {
            List<String> lowScorers = c.getStudentsBelowMark(40);
            for (String info : lowScorers) {
                System.out.println(info);
            }
        }
    }
}
