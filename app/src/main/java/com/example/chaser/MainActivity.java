package com.example.chaser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;



public class MainActivity extends AppCompatActivity {

objects obj ;

SharedPreferences sharedPreferences;
    LinearLayout layout ;Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        objects.highest = sharedPreferences.getInt("highestScore", 0);


        layout =(LinearLayout) findViewById(R.id.act);
        layout.setOrientation(LinearLayout.VERTICAL);
start=(Button) findViewById(R.id.vanish);

        obj = new objects(this);
        layout.addView(obj);
start.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        obj.startgame();
start.setVisibility(View.GONE);

    }
});
layout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        obj.runnerMove();
    }
});
    }

}



