package com.zzt.paykeyboard.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.example.paykeyboard.R;
import com.zzt.paykeyboard.listener.InputFinishListener;
import com.zzt.paykeyboard.widget.PasswordView;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;

public class KeyBoardAdapter extends BaseAdapter implements OnClickListener,OnTouchListener {
	private Context ctx;
	private List<Integer> keys = new ArrayList<Integer>();
	private PasswordView pwdView; //与键盘绑定的密码框
	private int index = 0;
	private StringBuilder sb = new StringBuilder();
	private boolean canInputPoint;
	public static final String TAG = "keyboard";
	private ArrayList<Button> btns = new ArrayList<Button>();
	private RelativeLayout key_del;
	private InputFinishListener inputFinishListener;
	
	public KeyBoardAdapter(Context ctx, PasswordView pwdView) {
		this(ctx,pwdView,false, true);
	}

	public KeyBoardAdapter(Context ctx, PasswordView pwdView ,boolean isDerangement, boolean inputPoint) {
		this.ctx = ctx;
		this.pwdView = pwdView;
		inputFinishListener = pwdView.getInputFinishListener();
		canInputPoint = inputPoint;
		initData(isDerangement);
	}
	
	private void initData(boolean isDerangement) {
		for(int i=1 ; i<10 ; i++) {
			keys.add(i);
		}
		if(isDerangement) { //乱序
			Collections.shuffle(keys);
		}
		keys.add(0); //占后三位 0不参与乱序
		keys.add(0);
		keys.add(0);
	}
	
	@Override
	public int getCount() {
		return keys.size();
	}

	@Override
	public Object getItem(int position) {
		return keys.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = View.inflate(ctx, R.layout.item_keyboard, null);
			int screenHeight = getScreenHeight();
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, screenHeight/12);
			convertView.setLayoutParams(lp);
			btns.add((Button)convertView.findViewById(R.id.btn_key));
			key_del = (RelativeLayout) convertView.findViewById(R.id.btn_del);
			convertView.setTag(key_del);
		} else {
			key_del = (RelativeLayout) convertView.getTag();
		}
		bindData(position);
		setListeners(position);
		return convertView;
	}
	
	private void bindData( int position) {
		Button key = btns.get(position);
		key.setSoundEffectsEnabled(false);
		key.setText(getItem(position)+"");
		if(position == 9) {
			if(canInputPoint) {
				key.setText(".");
			} else {
				key.setText("");
				key.setBackgroundColor(0xdbdbdd);
				key.setEnabled(false);
			}
		}
		if(position == 10) {
			key.setText("0");
		}
		if(position == 11) {
			key.setVisibility(View.GONE);
			key_del.setVisibility(View.VISIBLE);
		}
	}
	
	private void setListeners(int position) {
		btns.get(position).setOnClickListener(this);
		btns.get(position).setOnTouchListener(this);
//		holder.key.setOnClickListener(this);
//		holder.key.setOnTouchListener(this);
		key_del.setOnClickListener(this);
	}
	
	private int getScreenHeight() {
		DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

	private void reset() {
		sb = new StringBuilder();
		index = 0;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(index == 6) {
			return true;
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			v.setPressed(true);
			break;
		case MotionEvent.ACTION_UP:
			v.setPressed(false);
			String inputStr = ((Button)v).getText().toString();
			Log.e(TAG, "真实输入--->"+ inputStr);
			pwdView.showPwdOval(index);
			if (index < pwdView.PwdOvalID.length) {
				sb.append(inputStr);
				index++;
				Log.i(TAG, "index-------->" + index);
			}

			if (index == pwdView.PwdOvalID.length) { // 密码输入到第6位时
				Log.e(TAG, "password--->"+sb.toString());
				reset();
				//TODO 加密 给pwdView 赋值密文
//				new EncryptThread().start();
				inputFinishListener.onFinish();
				v.setPressed(false);
			}
			AutoClick();
		}
		return true; // 消费事件 不再向下传递
	}
	
	/**
	 * 自动化点击
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_key:
			v.setPressed(false);
//			Log.e(TAG, "自动点击--->"+((Button)v).getText());
			break;
		case R.id.btn_del: //删除按钮
			if (index > 0) {
				index--;
				sb.deleteCharAt(index);
				pwdView.hidePwdOval(index);
				// Log.i("tag", "index-------->" + index);
			}
			break;
		}
	}
	
	/**
	 * 自动点击键盘
	 */
	private void AutoClick() {
		for (int i = 0; i < 30; i++) {
			Random r = new Random();
			int random = r.nextInt(10);
			final Button key = btns.get(random);
			key.post(new Runnable() {
				@Override
				public void run() {
					key.performClick();
				}
			});
		}
	}
}
