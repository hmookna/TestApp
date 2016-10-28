package com.example.kanchapi.testapp.OneFragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kanchapi.testapp.R;
import com.example.kanchapi.testapp.TestClass;

import java.util.ArrayList;

/**
 * Created by admin on 9/19/16 AD.
 */
class RecyclerItem extends RecyclerView.Adapter<RecyclerItem.ItemViewHolder>{

    public final FragmentActivity mContext;
    private final ArrayList<TestClass> mDataItem;

    public RecyclerItem(Context mContext, ArrayList<TestClass> mDataItem ) {

        this.mContext = (FragmentActivity) mContext;
        this.mDataItem = mDataItem;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        ItemViewHolder item = new ItemViewHolder(v);
        return item;
    }


    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        holder.text.setText(mDataItem.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mDataItem.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final View Item;
        private final ImageView icon;
        private final TextView text;

        public ItemViewHolder(View itemView) {
            super(itemView);
            Item = itemView;
            text = (TextView) itemView.findViewById(R.id.text);
            icon = (ImageView) itemView.findViewById(R.id.handle);

        }
    }

}

