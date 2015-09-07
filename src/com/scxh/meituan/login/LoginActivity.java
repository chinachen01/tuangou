package com.scxh.meituan.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.scxh.meituan.R;
import com.scxh.meituan.dao.MeituanDataBaseHelper;

public class LoginActivity extends Activity {
	private EditText mEditName, mEditPassword;
	private ImageView mImageName, mImagePassword;
	private TextView mTextMsg;
	private Button mLoginBtn, mRegistBtn;
	private CheckBox mSaveCheckBtn;
	private SharedPreferences mPreferences;
	private Boolean isExist = false;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
		mEditName = (EditText) findViewById(R.id.login2_username_edit);

		mEditPassword = (EditText) findViewById(R.id.login2_password_edit);

		mImageName = (ImageView) findViewById(R.id.login2_clear_name_image);
		mImagePassword = (ImageView) findViewById(R.id.login2_clear_password_image);
		mTextMsg = (TextView) findViewById(R.id.login2_msg_fail_txt);
		mLoginBtn = (Button) findViewById(R.id.login2_login_btn);
		mRegistBtn = (Button) findViewById(R.id.login2_regist_btn);
		mSaveCheckBtn = (CheckBox) findViewById(R.id.login2_save_checkbtn);
		// 实例化数据库对象
		MeituanDataBaseHelper helper = MeituanDataBaseHelper
				.getInstanceDataBaseHelper(this);
		db = helper.getReadableDatabase();
		// 实例化SharedPreferences
		mPreferences = getSharedPreferences(
				"com.scxh.androidmydemo.LOGIN_PREFERENCES", MODE_PRIVATE);
		// 初始化账户与密码框
		initEditText();
		// 添加注册按键事件
		mRegistBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,
						RegistActivity.class);
				startActivity(intent);
				finish();
			}
		});
		// 添加登录按键事件
		mLoginBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor editor = mPreferences.edit();
				String name = mEditName.getText().toString();
				String password = mEditPassword.getText().toString();
				// 缺少代码,判断输入的信息是否符合数据库
				isExist = selectUser(name, password);
				if (isExist) {
					if (mSaveCheckBtn.isChecked()) {
						editor.putString("USER_NAME", name);
						editor.putString("USER_PASSWORD", password);
					}else{
						editor.clear();
					}
					editor.commit();
					Intent intent = new Intent(LoginActivity.this,
							TabHostActivity.class);
					startActivity(intent);
//					if (db != null) {
//						if (db.isOpen()) {
//							db.close();
//						}
//					}
					finish();
				} else {
					mTextMsg.setVisibility(View.VISIBLE);
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
				} else {
					mImageName.setVisibility(View.INVISIBLE);
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
				} else {
					mImagePassword.setVisibility(View.INVISIBLE);
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

	/**
	 * 从sharedPreferences文件获取保存的账户名与密码
	 */
	private void initEditText() {
		String name = mPreferences.getString("USER_NAME", "");
		String password = mPreferences.getString("USER_PASSWORD", "");
		if (!name.equals("") && !password.equals("")) {
			mEditName.setText(name);
			mEditPassword.setText(password);
		}

	}

	private boolean selectUser(String name, String password) {
		MeituanDataBaseHelper helper = MeituanDataBaseHelper
				.getInstanceDataBaseHelper(this);
		db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"select * from user where _name = ? and _password = ?",
				new String[] { name, password });
		boolean result = cursor.moveToNext();
		cursor.close();
		return result;
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (db != null) {
			if (db.isOpen()) {
				db.close();
			}
		}
	}
}
