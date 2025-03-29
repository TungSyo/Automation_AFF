package User.Cart.CartAdd;

public class Cart_Add_Data {
    private String product;
    private String quanlity;
    private String price;
    private String result;
    private String title;
    private String link;
    private String description;
    private String testType;
    private String typeCase;

    public Cart_Add_Data(String product, String quanlity, String price,
                        String result, String title, String link, 
                        String description, String testType, String typeCase) {
        this.product = product;
        this.quanlity = quanlity;
        this.price = price;
        this.result = result;
        this.title = title;
        this.link = link;
        this.description = description;
        this.testType = testType;
        this.typeCase = typeCase;
    }

    public String getProduct() { return product; }
    public String getQuanlity() { return quanlity; }
    public String getPrice() { return price; }
    public String getResult() { return result; }
    public String getTitle() { return title; }
    public String getLink() { return link; }
    public String getDescription() { return description; }
    public String getTestType() { return testType; }
    public String getTypeCase() { return typeCase; }
} 