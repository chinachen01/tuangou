package com.scxh.meituan.apollo;

import java.util.ArrayList;
import java.util.List;

import com.scxh.meituan.R;
import com.scxh.meituan.R.drawable;
import com.scxh.meituan.R.id;
import com.scxh.meituan.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class ApolloActivity extends Activity {
	private ViewPager mApolloViewPager;
	private long exitTime=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_apollo_layout);
		setApolloViewPager();
		
	}
	
	private void setApolloViewPager() {
		mApolloViewPager = (ViewPager) findViewById(R.id.apollo_view_pager);
		ApolloPagerAdapter adpter = new ApolloPagerAdapter();
		mApolloViewPager.setAdapter(adpter);
		adpter.setPagerData(getPagerData());
		
	}
	private List<View> getPagerData()	{
		List<View> list = new ArrayList<View>();
		ImageView v = (ImageView) LayoutInflater.from(this).inflate(R.layout.item_apollo_view_pager, null);
		v.setBackgroundResource(R.drawable.apollo1);
		list.add(v);
		v = (ImageView) LayoutInflater.from(this).inflate(R.layout.item_apollo_view_pager, null);
		v.setBackgroundResource(R.drawable.apollo2);
		list.add(v);
		return list;
	}
	/**
	 * ViewPager适配器
	 * @author scxh
	 *
	 */
	class ApolloPagerAdapter extends PagerAdapter {
		private List<View> list = new ArrayList<View>();
		private void setPagerData(List<View> list) {
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
	/**
	 * 按两次退出
	 */
			@Override
			public boolean onKeyDown(int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if(keyCode == KeyEvent.KEYCODE_BACK) {
					exitTime = System.currentTimeMillis()-exitTime;
					Log.e("tag", "" + exitTime);
					if(exitTime>2000){
						Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
						exitTime = System.currentTimeMillis();
					}else{
						Log.e("exit1", "" + exitTime);
						finish();
						System.exit(0);
						Log.e("exit2", "" + exitTime);
					}
					return false;
				}
				return super.onKeyDown(keyCode, event);
			}
}
