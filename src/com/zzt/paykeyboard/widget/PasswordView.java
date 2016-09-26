package com.zzt.paykeyboard.widget;

import com.example.paykeyboard.R;
import com.zzt.paykeyboard.listener.InputFinishListener;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *  密码框，6个小圆点
 * @author Es_ZzT
 *
 */
public class PasswordView extends RelativeLayout {
	private  Context ctx;
	private LinearLayout password_layout;
	/** 密码圆点ID **/
	public  final int[] PwdOvalID = new int[] { R.id.pwdOval_1, R.id.pwdOval_2, R.id.pwdOval_3, R.id.pwdOval_4,
			R.id.pwdOval_5, R.id.pwdOval_6 };
	/** 密码圆点 **/
	private TextView[] pwdOvals = new TextView[PwdOvalID.length];
	/** 密码圆点的位置 **/
	private int index = 0;
	/** 密码密文 **/
	private String cryptPwd;
	/** 真实密码 **/
	private String realPwd;
	
	public InputFinishListener inputFinishListener;

	public PasswordView(Context context) {
		this(context , null , 0);
		
	}
	
	public PasswordView(Context context, AttributeSet attrs){
		this(context , attrs , 0);
	}
	
	public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.ctx = context;
		initView();
	}
	
	private void initView() {
		LayoutInflater inflater = LayoutInflater.from(ctx);
		password_layout = (LinearLayout) inflater.inflate(R.layout.password_layout, null);
		LayoutParams lp = new LayoutParams(getScreenHeight()/15 * 8, getScreenHeight()/16);
		int margin = getScreenWidth()/30;
		lp.setMargins(margin, margin, margin, margin);
		password_layout.setLayoutParams(lp);
		password_layout.setGravity(Gravity.CENTER_VERTICAL);
		initPwdOval();
		addView(password_layout);
	}
	

	/**
	 * 初始化密码圆点
	 * 
	 * @param view
	 */
	private void initPwdOval() {
		for (int i = 0; i < PwdOvalID.length; i++) {
			pwdOvals[i] = (TextView) password_layout.findViewById(PwdOvalID[i]);
		}
	}
	
	/**
	 * 屏幕高度
	 * @return
	 */
	private int getScreenHeight() {
		DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}
	
	/**
	 * 屏幕宽度
	 * @return
	 */
	private int getScreenWidth() {
		DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}
	
	/**
	 * 显示圆点
	 * 
	 * @param index
	 */
	public void showPwdOval(int index) {
		pwdOvals[index].setText("●");
	}

	/**
	 * 隐藏圆点
	 * 
	 * @param index
	 */
	public void hidePwdOval(int index) {
		pwdOvals[index].setText("");
	}
	
	public void resetPwd() {
		for(int i=0; i<pwdOvals.length; i++) {
			hidePwdOval(i);
		}
		cryptPwd = "";
	}

	/**
	 *  获取密码密文
	 * @return
	 */
	public String getCryptPwd() {
		return cryptPwd;
	}

	public void setCryptPwd(String cryptPwd) {
		this.cryptPwd = cryptPwd;
	}

	public InputFinishListener getInputFinishListener() {
		return inputFinishListener;
	}

	public void setInputFinishListener(InputFinishListener inputFinishListener) {
		this.inputFinishListener = inputFinishListener;
	}
	
}
