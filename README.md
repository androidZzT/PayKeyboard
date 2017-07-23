仿微信支付的键盘
===
输入6位密码后自动收起键盘，然后可以通过mKeyWindow.getPassword获取输入密码。
密码键盘支持乱序，支持是否可以输入小数点。
可以通过setInfo方法传入map 设置文字提示内容。

用法
---
#### 初始化
```java
	public KeyboardWindow(Context context) {
		this(context,false,true);
	}
	
	
	/**
	 * 
	 * @param context
	 * @param isDerangement 键盘是否乱序
	 * @param canInputPoint 键盘是否可输入小数点
	 */
	public KeyboardWindow(Context context, boolean isDerangement, boolean canInputPoint) {
		this.ctx = context;
		this.isDerangement = isDerangement;
		this.canInputPoint = canInputPoint;
		initView(context);
	}
```
#### 弹出键盘
```java
	public void showWindow() {
		showAtLocation(((Activity) ctx).findViewById(android.R.id.content).getRootView(), Gravity.BOTTOM, 0, 0);
	}
```
#### 设置文字提示
```java
/**
	 *  设置内容信息
	 * @param data Map 将数据封装成map
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
```
#### 获取密码
```java
	public String getPassword() {
		return pwdView.getCryptPwd();
	}
```

