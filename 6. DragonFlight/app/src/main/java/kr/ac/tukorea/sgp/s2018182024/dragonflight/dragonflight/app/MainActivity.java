package kr.ac.tukorea.sgp.s2018182024.dragonflight.dragonflight.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.dragonflight.game.MainScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.scene.BaseScene;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.view.GameView;

public class MainActivity extends AppCompatActivity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        gameView.setFullScreen();
        setContentView(gameView);

        new MainScene().pushScene();
    }

    @Override
    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }

    @Override
    protected void onDestroy() {
        BaseScene.popAllScene();
        super.onDestroy();
    }
}