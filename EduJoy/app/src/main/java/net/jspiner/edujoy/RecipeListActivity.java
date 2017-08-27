package net.jspiner.edujoy;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import net.jspiner.edujoy.databinding.ActivityRecipeListBinding;
import net.jspiner.edujoy.ingredients.FoodItem;

import java.util.ArrayList;

public class RecipeListActivity extends AppCompatActivity {

    ActivityRecipeListBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_list);

        init();
    }

    void init(){
        ArrayList arrayList = new ArrayList();
        arrayList.add(new FoodItem("http://mblogthumb3.phinf.naver.net/20150628_74/cider99_1435419455892ToOnB_JPEG/003.jpg?type=w2","kimchi stew",0));
        arrayList.add(new FoodItem("http://cfile27.uf.tistory.com/image/2435564C55992D0A17161A","bulgogi",0));
        arrayList.add(new FoodItem("http://www.alltokk.co.kr/images/menu/photo1B.jpg","Tteokbokki",0));
        arrayList.add(new FoodItem("http://chedaol.com/images/sub/gimbap9.png","gimbob",0));
        arrayList.add(new FoodItem("http://cfile8.uf.tistory.com/image/1545A834502B511913452B","omelet",0));
        arrayList.add(new FoodItem("http://mblogthumb3.phinf.naver.net/20150628_74/cider99_1435419455892ToOnB_JPEG/003.jpg?type=w2","kimchi stew",0));
        arrayList.add(new FoodItem("http://cfile27.uf.tistory.com/image/2435564C55992D0A17161A","bulgogi",0));
        arrayList.add(new FoodItem("http://www.alltokk.co.kr/images/menu/photo1B.jpg","Tteokbokki",0));
        arrayList.add(new FoodItem("http://chedaol.com/images/sub/gimbap9.png","gimbob",0));
        arrayList.add(new FoodItem("http://cfile8.uf.tistory.com/image/1545A834502B511913452B","omelet",0));
        arrayList.add(new FoodItem("http://mblogthumb3.phinf.naver.net/20150628_74/cider99_1435419455892ToOnB_JPEG/003.jpg?type=w2","kimchi stew",0));
        arrayList.add(new FoodItem("http://cfile27.uf.tistory.com/image/2435564C55992D0A17161A","bulgogi",0));
        arrayList.add(new FoodItem("http://www.alltokk.co.kr/images/menu/photo1B.jpg","Tteokbokki",0));
        arrayList.add(new FoodItem("http://chedaol.com/images/sub/gimbap9.png","gimbob",0));
        arrayList.add(new FoodItem("http://cfile8.uf.tistory.com/image/1545A834502B511913452B","omelet",0));


        RecipeAdapter adapter = new RecipeAdapter(this, arrayList);
        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent= new Intent(RecipeListActivity.this, RecipeActivity.class);
                startActivity(intent);
            }

        });
    }
}
