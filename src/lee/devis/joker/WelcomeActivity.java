package lee.devis.joker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
		finish();

	}
}
