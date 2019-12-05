package vn.edu.poly.testduan2.common;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import vn.edu.poly.testduan2.R;

public class TabItemFragment extends Fragment {

    Fragment child;

    public TabItemFragment() {
    }

    public TabItemFragment(Fragment child) {
        this.child = child;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.tab_item_fragment, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (child != null) {
            getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainer, child).commit();
        }
    }
}
