package com.example.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    public int testx=30,testy=30;
    private int SrceenW,SrceenH;
    private SurfaceHolder sfh;
    private Paint paint;
    private Canvas canvas;
    int currentframe;

    Bitmap map[]=new Bitmap[5];
    public GameSurface(Context context, AttributeSet attrs)
    {
        super(context,attrs);
        sfh =this.getHolder();
        sfh.addCallback(this);//添加状态监听
        paint =new Paint();
        paint.setColor(Color.BLACK);

        map[0]= BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.timg1);
        map[1]= BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.timg2);
        map[2]= BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.timg3);
        map[3]= BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.timg4);
        map[4]= BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.timg5);

    }

    public void mydraw()
    {//为什么要try,因为的那个屏幕不可编辑时，canvas可能会返回Null.
        try {
            canvas = sfh.lockCanvas();
            //canvas.drawRect(0,0,this.getWidth(),this.getHeight(),paint);
            if(canvas!=null)
            {
                canvas.drawColor(Color.WHITE);
                currentframe++;
                if(currentframe>=5)currentframe=0;

                canvas.drawBitmap(map[currentframe],testx,testy,paint);
            }


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
        mydraw();//自定义的绘图函数
    }
    public void surfaceChanged(SurfaceHolder holder,int format,int width,int height)
    {
    }
    public  void surfaceDestroyed(SurfaceHolder holder)
    {
    }

    //通过线程刷屏
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        testx=(int )event.getX();
        testy=(int )event.getY();
        return true;
    }

}
