package net.jspiner.edujoy.main.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.jspiner.edujoy.R;
import net.jspiner.edujoy.databinding.ViewDishBinding;

public class DishListAdapter extends PagerAdapter {

    private Context context;
    private String[] image_urls;

    public DishListAdapter(Context context, String[] image_urls) {
        this.context = context;
        this.image_urls = image_urls;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewDishBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.view_dish,
                container,
                false
        );

        Picasso.with(context)
                .load(R.drawable.food_kimbob)
                .into(binding.image);

        container.addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return image_urls.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
