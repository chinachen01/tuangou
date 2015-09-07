package com.scxh.meituan.login;

import com.scxh.meituan.R;
import com.scxh.meituan.apollo.ApolloActivity;
import com.scxh.meituan.deal.DealActivity;
import com.scxh.meituan.more.MoreActivity;
import com.scxh.meituan.poi.PoiActivity;
import com.scxh.meituan.user.UserActivity;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabHostActivity extends TabActivity {
	private long exitTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_host);
		//��ʼ�����Դ
		int[] ids = {R.drawable.selector_menu_deal,R.drawable.selector_menu_aplo,R.drawable.selector_menu_poi,R.drawable.selector_menu_user,R.drawable.selector_menu_more};
		String[] contents = {"团购","上门","商家","我的","更多"};
		Class[] classes = {DealActivity.class,ApolloActivity.class,PoiActivity.class,UserActivity.class,MoreActivity.class};
		addTabHost(ids, contents, classes);
	}
	private void addTabHost(int[] ids, String[] contents,Class[] classes ) {
		TabHost tabHost = getTabHost();
		for (int i = 0; i < ids.length; i++) {
			TabSpec spec = tabHost.newTabSpec("tab"+i);
			View view = LayoutInflater.from(this).inflate(R.layout.item_tabspec, null);
			ImageView imag = (ImageView) view.findViewById(R.id.tabspec_imag);
			TextView txt = (TextView) view.findViewById(R.id.tabspec_txt);
			imag.setImageResource(ids[i]);
			txt.setText(contents[i]);
			spec.setIndicator(view);
			spec.setContent(new Intent(this,classes[i]));
			tabHost.addTab(spec);
		}
	}
	//按两次返回退出应用程序
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
