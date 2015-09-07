package com.scxh.meituan.poi;

import java.util.List;

import com.scxh.meituan.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FirstAdapter extends BaseAdapter {

	Context context; // 上下文
	LayoutInflater inflater;
	String[] datas;
	int last_item;
	int[] images;
	private String[][] subDatas;
	private String text;

	/**
	 * 
	 * @param context
	 *            引用上下文
	 * @param datas
	 *            一级菜单数据
	 * @param images
	 *            指示二级菜单的箭头图标
	 * @param subDatas
	 *            二级菜单数据
	 * @param text
	 *            当前按键上显示的文字
	 */
	public FirstAdapter(Context context, String[] datas, int[] images,
			String[][] subDatas, String text) {
		this.context = context;
		this.datas = datas;
		this.images = images;
		this.subDatas = subDatas;
		this.text = text;
		inflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = inflater.inflate(R.layout.mylist_item, null);
		// 此处不可采用常规的优化代码方法,否则会导致后面的判断失效
		TextView textView = (TextView) convertView.findViewById(R.id.textview);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.imageview);
		ImageView imageNext = (ImageView) convertView
				.findViewById(R.id.imageview_next);
		//判断一级菜单是否等于或包含按键内容,有则改变颜色
		boolean temp = false;
		if (subDatas[position] != null) {
			for (int i = 0; i < subDatas[position].length; i++) {
				if (subDatas[position][i].equals(text)) {
					temp = true;
					break;
				}
			}
		}
		if (datas[position].equals(text) || temp) {
			textView.setTextColor(context.getResources().getColor(
					R.color.title_normal_color));
		}
		textView.setText(datas[position]);
		imageView.setBackgroundResource(images[position]);
		if (subDatas[position] != null)
			imageNext.setImageResource(R.drawable.ic_global_arrow_right);
		return convertView;
	}

}
