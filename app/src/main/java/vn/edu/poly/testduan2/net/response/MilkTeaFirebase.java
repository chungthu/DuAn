package vn.edu.poly.testduan2.net.response;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MilkTeaFirebase {

    String id;
    String name;
    String id_catgory;
    String name_catgory;
    String image;
    String priceM;
    String priceL;
    String description;

    public MilkTeaFirebase() {
    }

    public MilkTeaFirebase(String id, String name, String id_catgory, String name_catgory, String image, String priceM, String priceL, String description) {
        this.id = id;
        this.name = name;
        this.id_catgory = id_catgory;
        this.name_catgory = name_catgory;
        this.image = image;
        this.priceM = priceM;
        this.priceL = priceL;
        this.description = description;
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

    public String getId_catgory() {
        return id_catgory;
    }

    public void setId_catgory(String id_catgory) {
        this.id_catgory = id_catgory;
    }

    public String getName_catgory() {
        return name_catgory;
    }

    public void setName_catgory(String name_catgory) {
        this.name_catgory = name_catgory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPriceM() {
        return priceM;
    }

    public void setPriceM(String priceM) {
        this.priceM = priceM;
    }

    public String getPriceL() {
        return priceL;
    }

    public void setPriceL(String priceL) {
        this.priceL = priceL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
