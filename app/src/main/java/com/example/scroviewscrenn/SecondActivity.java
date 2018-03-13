package com.example.scroviewscrenn;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.File;

public class SecondActivity extends AppCompatActivity {
    private ImageView vScreenImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        vScreenImg = findViewById(R.id.iv_screen);
        String image = getIntent().getStringExtra("image");
        File f = new File(image);
        Uri u = null;
        if (f != null && f.exists() && f.isFile()) {
            u = Uri.fromFile(f);
        }
        vScreenImg.setImageURI(u);
    }
}
