package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.mainbook.R;
import com.example.vo.Book;

public class Data {
	static List<Book> list;
	
	public static List<Book> getData(){
		list = new ArrayList<Book>();
		
		list.add(new Book("���˲�����к���","����","������ѧ������",R.drawable.z_boy));
		list.add(new Book("�����Ľ�����","������˹","�����ձ�������",R.drawable.z_jiaoyu));
		list.add(new Book("��Ц���ˡ�","���","�����ż�������",R.drawable.z_xiaomian));
		list.add(new Book("��ս������䡷","�ն���-�ֿ�","����ͼ��ݳ�����",R.drawable.z_zhangzheng));
		list.add(new Book("������Ϧʰ��","³Ѹ","������ѧ������ ",R.drawable.z_zhaoxi));
		
		return list;
	}
}
