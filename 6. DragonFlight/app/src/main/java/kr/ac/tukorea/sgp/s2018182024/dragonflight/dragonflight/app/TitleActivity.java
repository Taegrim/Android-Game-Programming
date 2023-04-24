package kr.ac.tukorea.sgp.s2018182024.dragonflight.dragonflight.app;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.databinding.ActivityTitleBinding;

public class TitleActivity extends AppCompatActivity {
    private ActivityTitleBinding binding;
    private ValueAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTitleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        createAnimator();
    }

    public void onBtnStartGame(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void createAnimator() {
        animator = ValueAnimator.ofFloat(0.f, 0.5f);
        animator.setDuration(10000); // 몇 초동안 할 것인지 ms 설정
        animator.setRepeatCount(ValueAnimator.INFINITE);    // 얼마나 반복할 것인지
        animator.setInterpolator(new LinearInterpolator()); // 보간 방법
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                float progress = (Float)animator.getAnimatedValue();

                float x = -1 * binding.titleTree.getWidth() * progress;
                binding.titleTree.setTranslationX(x);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAnimator();
    }

    @Override
    protected void onPause() {
        stopAnimator();
        super.onPause();
    }

    private void startAnimator() {
        animator.start();
    }

    private void stopAnimator() {
        animator.end();
    }
}