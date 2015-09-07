package com.scxh.meituan.login;

import com.scxh.meituan.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LoadingActivity extends Activity{
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Intent intent = new Intent(LoadingActivity.this, GuidViewPagerActivity.class);
			startActivity(intent);
			finish();
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading_layout);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = Message.obtain();
				mHandler.sendMessageDelayed(msg, 2000);
			}
		}).start();
	}
}
