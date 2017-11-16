package com.imk7.welearn.welearn;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment implements View.OnClickListener{

    public MainMenuFragment() {
        // Required empty public constructor
    }

    Button btnmodul1,btnmodul2,btnmodul3,btnmodul4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        btnmodul1 = view.findViewById(R.id.btn_start_1);
        btnmodul2 = view.findViewById(R.id.btn_start_2);
        btnmodul3 = view.findViewById(R.id.btn_start_3);
        btnmodul4 = view.findViewById(R.id.btn_start_4);

        btnmodul1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Modul1Activity.class);
                startActivity(intent);
            }
        });
        btnmodul2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Modul2Activity.class);
                startActivity(intent);
            }
        });
        btnmodul3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Modul3Activity.class);
                startActivity(intent);
            }
        });
        btnmodul4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Modul4Activity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
//        Intent intent;
//        switch (view.getId()) {
//            case R.id.btn_start_1 :
//                intent = new Intent(getActivity(),Modul1Activity.class);
//                startActivity(intent);
//            case R.id.btn_start_2 :
//                intent = new Intent(getActivity(),Modul2Activity.class);
//                startActivity(intent);
//            case R.id.btn_start_3 :
//                intent = new Intent(getActivity(),Modul3Activity.class);
//                startActivity(intent);
//            case R.id.btn_start_4 :
//                intent = new Intent(getActivity(),Modul4Activity.class);
//                startActivity(intent);
//        }
    }

}
