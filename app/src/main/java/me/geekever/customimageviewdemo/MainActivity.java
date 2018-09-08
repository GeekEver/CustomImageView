package me.geekever.customimageviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.geekever.customimageview.CustomImageView;

public class MainActivity extends AppCompatActivity {

    private CustomImageView mCustomImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCustomImageView = findViewById(R.id.customImageView);
        mCustomImageView.setShapeDrawble(getDrawable(R.drawable.test_shape2));
    }
}
