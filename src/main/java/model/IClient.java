package model;

public interface IClient extends IPerson {
    String getPhone();
    void setPhone(String phone);

    String getEmail();
    void setEmail(String email);
}
