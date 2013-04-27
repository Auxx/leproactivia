package com.grilledmonkey.leproactivia;

import com.grilledmonkey.lepropepyaka.Pepyaka;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class PepyakaService extends IntentService {
	private static final String PEPYAKA_SERVICE = "PepyakaService";

	public PepyakaService() {
		super(PEPYAKA_SERVICE);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Messenger messenger = intent.getParcelableExtra(Pepyaka.CALLBACK_MESSENGER);
		if(messenger != null) {
			Message message = Message.obtain();
			message.what = Pepyaka.CALLBACK_BITCH;
			Bundle data = new Bundle(1);
			data.putBoolean(Pepyaka.IS_LOGGED_IN, getSharedPreferences(Config.CONFIG, Context.MODE_PRIVATE).getBoolean(Config.IS_LOGGED_IN, false));
			message.setData(data);
			try {
				messenger.send(message);
			}
			catch(RemoteException e) {
				e.printStackTrace();
			}
			finally {
				message.recycle();
			}
		}
	}

}
