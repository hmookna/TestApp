package com.example.kanchapi.testapp.OneFragment;
import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.kanchapi.testapp.R;

import java.util.ArrayList;

/**
 * Created by kanchapi on 27/10/2559.
 */
public class OneFragment extends Fragment implements View.OnClickListener {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public String text;
    static FragmentManager fragMan;
    private Button button1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button1:
                // it was the first button
                RelativeLayout layout = (RelativeLayout)v.getParent();
                ImageView image = new ImageView(getContext());
                image.setLayoutParams(new ViewGroup.LayoutParams(160,160));
                image.setImageDrawable(getResources().getDrawable(R.drawable.shape));
                image.setOnTouchListener(new MyTouchListener());
                layout.setOnDragListener(new MyDragListener(layout));
                layout.addView(image);
                break;

        }
    }


    private class MyTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View arg0, MotionEvent arg1) {
            if(arg1.getAction() == MotionEvent.ACTION_DOWN){
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(arg0);
                arg0.startDrag(data, shadowBuilder, arg0, 0);
                arg0.setVisibility(View.INVISIBLE);
            }
            return true;
        }
    }


}