package vn.edu.poly.testduan2.net.firebase;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.common.Constants;
import vn.edu.poly.testduan2.common.evenBus.EvenLogin;
import vn.edu.poly.testduan2.common.evenBus.EventBusAction;
import vn.edu.poly.testduan2.common.utils.Utils;
import vn.edu.poly.testduan2.interfaces.DataBreadStatus;
import vn.edu.poly.testduan2.interfaces.DataFruitStatus;
import vn.edu.poly.testduan2.interfaces.DataMilkteaStatus;
import vn.edu.poly.testduan2.interfaces.DataTableStatus;
import vn.edu.poly.testduan2.net.response.BillResponse;
import vn.edu.poly.testduan2.net.response.BreadFirebase;
import vn.edu.poly.testduan2.net.response.FruitFirebase;
import vn.edu.poly.testduan2.net.response.MilkTeaFirebase;
import vn.edu.poly.testduan2.net.response.TableResponse;
import vn.edu.poly.testduan2.net.response.UserResponse;

public class FirebaseManager {
    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("Product").child("MilkTea");
    private static DatabaseReference mDatabaseFruit = FirebaseDatabase.getInstance().getReference("Product").child("Fruit");
    private static DatabaseReference mDatabaseBread = FirebaseDatabase.getInstance().getReference("Product").child("Bread");
    public static DatabaseReference mDatabaseBill = FirebaseDatabase.getInstance().getReference("Bill");
    private static DatabaseReference mDatabaseTable = FirebaseDatabase.getInstance().getReference("Table");
    private static DatabaseReference mDatabaseUser = FirebaseDatabase.getInstance().getReference("User");

    private List<MilkTeaFirebase> item = new ArrayList<>();
    private List<FruitFirebase> itemFruit = new ArrayList<>();
    private List<BreadFirebase> itemBr = new ArrayList<>();
    private List<TableResponse> itemTableResponse = new ArrayList<>();

    private DataMilkteaStatus dataMilkteaStatus;
    private DataFruitStatus dataFruitStatus;
    private DataBreadStatus dataBreadStatus;
    private DataTableStatus dataTableStatus;

    public void setDataMilkteaStatus(DataMilkteaStatus dataMilkteaStatus) {
        this.dataMilkteaStatus = dataMilkteaStatus;
    }

    public void setDataFruitStatus(DataFruitStatus dataFruitStatus) {
        this.dataFruitStatus = dataFruitStatus;
    }

    public void setDataBreadStatus(DataBreadStatus dataBreadStatus) {
        this.dataBreadStatus = dataBreadStatus;
    }

    public void setDataTableStatus(DataTableStatus dataTableStatus) {
        this.dataTableStatus = dataTableStatus;
    }

    /**
    TableResponse milk tea
     **/

    //Insert
    public void insertMilkTea(Context context, String name, String id_category, String name_category, String image ,
                                     String priceM, String priceL, String description){
        String key_milkteaId = mDatabase.push().getKey();

        MilkTeaFirebase item = new MilkTeaFirebase(key_milkteaId,name,id_category,"ROYAL CHEESE",image,priceM,priceL,description);

        assert key_milkteaId != null;
        mDatabase.child(key_milkteaId).setValue(item);
    }

    //Update
    public void updateMilkTea(Context context,String id_product , MilkTeaFirebase item){
        mDatabase.child(id_product).setValue(item);
    }

    //Delete
    public void deleteMilkTea(Context context,String id_product){
        assert id_product != null;
        mDatabase.child(id_product).removeValue();
    }

    //ReadAll
    public void reaAllDataTea(){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                item.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    MilkTeaFirebase milkTeaFirebase = childDataSnapshot.getValue(MilkTeaFirebase.class);
                    item.add(milkTeaFirebase);
                }
                dataMilkteaStatus.getData(item);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    /**
     TableResponse Fruit
     **/



    //Insert
    public void insertFruit(Context context, String name, String image, String price, String description){
        String key = mDatabaseFruit.push().getKey();

        FruitFirebase item = new FruitFirebase(key,name,image,price,description);

        assert key != null;
        mDatabaseFruit.child(key).setValue(item);
    }

    //Update
    public void updateFruit(Context context,String id_product , FruitFirebase item){
        mDatabaseFruit.child(id_product).setValue(item);
    }

    //Delete
    public void deleteFruit(Context context,String id_product){
        mDatabaseFruit.child(id_product).removeValue();
    }

    //ReadAll
    public void reaAllFruit(){
        mDatabaseFruit.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemFruit.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    FruitFirebase fruitFirebase = childDataSnapshot.getValue(FruitFirebase.class);
                    itemFruit.add(fruitFirebase);
                }
                dataFruitStatus.getData(itemFruit);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    /**
     TableResponse Bread
     **/


    //Insert
    public void insertBread(Context context, String name, String image ,
                                      String price, String description){
        String key = mDatabaseBread.push().getKey();

        BreadFirebase item = new BreadFirebase(key,name,image,price,description);

        assert key != null;
        mDatabaseBread.child(key).setValue(item);
    }

    //Update
    public void updateBread(Context context,String id_product , BreadFirebase item){
        mDatabaseBread.child(id_product).setValue(item);
    }

    //Delete
    public void deleteBread(Context context,String id_product){
        mDatabaseBread.child(id_product).removeValue();
    }

    //ReadAll
    public void readAllBread(){
        mDatabaseBread.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemBr.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    BreadFirebase breadFirebase = childDataSnapshot.getValue(BreadFirebase.class);
                    itemBr.add(breadFirebase);
                }
                dataBreadStatus.getData(itemBr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    /**
     TableResponse Bread
     **/

    //Insert
    public void insertBill(BillResponse billResponse){
        mDatabaseBill.child(billResponse.getID()).setValue(billResponse);
    }


    /**
     TableResponse TableResponse
     **/

    public void readAllTable(){
        mDatabaseTable.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemTableResponse.clear();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    TableResponse tableResponse = childDataSnapshot.getValue(TableResponse.class);
                    itemTableResponse.add(tableResponse);
                }
                dataTableStatus.getData(itemTableResponse);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void updateTable(String key, TableResponse tableResponse){
        mDatabaseTable.child(key).setValue(tableResponse);
    }


    /**
     TableResponse User
     **/

    public void login(Context context, String user, String password){
        Query query = mDatabaseUser.orderByChild("username").equalTo(user);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.getValue(UserResponse.class).getPassword().equals(password)) {
                        Utils.saveSharedPreferences(context, Constants.LOGIN_SUCCESS,data.getValue(UserResponse.class).getId());
                        EventBus.getDefault().postSticky(new EvenLogin(EventBusAction.LOGIN_SUCCESS,data.getValue(UserResponse.class)));
                        ConstactChange.USER_RESPONSE = data.getValue(UserResponse.class);
                    } else {
                        EventBus.getDefault().postSticky(new EvenLogin(EventBusAction.LOGIN_FAILL,null));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void getUserbyID(String id){
        Query query = mDatabaseUser.orderByChild("id").equalTo(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    EventBus.getDefault().postSticky(new EvenLogin(EventBusAction.LOGIN_SUCCESS,data.getValue(UserResponse.class)));
                    ConstactChange.USER_RESPONSE = data.getValue(UserResponse.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void updateUser(String key, UserResponse userResponse){
        mDatabaseUser.child(key).setValue(userResponse);
        EventBus.getDefault().postSticky(new EvenLogin(EventBusAction.UPDATE_USER_SUCCESS,null));
    }

}


