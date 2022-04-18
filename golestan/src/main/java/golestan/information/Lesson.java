package golestan.information;

import java.util.ArrayList;
public class Lesson {
    private String lessonName;
    private String professor;
    private String collegeL;
    private String lessonCode;
    private String time;
    private int unit;
    private double student_score = -1;
    ArrayList<String> participant = new ArrayList<>();


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

    public void addParticipant(String studentNumber) {
        participant.add(studentNumber);
    }

    public void setStudent_score(int student_score) {
        this.student_score = student_score;
    }

    public void setParticipant(ArrayList<String> participant) {
        this.participant = participant;
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

    public ArrayList<String> getParticipant() {
        return participant;
    }

    public double getStudent_score() {
        return student_score;
    }

    ///// remove
    public void deleteParticiple (String studentNumber) {
        participant.remove(studentNumber);
    }
}
