package golestan.information;

import java.util.ArrayList;

public class Student {
    private String username;
    private String password;
    private String totalName;
    private String studentID;
    private String field;
    private String college;
    private int entryYear;
    private double gradePointAverage;
    private ArrayList<Lesson> lessons = new ArrayList<>();

    /// set setter for all value
    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setTotalName(String totalName) {
        this.totalName = totalName;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setStudentID(String studentID) { this.studentID = studentID; }

    public void setCollege(String college) {
        this.college = college;
    }

    public boolean setEntryYear(int entryYear) {
        if (entryYear >= 1338 && entryYear <= 1400) {
            this.entryYear = entryYear;
            return true;
        } else {
            System.err.println("Wrong input!");
            return false;
        }
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

    public String getStudentID() {
        return studentID;
    }

    public String getField() {
        return field;
    }

    public String getCollege() {
        return college;
    }

    public int getEntryYear() {
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
        int index = -1;
        int len = this.lessons.size();
        for (int i = 0; i < len ; i++) {
            if (this.lessons.get(i).getLessonCode().equals(lesson.getLessonCode())) {
                index = i;
            }
        }
        if (index != -1) {
            this.lessons.remove(index);
        }
    }
}
