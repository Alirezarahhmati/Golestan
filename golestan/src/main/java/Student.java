import java.util.ArrayList;

public class Student {
    private String username;
    private String password;
    private String totalName;
    private String studentNumber;
    private String field;
    private String college;
    private String entryYear;
    private double gradePointAverage;
    private ArrayList<Lesson> lessons = new ArrayList<>();

//    public Student(String username, String password, String totalName, String studentNumber, String field, String college, String entryYear) {
//        this.username = username;
//        this.password = password;
//        this.totalName = totalName;
//        this.studentNumber = studentNumber;
//        this.field = field;
//        this.college = college;
//        this.entryYear = entryYear;
//    }

    /// set setter for all value
    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setTotalName(String totalName) {
        this.totalName = totalName;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setEntryYear(String entryYear) {
        this.entryYear = entryYear;
    }

    public void setGradePointAverage(double gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }

    public void addLessons (Lesson lesson) {
        lessons.add(lesson);
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    //// set getter for all value
    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getTotalName() {
        return totalName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getField() {
        return field;
    }

    public String getCollege() {
        return college;
    }

    public String getEntryYear() {
        return entryYear;
    }

    public double getGradePointAverage() {
        return gradePointAverage;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }



    /// remove
    public void deleteLesson (Lesson lesson) {
        lessons.remove(lesson);
    }



}
