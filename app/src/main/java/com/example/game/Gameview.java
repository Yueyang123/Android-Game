package com.example.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Gameview extends View {

    private int testx=30,testy=30;
   //构造函数
    public Gameview(Context context)
    {
        super(context);
    }

    protected void onDraw(Canvas canvas)
    {
        Paint paint =new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawText("杨越哼帅",testx,testy,paint);
        super.onDraw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        testx=(int )event.getX();
        testy=(int )event.getY();
        invalidate();
        //使用这种方式无法识别滑动
        //return super.onTouchEvent(event);
        return true;
    }

}


