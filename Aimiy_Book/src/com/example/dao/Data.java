package com.example.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.mainbook.R;
import com.example.vo.Book;

public class Data {
	static List<Book> list;
	
	public static List<Book> getData(){
		list = new ArrayList<Book>();
		
		list.add(new Book("《了不起的男孩》","云晓","人民文学出版社",R.drawable.z_boy));
		list.add(new Book("《爱的教育》","亚米契斯","人民日报出版社",R.drawable.z_jiaoyu));
		list.add(new Book("《笑面人》","雨果","北京古籍出版社",R.drawable.z_xiaomian));
		list.add(new Book("《战争与回忆》","赫尔曼-沃克","北京图书馆出版社",R.drawable.z_zhangzheng));
		list.add(new Book("《朝花夕拾》","鲁迅","复旦大学出版社 ",R.drawable.z_zhaoxi));
		
		return list;
	}
}
