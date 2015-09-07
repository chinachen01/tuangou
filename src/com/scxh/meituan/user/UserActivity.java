package com.scxh.meituan.user;

import com.scxh.meituan.R;
import com.scxh.meituan.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

public class UserActivity extends Activity {
	private long exitTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_layout);
	}
	
	/**
	 * 按两次返回退出应用程序
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
