import java.util.ArrayList;

public class Lesson {
    private String lessonName;
    private String professor;
    private String collegeL;
    private String lessonCode;
    private String time;
    private int unit;
    private double student_score = -1;
    ArrayList<String> participle = new ArrayList<>();


    // setter
    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setCollegeL(String collegeL) {
        this.collegeL = collegeL;
    }

    public void setLessonCode(String lessonCode) {
        this.lessonCode = lessonCode;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void addParticiple(String studentNumber) {
        participle.add(studentNumber);
    }

    public void setStudent_score(int student_score) {
        this.student_score = student_score;
    }

    public void setParticiple (ArrayList<String> participle) {
        this.participle = participle;
    }

    // getter
    public String getLessonName() {
        return lessonName;
    }

    public String getProfessor() {
        return professor;
    }

    public String getCollegeL() {
        return collegeL;
    }

    public String getLessonCode() {
        return lessonCode;
    }

    public int getUnit() {
        return unit;
    }

    public String getTime() {
        return time;
    }

    public ArrayList<String> getParticiple() {
        return participle;
    }

    public double getStudent_score() {
        return student_score;
    }

    ///// remove
    public void deleteParticiple (String studentNumber) {
        participle.remove(studentNumber);
    }
}
