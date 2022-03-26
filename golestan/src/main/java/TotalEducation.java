public class TotalEducation {
    private String username;
    private String password;
    private boolean newTerm = false;

    // setter
    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setNewTerm(boolean newTerm) { this.newTerm = newTerm; }

    // getter
    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public boolean isNewTerm() { return newTerm; }
}
