package net.jspiner.edujoy.ingredients;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import net.jspiner.edujoy.R;
import net.jspiner.edujoy.databinding.CardFoodBinding;
import net.jspiner.edujoy.databinding.ViewCategoryBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class SelectedItemsAdapter extends BaseAdapter {

    Context context;
    ArrayList<FoodItem> calList;

    public SelectedItemsAdapter(Context context, ArrayList<FoodItem> calList) {
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
        CardFoodBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.card_food,
                parent,
                false
        );


        Log.d("image", "pathj : " + calList.get(position).filePath);
        Picasso.with(context)
                .load(new File(calList.get(position).filePath))
                .resize(1000,1000)
                .centerCrop()
                .into(binding.image);
        binding.name.setText(calList.get(position).name);
        binding.kcal.setText(calList.get(position).kcal + " kcal");


        return binding.getRoot();
    }

    public void addItem(FoodItem foodItem){
        calList.add(foodItem);
        notifyDataSetChanged();
    }

}
