package com.grilledmonkey.leproactiviatest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.grilledmonkey.lepropepyaka.Pepyaka;
import com.grilledmonkey.lepropepyaka.Pepyaka.OnResultListener;

/**
 * Примитивный пример использования библиотеки LeproPepyaka для определения
 * типа пользователя.
 * 
 * В данном примере статус юзера определяется по тапу на кнопку, обычно же
 * его определять имеет смысл при старте приложения. Тут стоит помнить,
 * что общение с сервисом LeproActivia происходит асинхронно.
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
	 * По клику на кнопку мы просим пепяку пообщацо с активией.
	 * 
	 * @param v
	 */
	public void handleClick(View v) {
		Pepyaka.askStatus(this);
	}

	/**
	 * Для работы с сервисами пепяке необходимо знать контекст приложения.
	 * Кстати, Activity - это Context (:
	 */
	@Override
	public Context getContext() {
		return(this);
	}

	/**
	 * Сервис нам что-то ответил, ща разберёмся!
	 */
	@Override
	public void onResult(int status) {
		String msg;
		switch(status) {
			case Pepyaka.STATUS_NOT_LEPER:
				msg = "Не лепер!";
				break;
			case Pepyaka.STATUS_LEPER:
				msg = "Братюня!";
				break;
			default:
				msg = "LeproActivia не установлена.";
				break;
		}
		Toast.makeText(this, "Status: " + msg, Toast.LENGTH_LONG).show();
	}

}
