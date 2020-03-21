package SBoardServer.domain;

public class Employee {

    private int id;
    private String name;
    private String sName;
    private String patronymic;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String education;
    private String activities;
    private float rate;
    private long createdTime;
    private int companyId;

    public Employee(int id, String name, String sName, String patronymic, String email, String password, String phone, String address, String education, String activities, float rate, long createdTime, int companyId) {
        this.id = id;
        this.name = name;
        this.sName = sName;
        this.patronymic = patronymic;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.activities = activities;
        this.rate = rate;
        this.createdTime = createdTime;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getsName() {
        return sName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEducation() {
        return education;
    }

    public String getActivities() {
        return activities;
    }

    public float getRate() {
        return rate;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public int getCompanyId() {
        return companyId;
    }
}
