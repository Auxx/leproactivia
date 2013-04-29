package com.grilledmonkey.lepropepyaka;

import java.lang.ref.WeakReference;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Хендлер для Messenger. Мы обращаемся к сервису LeproActivia и передаём ему
 * ссылку на этот хендлер. После выполнения своей работы, сервис LeproActivia
 * пошлёт нам в ответ информация и сработает handleMessage().
 * 
 * В конструктор необходимо передать Pepyaka.OnResultListener. Тащемта это всё
 * делается автоматически при общении с классов Pepyaka, сюда лазить кагбе
 * и не надо.
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
	 * Сервис нам чё-та заслал, проверяем данные и если всё сходится - отправляем
	 * результат на родину. Если сервис пиздит, то и хуй с ним.
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
