package trainedge.loginactivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    private ImageView img;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = (ImageView) findViewById(R.id.iv);
        img = (ImageView) findViewById(R.id.iv1);

    }

    protected void onResume() {
        super.onResume();
        logo.animate()
                .scaleX(1.5f)
                .scaleY(1.5f)
                .setDuration(5000)
                .setInterpolator(new FastOutLinearInInterpolator());

        img.animate()
                .scaleX(1.5f)
                .scaleY(1.5f)
                .setDuration(5000)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
    }
}
