package com.grilledmonkey.leproactivia;

import com.grilledmonkey.leproactivia.WebClient.SuperInterface;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class MainActivity extends Activity implements OnClickListener, SuperInterface {
	private View introLayout, hurrayLayout;
	private WebView webview;
	private SharedPreferences settings;
	private boolean isLoggedIn = false, locked = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		settings = getSharedPreferences(Config.CONFIG, Context.MODE_PRIVATE);
		isLoggedIn = settings.getBoolean(Config.IS_LOGGED_IN, isLoggedIn);
		initUI();
	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initUI() {


		introLayout = findViewById(R.id.intro_layout);
		hurrayLayout = findViewById(R.id.hurray_layout);
		webview = (WebView)findViewById(R.id.webview);
		webview.setWebViewClient(new WebClient(this));
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setLoadWithOverviewMode(true);
		webview.getSettings().setUseWideViewPort(true);
		findViewById(R.id.button).setOnClickListener(this);
		findViewById(R.id.logout).setOnClickListener(this);

		if(isLoggedIn) {
			showOkay();
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
			case R.id.button:
				showLogin();
				break;
			case R.id.logout:
				logout();
				break;
		}
	}

	private void logout() {
		CookieManager.getInstance().removeAllCookie();
		isLoggedIn = false;
		settings.edit().putBoolean(Config.IS_LOGGED_IN, isLoggedIn).commit();
		showWelcome();
	}

	private void showWelcome() {
		introLayout.setVisibility(View.VISIBLE);
		webview.setVisibility(View.GONE);
		hurrayLayout.setVisibility(View.GONE);
	}

	private void showLogin() {
		introLayout.setVisibility(View.GONE);
		webview.setVisibility(View.VISIBLE);
		hurrayLayout.setVisibility(View.GONE);
		webview.loadUrl(Config.LOGIN_URL);
	}

	private void showOkay() {
		introLayout.setVisibility(View.GONE);
		webview.setVisibility(View.GONE);
		hurrayLayout.setVisibility(View.VISIBLE);
	}

	@Override
	public void onLoginSuccess() {
		isLoggedIn = true;
		settings.edit().putBoolean(Config.IS_LOGGED_IN, isLoggedIn).commit();
		showOkay();
	}

	@Override
	public void onPageStarted() {
		locked = true;
	}

	@Override
	public void onPageFinished() {
		locked = false;
	}

	@Override
	public Context getContext() {
		return(this);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && locked) {
	    	Toast.makeText(this, R.string.khoouy, Toast.LENGTH_SHORT).show();
	        return(true);
	    }
	    return(super.onKeyDown(keyCode, event));
	}

}