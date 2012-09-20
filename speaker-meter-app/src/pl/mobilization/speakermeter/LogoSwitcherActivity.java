package pl.mobilization.speakermeter;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.app.Activity;
import android.app.LauncherActivity;
import android.graphics.Matrix.ScaleToFit;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import android.widget.ImageView.ScaleType;

@ContentView(R.layout.logo_switcher)
public class LogoSwitcherActivity extends RoboActivity {

	private static final int[] PLATINUM = new int[] { R.drawable.cybercom,
			R.drawable.mobica, R.drawable.samsung };

	private static final int[] GOLD = new int[] { R.drawable.lsi,
			R.drawable.rf_logo, R.drawable.tomtom };

	private static final int[] SILVER = new int[] { R.drawable.agreeya,
			R.drawable.econsulting, R.drawable.ericpol, R.drawable.mobeelizer,
			R.drawable.symphonyteleca };

	private static final int[] PARTNERS = new int[] { R.drawable.confitura,
			R.drawable.gdg_krakow_lq, R.drawable.helion_wydawnictwo,
			R.drawable.lodzkie, R.drawable.mlodziwlodzi,
			R.drawable.mobiledeveloper, R.drawable.mobility, R.drawable.mslogo,
			R.drawable.pjug_small, R.drawable.poznan_jug,
			R.drawable.szczecin_jug, R.drawable.warszawa_jug

	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//
//		fillFlipper(viewFlipperPlatinum, PLATINUM);
//		fillFlipper(viewFlipperPlatinum, GOLD);
//		fillFlipper(viewFlipperPlatinum, SILVER);
//		fillFlipper(viewFlipperPlatinum, PARTNERS);

	}

	private void fillFlipper(ViewFlipper flipper, int[] ids) {
		for (int id : ids) {
			ImageView child = new ImageView(this);
			child.setBackgroundDrawable(getResources().getDrawable(id));
			child.setScaleType(ScaleType.FIT_CENTER);
			child.setAdjustViewBounds(true);
			LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			child.setLayoutParams(layoutParams);
			flipper.addView(child);
		}
	}
}
