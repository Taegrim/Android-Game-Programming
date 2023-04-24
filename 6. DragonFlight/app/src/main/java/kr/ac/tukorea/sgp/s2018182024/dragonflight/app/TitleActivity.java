package kr.ac.tukorea.sgp.s2018182024.dragonflight.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.databinding.ActivityTitleBinding;

public class TitleActivity extends AppCompatActivity {
    private ActivityTitleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTitleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onBtnStartGame(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    private float progress;
    public void onBtnProgress(View view) {
        progress += 0.01f;

        float x = -1 * binding.titleTree.getWidth() * progress;
        binding.titleTree.setTranslationX(x);
    }
}