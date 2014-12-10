package com.bawei.viewpager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.umeng.update.UmengUpdateAgent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class MainActivity extends Activity { 
	// viewPager右下角的脚标图片
	private ImageView[] imageViews = null;
	private ImageView mImageView = null;
	private ViewPager viewPager; 
	private List<View> mList;
	private ViewGroup group;
	private int mImageIndex = 0;
	private int[] image = {R.drawable.baobao,R.drawable.haipa,R.drawable.kanshu,R.drawable.music,R.drawable.xiayu,R.drawable.yanjing};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		UmengUpdateAgent.setUpdateOnlyWifi(false);
		 UmengUpdateAgent.update(this);
		group = (ViewGroup) findViewById(R.id.viewGroup);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		mList = new ArrayList<View>();
		for(int i = 0;i<image.length;i++){
			mImageView = new ImageView(MainActivity.this);
			mImageView.setBackgroundResource(image[i]); 
			mList.add(mImageView);
		}
		//对imageviews脚标进行填充  
		imageViews = new ImageView[mList.size()];  
		// 初始化小图标，自动滚动图片右下角的圆  
		setImageResources();
		viewPager.setAdapter(new ViewPageAdapter());
		viewPager.setOnPageChangeListener(new GuidePageChangeListener());
		//默认图片滚动
		startImageTimerTask();
		viewPager.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:  
				case MotionEvent.ACTION_MOVE: 
					// 停止图片滚动
					stopImageTimerTask(); 
					break;  
				case MotionEvent.ACTION_UP:
					// 开始图片滚动
					startImageTimerTask();
					break;
				}
				return false;
			}
		});



	}

	/**
	 * 装填图片数据
	 * 
	 * @param imageUrlList
	 * @param imageCycleViewListener
	 */
	public void setImageResources() {
		// 图片广告数量
		final int imageCount = mList.size();
		imageViews = new ImageView[imageCount];
		for (int i = 0; i < imageCount; i++) {
			mImageView = new ImageView(this);
			mImageView.setLayoutParams(new LayoutParams(20, 20));
			mImageView.setPadding(5, 5, 5, 5);
			imageViews[i] = mImageView;
			if (i == 0) {
				imageViews[i].setBackgroundResource(R.drawable.banner_dian_focus);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.banner_dian_blur);
			}
			group.addView(imageViews[i]);
		}
	}

	/**
	 * 开始轮播(手动控制自动轮播与否，便于资源控制)
	 */
	public void startImageCycle() {
		startImageTimerTask();
	}

	/**
	 * 暂停轮播——用于节省资源
	 */
	public void pushImageCycle() {
		stopImageTimerTask();
	}

	/**
	 * 开始图片滚动任务
	 */
	private void startImageTimerTask() {
		
		// 图片每3秒滚动一次
		mHandler.postDelayed(mImageTimerTask, 3000);
//		stopImageTimerTask();
	}

	/**
	 * 停止图片滚动任务
	 */
	private void stopImageTimerTask() {
		mHandler.removeCallbacks(mImageTimerTask);
	}

	private Handler mHandler = new Handler();

	/**
	 * 图片自动轮播Task
	 */
	private Runnable mImageTimerTask = new Runnable() {

		@Override
		public void run() {
			if (imageViews != null) {
				// 下标等于图片列表长度说明已滚动到最后一张图片,重置下标
				if ((++mImageIndex) == imageViews.length) {
					mImageIndex = 0;
				}
				viewPager.setCurrentItem(mImageIndex);
			}
		}
	};

	/**
	 * 轮播图片状态监听器
	 * 
	 * @author minking
	 */
	private final class GuidePageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int state) {
			if (state == ViewPager.SCROLL_STATE_IDLE)
				startImageTimerTask(); // 开始下次计时
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int index) {
			// 设置当前显示的图片下标
			mImageIndex = index;
			// 设置图片滚动指示器背景

			for (int i = 0; i < imageViews.length; i++) {

				imageViews[index].setBackgroundResource(R.drawable.banner_dian_focus);
				if(index != i){
					imageViews[i].setBackgroundResource(R.drawable.banner_dian_blur);
				}
			}

		}

	}

	//自定义的PagerAdapter
	class ViewPageAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			//判断View是否来自Object
			return arg0 == arg1;
		}

		@Override
		public Object instantiateItem(ViewGroup container, final int position) {
			// TODO Auto-generated method stub
			//添加图片
			View view = mList.get(position);
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			//删除图片
			View view = mList.get(position);
			container.removeView(view);
		}

	}
}
