package kr.ac.tukorea.sgp.s2018182024.samplegame.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.ac.tukorea.sgp.s2018182024.samplegame.game.MainScene;
import kr.ac.tukorea.sgp.s2018182024.samplegame.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new MainScene().pushScene();
    }
}