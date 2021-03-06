package layout;


import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

import sg.edu.rp.webservices.knowyourfactsp_10.R;

import static sg.edu.rp.webservices.knowyourfactsp_10.R.id.buttonChangeColour;
import static sg.edu.rp.webservices.knowyourfactsp_10.R.id.linear;

/**
 * A simple {@link Fragment} subclass.
 */
public class Frag2 extends Fragment {

    Button btnChangeColour ;
    LinearLayout linear1;
     Random color = new Random();
    final Paint p = new Paint();
    public Frag2() {
        // Required empty public constructor
    }



    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        int colorsaved = prefs.getInt("colorrr",p.getColor());
        linear1.setBackgroundColor(colorsaved);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag2, container, false);
        btnChangeColour = (Button) view.findViewById(buttonChangeColour);
        linear1 = (LinearLayout)view.findViewById(linear);
        btnChangeColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color=new Random();
                p.setARGB(255,color.nextInt(255),color.nextInt(255),color.nextInt(255));
                linear1.setBackgroundColor((p.getColor()));
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putInt("colorrr", p.getColor());
                prefEdit.commit();

            }
        });


        return view;
    }

}
