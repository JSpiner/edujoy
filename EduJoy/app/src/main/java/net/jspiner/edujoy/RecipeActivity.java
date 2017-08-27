package net.jspiner.edujoy;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import net.jspiner.edujoy.databinding.ActivityRecipeBinding;

public class RecipeActivity extends AppCompatActivity {

    ActivityRecipeBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe);

        Picasso.with(this)
                .load(R.drawable.jjj)
                .into(binding.image);
    }
}
