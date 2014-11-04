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
	
	/*����*/
	List<Book> list;
	public static final String TAG="MainActivity";
	public static int itemId;	//�����ĵ�ǰ��Itemid	
	
	/*��ѯͼ��*/
	public ListView lstv;
	public MyAdapter adp;
	public Button backBtn;
	public TextView text_empty;
	
	/*���ͼ��*/
	public Button addBtn;
	public EditText addBname,addAname,addDname;
	
	/*�޸�ͼ��*/
	public EditText modBname,modAname,modDname;
	
	


	
	/***************************** ***��ѯ����*** *******************************/
	
	public void queryBook() {
		// �趨�Զ��岼��
		final View view = getLayoutInflater().inflate(R.layout.query_item,null);

		// ��Ӱ�ť
		new AlertDialog.Builder(MainActivityDemo.this)
			.setView(view).setTitle("��ѯ").setIcon(R.drawable.miao2)
			.setPositiveButton("��ѯ", new android.content.DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				EditText edit = (EditText) view.findViewById(R.id.que_Bname);
				
				String queryName = edit.getText().toString();	//�õ���ѯ������
				Book isExistBook = null;

				Iterator<Book> it = list.iterator();	// ѭ�����ң��Ƿ��и�����
				
				while (it.hasNext()) {
					Book book = it.next();
					
					/*contains��ģ���Ƚϣ�equalsΪ��ȷ�Ƚ�*/
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
	
	
	/***************************** ***��Ӳ���*** *******************************/

	public void insertBook(){
		final View v = LayoutInflater.from(this).inflate(R.layout.insert_item,null);
		// ���ȷ����ť
		new AlertDialog.Builder(this)
			.setTitle("���ͼ��").setView(v).setIcon(R.drawable.miao2)
			.setPositiveButton("ȷ��",new android.content.DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					addBname = (EditText) v.findViewById(R.id.add_Bname);
					addAname = (EditText) v.findViewById(R.id.add_Aname);
					addDname = (EditText) v.findViewById(R.id.add_Dname);
					
					String Bname = addBname.getText().toString();
					String Aname = addAname.getText().toString();
					String Dname = addDname.getText().toString();
					
					if(("").equals(Bname) || ("").equals(Aname) || ("").equals(Dname)){
						
						Toast.makeText(MainActivityDemo.this, "��������ӣ������Ϊ�գ�", 0).show();
						
					} else {
						
						adp.list.add(new Book(Bname, Aname, Dname,R.drawable.msg));
						adp.notifyDataSetChanged();
					}
					
				}
			})
			.create().show();
	}
	
	
	
	/***************************** ***�޸Ĳ���*** *******************************/
	
	public void modifyBook(){
		final View v = LayoutInflater.from(this).inflate(R.layout.modify_item,null);
		modBname = (EditText) v.findViewById(R.id.mod_Bname);
		modAname = (EditText) v.findViewById(R.id.mod_Aname);
		modDname = (EditText) v.findViewById(R.id.mod_Dname);
		
		//���յ���������itemId
		final Book boo = list.get(itemId);
		System.err.println(itemId);
		
		modBname.setText(boo.getBname());
		modAname.setText(boo.getAname());
		modDname.setText(boo.getDname());
		
		new AlertDialog.Builder(this)
		.setTitle("�޸�ͼ��").setView(v)
		.setPositiveButton("ȷ��",new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				String Bname = modBname.getText().toString();
				String Aname = modAname.getText().toString();
				String Dname = modDname.getText().toString();
				
				adp.list.set(itemId, new Book(Bname,Aname,Dname,boo.getImage()));//���ı���ֵ������������
				adp.notifyDataSetChanged();
			}
		})
		.create().show();

	}
	
	
	/***************************** ***ɾ������*** *******************************/
	
	public void deleteBook(){
		// ���ȷ��(�϶�)��ť
		new AlertDialog.Builder(MainActivityDemo.this)
			.setTitle("��ʾ").setMessage("��ȷ��Ҫɾ����").setIcon(R.drawable.miao2)
			.setPositiveButton("ȷ��", new android.content.DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					list.remove(itemId);
					adp.notifyDataSetChanged(); //֪ͨ����
				}
			})
			.setNegativeButton("ȡ��", new android.content.DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			}).create().show();
	}

}
