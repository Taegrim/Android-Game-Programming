package kr.ac.tukorea.sgp.s2018182024.dragonflight.dragonflight.game;

import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.view.Metrics;
import kr.ac.tukorea.sgp.s2018182024.dragonflight.framework.object.Sprite;

public class Background extends Sprite {

    public Background(int resId, float speed) {
        super(resId, Metrics.gameWidth / 2, Metrics.gameHeight / 2,
                Metrics.gameWidth, Metrics.gameHeight);
        float height = bitmap.getHeight() * (Metrics.gameWidth / bitmap.getWidth());
        setSize(Metrics.gameWidth, height);
    }

}
