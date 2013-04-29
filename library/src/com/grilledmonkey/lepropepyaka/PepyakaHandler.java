package com.grilledmonkey.lepropepyaka;

import java.lang.ref.WeakReference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * ������� ��� Messenger. �� ���������� � ������� LeproActivia � ������� ���
 * ������ �� ���� �������. ����� ���������� ����� ������, ������ LeproActivia
 * ����� ��� � ����� ���������� � ��������� handleMessage().
 * 
 * � ����������� ���������� �������� Pepyaka.OnResultListener. ������� ��� ��
 * �������� ������������� ��� ������� � ������� Pepyaka, ���� ������ �����
 * � �� ����.
 * 
 * @author Aux
 *
 */
public class PepyakaHandler extends Handler {
	private final WeakReference<Pepyaka.OnResultListener> listener;

	public PepyakaHandler(Pepyaka.OnResultListener listener) {
		this.listener = new WeakReference<Pepyaka.OnResultListener>(listener);
	}

	/**
	 * ������ ��� ��-�� ������, ��������� ������ � ���� �� �������� - ����������
	 * ��������� �� ������. ���� ������ ������, �� � ��� � ���.
	 */
	@Override
	public void handleMessage(Message message) {
		Bundle data = message.getData();
		Pepyaka.OnResultListener listener = this.listener.get();

		if(listener != null && message.what == Pepyaka.CALLBACK_BITCH && data != null) {
			listener.onResult(data.getBoolean(Pepyaka.IS_LOGGED_IN, false) ? Pepyaka.STATUS_LEPER : Pepyaka.STATUS_NOT_LEPER);
		}
	}
}
