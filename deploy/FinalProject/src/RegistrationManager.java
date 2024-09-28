import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RegistrationManager {
    private Map<Student, Set<Course>> registrations = new HashMap<>();

    public String selectCourse(List<String> courses) {
        return null;
    }

    public List<Course> displayCourses() {
        return null;
    }

    public List<Course> requestRegistration() {
        return null;
    }

    public void registerCourse(Student student, Course course) {
    }

    public boolean isRegistered(Student student, Course course) {
        return false;
    }

    public void unregisterCourse(Student student, Course course) {
    }

    public Set<Course> getRegisteredCourses(Student student) {
        return null;
    }

    public void saveScore() {
    }
}