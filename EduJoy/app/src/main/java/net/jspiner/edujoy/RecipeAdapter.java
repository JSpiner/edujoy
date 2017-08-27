package net.jspiner.edujoy;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import net.jspiner.edujoy.databinding.ViewCategoryBinding;
import net.jspiner.edujoy.databinding.ViewRecipeBinding;
import net.jspiner.edujoy.ingredients.FoodItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class RecipeAdapter extends BaseAdapter {

    Context context;
    ArrayList<FoodItem> calList;

    public RecipeAdapter(Context context, ArrayList<FoodItem> calList) {
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
        ViewRecipeBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.view_recipe,
                parent,
                false
        );

        Picasso.with(context)
                .load(calList.get(position).filePath)
                .resize(1000,1000)
                .centerCrop()
                .into(binding.image);

        binding.name.setText(calList.get(position).name);
        return binding.getRoot();
    }
}
