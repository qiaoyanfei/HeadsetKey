package com.qiao.headsetkey;

import java.lang.reflect.Method;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;

public class HeadsetPlugReceiver extends BroadcastReceiver {

	@SuppressWarnings("unchecked")
	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.hasExtra("state")) {
			if (intent.getIntExtra("state", 0) == 0
					&& intent.getIntExtra("from_smartkey", 0) == 0) {
			} else if (intent.getIntExtra("state", 0) == 1) {

				AudioManager localAudioManager = (AudioManager) context
						.getSystemService(Context.AUDIO_SERVICE);
				try {
					if (Build.VERSION.SDK_INT < 16) {
						// Intent localIntent = new Intent();
						// localIntent.putExtra("name", intent.);
						// localIntent.putExtra("state", paramInt1);
						// localIntent.putExtra("microphone", paramInt2);
						// localIntent.putExtra("from_smartkey", 1);
						intent.putExtra("from_smartkey", 1);
						intent.putExtra("state", 0);
//						intent.addFlags(1073741824);
						// localIntent.setAction("android.intent.action.HEADSET_PLUG");
						Class localClass1 = Class
								.forName("android.app.ActivityManagerNative");

						Method localMethod1 = localClass1.getMethod(
								"broadcastStickyIntent", new Class[] {
										Intent.class, String.class });
						localMethod1.setAccessible(true);
						localMethod1.invoke(localClass1, new Object[] { intent,
								null });
						return;
					}
					Class localClass2 = localAudioManager.getClass();
					Class[] arrayOfClass = new Class[3];
					arrayOfClass[0] = Integer.TYPE;
					arrayOfClass[1] = Integer.TYPE;
					arrayOfClass[2] = String.class;
					Method localMethod2 = localClass2.getMethod(
							"setWiredDeviceConnectionState", arrayOfClass);
					localMethod2.setAccessible(true);
					int paramInt2 = intent.getIntExtra("microphone", -1);
					Object[] arrayOfObject = new Object[3];
					if (paramInt2 == 1) {

						int i = 4;

						arrayOfObject[0] = Integer.valueOf(i);
						arrayOfObject[1] = Integer.valueOf(0);
						String name = intent.getStringExtra("name");
						arrayOfObject[2] = name;
						localMethod2.invoke(localAudioManager, arrayOfObject);

					}

					else if (paramInt2 == 0) {

						int i = 0x4;

						arrayOfObject[0] = Integer.valueOf(i);
						arrayOfObject[1] = Integer.valueOf(0);
						String name = intent.getStringExtra("name");
						arrayOfObject[2] = name;
						localMethod2.invoke(localAudioManager, arrayOfObject);
					}

				} catch (Throwable localThrowable) {
					// a.b(localThrowable);
					return;
				}

			}
		}
	}

}
