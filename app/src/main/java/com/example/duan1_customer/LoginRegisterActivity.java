package com.example.duan1_customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class LoginRegisterActivity extends AppCompatActivity {
    AppCompatButton btnRegister;
    ImageSlider imgSlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        btnRegister = findViewById(R.id.btnRegister);
        imgSlide = findViewById(R.id.imgSlide);

        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.img_register2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_register1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_register3, ScaleTypes.FIT));
        imgSlide.setImageList(slideModels, ScaleTypes.FIT);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginRegisterActivity.this, RegisterPhoneActivity.class));
                finish();
            }
        });
    }
}