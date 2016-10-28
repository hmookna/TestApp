package com.example.kanchapi.testapp.OneFragment;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kanchapi.testapp.R;
import com.example.kanchapi.testapp.TestClass;

import java.util.ArrayList;

/**
 * Created by kanchapi on 27/10/2559.
 */
public class OneFragment extends Fragment implements View.OnClickListener {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public String text;
    static FragmentManager fragMan;
    private Button button1;
    private ArrayList<TestClass>  mDataIcon;
    private RelativeLayout wapper_top,wapper_bottom;
    private ImageView myimage1;
    private static final String IMAGEVIEW_TAG = "The Android Logo";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDataIcon = new ArrayList<TestClass>();
        prepareIcon();
        RecyclerItem adapter = new RecyclerItem(getContext(), mDataIcon);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleritem);;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        final int spanCount = getResources().getInteger(R.integer.grid_columns);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
        recyclerView.setLayoutManager(layoutManager);

        myimage1 = (ImageView) view.findViewById(R.id.myimage1);
        myimage1.setTag(IMAGEVIEW_TAG);

        // set the listener to the dragging data
        myimage1.setOnLongClickListener(new MyClickListener());
        //myimage1.setOnTouchListener(new MyTouchListener());
        wapper_top = (RelativeLayout) view.findViewById(R.id.wapper_icon);
        wapper_top.setOnDragListener(new MyDragListener());
        wapper_bottom = (RelativeLayout) view.findViewById(R.id.wapper_bottom);
        wapper_bottom.setOnDragListener(new MyDragListener());

    }


    public void onClick(View v) {
/*        switch(v.getId()) {
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

        }*/
    }


    private class MyTouchListener implements View.OnTouchListener {

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);

                return true;
            } else {
                return false;
            }
        }
    }

    private final class MyClickListener implements View.OnLongClickListener {

        // called when the item is long-clicked
        @Override
        public boolean onLongClick(View view) {
            // TODO Auto-generated method stub

            // create it from the object's tag
            ClipData.Item item = new ClipData.Item((CharSequence)view.getTag());

            String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
            ClipData data = new ClipData(view.getTag().toString(), mimeTypes, item);
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

            view.startDrag( data, //data to be dragged
                    shadowBuilder, //drag shadow
                    view, //local data about the drag and drop operation
                    0   //no needed flags
            );


            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    class MyDragListener implements View.OnDragListener {
        int x;
        int y;
        @Override
        public boolean onDrag(View v, DragEvent event) {

            // Handles each of the expected events
            switch (event.getAction()) {

                //signal for the start of a drag and drop operation.
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;

                //the drag point has entered the bounding box of the View
                case DragEvent.ACTION_DRAG_ENTERED:
//                    v.setBackground(targetShape);	//change the shape of the view
//                    break;

                //the user has moved the drag shadow outside the bounding box of the View
                case DragEvent.ACTION_DRAG_EXITED:
//                    v.setBackground(normalShape);	//change the shape of the view back to normal
//                    break;

                //drag shadow has been released,the drag point is within the bounding box of the View
                case DragEvent.ACTION_DROP:
                    // if the view is the bottomlinear, we accept the drag item
                    if(v == wapper_bottom) {
                        x = (int)event.getX();
                        y = (int)event.getY();
                        Log.d("DRAG", x + "," + y);

                        View view = (View) event.getLocalState();
                        ViewGroup viewgroup = (ViewGroup) view.getParent();
                        viewgroup.removeView(view);

                        RelativeLayout containView = (RelativeLayout) v;
                        containView.addView(view);
                        view.setX(x-(view.getWidth()/2));
                        view.setY(y-(view.getHeight()/2));
                        view.setVisibility(View.VISIBLE);
                    } else {
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        Context context = getContext();
                        Toast.makeText(context, "You can't drop the image here",
                                Toast.LENGTH_LONG).show();
                        break;
                    }
                    break;

                //the drag and drop operation has concluded.
                case DragEvent.ACTION_DRAG_ENDED:
//                    v.setBackground(normalShape);	//go back to normal shape

                default:
                    break;
            }
            return true;
        }
    }

    private void prepareIcon() {
        int[] covers = new int[]{
                R.drawable.ic_emergency,
                R.drawable.ic_emergency,
                R.drawable.ic_emergency,
                R.drawable.ic_emergency,
                R.drawable.ic_emergency,
                R.drawable.ic_emergency,
                R.drawable.ic_emergency,
                R.drawable.ic_emergency,
                R.drawable.ic_emergency};

        TestClass a = new TestClass("Camera", ""+covers[0]);
        mDataIcon.add(a);

        a = new TestClass("Camera", ""+covers[1]);
        mDataIcon.add(a);

        a = new TestClass("Camera", ""+covers[2]);
        mDataIcon.add(a);

        a = new TestClass("Camera", ""+covers[3]);
        mDataIcon.add(a);

        a = new TestClass("Camera", ""+covers[4]);
        mDataIcon.add(a);

        a = new TestClass("Camera", ""+covers[5]);
        mDataIcon.add(a);

        a = new TestClass("Camera", ""+covers[6]);
        mDataIcon.add(a);

        a = new TestClass("Camera", ""+covers[7]);
        mDataIcon.add(a);

        a = new TestClass("Camera", ""+covers[8]);
        mDataIcon.add(a);
    }

}