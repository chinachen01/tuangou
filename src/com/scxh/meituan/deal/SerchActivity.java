package com.scxh.meituan.deal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.scxh.meituan.R;
import com.scxh.meituan.R.id;
import com.scxh.meituan.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class SerchActivity extends Activity implements TextWatcher,OnClickListener{
	private AutoCompleteTextView mAuto;
	private ImageView mImageBack,mImageClear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.serch_layout);
		mAuto = (AutoCompleteTextView) findViewById(R.id.serch_context_auto);
		mAuto.setThreshold(1);
		mAuto.addTextChangedListener(this);
		//设置按键点击事件
		mImageBack = (ImageView) findViewById(R.id.serch_back_image);
		mImageClear = (ImageView) findViewById(R.id.serch_clear_image);
		mImageBack.setOnClickListener(this);
		mImageClear.setOnClickListener(this);
		
	}
	private List<HashMap<String, Object>> getData() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("content", "h火锅");
		item.put("count", "20");
		list.add(item);
		item = new HashMap<String, Object>();
		item.put("content", "h牛肉火锅");
		item.put("count", "31");
		list.add(item);
		item = new HashMap<String, Object>();
		item.put("content", "z中餐");
		item.put("count", "23");
		list.add(item);
		item = new HashMap<String, Object>();
		item.put("content", "z土家");
		item.put("count", "14");
		list.add(item);
		item = new HashMap<String, Object>();
		item.put("content", "c串串");
		item.put("count", "20");
		list.add(item);
		item = new HashMap<String, Object>();
		item.put("content", "h羊肉火锅");
		item.put("count", "21");
		list.add(item);
		item = new HashMap<String, Object>();
		item.put("content", "z火锅");
		item.put("count", "22");
		list.add(item);
		item = new HashMap<String, Object>();
		item.put("content", "z火锅");
		item.put("count", "23");
		list.add(item);
		item = new HashMap<String, Object>();
		item.put("content", "z火锅豆腐");
		item.put("count", "28");
		list.add(item);
		
		return list;
		
	}
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
			String[] from = {"content","count"};
			int[] to = {R.id.serch_content_txt,R.id.serch_count_txt};
			SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.item_auto_serch_layout, from, to);
			mAuto.setAdapter(adapter);
	}
	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		if (s.length()>0) {
			mImageClear.setVisibility(View.VISIBLE);
		}else{
			mImageClear.setVisibility(View.INVISIBLE);
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.serch_back_image:
			finish();
			break;
		case R.id.serch_clear_image:
			mAuto.setText("");
			break;
		default:
			break;
		}
	}
}
