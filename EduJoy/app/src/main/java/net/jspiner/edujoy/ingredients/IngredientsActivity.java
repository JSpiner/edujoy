package net.jspiner.edujoy.ingredients;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.esafirm.imagepicker.features.ImagePicker;

import net.jspiner.edujoy.R;
import net.jspiner.edujoy.choice.ChoiceActivity;
import net.jspiner.edujoy.databinding.ActivityIngredientsBinding;

public class IngredientsActivity extends AppCompatActivity {

    ActivityIngredientsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ingredients);

        init();
    }

    private void init(){
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.create(IngredientsActivity.this)
                        .single()
                        .theme(R.style.ImagePickerTheme)
                        .start(1001);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 1001){
                Intent intent = new Intent(this, ChoiceActivity.class);
                startActivity(intent);
            }
        }
    }
}
