package vn.edu.poly.testduan2.net.response;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class TableResponse {
    private String id;
    private String name;
    private int status;

    public TableResponse() {
    }

    public TableResponse(String id, String name, int status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
