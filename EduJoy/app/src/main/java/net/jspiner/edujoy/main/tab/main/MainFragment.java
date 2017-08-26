package net.jspiner.edujoy.main.tab.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.jspiner.edujoy.R;
import net.jspiner.edujoy.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    FragmentMainBinding binding;

    public MainFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_main,
                container,
                false
        );

        return binding.getRoot();
    }
}
