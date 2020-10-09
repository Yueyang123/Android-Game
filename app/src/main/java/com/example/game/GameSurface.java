package com.example.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class GameSurface extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    private int testx=30,testy=30;
    private int SrceenW,SrceenH;
    private SurfaceHolder sfh;
    private Paint paint;
    private Thread th;
    private boolean th_dead_flag;
    private Canvas canvas;

    public GameSurface(Context context)
    {
        super(context);
        sfh =this.getHolder();
        sfh.addCallback(this);//添加状态监听
        paint =new Paint();
        paint.setColor(Color.WHITE);
    }

    public void mydraw()
    {//为什么要try,因为的那个屏幕不可编辑时，canvas可能会返回Null.
        try {
            canvas = sfh.lockCanvas();
            //canvas.drawRect(0,0,this.getWidth(),this.getHeight(),paint);
            canvas.drawColor(Color.BLACK);
            canvas.drawText("杨越真帅", testx, testy, paint);
        }catch (Exception e){}
        finally {
            if(canvas!=null)sfh.unlockCanvasAndPost(canvas);
        }
    }
    //注意：线程的初始化和启动需要放在同一个函数中，否则当退出后再次start这个时候就会抛出异常
    public void surfaceCreated(SurfaceHolder holder)
    {
        this.SrceenH=this.getHeight();
        this.SrceenW=this.getWidth();
        th=new Thread(this);
        th.start();
        th_dead_flag=true;
        mydraw();//自定义的绘图函数
    }
    public void surfaceChanged(SurfaceHolder holder,int format,int width,int height)
    {
    }
    public  void surfaceDestroyed(SurfaceHolder holder)
    {
        th_dead_flag=false;
    }

    //通过线程刷屏
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        testx=(int )event.getX();
        testy=(int )event.getY();
        return true;
    }


    private void logic()//在这里添加游戏逻辑
    {

    }

    public void run(){
        while(th_dead_flag){
            //Log.d("err","1234");
            long start =System.currentTimeMillis();
            mydraw();
            logic();
            long end=System.currentTimeMillis();
            try
            {
                if(end-start<50)
                {//为什么要线程休眠，有可能logic太长，为了保持相同帧率需要线程演示一下子
                    Thread.sleep(50-(end-start));
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
