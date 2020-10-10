（一）
Surface与view的区别
 1.更新方式：VIEW一般有UI系统自动更新，提供invalidate
 2.视图：Surface多了双缓冲机制
（二）Canvas画布
    https://blog.csdn.net/qq_27061049/article/details/96888346?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522160225100019724835810095%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=160225100019724835810095&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_v2~rank_v28-3-96888346.pc_first_rank_v2_rank_v28&utm_term=ANdroid+Canvas%E7%94%BB%E5%B8%83&spm=1018.2118.3001.4187

                canvas.drawColor(Color.BLACK);
                canvas.drawPoint(10,20,paint);//画一个点
                canvas.drawPoints(new float[]{10,30,20,20,30,40},paint);//花许多点
                canvas.drawLine(10,20,30,40,paint);

                //绘制矩形
                Rect rect =new Rect(10,200,60,600);
                canvas.drawRect(rect,paint);
                //绘制圆角矩形
                RectF rectf =new RectF(40,50,60,70);
                canvas.drawRoundRect(rectf,30,30,paint);

                canvas.drawCircle(20,200,20,paint);

                //绘制制定路劲图形
                Path path =new Path();
                path.moveTo(160,150);
                path.lineTo(200,159);
                path.lineTo(180,200);
                path.close();
                canvas.drawPath(path,paint);
                canvas.drawTextOnPath("杨越郑帅",path,10,20,paint);

(三)Paint画笔
                // 创建画笔
                Paint p = new Paint();
                p.setColor(Color.RED);// 设置红色

                canvas.drawText("画圆：", 10, 20, p);// 画文本
                canvas.drawCircle(60, 20, 10, p);// 小圆
                p.setAntiAlias(true);// 设置画笔的锯齿效果。 true是去除，大家一看效果就明白了
                canvas.drawCircle(120, 20, 20, p);// 大圆

                canvas.drawText("画线及弧线：", 10, 60, p);
                p.setColor(Color.GREEN);// 设置绿色
                canvas.drawLine(60, 40, 100, 40, p);// 画线
                canvas.drawLine(110, 40, 190, 80, p);// 斜线
                //画笑脸弧线
                p.setStyle(Paint.Style.STROKE);//设置空心
                RectF oval1=new RectF(150,20,180,40);
                canvas.drawArc(oval1, 180, 180, false, p);//小弧形
                oval1.set(190, 20, 220, 40);
                canvas.drawArc(oval1, 180, 180, false, p);//小弧形
                oval1.set(160, 30, 210, 60);
                canvas.drawArc(oval1, 0, 180, false, p);//小弧形

                canvas.drawText("画矩形：", 10, 80, p);
                p.setColor(Color.GRAY);// 设置灰色
                p.setStyle(Paint.Style.FILL);//设置填满
                canvas.drawRect(60, 60, 80, 80, p);// 正方形
                canvas.drawRect(60, 90, 160, 100, p);// 长方形

                canvas.drawText("画扇形和椭圆:", 10, 120, p);
                /* 设置渐变色 这个正方形的颜色是改变的 */
                Shader mShader = new LinearGradient(0, 0, 100, 100,
                        new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW,
                                Color.LTGRAY }, null, Shader.TileMode.REPEAT); // 一个材质,打造出一个线性梯度沿著一条线。
                p.setShader(mShader);
                // p.setColor(Color.BLUE);
                RectF oval2 = new RectF(60, 100, 200, 240);// 设置个新的长方形，扫描测量
                canvas.drawArc(oval2, 200, 130, true, p);
                // 画弧，第一个参数是RectF：该类是第二个参数是角度的开始，第三个参数是多少度，第四个参数是真的时候画扇形，是假的时候画弧线
                //画椭圆，把oval改一下
                oval2.set(210,100,250,130);
                canvas.drawOval(oval2, p);

                canvas.drawText("画三角形：", 10, 200, p);
                // 绘制这个三角形,你可以绘制任意多边形
                Path path = new Path();
                path.moveTo(80, 200);// 此点为多边形的起点
                path.lineTo(120, 250);
                path.lineTo(80, 250);
                path.close(); // 使这些点构成封闭的多边形
                canvas.drawPath(path, p);

                // 你可以绘制很多任意多边形，比如下面画六连形
                p.reset();//重置
                p.setColor(Color.LTGRAY);
                p.setStyle(Paint.Style.STROKE);//设置空心
                Path path1=new Path();
                path1.moveTo(180, 200);
                path1.lineTo(200, 200);
                path1.lineTo(210, 210);
                path1.lineTo(200, 220);
                path1.lineTo(180, 220);
                path1.lineTo(170, 210);
                path1.close();//封闭
                canvas.drawPath(path1, p);
                /*
                 * Path类封装复合(多轮廓几何图形的路径
                 * 由直线段*、二次曲线,和三次方曲线，也可画以油画。drawPath(路径、油漆),要么已填充的或抚摸
                 * (基于油漆的风格),或者可以用于剪断或画画的文本在路径。
                 */

                //画圆角矩形
                p.setStyle(Paint.Style.FILL);//充满
                p.setColor(Color.LTGRAY);
                p.setAntiAlias(true);// 设置画笔的锯齿效果
                canvas.drawText("画圆角矩形:", 10, 260, p);
                RectF oval3 = new RectF(80, 260, 200, 300);// 设置个新的长方形
                canvas.drawRoundRect(oval3, 20, 15, p);//第二个参数是x半径，第三个参数是y半径

                //画贝塞尔曲线
                canvas.drawText("画贝塞尔曲线:", 10, 310, p);
                p.reset();
                p.setStyle(Paint.Style.STROKE);
                p.setColor(Color.GREEN);
                Path path2=new Path();
                path2.moveTo(100, 320);//设置Path的起点
                path2.quadTo(150, 310, 170, 400); //设置贝塞尔曲线的控制点坐标和终点坐标
                canvas.drawPath(path2, p);//画出贝塞尔曲线

                //画点
                p.setStyle(Paint.Style.FILL);
                canvas.drawText("画点：", 10, 390, p);
                canvas.drawPoint(60, 390, p);//画一个点
                canvas.drawPoints(new float[]{60,400,65,400,70,400}, p);//画多个点

