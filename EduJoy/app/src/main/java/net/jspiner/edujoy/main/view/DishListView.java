package net.jspiner.edujoy.main.view;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import net.jspiner.edujoy.R;
import net.jspiner.edujoy.databinding.ViewDishListBinding;

public class DishListView extends FrameLayout {

    private ViewDishListBinding binding;

    public DishListView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public DishListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        binding = DataBindingUtil.inflate(inflater, R.layout.view_dish_list, this, true);

        DishListAdapter adapter = new DishListAdapter(getContext(), new String[]{"d","d","d"});
        binding.pager.setAdapter(adapter);
        binding.pager.setClipToPadding(false);
        binding.pager.setPadding(120, 0, 150, 0);
        binding.pager.setPageMargin(75);
    }

    public void setTitleType(int type){
        if(type == 1){
            binding.title.setText("Recent Recipe");
        }
        else{
            binding.title.setText("Recommend for you");
        }
    }

}
