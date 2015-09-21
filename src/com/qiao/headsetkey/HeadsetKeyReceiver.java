package com.qiao.headsetkey;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

public class HeadsetKeyReceiver extends BroadcastReceiver {
	// 通知MEDIA_BUTTON的广播
	public static final String MEDIA_BUTTON = "com.godinsec.seland.MEDIA_BUTTON";

	public void onReceive(Context context, Intent intent) {
		String intentAction = intent.getAction();
		Log.e("HeadsetKeyReceiver", "接收广播");
	
		if (Intent.ACTION_MEDIA_BUTTON.equals(intentAction)) {

			KeyEvent event = (KeyEvent) intent
					.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
			if (event == null) {
				return;
			}

			int action = event.getAction();

			if (action == KeyEvent.ACTION_UP) {
				Log.e("HeadsetKeyReceiver", "按键");
				Intent notifyIntent = new Intent(MEDIA_BUTTON);
				context.sendBroadcast(notifyIntent);
			}
			abortBroadcast();

		}
	}
}