public class Course {
    private String course_id;
    private String course_name;
    private int credits;

    public Course(String course_id, String course_name, int credits) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.credits = credits;
    }

    public String getCourseId() {
        return course_id;
    }

    public void setCourseId(String course_id) {
        this.course_id = course_id;
    }

    public String getCourseName() {
        return course_name;
    }

    public void setCourseName(String course_name) {
        this.course_name = course_name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}