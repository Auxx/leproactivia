package com.grilledmonkey.lepropepyaka;

import java.lang.ref.WeakReference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class PepyakaHandler extends Handler {
	private static final String LOG_TAG = "PepyakaHandler";

	private final WeakReference<Pepyaka.OnResultListener> listener;

	public PepyakaHandler(Pepyaka.OnResultListener listener) {
		this.listener = new WeakReference<Pepyaka.OnResultListener>(listener);
	}

	@Override
    public void handleMessage(Message message) {
		Bundle data = message.getData();
		Pepyaka.OnResultListener listener = this.listener.get();

		Log.w(LOG_TAG, "message.what: " + message.what);

		if(listener != null && message.what == Pepyaka.CALLBACK_BITCH && data != null) {
			listener.onResult(data.getBoolean(Pepyaka.IS_LOGGED_IN, false));
		}
	}
}
