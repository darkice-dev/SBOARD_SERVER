package SBoardServer.domain;

public class User {

    private int id;
    private String name;
    private String sName;
    private String patronymic;
    private String email;
    private String phone;
    private String address;
    private long createdTime;

    public User(int id, String name, String sName, String patronymic, String email, String phone, String address, long createdTime) {
        this.id = id;
        this.name = name;
        this.sName = sName;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.createdTime = createdTime;
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

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public long getCreatedTime() {
        return createdTime;
    }
}
