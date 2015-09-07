package com.scxh.meituan.deal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;







import com.scxh.meituan.R;
import com.scxh.meituan.R.drawable;
import com.scxh.meituan.R.id;
import com.scxh.meituan.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class DealActivity extends Activity implements OnPageChangeListener {
	private MyViewPager mViewPager;
	private MyGridView mDealGridView;
	private EditText mSerchEditText;
	private ListView mDealListView;
	private long exitTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deal);
		mViewPager = (MyViewPager) findViewById(R.id.deal_view_pager);
		DealPagerAdapter adapter = new DealPagerAdapter();
		mViewPager.setAdapter(adapter);
		adapter.setPagerData(getPagerData());
		mViewPager.setOnPageChangeListener(this);
		//mDealGridView初始化
		setDealGridView();
		mSerchEditText = (EditText) findViewById(R.id.titile_edit);
		//搜索栏添加时间,跳转到搜索界面
		mSerchEditText.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(DealActivity.this, SerchActivity.class);
				startActivity(intent);
			}
		});
		//mDealListVie初始化
		setDealListView();
	}
	/**
	 * mDealGridView配置适配器
	 */
	private void setDealGridView() {
		mDealGridView = (MyGridView) findViewById(R.id.deal_spec_gridview);
		String[] from = {"imag"};
		int[] to = {R.id.deal_spec_imag};
		SimpleAdapter adapter = new SimpleAdapter(this, getDealGridViewData(), R.layout.item_deal_spec_imag, from, to);
		mDealGridView.setAdapter(adapter);
	}
	/**
	 * 获取mDealGridView数据源
	 * @return 返回List集合
	 */
	private List<HashMap<String, Object>> getDealGridViewData() {
		int[] ids = {R.drawable.ic_deal_spec2,R.drawable.ic_deal_spec3,R.drawable.ic_deal_spec4,R.drawable.ic_deal_spec5};
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> item = null;
		for (int i = 0; i < ids.length; i++) {
			item = new HashMap<String, Object>();
			item.put("imag", ids[i]);
			list.add(item);
		}
		return list;
	}

	/**
	 * 自定义ViewPager适配器
	 * @author scxh
	 *
	 */
	class DealPagerAdapter extends PagerAdapter {
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
	 * ViewPager获取数据源
	 * @return 返回List集合
	 */
	private List<View> getPagerData() {
		List<View> list = new ArrayList<View>();
		LayoutInflater inflater = LayoutInflater.from(this);
		int[] imag1 = { R.drawable.ic_category_0, R.drawable.ic_category_1,
				R.drawable.ic_category_2, R.drawable.ic_category_3,
				R.drawable.ic_category_4, R.drawable.ic_category_5,
				R.drawable.ic_category_6, R.drawable.ic_category_7 };
		// ���Դ
		String[] txt1 = { "美食", "电影", "酒店", "KTV", "优惠买单", "周边游", "丽人", "休闲娱乐" };
		int[] imag2 = { R.drawable.ic_category_8, R.drawable.ic_category_9,
				R.drawable.ic_category_10, R.drawable.ic_category_11,
				R.drawable.ic_category_12, R.drawable.ic_category_13,
				R.drawable.ic_category_14, R.drawable.ic_category_15 };
		String[] txt2 = { "今日新单", "购物", "生活服务", "旅游", "足疗按摩", "小吃快餐", "蛋糕甜点",
				"全部分类" };
		String[] from = { "imag", "txt" };
		int[] to = { R.id.category_imag, R.id.category_txt };
		SimpleAdapter adapter1 = new SimpleAdapter(this, setGridviewData(imag1,
				txt1), R.layout.item_gridview, from, to);
		GridView pagerGridview1 = (GridView) inflater.inflate(
				R.layout.pager_gridview, null);
		pagerGridview1.setAdapter(adapter1);
		list.add(pagerGridview1);
		SimpleAdapter adapter2 = new SimpleAdapter(this, setGridviewData(imag2,
				txt2), R.layout.item_gridview, from, to);
		GridView pagerGridview2 = (GridView) inflater.inflate(
				R.layout.pager_gridview, null);
		pagerGridview2.setAdapter(adapter2);
		list.add(pagerGridview2);
		return list;

	}


	/**
	 * GridView获取数据源
	 * @param imag 图片资源
	 * @param txt 图片下方的文字
	 * @return
	 */
	private List<HashMap<String, Object>> setGridviewData(int[] imag,
			String[] txt) {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> item = null;
		for (int i = 0; i < txt.length; i++) {
			item = new HashMap<String, Object>();
			item.put("imag", imag[i]);
			item.put("txt", txt[i]);
			list.add(item);
		}
		return list;
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
		ImageView imag1 = (ImageView) findViewById(R.id.view_pager_imag1);
		ImageView imag2 = (ImageView) findViewById(R.id.view_pager_imag2);

		switch (arg0) {
		case 0:
			if (imag1 != null)
				imag1.setImageResource(R.drawable.ic_radio_btn_on);
			if (imag2 != null)
				imag2.setImageResource(R.drawable.ic_radio_btn_off);
			break;
		case 1:
			if (imag1 != null)
				imag1.setImageResource(R.drawable.ic_radio_btn_off);
			if (imag2 != null)
				imag2.setImageResource(R.drawable.ic_radio_btn_on);
			break;

		default:
			break;
		}
	}
	
	private void setDealListView() {
		mDealListView = (ListView) findViewById(R.id.deal_listview);
		String[] from = new String[]{"title","content","icon"};
		int[] to = new int[]{R.id.simple_title_textview,R.id.simple_content_textview,R.id.simple_icon_image};
		SimpleAdapter adapter = new SimpleAdapter(this, getDealListViewData(), R.layout.item_deal_textview_layout, from, to);
		mDealListView.setAdapter(adapter);
	}
	private List<HashMap<String, Object>> getDealListViewData() {
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> item = new HashMap<String, Object>();
		item.put("title", "澳洲肥牛捞捞锅（双流店）");
		item.put("content", "¥52起");
		item.put("icon", R.drawable.listimage1);
		data.add(item);
		item = new HashMap<String, Object>();
		item.put("title", "月满大江火锅（九眼桥店）");
		item.put("content", "¥78起");
		item.put("icon", R.drawable.listimage2);
		data.add(item);
		item = new HashMap<String, Object>();
		item.put("title", "大嘴霸王排骨（第五大道店）");
		item.put("content", "¥79起");
		item.put("icon", R.drawable.listimage3);
		data.add(item);
		item = new HashMap<String, Object>();
		item.put("title", "绝城芋儿鸡（龙湖时代天街店）");
		item.put("content", "¥55起");
		item.put("icon", R.drawable.listimage4);
		data.add(item);
		item = new HashMap<String, Object>();
		item.put("title", "大嘴霸王排骨（第五大道店）");
		item.put("content", "¥79起");
		item.put("icon", R.drawable.listimage3);
		data.add(item);
		item = new HashMap<String, Object>();
		item.put("title", "绝城芋儿鸡（龙湖时代天街店）");
		item.put("content", "¥55起");
		item.put("icon", R.drawable.listimage4);
		data.add(item);
		item = new HashMap<String, Object>();
		item.put("title", "大嘴霸王排骨（第五大道店）");
		item.put("content", "¥79起");
		item.put("icon", R.drawable.listimage3);
		data.add(item);
		item = new HashMap<String, Object>();
		item.put("title", "绝城芋儿鸡（龙湖时代天街店）");
		item.put("content", "¥55起");
		item.put("icon", R.drawable.listimage4);
		data.add(item);
		item = new HashMap<String, Object>();
		item.put("title", "大嘴霸王排骨（第五大道店）");
		item.put("content", "¥79起");
		item.put("icon", R.drawable.listimage3);
		data.add(item);
		item = new HashMap<String, Object>();
		item.put("title", "绝城芋儿鸡（龙湖时代天街店）");
		item.put("content", "¥55起");
		item.put("icon", R.drawable.listimage4);
		data.add(item);
		return data;
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


