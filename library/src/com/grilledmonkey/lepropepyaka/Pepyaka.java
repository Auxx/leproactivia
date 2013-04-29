package com.grilledmonkey.lepropepyaka;

import android.content.Context;
import android.content.Intent;
import android.os.Messenger;

/**
 * Всё общение приложения с LeproActivia происходит с помощью Pepyaka.
 * Нам надо просто дёрнуть статический метод askStatus() и передать ему
 * listener. Когда сервис соизволит ответить, listener об этом сразу узнает.
 * 
 * Также тут мы видим несколько констант, менять их не надо (: Константы
 * STATUS_* расскажут нам кто есть текущий юзер. Все виды не леперов
 * меньше единицы.
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
	 * Если у вас lepra-only приложение, то получив данный статус можно
	 * предложить юзеру поставить LeproActivia и радоваться.
	 */
	public static final int STATUS_FAIL = -1;
	/**
	 * 146% не лепер! Бей его, ребзя!
	 */
	public static final int STATUS_NOT_LEPER = 0;
	/**
	 * Братюня!
	 */
	public static final int STATUS_LEPER = 1;

	/**
	 * Этот метод инициирует связь с сервисом LeproActivia и просит его
	 * вернуть статус юзера. Listener получит статус во время
	 * обработки onResult().
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
	 * Слушатель статуса юзера. Нам также требуется Context для успешного
	 * общения с сервисом, поэтому слушатель должен вернуть текущий Context
	 * в методе getContext(). А без этого будет Null Pointer Exception.
	 * 
	 * @author Aux
	 *
	 */
	public static interface OnResultListener {
		public void onResult(int status);
		public Context getContext();
	}
}
