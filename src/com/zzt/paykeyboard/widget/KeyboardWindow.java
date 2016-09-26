package com.zzt.paykeyboard.widget;

import java.util.Map;

import com.example.paykeyboard.R;
import com.zzt.paykeyboard.listener.InputFinishListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class KeyboardWindow extends PopupWindow implements InputFinishListener{
	private Context ctx;
	/** popWindow  **/
	private RelativeLayout content_layout;
	/** ������Ϣ�� **/
	private RelativeLayout transactionInfo_layout;
	/** ����� **/
	private PasswordView pwdView;
	/** ���� **/
	private KeyboardView keyboard;
	/** Text ��ֵ�� ������������**/
	private TextView key1;
	private TextView key2;
	private TextView key3;
	private TextView value1;
	private TextView value2;
	private TextView value3;
	private TextView[] keys;
	private TextView[] values;
	
	public KeyboardWindow(Context context) {
		this.ctx = context;
		initView(context);
	}

	private void initView(Context context) {
		initTransactionInfo();
		initKeyboard();
		initContent();
		// ����SelectPicPopupWindow��View
        this.setContentView(content_layout);
        // ����SelectPicPopupWindow��������Ŀ�
        this.setWidth(LayoutParams.MATCH_PARENT);
        // ����SelectPicPopupWindow��������ĸ�
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // ����SelectPicPopupWindow��������ɵ��
        this.setFocusable(true);
        // ����SelectPicPopupWindow�������嶯��Ч��
        this.setAnimationStyle(R.style.AnimBottom);
        // ʵ����һ��ColorDrawable��ɫΪ��͸��
        ColorDrawable dw = new ColorDrawable(0x66000000);
        // ����SelectPicPopupWindow��������ı���
        this.setBackgroundDrawable(dw);
	}
	
	private void initKeyboard() {
		keyboard = new KeyboardView(ctx);
		keyboard.setDerangement(true);
		keyboard.setCanInputPoint(false);
		keyboard.bindKBoardWithPwdView(pwdView); //�� ����д ��������޷���ʼ��
	}
	
	private void initTransactionInfo() {
		LayoutInflater inflater = LayoutInflater.from(ctx);
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		int margin = getScreenWidth()/8;
		lp.setMargins(margin, margin, margin, margin);
		transactionInfo_layout = (RelativeLayout) inflater.inflate(R.layout.transaction_content_layout, null);
		transactionInfo_layout.setLayoutParams(lp);
		pwdView = (PasswordView) transactionInfo_layout.findViewById(R.id.passwordView);
		pwdView.setInputFinishListener(this);
		key1 = (TextView) transactionInfo_layout.findViewById(R.id.tv_key1);
		key2 = (TextView) transactionInfo_layout.findViewById(R.id.tv_key2);
		key3 = (TextView) transactionInfo_layout.findViewById(R.id.tv_key3);
		value1 = (TextView) transactionInfo_layout.findViewById(R.id.tv_value1);
		value2 = (TextView) transactionInfo_layout.findViewById(R.id.tv_value2);
		value3 = (TextView) transactionInfo_layout.findViewById(R.id.tv_value3);
		keys = new TextView[]{key1,key2,key3};
		values = new TextView[]{value1,value2,value3};
		RelativeLayout exit_Img = (RelativeLayout) transactionInfo_layout.findViewById(R.id.iv_exit);
		exit_Img.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}
	
	private void initContent() {
		content_layout = new RelativeLayout(ctx);
		content_layout.addView(transactionInfo_layout);
		content_layout.addView(keyboard);
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
	 *  ����������Ϣ
	 * @param data Map
	 */
	public void setInfo(Map<String, String> data) {
		int position = 0;
		for(String key : data.keySet()) {
			keys[position].setText(key);
			String value = data.get(key);
			values[position].setText(value);
			position++;
		}
	}
	
	public void showWindow() {
		showAtLocation(((Activity) ctx).findViewById(android.R.id.content).getRootView(), Gravity.BOTTOM, 0, 0);
	}
	
	@Override
	public void onFinish() {
		pwdView.post(new Runnable() {
			@Override
			public void run() {
				pwdView.resetPwd();
				dismiss();
			}
		});
	}
}
