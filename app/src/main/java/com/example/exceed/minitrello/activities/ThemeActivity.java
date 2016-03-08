package com.example.exceed.minitrello.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.exceed.minitrello.R;
import com.example.exceed.minitrello.models.ColorAll;
import com.example.exceed.minitrello.models.Storage;

public class ThemeActivity extends AppCompatActivity{

    private TextView button_text;
    private SeekBar button_R;
    private SeekBar button_G;
    private SeekBar button_B;
    private int button_int_R;
    private int button_int_G;
    private int button_int_B;

    private TextView foreground_text;
    private SeekBar foreground_R;
    private SeekBar foreground_G;
    private SeekBar foreground_B;
    private int foreground_int_R;
    private int foreground_int_G;
    private int foreground_int_B;
    private TextView background_text;
    private SeekBar background_R;
    private SeekBar background_G;
    private SeekBar background_B;
    private int background_int_R;
    private int background_int_G;
    private int background_int_B;
    private Button set_theme_button;

    private Toolbar toolbar;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        setTitle("Theme");
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
        layout = (RelativeLayout) findViewById(R.id.background);
        layout.setBackgroundColor(Color.argb(255, Storage.getInstance().getColor().getBackgroundColor()[0], Storage.getInstance().getColor().getBackgroundColor()[1], Storage.getInstance().getColor().getBackgroundColor()[2]));
        toolbar = MainActivity.toolbar;
        toolbar.setBackgroundColor(Color.argb(255, Storage.getInstance().getColor().getToolbarColor()[0], Storage.getInstance().getColor().getToolbarColor()[1], Storage.getInstance().getColor().getToolbarColor()[2]));
        background_int_R = Storage.getInstance().getColor().getBackgroundColor()[0];
        background_int_G = Storage.getInstance().getColor().getBackgroundColor()[1];
        background_int_B = Storage.getInstance().getColor().getBackgroundColor()[2];
        foreground_int_R = 0;
        foreground_int_G = 0;
        foreground_int_B = 0;
        button_int_R = Storage.getInstance().getColor().getToolbarColor()[0];
        button_int_G = Storage.getInstance().getColor().getToolbarColor()[1];
        button_int_B = Storage.getInstance().getColor().getToolbarColor()[2];
        initButton();
        initForeground();
        initBackground();
        set_theme_button = (Button) findViewById(R.id.button_set_theme);
        set_theme_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Storage.getInstance().setColor(new ColorAll(getColorText(), getColorToolbar(), getColorBackground()));
                toolbar.setBackgroundColor(Color.argb(255,Storage.getInstance().getColor().getToolbarColor()[0],Storage.getInstance().getColor().getToolbarColor()[1],Storage.getInstance().getColor().getToolbarColor()[2]));
                layout.setBackgroundColor(Color.argb(255, Storage.getInstance().getColor().getBackgroundColor()[0], Storage.getInstance().getColor().getBackgroundColor()[1], Storage.getInstance().getColor().getBackgroundColor()[2]));
            }
        });

    }

    private void initButton(){
        button_text = (TextView) findViewById(R.id.Button_theme);
        button_R = (SeekBar) findViewById(R.id.Button_R);
        button_G = (SeekBar) findViewById(R.id.Button_G);
        button_B = (SeekBar) findViewById(R.id.Button_B);
        button_R.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                button_int_R = (int) (progress * (255.0 / 100.0));
                button_text.setTextColor(Color.argb(255, button_int_R, button_int_G, button_int_B));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button_G.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                button_int_G = (int) (progress * (255.0 / 100.0));
                button_text.setTextColor(Color.argb(255, button_int_R, button_int_G, button_int_B));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button_B.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                button_int_B = (int) (progress * (255.0 / 100.0));
                button_text.setTextColor(Color.argb(255, button_int_R, button_int_G, button_int_B));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initForeground() {
        foreground_text = (TextView) findViewById(R.id.Foreground_theme);
        foreground_R = (SeekBar) findViewById(R.id.Foreground_R);
        foreground_G = (SeekBar) findViewById(R.id.Foreground_G);
        foreground_B = (SeekBar) findViewById(R.id.Foreground_B);
        foreground_R.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                foreground_int_R = (int) (progress * (255.0 / 100.0));
                foreground_text.setTextColor(Color.argb(255, foreground_int_R, foreground_int_G, foreground_int_B));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        foreground_G.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                foreground_int_G = (int) (progress * (255.0 / 100.0));
                foreground_text.setTextColor(Color.argb(255, foreground_int_R, foreground_int_G, foreground_int_B));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        foreground_B.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                foreground_int_B = (int) (progress * (255.0 / 100.0));
                foreground_text.setTextColor(Color.argb(255, foreground_int_R, foreground_int_G, foreground_int_B));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initBackground() {
        background_text = (TextView) findViewById(R.id.Background_theme);
        background_R = (SeekBar) findViewById(R.id.Background_R);
        background_G = (SeekBar) findViewById(R.id.Background_G);
        background_B = (SeekBar) findViewById(R.id.Background_B);
        background_R.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                background_int_R = (int) (progress * (255.0 / 100.0));
                background_text.setTextColor(Color.argb(255, background_int_R, background_int_G, background_int_B));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        background_G.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                background_int_G = (int) (progress * (255.0 / 100.0));
                background_text.setTextColor(Color.argb(255, background_int_R, background_int_G, background_int_B));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        background_B.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                background_int_B = (int) (progress * (255.0 / 100.0));
                background_text.setTextColor(Color.argb(255, background_int_R, background_int_G, background_int_B));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private int[] getColorText(){
        return new int []{button_int_R, button_int_G, button_int_B};
    }

    private int[] getColorToolbar(){
        return new int []{foreground_int_R, foreground_int_G, foreground_int_B};
    }

    private int[] getColorBackground(){
        return new int []{background_int_R, background_int_G, background_int_B};
    }

}
