package com.grilledmonkey.lepropepyaka;

import android.content.Context;
import android.content.Intent;
import android.os.Messenger;

public class Pepyaka {
	public static final String REQUEST_INTENT = "com.grilledmonkey.leproactivia.ATATA";
	public static final String RESPONSE_INTENT = "com.grilledmonkey.leproactivia.PISH";
	public static final String IS_LOGGED_IN = "isLoggedIn";
	public static final String CALLBACK_MESSENGER = "callbackMessenger";

	public static final int CALLBACK_BITCH = 0;

	public static void askStatus(OnResultListener listener) {
		Intent intent = new Intent(Pepyaka.REQUEST_INTENT);
		Messenger messenger = new Messenger(new PepyakaHandler(listener));
		intent.putExtra(Pepyaka.CALLBACK_MESSENGER, messenger);
		if(listener.getContext().startService(intent) == null) {
			listener.onFail();
		}
	}

	public static interface OnResultListener {
		public void onResult(boolean isLoggedIn);
		public void onFail();
		public Context getContext();
	}
}
