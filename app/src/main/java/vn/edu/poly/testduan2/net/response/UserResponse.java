package vn.edu.poly.testduan2.net.response;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UserResponse {
    String id;
    String username;
    String password;
    int position;
    int bill_position;

    public UserResponse() {
    }

    public UserResponse(String id, String username, String password, int position, int bill_position) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.position = position;
        this.bill_position = bill_position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getBill_position() {
        return bill_position;
    }

    public void setBill_position(int bill_position) {
        this.bill_position = bill_position;
    }
}
