package com.scxh.meituan.login;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scxh.meituan.R;
import com.scxh.meituan.dao.MeituanDataBaseHelper;

public class RegistActivity extends Activity {
	private EditText mEditName, mEditPassword;
	private ImageView mImageName, mImagePassword;
	private TextView mTextName, mTextPassword, mTextMsg;
	private Button  mRegistBtn,mBackBtn;
	private SQLiteDatabase db;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login_regist_layout);
		mEditName = (EditText) findViewById(R.id.regist_username_edit);
		mEditPassword = (EditText) findViewById(R.id.regist_password_edit);
		mImageName = (ImageView) findViewById(R.id.regist_clear_name_image);
		mImagePassword = (ImageView) findViewById(R.id.regist_clear_password_image);
		mTextName = (TextView) findViewById(R.id.regist_msg_name_hint_txt);
		mTextPassword = (TextView) findViewById(R.id.regist_msg_password_hint_txt);
		mTextMsg = (TextView) findViewById(R.id.regist_exist_msg_txt);
		mRegistBtn = (Button) findViewById(R.id.regist_regist_btn);
		mBackBtn = (Button) findViewById(R.id.regist_back_btn);
		mBackBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(RegistActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}
		});
		//实例化数据库对象
//		MeituanDataBaseHelper helper = MeituanDataBaseHelper.getInstanceDataBaseHelper(this);
//		db = helper.getReadableDatabase();
		//添加注册事件保存数据到本地数据库
		mRegistBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = mEditName.getText().toString();
				String password = mEditPassword.getText().toString();
				boolean isExist = selectUser(name);
				if(isExist){
					mTextMsg.setText("此用户已注册");
					mTextMsg.setVisibility(View.VISIBLE);
				}else if(name.equals("")) {
					mTextMsg.setText("用户名不能为空");
					mTextMsg.setVisibility(View.VISIBLE);
				}else if(password.equals("")){
					mTextMsg.setText("密码不能为空");
					mTextMsg.setVisibility(View.VISIBLE);
				}
				else {
					addUser(name,password);
					mTextMsg.setText("注册成功,3秒后自动跳转到登录界面");
					mTextMsg.setVisibility(View.VISIBLE);
					handler.sendEmptyMessageDelayed(0, 2000);
				}
			}
		});
		mEditName.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s.length() > 0) {
					mImageName.setVisibility(View.VISIBLE);
					mTextName.setVisibility(View.INVISIBLE);
				} else {
					mImageName.setVisibility(View.INVISIBLE);
					mTextName.setVisibility(View.VISIBLE);
				}
			}
		});
		mEditPassword.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s.length() > 0) {
					mImagePassword.setVisibility(View.VISIBLE);
					mTextPassword.setVisibility(View.INVISIBLE);
				} else {
					mImagePassword.setVisibility(View.INVISIBLE);
					mTextPassword.setVisibility(View.VISIBLE);
				}

			}
		});
		mImageName.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mEditName.setText("");
			}
		});
		mImagePassword.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mEditPassword.setText("");
			}
		});
	
	}
	private void addUser(String name,String password) {
		MeituanDataBaseHelper helper = MeituanDataBaseHelper.getInstanceDataBaseHelper(this);
		db = helper.getReadableDatabase();
		db.execSQL("insert into user (_name, _password) values (?,?)", new String[]{name,password});
	}
	private boolean selectUser(String name) {
		MeituanDataBaseHelper helper = MeituanDataBaseHelper.getInstanceDataBaseHelper(this);
		db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from user where _name = ?", new String[]{name});
		boolean result = cursor.moveToNext();
		cursor.close();
		return result;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(db != null) {
			if(db.isOpen()){
				db.close();
			}
		}
	}
}
