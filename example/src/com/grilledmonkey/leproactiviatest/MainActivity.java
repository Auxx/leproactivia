package com.grilledmonkey.leproactiviatest;

import com.grilledmonkey.lepropepyaka.Pepyaka;
import com.grilledmonkey.lepropepyaka.Pepyaka.OnResultListener;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity implements OnResultListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onResult(boolean isLoggedIn) {
		Toast.makeText(this, "Status: " + (isLoggedIn ? "LEPER!" : "HUJ ):"), Toast.LENGTH_LONG).show();
	}

	@Override
	public Context getContext() {
		return(this);
	}

	@Override
	public void onFail() {
		Toast.makeText(this, "FAIL WHALE!", Toast.LENGTH_LONG).show();
	}

	public void handleClick(View v) {
		Pepyaka.askStatus(this);
	}

}
