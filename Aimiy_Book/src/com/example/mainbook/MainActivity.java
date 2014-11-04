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
		
		registerForContextMenu(lstv);// Ϊ�ı���ע�������Ĳ˵�
		
		/*��ȡ�������еĲ���*/
		list = new Data().getData();
		adp = new MyAdapter(list,this);
		
		lstv.setAdapter(adp);
		lstv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				
				Log.i("ѧ����Ϣ", "�ú�ѧϰ����������");
				
				itemId = position; //��itemId��ֵ
				return false;
			}
		});
			
		
		/*ʵ�ַ��ذ�ť�Ĺ���*/
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,MainActivity.class);//��ת
				intent.putExtra("query_return",true);
				startActivity(intent);
				finish();
			}
		});
		
		
		/*ʵ����Ӱ�ť�Ĺ���*/
		addBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				insertBook();
			}
		});
		registerForContextMenu(lstv);

	}
		
	
	/***************************** ���������Ĳ˵� *******************************/

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		MenuInflater inflator = new MenuInflater(this); // ʵ����һ��MenuInflater����
		inflator.inflate(R.menu.activity_main, menu); // �����˵��ļ�
		
		menu.setHeaderIcon(R.drawable.miao2); // Ϊ�˵�ͷ����ͼ��
		menu.setHeaderTitle("��ѡ��"); // Ϊ�˵�ͷ���ñ���

		super.onCreateContextMenu(menu, v, menuInfo);
		
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.meu_query:
			queryBook();
			Log.i("���", "��ѯ");
			break;
		case R.id.meu_insert:
			insertBook();
			Log.i("���", "���");
			break;
		case R.id.meu_modify:
			modifyBook();
			Log.i("���", "�޸�");
			break;
		case R.id.meu_delete:
			deleteBook();
			Log.i("���", "ɾ��");
			break;
		}
		return super.onContextItemSelected(item);
	}

}
