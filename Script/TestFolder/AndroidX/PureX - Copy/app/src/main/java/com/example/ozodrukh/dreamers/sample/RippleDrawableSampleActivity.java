package com.example.ozodrukh.dreamers.sample;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.lzsoft.lzdata.mobile.purex.R;
import com.ozodrukh.codetail.graphics.drawables.DrawableHotspotTouch;
//import com.ozodrukh.codetail.graphics.drawables.LollipopDrawable;

import com.ozodrukh.codetail.graphics.drawables.LollipopDrawable;
import com.ozodrukh.codetail.graphics.drawables.LollipopDrawablesCompat;

public class RippleDrawableSampleActivity extends AppCompatActivity {

    private ListView mListView;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ozodrukh_rippledrawable_sample);

        mListView = (ListView) findViewById(R.id.list);
        mListView.setSelector(getDrawable2(R.drawable.ozodrukh_rippledrawable_list_selector));
        mListView.setAdapter(new ListAdapter());

        mActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mActionButton.setBackgroundDrawable(getDrawable2(R.drawable.fab_background));
        mActionButton.setClickable(true);
        mActionButton.setOnTouchListener(
                new DrawableHotspotTouch((RippleDrawable) mActionButton.getBackground()));
    }

    /**
     * {@link #getDrawable(int)} is already taken by Android API
     * and method is final, so we need to give another name :(
     */
    public Drawable getDrawable2(int id){
        return LollipopDrawablesCompat.getDrawable(getResources(), id, getTheme());
    }

    static class ListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ozodrukh_rippledrawable_sample_item, parent, false);
            }

            return convertView;
        }
    }

}