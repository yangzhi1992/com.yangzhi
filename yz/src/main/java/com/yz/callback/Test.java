package com.yz.callback;

public class Test {
	public static void main(String[] args) {
		final String name = "yangzhi";
		DoCallBack dcb = new DoCallBack();
		Object ret = dcb.CallBack(new CallBack(){
			@Override
			public Object callBack() {
				return name;
			}
		});
		
		System.out.println(ret);
	}
}
