package com.example.example_custonview.simplecustomview;

/*
 * Created by chenjin on 2017/4/17.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.example_custonview.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CustomTitleView extends View
{
    //文本内容
    private String mTitleText;
    //文本颜色
    private int mTitleTextColor;
    //文本大小
    private int mTitleTextSize;

    //绘制时控制文本的绘制范围
    private Rect mBound;
    private Paint mPaint;


    //两个构造函数
    public CustomTitleView(Context context, AttributeSet attributeSet)
    {
        this(context, attributeSet, 0);
    }

    public CustomTitleView(Context context)
    {
        this(context, null);
    }

    //获取自己定义的样式属性

    public CustomTitleView(Context context, AttributeSet attributeSet, int defStyle)
    {
        super(context, attributeSet, defStyle);
        //获取自定义的样式属性
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.CustomTitleView, defStyle, 0);


        for(int i=0;i<typedArray.getIndexCount();i++)
        {
            int attr=typedArray.getIndex(i);
            switch(attr)
            {
                case R.styleable.CustomTitleView_titleText:
                    mTitleText=typedArray.getString(attr);
                    break;
                case R.styleable.CustomTitleView_titleTextColor:
                    //默认颜色设置为蓝色
                    mTitleTextColor=typedArray.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.CustomTitleView_titleTextSize:
                    //默认设置为16sp，TypeValue也可以把sp转换为px
                    mTitleTextSize=typedArray.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
            }

        }

        typedArray.recycle();

        //获得绘制文本的宽和高
        //paint绘画
        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        //bound界限
        mBound=new Rect();
        //String text, int start, int end, Rect bounds
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        //为自定义view添加一个事件
        this.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                mTitleText=randomText();
                //// 使用postInvalidate可以直接在线程中更新界面
                postInvalidate();
            }
        });



    }

    //int型数据包含32个bit,前两个bit区分布局模式，后20bit存放尺寸数据
    /*protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }*/

    //重写onMeasure代码：
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        /*MeasureSpec,getMode根据提供的测量值(格式)提取模式(上述三个模式之一)
        getSize根据提供的测量值(格式)提取大小值(这个大小也就是我们通常所说的大小)
        */

        int widthMode= MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        //EXACTLY,match_parent
        if(widthMode==MeasureSpec.EXACTLY)
        {
            width=widthSize;
        }
        else
        {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textWidth=mBound.width();
            int desired=(int)(getPaddingLeft()+textWidth+getPaddingRight());
            width=desired;
        }

        if(heightMode==MeasureSpec.EXACTLY)
        {
            height=heightSize;
        }
        else
        {
            mPaint.setTextSize(mTitleTextSize);
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
            float textHeight=mBound.height();
            int desired=(int)(getPaddingTop()+textHeight+getPaddingBottom());
            height=desired;
        }

        setMeasuredDimension(width, height);
    }


    protected void onDraw(Canvas canvas)
    {   //设置画布（背景）颜色
        mPaint.setColor(Color.YELLOW);
        //绘制一个矩形
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        //设置画布字体颜色
        mPaint.setColor(mTitleTextColor);
        //String text, float x, float y,  Paint paint
        canvas.drawText(mTitleText, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);




    }

    //设置随机产生数字，验证码功能
    private String randomText()
    {
        Random random=new Random();
        //设置Hash数组
        Set<Integer> set=new HashSet<>();
        //0,1,2,3即随机产生4个数字
        while(set.size()<4)
        {   //随机产生大于等于0，小于10的整数
            int randomInt=random.nextInt(10);
            //将随机数添加到set数组中
            set.add(randomInt);
        }

        //创建一个字符串变量
        StringBuffer stringBuffer=new StringBuffer();
        //遍历set中的每一元素
        for(Integer i : set)
        {   //将i转换成字符串
            //append将在set里面添加新字符串
            stringBuffer.append(""+i);
        }

        return stringBuffer.toString();
    }








}
