package test.pixiolibgdx.game;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import test.pixiolibgdx.game.PixioLibgdx;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.numSamples = 4;
		initialize(new PixioLibgdx(), config);
	}
}
