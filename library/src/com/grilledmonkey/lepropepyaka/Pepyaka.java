package com.grilledmonkey.lepropepyaka;

import android.content.Context;
import android.content.Intent;
import android.os.Messenger;

/**
 * �� ������� ���������� � LeproActivia ���������� � ������� Pepyaka.
 * ��� ���� ������ ������ ����������� ����� askStatus() � �������� ���
 * listener. ����� ������ ��������� ��������, listener �� ���� ����� ������.
 * 
 * ����� ��� �� ����� ��������� ��������, ������ �� �� ���� (: ���������
 * STATUS_* ��������� ��� ��� ���� ������� ����. ��� ���� �� �������
 * ������ �������.
 * 
 * @author Aux
 *
 */
public class Pepyaka {
	public static final String REQUEST_INTENT = "com.grilledmonkey.leproactivia.ATATA";
	public static final String IS_LOGGED_IN = "isLoggedIn";
	public static final String CALLBACK_MESSENGER = "callbackMessenger";

	public static final int CALLBACK_BITCH = 0;

	/**
	 * ���� � ��� lepra-only ����������, �� ������� ������ ������ �����
	 * ���������� ����� ��������� LeproActivia � ����������.
	 */
	public static final int STATUS_FAIL = -1;
	/**
	 * 146% �� �����! ��� ���, �����!
	 */
	public static final int STATUS_NOT_LEPER = 0;
	/**
	 * �������!
	 */
	public static final int STATUS_LEPER = 1;

	/**
	 * ���� ����� ���������� ����� � �������� LeproActivia � ������ ���
	 * ������� ������ �����. Listener ������� ������ �� �����
	 * ��������� onResult().
	 * 
	 * @param listener
	 */
	public static void askStatus(OnResultListener listener) {
		Intent intent = new Intent(Pepyaka.REQUEST_INTENT);
		Messenger messenger = new Messenger(new PepyakaHandler(listener));
		intent.putExtra(Pepyaka.CALLBACK_MESSENGER, messenger);
		if(listener.getContext().startService(intent) == null) {
			listener.onResult(STATUS_FAIL);
		}
	}

	/**
	 * ��������� ������� �����. ��� ����� ��������� Context ��� ���������
	 * ������� � ��������, ������� ��������� ������ ������� ������� Context
	 * � ������ getContext(). � ��� ����� ����� Null Pointer Exception.
	 * 
	 * @author Aux
	 *
	 */
	public static interface OnResultListener {
		public void onResult(int status);
		public Context getContext();
	}
}
