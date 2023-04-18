package kr.ac.tukorea.sgp.s2018182024.dragonflight.game;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Metrics;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.Sprite;

public class Background extends Sprite {

    public Background(int resId) {
        super(resId, Metrics.gameWidth / 2, Metrics.gameHeight / 2,
                Metrics.gameWidth, Metrics.gameHeight);
        float height = bitmap.getHeight() * Metrics.gameWidth / bitmap.getWidth();
        setSize(Metrics.gameWidth, height);
    }
}
