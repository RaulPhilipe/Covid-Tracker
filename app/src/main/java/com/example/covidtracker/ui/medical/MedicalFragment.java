package com.example.covidtracker.ui.medical;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.covidtracker.R;

public class MedicalFragment extends Fragment {




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_medical, container, false);

        Button button = (Button)root.findViewById(R.id.MoreButton1);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                switch(v.getId()){

                    case R.id.MoreButton1:
                        Intent intent1 = new Intent(getActivity(), MoreAboutCovid.class);
                        startActivity(intent1);//Edited here
                        break;


                }
            }
        });

        TextView textLinks1 =(TextView)root.findViewById(R.id.text_content);
        textLinks1.setMovementMethod(LinkMovementMethod.getInstance());
        TextView textLinks2 =(TextView)root.findViewById(R.id.text_content2);
        textLinks2.setMovementMethod(LinkMovementMethod.getInstance());

        return root;

    }



}