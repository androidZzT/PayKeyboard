package com.zzt.paykeyboard.widget;

import com.example.paykeyboard.R;
import com.zzt.paykeyboard.adapter.KeyBoardAdapter;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class KeyboardView extends RelativeLayout {
	private Context context;
	/** 键盘 **/
	private GridView mKeyboard;
	/** 键盘适配 **/
	private KeyBoardAdapter adapter;
	/** 是否乱序 **/
	private boolean isDerangement;
	/** 是否可输入小数点 **/
	private boolean canInputPoint = true;
	
	
	public KeyboardView(Context context) {
		this(context, null, 0);
	}
	
	public KeyboardView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public KeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		initView(context);
	}

	private void initView(Context context) {
		LayoutInflater inflater = LayoutInflater.from(context);
		RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.keyboard_layout, null);
		mKeyboard = (GridView) layout.findViewById(R.id.gv_keyboard);
		layout.setGravity(Gravity.BOTTOM);
		addView(layout);
	}
	
	public void bindKBoardWithPwdView(PasswordView pwdView) {
		adapter = new KeyBoardAdapter(context, pwdView, isDerangement, canInputPoint);
		mKeyboard.setAdapter(adapter);
	}

	public GridView getKeyboard() {
		return mKeyboard;
	}
	
	public boolean isDerangement() {
		return isDerangement;
	}
	public void setDerangement(boolean isDerangement) {
		this.isDerangement = isDerangement;
	}
	public boolean isCanInputPoint() {
		return canInputPoint;
	}
	public void setCanInputPoint(boolean canInputPoint) {
		this.canInputPoint = canInputPoint;
	}

}
