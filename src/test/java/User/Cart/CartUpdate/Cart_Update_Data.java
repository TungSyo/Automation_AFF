package User.Cart.CartUpdate;

public class Cart_Update_Data {
    private String Quanlity;
    private String Price;
    private String result;
    private String title;
    private String link;
    private String description;
    private String testType;
    private String typeCase;

    public Cart_Update_Data(String Quanlity, String Price, String result, String title, 
                          String link, String description, String testType, String typeCase) {
        this.Quanlity = Quanlity;
        this.Price = Price;
        this.result = result;
        this.title = title;
        this.link = link;
        this.description = description;
        this.testType = testType;
        this.typeCase = typeCase;
    }

    public String getQuanlity() { return Quanlity; }
    public String getPrice() { return Price; }
    public String getResult() { return result; }
    public String getTitle() { return title; }
    public String getLink() { return link; }
    public String getDescription() { return description; }
    public String getTestType() { return testType; }
    public String getTypeCase() { return typeCase; }
} 