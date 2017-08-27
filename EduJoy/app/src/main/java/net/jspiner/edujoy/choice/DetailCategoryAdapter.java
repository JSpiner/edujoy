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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailCategoryAdapter extends BaseAdapter {

    Context context;
    ArrayList<JSONObject> calList;

    public DetailCategoryAdapter(Context context, ArrayList<JSONObject> calList) {
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

        try {
            binding.title.setText(calList.get(position).get("name").toString());
            Object kcal = calList.get(position).get("cal");
            if(kcal instanceof Double){
                kcal = ((Double) kcal).intValue();
            }
            binding.kcal.setText(kcal + "kcal");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return binding.getRoot();
    }

}
