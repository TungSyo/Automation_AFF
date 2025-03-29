package User.Information;

public class Information_Data {
    private String name;
    private String cmnd;
    private String city;
    private String district;
    private String ward;
    private String location;
    private String mst;
    private String date;
    private String bank;
    private String stk;
    private String result;
    private String title;
    private String link;
    private String description;
    private String testType;

    public Information_Data(String name, String cmnd, String city, String district, 
                                String ward, String location, String mst, String date, 
                                String bank, String stk, String result, String title, 
                                String link, String description, String testType) {
        this.name = name;
        this.cmnd = cmnd;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.location = location;
        this.mst = mst;
        this.date = date;
        this.bank = bank;
        this.stk = stk;
        this.result = result;
        this.title = title;
        this.link = link;
        this.description = description;
        this.testType = testType;
    }

    public String getName() { return name; }
    public String getCmnd() { return cmnd; }
    public String getCity() { return city; }
    public String getDistrict() { return district; }
    public String getWard() { return ward; }
    public String getLocation() { return location; }
    public String getMst() { return mst; }
    public String getDate() { return date; }
    public String getBank() { return bank; }
    public String getStk() { return stk; }
    public String getResult() { return result; }
    public String getTitle() { return title; }
    public String getLink() { return link; }
    public String getDescription() { return description; }
    public String getTestType() { return testType; }
} 