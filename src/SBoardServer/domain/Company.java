package SBoardServer.domain;

public class Company {
    private int id;
    private String name;
    private String desc;
    private String address;
    private String email;
    private String password;
    private String phone;
    private float rate;
    private long createdTime;

    public Company(int id, String name, String desc, String address, String email, String password, String phone, float rate, long createdTime) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.rate = rate;
        this.createdTime = createdTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getAddress() {
        return address;
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

    public float getRate() {
        return rate;
    }

    public long getCreatedTime() {
        return createdTime;
    }
}
