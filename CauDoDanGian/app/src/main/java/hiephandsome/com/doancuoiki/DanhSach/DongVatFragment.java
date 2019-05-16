package hiephandsome.com.doancuoiki.DanhSach;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import hiephandsome.com.doancuoiki.MainActivity;
import hiephandsome.com.doancuoiki.R;
import hiephandsome.com.doancuoiki.Slide.ScreenSlideActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DongVatFragment extends Fragment {

    Button btnStart;
    public DongVatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       ((MainActivity) getActivity()).getSupportActionBar().setTitle("Câu đố dân gian về động vật");

        return inflater.inflate(R.layout.fragment_dong_vat, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btnStart=(Button) getActivity().findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), ScreenSlideActivity.class);
                startActivity(i);
            }
        });
    }
}
