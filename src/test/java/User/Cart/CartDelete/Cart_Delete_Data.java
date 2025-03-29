package User.Cart.CartDelete;

public class Cart_Delete_Data {
    private String result;
    private String title;
    private String link;
    private String description;
    private String testType;
    private String typeCase;

    public Cart_Delete_Data(String result, String title, String link, String description, String testType, String typeCase) {
        this.result = result;
        this.title = title;
        this.link = link;
        this.description = description;
        this.testType = testType;
        this.typeCase = typeCase;
    }

    public String getResult() { return result; }
    public String getTitle() { return title; }
    public String getLink() { return link; }
    public String getDescription() { return description; }
    public String getTestType() { return testType; }
    public String getTypeCase() { return typeCase; }
} 