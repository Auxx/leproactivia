package com.grilledmonkey.leproactivia;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebClient extends WebViewClient {
	private final SuperInterface handler;
	private final Context context;
	private ProgressDialog progress;

	public WebClient(SuperInterface handler) {
		this.handler = handler;
		if(handler != null) {
			this.context = handler.getContext();
		}
		else {
			this.context = null;
		}
	}

	@Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
		if(Config.SUCCESS_URL.equals(url)) {
			if(handler != null) {
				handler.onLoginSuccess();
			}
			return(true);
		}
		return(false);
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		progress.dismiss();
	}

	@Override
	public void onPageStarted(WebView view, String url, Bitmap favicon) {
		progress = ProgressDialog.show(context, context.getString(R.string.progress_title), context.getString(R.string.progress_text), true, false);
	}

	public static interface SuperInterface {
		public void onLoginSuccess();
		public void onPageStarted();
		public void onPageFinished();
		public Context getContext();
	}
}
