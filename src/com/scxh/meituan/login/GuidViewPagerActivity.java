package com.scxh.meituan.login;

import java.util.ArrayList;
import java.util.List;

import com.scxh.meituan.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class GuidViewPagerActivity extends Activity implements OnPageChangeListener{
	private ViewPager mViewPager;
	private RadioGroup mRadioGroup;
	private Handler mHanler = new Handler(){
		public void handleMessage(Message msg) {};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading_view_pager);
		mViewPager = (ViewPager) findViewById(R.id.loadign_view_pager);
		LoadingPagerAdapter adapter = new LoadingPagerAdapter();
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(this);
		adapter.setLoadingData(getLoadingData());
		mRadioGroup = (RadioGroup) findViewById(R.id.loadingpager_radio_group);
		RadioButton r1 = (RadioButton) mRadioGroup.getChildAt(0);
		r1.toggle();
		
	}
	public List<View> getLoadingData() {
		List<View> list = new ArrayList<View>();
		View view1 = LayoutInflater.from(this).inflate(R.layout.item_loading_view, null);
		ImageView imag = (ImageView) view1.findViewById(R.id.loading_imag);
		imag.setBackgroundResource(R.drawable.img_exchange_guide0);
		list.add(view1);
		View view2 = LayoutInflater.from(this).inflate(R.layout.item_loading_view, null);
		ImageView imag2 = (ImageView) view2.findViewById(R.id.loading_imag);
		imag2.setBackgroundResource(R.drawable.img_exchange_guide1);
		list.add(view2);
		View view3 = LayoutInflater.from(this).inflate(R.layout.item_loading_view, null);
		ImageView imag3 = (ImageView) view3.findViewById(R.id.loading_imag);
		imag3.setBackgroundResource(R.drawable.img_exchange_guide2);
		list.add(view3);
		View view4 = LayoutInflater.from(this).inflate(R.layout.item_loading_view, null);
		ImageView imag4 = (ImageView) view4.findViewById(R.id.loading_imag);
		imag4.setBackgroundResource(R.drawable.img_exchange_guide3);
		list.add(view4);
		return list;
	}
	class LoadingPagerAdapter extends PagerAdapter {
		private List<View> list = new ArrayList<View>();
		public void setLoadingData(List<View> list) {
			this.list = list;
			notifyDataSetChanged();
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			View child = list.get(position);
			container.addView(child);
			return child;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			View child = list.get(position);
			container.removeView(child);
		}
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		RadioButton r1 = (RadioButton) mRadioGroup.getChildAt(arg0);
		r1.toggle();
		if(arg0 == 3){
			mHanler.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Intent intent = new Intent(GuidViewPagerActivity.this, LoginActivity.class);
					startActivity(intent);
					finish();
				}
			}, 1000);
		} 
	}
}
