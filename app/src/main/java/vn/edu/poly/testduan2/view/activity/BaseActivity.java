package vn.edu.poly.testduan2.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.edu.poly.testduan2.R;

public abstract class BaseActivity extends AppCompatActivity {

    public enum NavigationStyle {
        Default,
        Backable,
        Centered,
        None
    }

    @BindView(R.id.nav_bar)
    RelativeLayout navBar;
    @BindView(R.id.nav_left_button)
    ImageButton navLeftButton;
    @BindView(R.id.nav_title)
    TextView navTitle;
    @BindView(R.id.nav_right_button)
    ImageButton navRightButton;

    protected abstract int getActivityLayoutId();

    protected abstract void initialize(@Nullable Bundle savedInstanceState);

    protected void initBeforeSetContent(@Nullable Bundle savedInstanceState) {
    }

    Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBeforeSetContent(savedInstanceState);
        View view = View.inflate(this, R.layout.base, null);
        View navContainer = view.findViewById(R.id.nav_container);
        LayoutInflater.from(this).inflate(getActivityLayoutId(), (ViewGroup) navContainer, true);
        setContentView(view);
        bind = ButterKnife.bind(this);
        updateNavigationBar();
        initialize(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bind != null) bind.unbind();
    }

    @SuppressLint("NewApi")
    protected final void updateNavigationBar() {

        navTitle.setText(getNavigationTitle());
        navLeftButton.setBackground(null);
        navRightButton.setBackground(null);
        navBar.setVisibility(View.VISIBLE);

        NavigationStyle style = getNavigationStyle();
        switch (style) {
            case Backable:
                navLeftButton.setImageResource(R.drawable.ic_chevron_left_black_24dp);
                navLeftButton.setOnClickListener(v -> backButtonClicked(v));
                navRightButton.setVisibility(View.VISIBLE);
                navRightButton.setOnClickListener(v -> rightButtonClicked(v));
                navRightButton.setImageIcon(getRightButtonIcon());
                navTitle.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                break;
            case Centered:
                navLeftButton.setVisibility(View.VISIBLE);
                navLeftButton.setOnClickListener(v -> leftButtonClicked(v));
                navLeftButton.setImageIcon(getLeftButtonIcon());
                navRightButton.setVisibility(View.VISIBLE);
                navRightButton.setOnClickListener(v -> rightButtonClicked(v));
                navRightButton.setImageIcon(getRightButtonIcon());
                navTitle.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                break;
            case None:
                navBar.setVisibility(View.GONE);
                break;
            default:
                navLeftButton.setVisibility(View.GONE);
                navRightButton.setVisibility(View.GONE);
                navTitle.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
                break;
        }
    }

    //region Overridable by subclasses

    protected void leftButtonClicked(View v) {
        finish();
    }

    protected void rightButtonClicked(View v) {
    }

    protected String getNavigationTitle() {
        return null;
    }


    protected void backButtonClicked(View v) {
        finish();
    }

    protected NavigationStyle getNavigationStyle() {
        return NavigationStyle.Backable;
    }

    @SuppressLint("NewApi")
    protected Icon getLeftButtonIcon() {
        return Icon.createWithResource(this, R.drawable.ic_close_whith_24dp);
    }

    protected Icon getRightButtonIcon() {
        return null;
    }

    //endregion
}
