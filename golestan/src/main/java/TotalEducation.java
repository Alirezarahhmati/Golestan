import java.util.ArrayList;

public class TotalEducation {
    private String username;
    private String password;
    private boolean newTerm = false;
    private boolean termInProgress = false;
    private ArrayList<String> Faculties = new ArrayList<>();

    // setter
    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setNewTerm(boolean newTerm) { this.newTerm = newTerm; }

    public void addFaculties(String newFaculty) {
        Faculties.add(newFaculty);
    }

    public void setTermInProgress(boolean termInProgress) {
        this.termInProgress = termInProgress;
    }

    // getter
    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public boolean isNewTerm() { return newTerm; }

    public ArrayList<String> getFaculties() {
        return Faculties;
    }

    public boolean isTermInProgress() {
        return termInProgress;
    }
}
