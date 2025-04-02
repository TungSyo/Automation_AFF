package User.Order;

public class Order_Data {
    private String name;
    private String phone;
    private String city;
    private String district;
    private String ward;
    private String location;
    private String bank;
    private String note;
    private String result;
    private String title;
    private String link;
    private String description;
    private String testType;
    private String typeCase;

    public Order_Data(String name, String phone, String city, String district, String ward, String location,
            String bank, String note, String result, String title, String link, String description, String testType,
            String typeCase) {
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.location = location;
        this.bank = bank;
        this.note = note;
        this.result = result;
        this.title = title;
        this.link = link;
        this.description = description;
        this.testType = testType;
        this.typeCase = typeCase;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getWard() {
        return ward;
    }

    public String getLocation() {
        return location;
    }

    public String getBank() {
        return bank;
    }

    public String getNote() {
        return note;
    }

    public String getResult() {
        return result;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }

    public String getTestType() {
        return testType;
    }

    public String getTypeCase() {
        return typeCase;
    }
}