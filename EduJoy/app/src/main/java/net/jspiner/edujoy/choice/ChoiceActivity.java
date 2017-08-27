package net.jspiner.edujoy.choice;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.squareup.picasso.Picasso;

import net.jspiner.edujoy.R;
import net.jspiner.edujoy.databinding.ActivityChoiceBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class ChoiceActivity extends AppCompatActivity {

    ActivityChoiceBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_choice);

        init();
    }


    private void init(){

        final CategoryPagerAdapter adapter = new CategoryPagerAdapter(this, new String[] {"dd","dd","dd","dd","dd","dd","dd"});
        binding.pager.setAdapter(adapter);
        binding.pager.setClipToPadding(false);
        binding.pager.setPadding(600, 0, 600, 0);
        binding.pager.setPageMargin(20);
        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i=0;i<adapter.getCount();i++){
                    setBackgroundResource(binding.pager.findViewWithTag("view" + i), R.drawable.corner_empty);
                }
                setBackgroundResource(binding.pager.findViewWithTag("view" + position), R.drawable.corner);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.pager.setCurrentItem(0);

        final String path = getIntent().getStringExtra("filepath");

        Picasso.with(this)
                .load(new File(path))
                .into(binding.image);

        String responseString = getIntent().getStringExtra("response");
        JSONObject array = null;
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            array = jsonObject.getJSONObject("0");

            for(int i=0;i<array.length() - 1;i++){
                arrayList.add(array.get(String.valueOf(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final DetailCategoryAdapter detailCategoryAdapter = new DetailCategoryAdapter(this, arrayList);
        binding.recyclerView.setAdapter(detailCategoryAdapter);
        binding.recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                JSONObject jsonObject = (JSONObject) detailCategoryAdapter.getItem(i);
                try {

                    Object kcal = jsonObject.get("cal");
                    if(kcal instanceof Double){
                        kcal = ((Double) kcal).intValue();
                    }

                    intent.putExtra("name", jsonObject.get("name").toString());
                    intent.putExtra("kcal", (int)kcal);
                    intent.putExtra("filepath", path);
                } catch (JSONException e) {
                    e.printStackTrace();
                };


                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void setBackgroundResource(View view, int resourceId){
        if(view == null) return;
        View insideView = view.findViewById(R.id.container);
        if(insideView == null) return;;
        insideView.setBackgroundResource(resourceId);
    }

}
