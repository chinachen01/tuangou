package com.scxh.meituan.deal;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
/**
 * 自动以ViewPager,解决ViewPager无法自适应高度的问题
 * @author scxh
 *
 */
public class MyViewPager extends ViewPager {

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	 @Override
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	 
	        int height = 0;
	        for (int i = 0; i < getChildCount(); i++) {
	            View child = getChildAt(i);
	            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
	            int h = child.getMeasuredHeight();
	            if (h > height)
	                height = h;
	        }
	        //����2Ϊ��Ҫ��ʾ������
	        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)*2;
	        System.out.println(heightMeasureSpec);
	        System.out.println(widthMeasureSpec);
	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	    }
}
