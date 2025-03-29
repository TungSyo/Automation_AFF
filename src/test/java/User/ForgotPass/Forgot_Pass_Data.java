package User.ForgotPass;

public class Forgot_Pass_Data {
    private String email;
    private String newPass;
    private String confirmPass;
    private String result;
    private String title;
    private String link;
    private String description;
    private String testType;
    private String pop3;

    public Forgot_Pass_Data(String email, String newPass, String confirmPass, 
                           String result, String title, String link, 
                           String description, String testType, String pop3) {
        this.email = email;
        this.newPass = newPass;
        this.confirmPass = confirmPass;
        this.result = result;
        this.title = title;
        this.link = link;
        this.description = description;
        this.testType = testType;
        this.pop3 = pop3;
    }

    // Getters
    public String getEmail() { return email; }
    public String getNewPass() { return newPass; }
    public String getConfirmPass() { return confirmPass; }
    public String getResult() { return result; }
    public String getTitle() { return title; }
    public String getLink() { return link; }
    public String getDescription() { return description; }
    public String getTestType() { return testType; }
    public String getPop3() { return pop3; }
} 