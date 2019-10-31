package vn.edu.poly.testduan2.view.activity;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import butterknife.BindView;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.Constacts;
import vn.edu.poly.testduan2.common.ImageFirebaseUlits;

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
    private StorageReference mStorageReference;
    private Uri uriImage;
    private StorageTask uploadTask;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_add_product_ch;
    }

    @Override
    protected void initialize(Bundle savedInstanceState) {
        mStorageReference = FirebaseStorage.getInstance().getReference("Images");
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
                if (uploadTask != null && uploadTask.isInProgress()){
                    Toast.makeText(this, "Upload in progress", Toast.LENGTH_SHORT).show();
                }else {
                    String s = ImageFirebaseUlits.fileUploader(this,uriImage);
                    Log.e("AAA", s);
                }
                break;
            case R.id.btnCancelAdd:
                break;
        }
    }
}
