package io.github.zeleven.mua;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

//启动动画页
public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.splash_layout) RelativeLayout splashLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        AlphaAnimation animation = new AlphaAnimation(0.0f , 1.0f );
        animation.setFillAfter(true);
        animation.setDuration(500);
        splashLayout.startAnimation(animation);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 500);
    }
}
