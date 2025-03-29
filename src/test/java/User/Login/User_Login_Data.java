package User.Login;

public class User_Login_Data {
    private String email;
    private String password;
    private String result;
    private String title;
    private String link;
    private String description;
    private String testType;

    public User_Login_Data(String email, String password, String result, String title, String link, String description, String testType) {
        this.email = email;
        this.password = password;
        this.result = result;
        this.title = title;
        this.link = link;
        this.description = description;
        this.testType = testType;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getResult() { return result; }
    public String getTitle() { return title; }
    public String getLink() { return link; }
    public String getDescription() { return description; }
    public String getTestType() { return testType; }
} 