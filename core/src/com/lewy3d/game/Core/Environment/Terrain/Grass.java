package com.lewy3d.game.Core.Environment.Terrain;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.lewy3d.game.Core.GameModel;

public class Grass extends GameModel {

    @Override
    protected void start() {

        loadModel("core/terrain/grass/grass.g3db");

    }

    @Override
    protected void update(float deltaTime) {

    }
}
