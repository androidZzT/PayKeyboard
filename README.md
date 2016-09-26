仿微信支付的Demo
===

用法
---
######初始化
```java
public KeyboardWindow(Context context) {
		this.ctx = context;
		initView(context);
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
