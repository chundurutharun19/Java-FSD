import java.util.ArrayList;

class Course {
    
    private String code;
    private String name;
    private int credits;

    public Course(String code, String name, int credits) {
        this.code = code;
        this.name = name;
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public void display() {
        System.out.println("Code: " + code +
                ", Name: " + name +
                ", Credits: " + credits);
    }
}

class CourseRegistrationSystem {
    private ArrayList<Course> courses;

    public CourseRegistrationSystem() {
        courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
        System.out.println(course.getName() + " added successfully.");
    }

    public void removeCourse(String code) {
        for (Course c : courses) {
            if (c.getCode().equalsIgnoreCase(code)) {
                courses.remove(c);
                System.out.println("Course removed successfully.");
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public void displayCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("\nAvailable Courses:");
        for (Course c : courses) {
            c.display();
        }
    }

    public void findCourse(String code) {
        for (Course c : courses) {
            if (c.getCode().equalsIgnoreCase(code)) {
                System.out.println("Course Found:");
                c.display();
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public int calculateCredits() {
        int total = 0;
        for (Course c : courses) {
            total += c.getCredits();
        }
        return total;
    }
}

public class Main {
    public static void main(String[] args) {

        CourseRegistrationSystem system = new CourseRegistrationSystem();

        system.addCourse(new Course("CS101", "Java", 4));
        system.addCourse(new Course("CS102", "Python", 3));
        system.addCourse(new Course("CS103", "Spring Boot", 4));
        system.addCourse(new Course("CS104", "React", 3));
        system.addCourse(new Course("CS105", "Docker", 2));

        system.displayCourses();

        System.out.println("\nFinding CS103:");
        system.findCourse("CS103");

        System.out.println("\nTotal Credits: " + system.calculateCredits());

        System.out.println("\nRemoving CS102:");
        system.removeCourse("CS102");

        system.displayCourses();

        System.out.println("\nTotal Credits after removal: " + system.calculateCredits());
    }
}