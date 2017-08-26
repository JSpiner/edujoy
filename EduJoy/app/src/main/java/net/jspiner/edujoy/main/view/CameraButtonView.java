package net.jspiner.edujoy.main.view;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.squareup.picasso.Picasso;

import net.jspiner.edujoy.R;
import net.jspiner.edujoy.databinding.ViewCameraBinding;
import net.jspiner.edujoy.databinding.ViewDishListBinding;

public class CameraButtonView extends FrameLayout {

    private ViewCameraBinding binding;

    public CameraButtonView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public CameraButtonView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        binding = DataBindingUtil.inflate(inflater, R.layout.view_camera, this, true);

        Picasso.with(context)
                .load(R.drawable.icon_camera)
                .resize(500,500)
                .into(binding.camera);
    }

}
