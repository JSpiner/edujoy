package net.jspiner.edujoy.choice;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.jspiner.edujoy.R;
import net.jspiner.edujoy.databinding.ActivityChoiceBinding;

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

        ArrayList arrayList = new ArrayList();
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        DetailCategoryAdapter detailCategoryAdapter = new DetailCategoryAdapter(this, arrayList);
        binding.recyclerView.setAdapter(detailCategoryAdapter);
    }

    private void setBackgroundResource(View view, int resourceId){
        if(view == null) return;
        View insideView = view.findViewById(R.id.container);
        if(insideView == null) return;;
        insideView.setBackgroundResource(resourceId);
    }

}
