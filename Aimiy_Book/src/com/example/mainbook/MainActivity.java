package com.example.mainbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mainbook.R;
import com.example.dao.Data;
import com.example.myadapter.MyAdapter;

@SuppressWarnings("all")
public class MainActivity extends MainActivityDemo {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lstv = (ListView) findViewById(R.id.x_lstv);
		addBtn = (Button) findViewById(R.id.add_Button);
		backBtn = (Button) findViewById(R.id.x_back);
		text_empty = (TextView) findViewById(R.id.x_empty);
		
		registerForContextMenu(lstv);// 为文本框注册上下文菜单
		
		/*获取适配器中的参数*/
		list = new Data().getData();
		adp = new MyAdapter(list,this);
		
		lstv.setAdapter(adp);
		lstv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				
				Log.i("学生信息", "好好学习，天天向上");
				
				itemId = position; //给itemId赋值
				return false;
			}
		});
			
		
		/*实现返回按钮的功能*/
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,MainActivity.class);//跳转
				intent.putExtra("query_return",true);
				startActivity(intent);
				finish();
			}
		});
		
		
		/*实现添加按钮的功能*/
		addBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				insertBook();
			}
		});
		registerForContextMenu(lstv);

	}
		
	
	/***************************** 创建上下文菜单 *******************************/

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		MenuInflater inflator = new MenuInflater(this); // 实例化一个MenuInflater对象
		inflator.inflate(R.menu.activity_main, menu); // 解析菜单文件
		
		menu.setHeaderIcon(R.drawable.miao2); // 为菜单头设置图标
		menu.setHeaderTitle("请选择："); // 为菜单头设置标题

		super.onCreateContextMenu(menu, v, menuInfo);
		
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.meu_query:
			queryBook();
			Log.i("点击", "查询");
			break;
		case R.id.meu_insert:
			insertBook();
			Log.i("点击", "添加");
			break;
		case R.id.meu_modify:
			modifyBook();
			Log.i("点击", "修改");
			break;
		case R.id.meu_delete:
			deleteBook();
			Log.i("点击", "删除");
			break;
		}
		return super.onContextItemSelected(item);
	}

}
