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
 *  �����6��СԲ��
 * @author Es_ZzT
 *
 */
public class PasswordView extends RelativeLayout {
	private  Context ctx;
	private LinearLayout password_layout;
	/** ����Բ��ID **/
	public  final int[] PwdOvalID = new int[] { R.id.pwdOval_1, R.id.pwdOval_2, R.id.pwdOval_3, R.id.pwdOval_4,
			R.id.pwdOval_5, R.id.pwdOval_6 };
	/** ����Բ�� **/
	private TextView[] pwdOvals = new TextView[PwdOvalID.length];
	/** ����Բ���λ�� **/
	private int index = 0;
	/** �������� **/
	private String cryptPwd;
	/** ��ʵ���� **/
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
	 * ��ʼ������Բ��
	 * 
	 * @param view
	 */
	private void initPwdOval() {
		for (int i = 0; i < PwdOvalID.length; i++) {
			pwdOvals[i] = (TextView) password_layout.findViewById(PwdOvalID[i]);
		}
	}
	
	/**
	 * ��Ļ�߶�
	 * @return
	 */
	private int getScreenHeight() {
		DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}
	
	/**
	 * ��Ļ���
	 * @return
	 */
	private int getScreenWidth() {
		DisplayMetrics dm = ctx.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}
	
	/**
	 * ��ʾԲ��
	 * 
	 * @param index
	 */
	public void showPwdOval(int index) {
		pwdOvals[index].setText("��");
	}

	/**
	 * ����Բ��
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
	 *  ��ȡ��������
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
