package com.example.scroviewscrenn;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.scroviewscrenn.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ScrollView vScroview;
    private TextView vJietu;
    private ImageView vBig;
    private TextView vTvDetail;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vScroview = findViewById(R.id.scroview);
        vJietu = findViewById(R.id.tv_jietu);
        vBig = findViewById(R.id.iv);
        vTvDetail = findViewById(R.id.tv_text);
        vJietu.setOnClickListener(this);
        ViewTreeObserver vto = vScroview.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                height = vTvDetail.getMeasuredHeight();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("image", imagePath());
        startActivity(intent);
    }

    public String imagePath() {
        File file = new File(FileUtils.getShareDir(), "image.png");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
//          list_vp.setDrawingCacheEnabled(true);
//			Bitmap pic = Bitmap.createBitmap(list_vp.getDrawingCache());
//          list_vp.setDrawingCacheEnabled(false);

            Bitmap pic = shotScrollView();
            FileOutputStream fos = new FileOutputStream(file);
            pic.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();

            return file.getAbsolutePath();
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    private Bitmap shotScrollView() {
        Bitmap bitmap = Bitmap.createBitmap(getScreenWidth(this), height, Bitmap.Config.RGB_565);
        bitmap.eraseColor(Color.WHITE);
        Canvas canvas = new Canvas(bitmap);
        vScroview.draw(canvas);

        return bitmap;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        if (null == wm) {
            return -1;
        }
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }
}
