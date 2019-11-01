package vn.edu.poly.testduan2.view.activity;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.common.Constacts;
import vn.edu.poly.testduan2.common.ImageFirebaseUlits;
import vn.edu.poly.testduan2.firebase.FirebaseManager;

public class AddProductCHActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.spnType)
    Spinner spnType;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.edtName)
    EditText edtName;
    @BindView(R.id.tvImageProduct)
    TextView tvImageProduct;
    @BindView(R.id.imgProduct)
    ImageView imgProduct;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvPrice1)
    TextView tvPrice1;
    @BindView(R.id.edtPrice1)
    EditText edtPrice1;
    @BindView(R.id.tvPrice2)
    TextView tvPrice2;
    @BindView(R.id.edtPrice2)
    EditText edtPrice2;
    @BindView(R.id.rdPrice1)
    RadioButton rdPrice1;
    @BindView(R.id.rdPrice2)
    RadioButton rdPrice2;
    @BindView(R.id.rdTopping)
    RadioGroup rdTopping;
    @BindView(R.id.btnAddProduct)
    Button btnAddProduct;
    @BindView(R.id.btnCancelAdd)
    Button btnCancelAdd;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.edt_category)
    EditText edtCategory;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private Uri uriImage;
    private String image;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_add_product_ch;
    }

    @Override
    protected void initialize(Bundle savedInstanceState) {
        if (ConstactChange.STATUS_ADD == 1) {
            tvTitle.setText("Add Milk Tea");
        }
        if (ConstactChange.STATUS_ADD == 2) {
            tvTitle.setText("Add Fruit");
            tvPrice2.setVisibility(View.GONE);
            edtPrice2.setVisibility(View.GONE);
        }
        if (ConstactChange.STATUS_ADD == 3) {
            tvTitle.setText("Add Bread");
            tvPrice2.setVisibility(View.GONE);
            edtPrice2.setVisibility(View.GONE);
        }
    }

    private void filebasechooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, Constacts.REQUEST_CODE_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constacts.REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriImage = data.getData();
            imgProduct.setImageURI(uriImage);
            image = ImageFirebaseUlits.fileUploader(this, uriImage);
        }
    }

    private String getExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @OnClick({R.id.imgProduct, R.id.btnAddProduct, R.id.btnCancelAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgProduct:
                filebasechooseImage();
                break;
            case R.id.btnAddProduct:
                if (ConstactChange.STATUS_ADD == 1) {
                    insertMilkTea();
                }
                if (ConstactChange.STATUS_ADD == 2) {
                    insertFruit();

                }
                if (ConstactChange.STATUS_ADD == 3) {
                    insertBread();
                }
        break;
        case R.id.btnCancelAdd:
        finish();
        break;
    }

}


    private void insertMilkTea() {
        String nameproduct = edtName.getText().toString();
        String idcategory = edtCategory.getText().toString();
        String priceM = edtPrice1.getText().toString();
        String priceL = edtPrice2.getText().toString();
        String description = "";
        FirebaseManager.insertMilkTea(this, nameproduct, "1", "ROYAL CHEESE", image, priceM, priceL, description);
    }

    private void insertFruit() {
        String nameproduct = edtName.getText().toString();
        String image = ImageFirebaseUlits.fileUploader(this, uriImage);
        String priceM = edtPrice1.getText().toString();
        String description = "";
        FirebaseManager.insertFruit(this, nameproduct,image,priceM,description);
    }

    private void insertBread() {
        String nameproduct = edtName.getText().toString();
        String idcategory = edtCategory.getText().toString();
        String image = ImageFirebaseUlits.fileUploader(this, uriImage);
        String priceM = edtPrice1.getText().toString();
        String description = "";
        FirebaseManager.insertBread(this, nameproduct,image,priceM,description);
    }

}
