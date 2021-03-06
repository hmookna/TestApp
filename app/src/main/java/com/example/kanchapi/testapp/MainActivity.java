package com.example.kanchapi.testapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.kanchapi.testapp.DragActivityV2.DragActivityV2;
import com.example.kanchapi.testapp.OneFragment.OneFragment;
import com.example.kanchapi.testapp.OneFragment.RecyclerGridFragment;
import com.example.kanchapi.testapp.TreeActivity.TreeActivity;
import com.example.kanchapi.testapp.TwoFragment.TwoFragment;

/**
 * Created by kanchapi on 27/10/2559.
 */
public class MainActivity extends AppCompatActivity implements MainFragment.OnListItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            MainFragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, fragment)
                    .commit();
        }
    }

    @Override
    public void onListItemClick(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new OneFragment();
        //        fragment = new RecyclerGridFragment();
                break;
            case 1:
                fragment = new TwoFragment();
                break;
            case 2:
//                fragment = new TwoFragment();
                Intent intent = new Intent(this, TreeActivity.class);
                startActivity(intent);
                break;

            case 3:
                break;

        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

}