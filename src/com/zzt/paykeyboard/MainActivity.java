package com.zzt.paykeyboard;

import com.example.paykeyboard.R;
import com.zzt.paykeyboard.adapter.KeyBoardAdapter;
import com.zzt.paykeyboard.widget.KeyboardWindow;
import com.zzt.paykeyboard.widget.PasswordView;
import com.zzt.paykeyboard.widget.KeyboardView;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends Activity {
	private Button btn;
	private KeyboardWindow keyWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		keyWindow = new KeyboardWindow(this);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				keyWindow.showWindow();
			}
		});
	}

}
