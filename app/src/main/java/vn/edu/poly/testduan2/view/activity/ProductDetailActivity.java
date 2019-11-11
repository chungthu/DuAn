package vn.edu.poly.testduan2.view.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.utils.EventBusAction;
import vn.edu.poly.testduan2.common.utils.MessageEvent;
import vn.edu.poly.testduan2.model.BreadFirebase;
import vn.edu.poly.testduan2.model.FruitFirebase;
import vn.edu.poly.testduan2.model.MilkTeaFirebase;

public class ProductDetailActivity extends BaseActivity {

    @BindView(R.id.img_ImageProduct)
    ImageView imgImageProduct;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_price_title)
    TextView tvPriceTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.img_remove)
    ImageView imgRemove;
    @BindView(R.id.img_add)
    ImageView imgAdd;
    @BindView(R.id.edt_note)
    EditText edtNote;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.tv_Amount)
    TextView tvAmount;
    private MilkTeaFirebase milkTea;
    private FruitFirebase fruit;
    private BreadFirebase bread;
    private String title;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_product_detail2;
    }

    @Override
    protected void initialize(@Nullable Bundle savedInstanceState) {
        setupTotal();
    }

    @Override
    protected String getNavigationTitle() {
        return "SP "+title;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void handleEvent(MessageEvent event) {
        switch (event.action) {
            case EventBusAction.MILKTEA_DETAIL:
                title = String.valueOf(event.position);
                milkTea = (MilkTeaFirebase) event.object;
                setUpMilkTea(milkTea);
                break;
            case EventBusAction.FRUIT_DETAIL:
                title = String.valueOf(event.position);
                fruit = (FruitFirebase) event.object;
                setUpFruit(fruit);
                break;
            case EventBusAction.BREAD_DETAIL:
                title = String.valueOf(event.position);
                bread = (BreadFirebase) event.object;
                setUpBread(bread);
                break;
        }
    }

    @OnClick({R.id.img_remove, R.id.img_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_remove:
                if (Double.parseDouble(tvAmount.getText().toString())>1){
                    tvAmount.setText(String.valueOf(Double.parseDouble(tvAmount.getText().toString())-1));
                }
                break;
            case R.id.img_add:
                tvAmount.setText(String.valueOf(Double.parseDouble(tvAmount.getText().toString())+1));
                break;
        }
    }

    private void setUpMilkTea(MilkTeaFirebase item) {
        if (!item.getImage().isEmpty() && !item.getImage().equals("")) {
            Picasso.get().load(item.getImage()).into(imgImageProduct);
        }
        tvName.setText(item.getName());
        tvPriceTitle.setText(item.getPriceM());
        tvPrice.setText(item.getPriceM());
        total.setText(String.valueOf(Double.parseDouble(tvPrice.getText().toString()) *
                Double.parseDouble(tvAmount.getText().toString())));
    }

    private void setUpFruit(FruitFirebase item) {
        if (item.getImage().isEmpty() && !item.getImage().equals("")) {
            Picasso.get().load(item.getImage()).into(imgImageProduct);
        }
        tvName.setText(item.getName());
        tvPriceTitle.setText(item.getPrice());
        tvPrice.setText(item.getPrice());
        total.setText(String.valueOf(Double.parseDouble(tvPrice.getText().toString()) *
                Double.parseDouble(tvAmount.getText().toString())));
    }

    private void setUpBread(BreadFirebase item) {
        if (item.getImage().isEmpty() && !item.getImage().equals("")) {
            Picasso.get().load(item.getImage()).into(imgImageProduct);
        }
        tvName.setText(item.getName());
        tvPriceTitle.setText(item.getPrice());
        tvPrice.setText(item.getPrice());
        total.setText(String.valueOf(Double.parseDouble(tvPrice.getText().toString()) *
                Double.parseDouble(tvAmount.getText().toString())));
    }

    private void setupTotal() {
        tvAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                total.setText(String.valueOf(Double.parseDouble(tvPrice.getText().toString()) *
                        Double.parseDouble(tvAmount.getText().toString())));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}