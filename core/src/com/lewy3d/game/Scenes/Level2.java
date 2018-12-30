package com.lewy3d.game.Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.lewy3d.game.Core.GameWorld;
import com.lewy3d.game.Core.Shapes.Cube;

public class Level2 extends GameWorld {

    @Override
    public void start() {
        Cube newCube = new Cube(Color.GOLD, 10, 10, 10);
        newCube.name = "Gold Cube";

        Instantiate(newCube);
    }

    @Override
    public void update(float deltaTime) {

    }

}
