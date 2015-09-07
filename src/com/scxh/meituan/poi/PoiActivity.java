package com.scxh.meituan.poi;

import java.util.ArrayList;
import java.util.List;

import com.scxh.meituan.R;
import com.scxh.meituan.R.drawable;
import com.scxh.meituan.R.id;
import com.scxh.meituan.R.layout;
import com.scxh.meituan.deal.SerchActivity;

import android.R.anim;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class PoiActivity extends Activity implements OnClickListener {
	private Button mButton1;
	private ImageView mImageSerch, mImageBack;
	private PopupWindow mPopupWindow1;
	private long exitTime;
	private ListView mListView;

	private ListView listView;
	private ListView subListView;
	private FirstAdapter firstAdapter;
	private SubAdapter subAdapter;

	String subListData[][] = new String[][] {
			null,
			
			new String[] {"北京菜","鲁菜","川菜","湘菜","湖北菜","江浙菜","粤菜","东北菜","新疆/清真","西北菜","云南菜","贵州菜","素菜","火锅","海鲜","小吃","快餐","日本料理","韩国料理","东南亚菜","西餐","","自助餐","面包","甜点"},
			new String[] {"相册 ","幽默 ","健康 ","社区 ","KTV汽车 ","房产 ","体育 ","娱乐 ","影院 ","数码 ","生活 "," 时尚 ","运程 ","游戏 ","新闻 "},
					null,
			new String[] { "全", "咖啡厅", "酒吧", "茶馆", "KTV", "游乐游艺", "公园",
					"景点/郊游", "洗浴", "足浴按摩", "文化艺术", "DIY手工坊", "桌球馆", "桌面游戏",
					"更多休闲娱乐" },
			new String[] { "全部", "咖啡厅1", "酒吧1", "茶馆2", "电影院1", "游乐游艺1", "公园1",
					"景点/郊游1", "洗1浴", "足浴按摩1", "文化艺术1", "DIY手工坊1", "桌球馆1", "桌面游戏1",
					"更多休闲娱乐1" },
					null,
					null,
			new String[] { "全部休闲娱2", "咖啡厅2", "酒吧2", "茶馆2", "KTV2", "电影院2", "游乐游艺2",
					"公园2", "景点/郊游2", "洗浴2", "足浴按摩2", "文化艺术2", "DIY手工坊2", "桌球馆2",
					"桌面游戏2" },
			new String[] { "全部休闲娱乐3", "咖啡厅3", "酒吧3", "茶馆3", "KTV3", "电影院3", "游乐游艺3",
					"公园3", "景点/郊游3", "洗浴3", "足浴按摩3", "文化艺术3", "DIY手工坊3", "桌球馆3",
					"桌面游戏3", "更多休闲娱乐3" },
					null};
	String ListData[] = new String[] { "全部频道", "美食", "休闲娱乐", "购物", "酒店", "丽人",
			"运动健身", "结婚", "亲子", "爱车", "生活服务" };
	int images[] = new int[] { R.drawable.ic_category_70,
			R.drawable.ic_category_60, R.drawable.ic_category_30,
			R.drawable.ic_category_20, R.drawable.ic_category_60,
			R.drawable.ic_category_50, R.drawable.ic_category_45,
			R.drawable.ic_category_50, R.drawable.ic_category_70,
			R.drawable.ic_category_65, R.drawable.ic_category_80 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_poi_layout);

		// 添加搜索事件
		mImageSerch = (ImageView) findViewById(R.id.titile_serch_image);
		mImageSerch.setOnClickListener(this);
		// 添加返回事件
		mImageBack = (ImageView) findViewById(R.id.titile_back_image);
		mImageBack.setOnClickListener(this);
		mButton1 = (Button) findViewById(R.id.poi_type_spinner);
		mButton1.setOnClickListener(this);
		mListView = (ListView) findViewById(R.id.poi_my_listview);
		FoodAdapter adapter = new FoodAdapter(this, getFoodData(),
				R.layout.item_deal_textview_layout);
		mListView.setAdapter(adapter);
//		mPopupWindow1.getContentView().setOnTouchListener(new OnTouchListener(){  
//		     @Override  
//		     public boolean onTouch(View v, MotionEvent event) {  
//		      // TODO Auto-generated method stub  
//		      mPopupWindow1.setFocusable(false);  
//		      mPopupWindow1.dismiss();  
//		      return true;  
//		     }
//		    });  
	}

	// ===================================设置一二级列表===================
	/**
	 * 种类二级列表初始化
	 */
	public void setTypeListView() {
		// 弹窗1
		View v = LayoutInflater.from(this).inflate(
				R.layout.item_poi_popup_window, null);
		// 实例化一二级菜单对象
		listView = (ListView) v.findViewById(R.id.listView);
		subListView = (ListView) v.findViewById(R.id.subListView);
		// 当前Button显示的文字
		String text = (String) mButton1.getText();
		// 创建适配器
		firstAdapter = new FirstAdapter(this, ListData, images, subListData,text);
		listView.setAdapter(firstAdapter);
		mPopupWindow1 = new PopupWindow(v, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		// 设置一级菜单事件监听
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				//判断是否有二级菜单,无则点击后关闭弹窗
				if (subListData[position] == null) {
					mPopupWindow1.dismiss();
					TextView txt = (TextView) view.findViewById(R.id.textview);
					mButton1.setText(txt.getText());
					mButton1.setTextColor(getResources().getColor(android.R.color.black));
				} else {
					subAdapter = new SubAdapter(getApplicationContext(),
							subListData, position);
					subListView.setAdapter(subAdapter);
					subListView
							.setOnItemClickListener(new OnItemClickListener() {

								@Override
								public void onItemClick(AdapterView<?> arg0,
										View arg1, int position, long arg3) {
									// TODO Auto-generated method stub
									mPopupWindow1.dismiss();
									TextView txt = (TextView) arg1
											.findViewById(R.id.textview1);
									mButton1.setText(txt.getText());
									mButton1.setTextColor(getResources().getColor(android.R.color.black));
								}
							});
				}
			}
		});

	}

	/**
	 * 设置二级菜单
	 */
	
	// ===================================设置一二级列表===================
	
	/**
	 * 按键点击事件
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.titile_serch_image:
			Intent intent = new Intent(this, SerchActivity.class);
			startActivity(intent);
			break;
		case R.id.titile_back_image:
			break;
		case R.id.poi_type_spinner:
			setTypeListView();
			// 这句代码很重要,如果没有这句代码,则ListView事件无法响应,
			mPopupWindow1.setBackgroundDrawable(new BitmapDrawable());
			mPopupWindow1.setFocusable(true);
			mPopupWindow1.setOutsideTouchable(true); 
			if (mPopupWindow1.isShowing()){
				mPopupWindow1.dismiss();
				mButton1.setTextColor(getResources().getColor(android.R.color.black));
			}
			else{
				mPopupWindow1.showAsDropDown(mButton1, 0, 0);
			}
			break;
		default:
			break;
		}
	}
  
	// 按两次返回退出应用程序
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitTime = System.currentTimeMillis() - exitTime;
			Log.e("tag", "" + exitTime);
			if (exitTime > 2000) {
				Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				Log.e("exit1", "" + exitTime);
				finish();
				System.exit(0);
				Log.e("exit2", "" + exitTime);
			}
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	private List<FoodBean> getFoodData() {
		List<FoodBean> list = new ArrayList<FoodBean>();
		FoodBean food = new FoodBean();
		food.setTitle("澳洲肥牛捞捞锅（双流店）");
		food.setContent("¥52起");
		food.setImage(R.drawable.listimage1);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		food = new FoodBean();
		food.setTitle("月满大江火锅（九眼桥店）");
		food.setContent("¥78起");
		food.setImage(R.drawable.listimage2);
		list.add(food);
		food = new FoodBean();
		food.setTitle("大嘴霸王排骨（第五大道店）");
		food.setContent("¥79起");
		food.setImage(R.drawable.listimage3);
		list.add(food);
		return list;
	}

	class FoodAdapter extends BaseAdapter {
		private Context mContext;
		private List<FoodBean> mData;
		private int mResourse;
		private LayoutInflater inflater;

		public FoodAdapter(Context context, List<FoodBean> data, int resourse) {
			// TODO Auto-generated constructor stub
			mContext = context;
			mData = data;
			mResourse = resourse;
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return mData.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		/**
		 * �?��优化:只有在convertView为空的情况下才创建新的View对象,达到View的复用�?
		 * 二级优化:利用view可以保存对象的方�?只有在convertView为空的情况下才执行findViewByID方法.
		 */
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder = null;
			if (convertView == null) {
				convertView = inflater.inflate(mResourse, null);
				viewHolder = new ViewHolder();
				viewHolder.title = (TextView) convertView
						.findViewById(R.id.simple_title_textview);
				viewHolder.content = (TextView) convertView
						.findViewById(R.id.simple_content_textview);
				viewHolder.icon = (ImageView) convertView
						.findViewById(R.id.simple_icon_image);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.title.setText(mData.get(position).getTitle());
			viewHolder.content.setText(mData.get(position).getContent());
			viewHolder.icon.setImageResource((mData.get(position).getImage()));
			return convertView;
		}

		class ViewHolder {
			TextView title;
			TextView content;
			ImageView icon;
		}

	}

	class FoodBean {
		private String title;
		private String content;
		private int image;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public int getImage() {
			return image;
		}

		public void setImage(int image) {
			this.image = image;
		}

	}

}