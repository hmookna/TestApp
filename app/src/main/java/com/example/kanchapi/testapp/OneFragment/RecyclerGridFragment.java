package com.example.kanchapi.testapp.OneFragment;

import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.kanchapi.testapp.OneFragment.helper.OnStartDragListener;
import com.example.kanchapi.testapp.OneFragment.helper.SimpleItemTouchHelperCallback;
import com.example.kanchapi.testapp.R;

/**
 * @author Paul Burke (ipaulpro)
 */
public class RecyclerGridFragment extends Fragment implements OnStartDragListener,View.OnClickListener {

    private ItemTouchHelper mItemTouchHelper;
    private Button button1;

    public RecyclerGridFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return new RecyclerView(container.getContext());
        View v = inflater.inflate(R.layout.fragment_one, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerListAdapter adapter = new RecyclerListAdapter(getActivity(), this);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycleritem);;
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        final int spanCount = getResources().getInteger(R.integer.grid_columns);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), spanCount);
        recyclerView.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    public void onClick(View v) {
        /*switch(v.getId()) {
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
}