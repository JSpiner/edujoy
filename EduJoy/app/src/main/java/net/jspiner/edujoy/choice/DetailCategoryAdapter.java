package net.jspiner.edujoy.choice;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.jspiner.edujoy.R;
import net.jspiner.edujoy.databinding.ViewCategoryBinding;
import net.jspiner.edujoy.databinding.ViewLargeTypeBinding;

import java.util.ArrayList;

public class DetailCategoryAdapter extends BaseAdapter {

    Context context;
    ArrayList<Object> calList;

    public DetailCategoryAdapter(Context context, ArrayList<Object> calList) {
        this.context = context;
        this.calList = calList;
    }

    @Override
    public int getCount() {
        return calList.size();
    }

    @Override
    public Object getItem(int position) {
        return calList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewCategoryBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.view_category,
                parent,
                false
        );

        return binding.getRoot();
    }

}
