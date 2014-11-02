package org.xiangbalao.flowlayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.R.integer;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private FlowLayout mFlowLayout;

	private List<String> mDatas = new ArrayList<String>();

	
     private Button mButton;
	
     int n=7;
     int temp =0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
		

		// mRelativelayout.addView(mFlowLayout);
	}

	/**
	 * 添加带自定义背景的TextView
	 */
	private void andTextView() {
		
		for (int i = temp; i <temp+n; i++) {

			mDatas.add("字符串" + i);

		}
		temp=temp+n;
		
		
		
		// mFlowLayout = new FlowLayout(MainActivity.this);
		int layoutPadding = UIUtils.dip2px(13);
		mFlowLayout.setPadding(layoutPadding, layoutPadding, layoutPadding,
				layoutPadding);
		mFlowLayout.setHorizontalSpacing(layoutPadding);
		mFlowLayout.setVerticalSpacing(layoutPadding);

		int textPaddingV = UIUtils.dip2px(4);
		int textPaddingH = UIUtils.dip2px(7);
		int backColor = 0xffcecece;
		int radius = UIUtils.dip2px(5);
		// 代码动态创建一个图片
		GradientDrawable pressDrawable = DrawableUtils.createDrawable(
				backColor, backColor, radius);
		Random mRdm = new Random();
		for (int i = 0; i < mDatas.size(); i++) {
			TextView tv = new TextView(UIUtils.getContext());
			// 随机颜色的范围0x202020~0xefefef
			int red = 32 + mRdm.nextInt(208);
			int green = 32 + mRdm.nextInt(208);
			int blue = 32 + mRdm.nextInt(208);
			int color = 0xff000000 | (red << 16) | (green << 8) | blue;
			// 创建背景图片选择器
			GradientDrawable normalDrawable = DrawableUtils.createDrawable(
					color, backColor, radius);
			StateListDrawable selector = DrawableUtils.createSelector(
					normalDrawable, pressDrawable);
			tv.setBackgroundDrawable(selector);

			final String text = mDatas.get(i);
			tv.setText(text);
			tv.setTextColor(Color.rgb(255, 255, 255));
			tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
			tv.setGravity(Gravity.CENTER);
			tv.setPadding(textPaddingH, textPaddingV, textPaddingH,
					textPaddingV);
			tv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT)
							.show();

				}
			});
			mFlowLayout.addView(tv);
		}
	}

	/**
	 * 初始化
	 */
	private void init() {
		
	

		mFlowLayout = (FlowLayout) findViewById(R.id.mFlowLayout);
		mButton =(Button) findViewById(R.id.lodingText);
		mButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lodingText:
			
			andTextView();
			
			break;

		default:
			break;
		}
		
	}

}
