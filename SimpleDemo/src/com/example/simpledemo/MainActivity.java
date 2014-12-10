package com.example.simpledemo;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qzone.QZone;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ShareSDK.initSDK(this);
		textView = (TextView) findViewById(R.id.tvQq);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Platform qzone = ShareSDK.getPlatform(QZone.NAME);
				qzone.SSOSetting(false);
				qzone.showUser(null);
				
			}
		});
		
	}

}
