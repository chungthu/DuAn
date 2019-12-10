package vn.edu.poly.testduan2.view.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import vn.edu.poly.testduan2.R;
import vn.edu.poly.testduan2.common.ConstactChange;
import vn.edu.poly.testduan2.common.utils.Utils;
import vn.edu.poly.testduan2.interfaces.DataBreadStatus;
import vn.edu.poly.testduan2.interfaces.DataFruitStatus;
import vn.edu.poly.testduan2.interfaces.DataMilkteaStatus;
import vn.edu.poly.testduan2.net.firebase.FirebaseManager;
import vn.edu.poly.testduan2.net.response.BreadFirebase;
import vn.edu.poly.testduan2.net.response.FruitFirebase;
import vn.edu.poly.testduan2.net.response.MilkTeaFirebase;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.sv_Search)
    EditText svSearch;
    @BindView(R.id.ll_click_search)
    LinearLayout llClickSearch;
    @BindView(R.id.nav_bar)
    RelativeLayout navBar;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tv_name_search_milk)
    TextView tvNameSearchMilk;
    @BindView(R.id.tv_n_search_milk1)
    TextView tvNSearchMilk1;
    @BindView(R.id.tv_size_milk)
    TextView tvSizeMilk;
    @BindView(R.id.tv_n_search_milk2)
    TextView tvNSearchMilk2;
    @BindView(R.id.ln_search_milk)
    LinearLayout lnSearchMilk;
    @BindView(R.id.tv_name_search_fruit)
    TextView tvNameSearchFruit;
    @BindView(R.id.tv_n_search_fruit1)
    TextView tvNSearchFruit1;
    @BindView(R.id.tv_size_fruit)
    TextView tvSizeFruit;
    @BindView(R.id.tv_n_search_fruit2)
    TextView tvNSearchFruit2;
    @BindView(R.id.ln_search_fruit)
    LinearLayout lnSearchFruit;
    @BindView(R.id.tv_name_search_bread)
    TextView tvNameSearchBread;
    @BindView(R.id.tv_n_search_bread1)
    TextView tvNSearchBread1;
    @BindView(R.id.tv_size_bread)
    TextView tvSizeBread;
    @BindView(R.id.tv_n_search_bread2)
    TextView tvNSearchBread2;
    @BindView(R.id.ln_search_bread)
    LinearLayout lnSearchBread;
    @BindView(R.id.ln_search)
    LinearLayout lnSearch;
    @BindView(R.id.rv_search)
    RecyclerView rvSearch;
    private List<MilkTeaFirebase> listmilk = new ArrayList<>();
    private List<FruitFirebase> listfruit = new ArrayList<>();
    private List<BreadFirebase> listbread = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initializeViews(View view, Bundle savedInstanceState) {
        lnSearch.setVisibility(View.GONE);
        search();
    }

    @OnClick({R.id.img_back, R.id.view, R.id.ln_search_milk, R.id.ln_search_fruit, R.id.ln_search_bread})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                trans.remove(new SearchFragment());
                trans.commit();
                manager.popBackStack();
                Utils.hideKeyboard(Objects.requireNonNull(getActivity()));
                break;
            case R.id.view:
                break;
            case R.id.ln_search_milk:

                tvNameSearchMilk.setTextColor(Color.parseColor("#3E9345"));
                tvSizeMilk.setTextColor(Color.parseColor("#3E9345"));
                tvNSearchMilk1.setTextColor(Color.parseColor("#3E9345"));
                tvNSearchMilk2.setTextColor(Color.parseColor("#3E9345"));

                tvNameSearchFruit.setTextColor(Color.parseColor("#000000"));
                tvSizeFruit.setTextColor(Color.parseColor("#000000"));
                tvNSearchFruit1.setTextColor(Color.parseColor("#000000"));
                tvNSearchFruit2.setTextColor(Color.parseColor("#000000"));

                tvNameSearchBread.setTextColor(Color.parseColor("#000000"));
                tvSizeBread.setTextColor(Color.parseColor("#000000"));
                tvNSearchBread1.setTextColor(Color.parseColor("#000000"));
                tvNSearchBread2.setTextColor(Color.parseColor("#000000"));

                break;
            case R.id.ln_search_fruit:

                tvNameSearchMilk.setTextColor(Color.parseColor("#000000"));
                tvSizeMilk.setTextColor(Color.parseColor("#000000"));
                tvNSearchMilk1.setTextColor(Color.parseColor("#000000"));
                tvNSearchMilk2.setTextColor(Color.parseColor("#000000"));

                tvNameSearchFruit.setTextColor(Color.parseColor("#3E9345"));
                tvSizeFruit.setTextColor(Color.parseColor("#3E9345"));
                tvNSearchFruit1.setTextColor(Color.parseColor("#3E9345"));
                tvNSearchFruit2.setTextColor(Color.parseColor("#3E9345"));

                tvNameSearchBread.setTextColor(Color.parseColor("#000000"));
                tvSizeBread.setTextColor(Color.parseColor("#000000"));
                tvNSearchBread1.setTextColor(Color.parseColor("#000000"));
                tvNSearchBread2.setTextColor(Color.parseColor("#000000"));

                break;
            case R.id.ln_search_bread:

                tvNameSearchMilk.setTextColor(Color.parseColor("#000000"));
                tvSizeMilk.setTextColor(Color.parseColor("#000000"));
                tvNSearchMilk1.setTextColor(Color.parseColor("#000000"));
                tvNSearchMilk2.setTextColor(Color.parseColor("#000000"));

                tvNameSearchFruit.setTextColor(Color.parseColor("#000000"));
                tvSizeFruit.setTextColor(Color.parseColor("#000000"));
                tvNSearchFruit1.setTextColor(Color.parseColor("#000000"));
                tvNSearchFruit2.setTextColor(Color.parseColor("#000000"));

                tvNameSearchBread.setTextColor(Color.parseColor("#3E9345"));
                tvSizeBread.setTextColor(Color.parseColor("#3E9345"));
                tvNSearchBread1.setTextColor(Color.parseColor("#3E9345"));
                tvNSearchBread2.setTextColor(Color.parseColor("#3E9345"));

                break;
        }
    }

    private void search() {
        svSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Timer timer = new Timer();
                String newText = svSearch.getText().toString();

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (!Utils.isEmpty(newText)) {
                            if (ConstactChange.listMilkTeaSearch != null && ConstactChange.listMilkTeaSearch.size() > 0){
                                for (int i = 0; i < ConstactChange.listMilkTeaSearch.size(); i++) {
                                    if (Utils.nonEmptyString(Utils.vnCharacterUtils(ConstactChange.listMilkTeaSearch.get(i).getName())).toLowerCase().
                                            contains(Utils.vnCharacterUtils(newText).toLowerCase())) {
                                        listmilk.add(ConstactChange.listMilkTeaSearch.get(i));
                                    }
                                }
                            }
                            if (ConstactChange.listFruitSearch != null && ConstactChange.listFruitSearch.size() > 0){
                                for (int i = 0; i < ConstactChange.listFruitSearch.size(); i++) {
                                    if (Utils.nonEmptyString(Utils.vnCharacterUtils(ConstactChange.listFruitSearch.get(i).getName())).toLowerCase().
                                            contains(Utils.vnCharacterUtils(newText).toLowerCase())) {
                                        listfruit.add(ConstactChange.listFruitSearch.get(i));
                                    }
                                }
                            }
                            if (ConstactChange.listBreadSearch != null && ConstactChange.listBreadSearch.size() > 0){
                                for (int i = 0; i < ConstactChange.listBreadSearch.size(); i++) {
                                    if (Utils.nonEmptyString(Utils.vnCharacterUtils(ConstactChange.listBreadSearch.get(i).getName())).toLowerCase().
                                            contains(Utils.vnCharacterUtils(newText).toLowerCase())) {
                                        listbread.add(ConstactChange.listBreadSearch.get(i));
                                    }
                                }
                            }
                        } else {
                            listmilk.clear();
                            listfruit.clear();
                            listbread.clear();
                            lnSearch.setVisibility(View.GONE);
                        }
                    }
                },100);

                if (listmilk.size() != 0 || listbread.size() != 0 || listfruit.size() != 0 ){
                    lnSearch.setVisibility(View.INVISIBLE);
                    tvSizeMilk.setText(listmilk.size());
                    tvSizeFruit.setText(listfruit.size());
                    tvSizeBread.setText(listbread.size());

                }else {
                    lnSearch.setVisibility(View.GONE);
                    listmilk.clear();
                    listfruit.clear();
                    listbread.clear();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

}
