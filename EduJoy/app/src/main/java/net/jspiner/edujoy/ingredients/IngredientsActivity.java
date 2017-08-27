package net.jspiner.edujoy.ingredients;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.esafirm.imagepicker.features.ImagePicker;

import net.jspiner.edujoy.Network;
import net.jspiner.edujoy.R;
import net.jspiner.edujoy.RecipeListActivity;
import net.jspiner.edujoy.choice.ChoiceActivity;
import net.jspiner.edujoy.databinding.ActivityIngredientsBinding;
import net.jspiner.edujoy.main.view.DishListAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngredientsActivity extends AppCompatActivity {

    Uri cameraCapturedImageUri;
    ActivityIngredientsBinding binding;
    SelectedItemsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ingredients);

        init();
    }

    private void init() {
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ImagePicker.create(IngredientsActivity.this)
                        .single()
                        .start(1001);
            }
        });

        adapter = new SelectedItemsAdapter(this, new ArrayList<FoodItem>());
        binding.listview.setAdapter(adapter);
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IngredientsActivity.this, RecipeListActivity.class);
                startActivity(intent);
            }
        });

/*
        adapter.addItem(new FoodItem("/storage/emulated/0/Pictures/Camera/IMG_20170827_0504181525406066.jpg", "Egg", 123));
        adapter.addItem(new FoodItem("/storage/emulated/0/Pictures/Camera/IMG_20170827_0504181525406066.jpg", "Egg", 123));
        adapter.addItem(new FoodItem("/storage/emulated/0/Pictures/Camera/IMG_20170827_0504181525406066.jpg", "Egg", 123));
        adapter.addItem(new FoodItem("/storage/emulated/0/Pictures/Camera/IMG_20170827_0504181525406066.jpg", "Egg", 123));
*/    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1001) {

                final String path = ImagePicker.getImages(data).get(0).getPath();
                Log.d("path","path : " + path);
                final File imageFile = new File(
                        path
                );

                RequestBody requestBody = null;
                try {
                    requestBody = RequestBody.create(
                            MediaType.parse("multipart/form-data"),
                            getCompressedBitmapBytes(
                                    imageFile
                            )

                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }

                MultipartBody.Part part =
                        MultipartBody.Part.createFormData(
                                "media",
                                imageFile.getName(),
                                requestBody
                        );

                Network.getService().sendFoodImage(part).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("onResponse", "call response : " + response.body());

                        Intent intent = new Intent(IngredientsActivity.this, ChoiceActivity.class);
                        intent.putExtra("response", response.body());
                        intent.putExtra("filepath", path);
                        startActivityForResult(intent, 1002);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("onFilaure", "call response : " + t.getMessage());
                    }
                });
            }
            else if(requestCode == 1002){
                String filePath = data.getStringExtra("filepath");
                String name = data.getStringExtra("name");
                int kcal = data.getIntExtra("kcal", 0);

                FoodItem item = new FoodItem(filePath, name, kcal);
                adapter.addItem(item);

            }


        }
    }


    public static byte[] getCompressedBitmapBytes(File file) throws IOException {
        Bitmap bitmap = decodeSampledBitmapFromFile(file, 300, 300);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    public static Bitmap decodeSampledBitmapFromFile(File imageFile, int reqWidth, int reqHeight) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;

        Bitmap scaledBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);

        ExifInterface exif;
        exif = new ExifInterface(imageFile.getAbsolutePath());
        int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
        Matrix matrix = new Matrix();
        if (orientation == 6) {
            matrix.postRotate(90);
        } else if (orientation == 3) {
            matrix.postRotate(180);
        } else if (orientation == 8) {
            matrix.postRotate(270);
        }
        scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        return scaledBitmap;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            while ((height / inSampleSize) >= reqHeight && (width / inSampleSize) >= reqWidth) {
                inSampleSize += 1;
            }
        }

        return inSampleSize;
    }
}
