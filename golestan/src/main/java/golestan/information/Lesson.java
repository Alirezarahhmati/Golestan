package golestan.information;

import golestan.NeedFunctions;

import java.util.ArrayList;
public class Lesson extends NeedFunctions {
    private String lessonName;
    private String professor;
    private String collegeL;
    private String lessonCode;
    private double startTime;
    private double endTime;
    private ArrayList<weekday> weekdays = new ArrayList<>();
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

    public void addParticipant(String studentNumber) {
        participant.add(studentNumber);
    }

    public void setStudent_score(int student_score) {
        this.student_score = student_score;
    }

    public void setParticipant(ArrayList<String> participant) {
        this.participant = participant;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public void setWeekdays(ArrayList<weekday> weekdays) {
        this.weekdays = weekdays;
    }

    public void addWeekdays(NeedFunctions.weekday weekday) {
        this.weekdays.add(weekday);
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

    public ArrayList<String> getParticipant() {
        return participant;
    }

    public double getStudent_score() {
        return student_score;
    }


    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public ArrayList<weekday> getWeekdays() {
        return this.weekdays;
    }

    ///// remove
    public void deleteParticiple (String studentNumber) {
        participant.remove(studentNumber);
    }
}
