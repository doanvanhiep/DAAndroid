package hiephandsome.com.doancuoiki.DanhSach;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hiephandsome.com.doancuoiki.MainActivity;
import hiephandsome.com.doancuoiki.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NgayTetFragment extends Fragment {


    public NgayTetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Câu đố dân gian về ngày tết");
        return inflater.inflate(R.layout.fragment_ngay_tet, container, false);
    }

}
