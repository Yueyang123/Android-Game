package com.example.game;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable{
    private GameSurface mainSurf;
    public MyRockerView mRockerViewXY;
    private MyRockerView.Direction HIdirrction;
    private Thread th;
    private double HIangle;
    private int HIlever;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置为横屏

        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN; // hide status bar

        if( android.os.Build.VERSION.SDK_INT >= 19 ){
            uiFlags |= 0x00001000;    //SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide navigation bars - compatibility: building API level is lower thatn 19, use magic number directly for higher API target level
        } else {
            uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }
        getWindow().getDecorView().setSystemUiVisibility(uiFlags);


        // Example of a call to a native method
        /**
         * Surface与view的区别
         * 1.更新方式：VIEW一般有UI系统自动更新，提供invalidate
         * 2.师徒机制：Surface多了双缓冲机制
         */
        setContentView(R.layout.activity_main);//注意这里的执行顺序
        mainSurf=(GameSurface)findViewById(R.id.GameSurface);
        if(mainSurf==null)Log.d("error","mainSurface is null");
        mRockerViewXY =findViewById(R.id.rockerXY_View);//8方向
        if(mRockerViewXY!=null) {
            mRockerViewXY.setOnShakeListener(MyRockerView.DirectionMode.DIRECTION_8, new MyRockerView.OnShakeListener() {
                @Override
                public void onStart() {
                }

                @Override
                public void direction(MyRockerView.Direction direction) {
                    HIdirrction=direction;
                }

                @Override
                public void onFinish() {
                }
            });
////角度
//        mRockerViewXY.setOnAngleChangeListener(new MyRockerView.OnAngleChangeListener() {
//            @Override
//            public void onStart() {
//            }
//
//            @Override
//            public void angle(double angle) {
//                HIangle=angle;
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
////级别
//        mRockerViewXY.setOnDistanceLevelListener(new MyRockerView.OnDistanceLevelListener() {
//            @Override
//            public void onDistanceLevel(int level) {
//                HIlever=level;
//            }
//        });
        }
        th=new Thread(this);
        th.start();

    }

    public void logic()
    {
        if (HIdirrction == MyRockerView.Direction.DIRECTION_DOWN){
            mainSurf.testy+=10;
        }else if (HIdirrction == MyRockerView.Direction.DIRECTION_LEFT){
            mainSurf.testx-=10;
        }else if (HIdirrction == MyRockerView.Direction.DIRECTION_UP){
            mainSurf.testy-=10;
        }else if (HIdirrction == MyRockerView.Direction.DIRECTION_RIGHT){
            mainSurf.testx+=10;
        }
    }

    public void run()
    {
        while (true) {
            mainSurf.mydraw();
            logic();
        }
    }

}