(四)位图Bitmap

                Bitmap bmppng=BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.test);
                //方式2
                //    FileInputStream fis = new FileInputStream("/sdcard/test.png");
                //    Bitmap bitmap  = BitmapFactory.decodeStream(fis);
            
                //方式3
                //Bitmap.bitmap=BitmapFactory.decodeStream(getClass().getResourceAsStream(“/res/drawable/test.png”));

                canvas.drawCircle(30,15,10,paint);
                canvas.drawBitmap(bmppng,0,0,paint);

                canvas.save();
                canvas.scale(2f,2f,50+bmppng.getWidth()/2,50+bmppng.getHeight()/2);

                canvas.drawBitmap(bmppng,50,50,paint);
                canvas.restore();
                canvas.drawBitmap(bmppng,50,50,paint);
                
(五)帧动画

                public GameSurface(Context context)
                {
                    super(context);
                    sfh =this.getHolder();
                    sfh.addCallback(this);//添加状态监听
                    paint =new Paint();
                    paint.setColor(Color.WHITE);
            
            
                    // 设置想要的大小
                    int newWidth = 1280;
                    int newHeight = 720;
                    for(int i=0;i<map.length;i++)
                    {
                        map[i]=BitmapFactory.decodeResource(this.getContext().getResources(),R.drawable.t001+i);
                        int height = map[i].getHeight();
                        int width = map[i].getWidth();
                        // 计算缩放比例
                        float scaleWidth = ((float) newWidth) / width;
                        float scaleHeight = ((float) newHeight) / height;
            
                        // 取得想要缩放的matrix参数
                        Matrix matrix = new Matrix();
                        matrix.postScale(scaleWidth, scaleHeight);
                        // 得到新的图片
                        map[i] = Bitmap.createBitmap(map[i], 0, 0, width, height, matrix,
                                true);
                    }
                }
            
                public void mydraw()
                {//为什么要try,因为的那个屏幕不可编辑时，canvas可能会返回Null.
                    try {
                        canvas = sfh.lockCanvas();
                        //canvas.drawRect(0,0,this.getWidth(),this.getHeight(),paint);
                        if(canvas!=null)
                        {
                            canvas.drawColor(Color.WHITE);
            
                            canvas.drawBitmap(map[currentframe],0,0,paint);
                        }
            
                        canvas.drawText("杨越真帅", testx, testy, paint);
                    }catch (Exception e){}
                    finally {
                        if(canvas!=null)sfh.unlockCanvasAndPost(canvas);
                    }
                }