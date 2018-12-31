package com.lewy3d.game.Scenes.Level2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.lewy3d.game.Core.Camera.Camera;
import com.lewy3d.game.Core.GameObject;
import com.lewy3d.game.Core.GameWorld;
import com.lewy3d.game.Core.Shapes.Cube;

public class Level2 extends GameWorld {

    @Override
    public void start() {

        Camera camera = new Camera();
        camera.name = "camera";

        Cube newCube = new Cube(Color.GOLD, 10, 10, 10);
        newCube.name = "Gold Cube";

        camera.getCamera().lookAt(newCube.transform.position);

        Instantiate(newCube);
        Instantiate(camera);
    }

    @Override
    public void update(float deltaTime) {
        Camera camera = (Camera) GameObject.Find("camera");

        camera.transform.translate(5 * deltaTime, 0, 2 * deltaTime);
        camera.getCamera().lookAt(GameObject.Find("Gold Cube").transform.position);
    }

}
