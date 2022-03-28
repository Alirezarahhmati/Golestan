public class Professor {
    private String username;
    private String password;
    private String totalName;
    private String group;
    private String collegeP;

    //// setter
    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setTotalName(String totalName) {
        this.totalName = totalName;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setCollegeP(String collegeP) {
        this.collegeP = collegeP;
    }

    //// getter
    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public String getTotalName() { return totalName; }

    public String getGroup() {
        return group;
    }

    public String getCollegeP() {
        return collegeP;
    }
}
