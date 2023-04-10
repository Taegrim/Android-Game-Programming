package kr.ac.tukorea.sgp.s2018182024.dragonflight.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.R;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.GameView;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.game.MainScene;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);

        new MainScene().pushScene();
    }
}