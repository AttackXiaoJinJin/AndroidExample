package com.example.example_custonview.simplecustomview;

/*
 * Created by chenjin on 2017/4/18.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.example_custonview.R;

public class SimpleImageView extends View
{
    //文本内容
    private String mTitleText;
    //文本颜色
    private int mTitleTextColor;
    //文本大小
    private int mTitleTextSize;

    //绘制时控制文本的绘制范围
    private Rect mBound;
    //画笔
    private Paint mPaint;

    //图片drawable
    private Drawable drawable;
    private Bitmap bitmap;
    //View的宽度和高度
    private int mWidth;
    private int mHeight;


    //两个构造函数
    public SimpleImageView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet, 0);
        //根据属性初始化
        initAttrs(attributeSet);
        //初始化画笔
        mPaint=new Paint();
        //设置是否使用抗锯齿功能，消耗资源，绘制速度慢
        mPaint.setAntiAlias(true);
    }

    public SimpleImageView(Context context)
    {
        this(context, null);
    }


    private void initAttrs(AttributeSet attributeSet)
    {
        if(attributeSet!=null)
        {
            TypedArray typedArray=null;
            try
            {
                typedArray=getContext().obtainStyledAttributes(attributeSet, R.styleable.SimpleImageView);
                //根据图片id获取drawable对象
                drawable=typedArray.getDrawable(R.styleable.SimpleImageView_src);

                //测量Drawable对象的宽和高
                measureDrawable();
            }
            finally {
                if(typedArray!=null)
                {
                    typedArray.recycle();
                }
            }
        }
    }






    private void measureDrawable()
    {
        if(drawable==null)
        {
            throw new RuntimeException("drawable不能为空");
        }
        mWidth=drawable.getIntrinsicWidth();
        mHeight=drawable.getIntrinsicHeight();
    }


    //int型数据包含32个bit,前两个bit区分布局模式，后20bit存放尺寸数据
    //原图大小，wrap_content
   /* protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        //设置View的宽和高为图片的宽和高
        setMeasuredDimension(mWidth, mHeight);
    }*/

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode= MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(measureWidth(widthMode, widthSize), measureHeight(heightMode, heightSize));
    }

    private int measureWidth(int mode, int width)
    {
        switch(mode)
        {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mWidth=width;
                break;
        }
        return mWidth;
    }

    private int measureHeight(int mode, int height)
    {
        switch(mode)
        {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mHeight=height;
                break;
        }
        return mHeight;
    }




   /* protected void onDraw(Canvas canvas)
    {   if(drawable==null)
    {return;}
        //绘制图片
        canvas.drawBitmap(drawableToBitmap(drawable), getLeft(), getTop(), mPaint);
    }*/

    protected void onDraw(Canvas canvas)
    {   if(bitmap==null)
    {
        bitmap=Bitmap.createScaledBitmap(drawableToBitmap(drawable), getMeasuredWidth(), getMeasuredHeight(), true);
    }
    else
    {return;}
        //绘制图片
       canvas.drawBitmap(bitmap, getLeft(), getTop(), mPaint);

        //保存画布状态
        canvas.save();
        //旋转90度
        canvas.rotate(90);
        //绘制文字
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(300);
        //使用getMeasuredWidth(), getMeasuredHeight()，要减，调整，不然看不到
        //顺时针旋转90度，坐标也旋转了90度
        canvas.drawText("泫雅", 0  ,0, mPaint);
        //恢复原来的状态
        canvas.restore();


    }



    //Drawable → Bitmap
    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = Bitmap.createBitmap(

                drawable.getIntrinsicWidth(),

                drawable.getIntrinsicHeight(),

                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888

                        : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);

        //canvas.setBitmap(bitmap);

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        drawable.draw(canvas);

        return bitmap;

    }









}
