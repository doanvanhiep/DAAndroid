package hiephandsome.com.doancuoiki.Slide;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import hiephandsome.com.doancuoiki.CauHoi.BoCauHoi;
import hiephandsome.com.doancuoiki.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {
    ArrayList<BoCauHoi> boCauHoiArrayList ; //Danh sách câu hỏi
    public static final String PageNumber="PageNumber";
    private int mPageNumber;//Vị trí trang hiện tại

    TextView tvNum,tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA,radB,radC,radD;

    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        tvNum=(TextView)rootView.findViewById(R.id.tvNum);
        tvQuestion=(TextView)rootView.findViewById(R.id.tvQuestion);
        radA=(RadioButton)rootView.findViewById(R.id.radA);
        radB=(RadioButton)rootView.findViewById(R.id.radB);
        radC=(RadioButton)rootView.findViewById(R.id.radC);
        radD=(RadioButton)rootView.findViewById(R.id.radD);
        radioGroup=(RadioGroup)rootView.findViewById(R.id.radGroup);
        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boCauHoiArrayList=new ArrayList<BoCauHoi>();
        ScreenSlideActivity screenSlideActivity= (ScreenSlideActivity) getActivity();

            boCauHoiArrayList=screenSlideActivity.getBoCauHoiArrayList();


        mPageNumber= getArguments().getInt(PageNumber);
    }

    //Hàm tạo để lấy được pagenumber
    public static ScreenSlidePageFragment create(int pageNumber)
    {
        ScreenSlidePageFragment screenSlidePageFragment=new ScreenSlidePageFragment();
        Bundle bundle=new Bundle();
        bundle.putInt(PageNumber,pageNumber);
        screenSlidePageFragment.setArguments(bundle);
        return screenSlidePageFragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //set các giá trị của các trường trong fragment slide

      if(mPageNumber>=boCauHoiArrayList.size())
      {
          mPageNumber=boCauHoiArrayList.size()-1;
      }
        tvNum.setText("Câu " +(mPageNumber+1));
        tvQuestion.setText(boCauHoiArrayList.get(mPageNumber).getCauHoi());
        radA.setText(boCauHoiArrayList.get(mPageNumber).getDapAnA());
        radB.setText(boCauHoiArrayList.get(mPageNumber).getDapAnB());
        radC.setText(boCauHoiArrayList.get(mPageNumber).getDapAnC());
        radD.setText(boCauHoiArrayList.get(mPageNumber).getDapAnD());
        final String DapAn=boCauHoiArrayList.get(mPageNumber).getCauTraLoi();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int da=checkedId%10; //Lấy số cuối của id để xác nhận đang click vào radio button nào
                //
                int a=5-1;int b=6-1;int c=7-1;int d=8-1;
                // 5:A - 6:B - 7:C  - 8:D
//                Toast.makeText(getActivity(),""+checkedId, Toast.LENGTH_SHORT).show();
               if(DapAn.equals("A"))
               {
                   if(da==a)
                   {
                       radA.setBackgroundColor(Color.GREEN);
                   }
                   else{
                       radA.setBackgroundColor(Color.RED);
                   }
               }else{
                   if(DapAn.equals("B")){
                       if(da==b)
                       {
                           radB.setBackgroundColor(Color.GREEN);
                       }
                       else{
                           radB.setBackgroundColor(Color.RED);
                       }
                   }else{
                       if(DapAn.equals("C"))
                       {
                           if(da==c)
                           {
                               radC.setBackgroundColor(Color.GREEN);
                           }
                           else{
                               radC.setBackgroundColor(Color.RED);
                           }
                       }else{
                           if(da==d)
                           {
                               radD.setBackgroundColor(Color.GREEN);
                           }
                           else{
                               radD.setBackgroundColor(Color.RED);
                           }
                       }
                   }
               }
            }
        });
    }
}
