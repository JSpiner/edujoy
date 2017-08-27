package net.jspiner.edujoy.main;

import android.Manifest;
import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.jspiner.edujoy.R;
import net.jspiner.edujoy.databinding.ActivityMainBinding;
import net.jspiner.edujoy.main.adapter.MainPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {

        adapter = new MainPagerAdapter(getSupportFragmentManager());
        binding.pager.setAdapter(adapter);

        binding.tabLayout.setupWithViewPager(binding.pager);

        requestPermissions(
                new String[] {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                },
                1
        );
    }
}
