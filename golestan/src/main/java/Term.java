import java.util.ArrayList;

public class Term {
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Professor> professors = new ArrayList<>();
    private ArrayList<Lesson> lessons = new ArrayList<>();

    // setter
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setProfessors(ArrayList<Professor> professors) {
        this.professors = professors;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    /// getter
    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }


}
