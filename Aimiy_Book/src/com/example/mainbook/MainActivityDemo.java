package com.example.mainbook;

import java.util.Iterator;
import java.util.List;

import com.example.mainbook.R;
import com.example.myadapter.MyAdapter;
import com.example.vo.Book;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("all")
public class MainActivityDemo extends Activity {
	
	/*公用*/
	List<Book> list;
	public static final String TAG="MainActivity";
	public static int itemId;	//操作的当前的Itemid	
	
	/*查询图书*/
	public ListView lstv;
	public MyAdapter adp;
	public Button backBtn;
	public TextView text_empty;
	
	/*添加图书*/
	public Button addBtn;
	public EditText addBname,addAname,addDname;
	
	/*修改图书*/
	public EditText modBname,modAname,modDname;
	
	


	
	/***************************** ***查询操作*** *******************************/
	
	public void queryBook() {
		// 设定自定义布局
		final View view = getLayoutInflater().inflate(R.layout.query_item,null);

		// 添加按钮
		new AlertDialog.Builder(MainActivityDemo.this)
			.setView(view).setTitle("查询").setIcon(R.drawable.miao2)
			.setPositiveButton("查询", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText edit = (EditText) view.findViewById(R.id.que_Bname);
				
				String queryName = edit.getText().toString();	//得到查询的名字
				Book isExistBook = null;

				Iterator<Book> it = list.iterator();	// 循环查找，是否有该名字
				
				while (it.hasNext()) {
					Book book = it.next();
					
					/*contains是模糊比较，equals为精确比较*/
					if (book.getBname().equals(queryName)) {
						isExistBook = book;
					}
				}
				list.clear();
				if (isExistBook != null) {
					list.add(isExistBook);
				} else {
					lstv.setVisibility(View.GONE);
					text_empty.setVisibility(View.VISIBLE);
				}
				backBtn.setVisibility(View.VISIBLE);
				adp.notifyDataSetChanged();
			}
		}).create().show();
		
	}
	
	
	/***************************** ***添加操作*** *******************************/

	public void insertBook(){
		final View v = LayoutInflater.from(this).inflate(R.layout.insert_item,null);
		// 添加确定按钮
		new AlertDialog.Builder(this)
			.setTitle("添加图书").setView(v).setIcon(R.drawable.miao2)
			.setPositiveButton("确定",new android.content.DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					addBname = (EditText) v.findViewById(R.id.add_Bname);
					addAname = (EditText) v.findViewById(R.id.add_Aname);
					addDname = (EditText) v.findViewById(R.id.add_Dname);
					
					String Bname = addBname.getText().toString();
					String Aname = addAname.getText().toString();
					String Dname = addDname.getText().toString();
					
					if(("").equals(Bname) || ("").equals(Aname) || ("").equals(Dname)){
						
						Toast.makeText(MainActivityDemo.this, "请认真添加，各项不能为空！", 0).show();
						
					} else {
						
						adp.list.add(new Book(Bname, Aname, Dname,R.drawable.msg));
						adp.notifyDataSetChanged();
					}
					
				}
			})
			.create().show();
	}
	
	
	
	/***************************** ***修改操作*** *******************************/
	
	public void modifyBook(){
		final View v = LayoutInflater.from(this).inflate(R.layout.modify_item,null);
		modBname = (EditText) v.findViewById(R.id.mod_Bname);
		modAname = (EditText) v.findViewById(R.id.mod_Aname);
		modDname = (EditText) v.findViewById(R.id.mod_Dname);
		
		//接收到传过来的itemId
		final Book boo = list.get(itemId);
		System.err.println(itemId);
		
		modBname.setText(boo.getBname());
		modAname.setText(boo.getAname());
		modDname.setText(boo.getDname());
		
		new AlertDialog.Builder(this)
		.setTitle("修改图书").setView(v)
		.setPositiveButton("确定",new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				String Bname = modBname.getText().toString();
				String Aname = modAname.getText().toString();
				String Dname = modDname.getText().toString();
				
				adp.list.set(itemId, new Book(Bname,Aname,Dname,boo.getImage()));//将改变后的值放入适配器中
				adp.notifyDataSetChanged();
			}
		})
		.create().show();

	}
	
	
	/***************************** ***删除操作*** *******************************/
	
	public void deleteBook(){
		// 添加确定(肯定)按钮
		new AlertDialog.Builder(MainActivityDemo.this)
			.setTitle("提示").setMessage("你确定要删除吗？").setIcon(R.drawable.miao2)
			.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					list.remove(itemId);
					adp.notifyDataSetChanged(); //通知更新
				}
			})
			.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			}).create().show();
	}

}
