package com.grilledmonkey.leproactiviatest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.grilledmonkey.lepropepyaka.Pepyaka;
import com.grilledmonkey.lepropepyaka.Pepyaka.OnResultListener;

/**
 * ����������� ������ ������������� ���������� LeproPepyaka ��� �����������
 * ���� ������������.
 * 
 * � ������ ������� ������ ����� ������������ �� ���� �� ������, ������ ��
 * ��� ���������� ����� ����� ��� ������ ����������. ��� ����� �������,
 * ��� ������� � �������� LeproActivia ���������� ����������.
 * 
 * @author Aux
 *
 */
public class MainActivity extends Activity implements OnResultListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * �� ����� �� ������ �� ������ ������ �������� � ��������.
	 * 
	 * @param v
	 */
	public void handleClick(View v) {
		Pepyaka.askStatus(this);
	}

	/**
	 * ��� ������ � ��������� ������ ���������� ����� �������� ����������.
	 * ������, Activity - ��� Context (:
	 */
	@Override
	public Context getContext() {
		return(this);
	}

	/**
	 * ������ ��� ���-�� �������, �� ���������!
	 */
	@Override
	public void onResult(int status) {
		String msg;
		switch(status) {
			case Pepyaka.STATUS_NOT_LEPER:
				msg = "�� �����!";
				break;
			case Pepyaka.STATUS_LEPER:
				msg = "�������!";
				break;
			default:
				msg = "LeproActivia �� �����������.";
				break;
		}
		Toast.makeText(this, "Status: " + msg, Toast.LENGTH_LONG).show();
	}

}
