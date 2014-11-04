package com.example.myadapter;

import java.io.Serializable;
import java.util.List;

import com.example.mainbook.R;
import com.example.vo.Book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("all")
public class MyAdapter extends BaseAdapter {
	private Context context;
	private ViewHolder vh;
	public static List<Book> list;
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			
			convertView = LayoutInflater.from(context).inflate(R.layout.listview_item,null);
			vh = new ViewHolder();
			vh.v_image= (ImageView) convertView.findViewById(R.id.x_imgv);
			vh.v_Bname = (TextView) convertView.findViewById(R.id.x_Bname);
			vh.v_Aname = (TextView) convertView.findViewById(R.id.x_Aname);
			vh.v_Dname = (TextView) convertView.findViewById(R.id.x_Dname);
			convertView.setTag(vh);
			
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		
		final Book book = list.get(position);
		vh.v_image.setImageResource(book.getImage());
		vh.v_Bname.setText(book.getBname());
		vh.v_Aname.setText(book.getAname());
		vh.v_Dname.setText(book.getDname());
		
		return convertView;
	}
	
	public MyAdapter(List<Book> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}
	
	class ViewHolder implements Serializable{
		  ImageView v_image;
		  TextView v_Bname,v_Aname,v_Dname;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
 
}
