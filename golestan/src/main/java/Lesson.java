import java.util.ArrayList;

public class Lesson {
    private String lessonName;
    private String professor;
    private String collegeL;
    private String lessonCode;
    private String unit;
    ArrayList<String> participle;

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

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setParticiple(ArrayList<String> participle) {
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

    public String getUnit() {
        return unit;
    }

    public ArrayList<String> getParticiple() {
        return participle;
    }
}
