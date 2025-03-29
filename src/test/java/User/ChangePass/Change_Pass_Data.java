package User.ChangePass;

public class Change_Pass_Data {
    private String email;
    private String pass;
    private String passOld;
    private String passNew;
    private String result;
    private String title;
    private String link;
    private String description;
    private String testType;

    public Change_Pass_Data(String email, String pass, String passOld, String passNew, 
                               String result, String title, String link, 
                               String description, String testType) {
        this.email = email;
        this.pass = pass;
        this.passOld = passOld;
        this.passNew = passNew;
        this.result = result;
        this.title = title;
        this.link = link;
        this.description = description;
        this.testType = testType;
    }

    public String getEmail() { return email; }
    public String getPass() { return pass; }
    public String getPassOld() { return passOld; }
    public String getPassNew() { return passNew; }
    public String getResult() { return result; }
    public String getTitle() { return title; }
    public String getLink() { return link; }
    public String getDescription() { return description; }
    public String getTestType() { return testType; }
} 