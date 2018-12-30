package com.lewy3d.game;

import com.badlogic.gdx.Gdx;
import com.lewy3d.game.Core.UI.Text;

public class InfoText extends Text {

    @Override
    protected void start() {

    }

    @Override
    protected void update(float deltaTime) {
        text = "FPS: "+ Gdx.graphics.getFramesPerSecond()+"\n\nPress 'F' to load Level 2";
    }
}
