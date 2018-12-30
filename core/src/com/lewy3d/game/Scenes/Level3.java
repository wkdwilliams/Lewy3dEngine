
package com.lewy3d.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.lewy3d.game.Core.Camera.FirstPersonController;
import com.lewy3d.game.Core.Component.Physics.RigidBody;
import com.lewy3d.game.Core.Environment.Terrain.Terrain;
import com.lewy3d.game.Core.GameObject;
import com.lewy3d.game.Core.GameWorld;
import com.lewy3d.game.Core.Shapes.Cube;

public class Level3 extends GameWorld {

    @Override
    public void start () {
        Cube box = new Cube(Color.RED, 50, 2, 50);
        box.name = "Ground";
        box.transform.position.y = -50;

        Cube box2 = new Cube(Color.BLUE, 10, 10, 10);
        box2.name = "Box2";
        box2.setTranslation(0, 100, 0);
        box2.addComponent(new RigidBody());

        FirstPersonController player = new FirstPersonController();
        player.transform.position.x = -150;
        player.name = "Player";

        Instantiate(player);
        Instantiate(box);
        Instantiate(box2);

        Instantiate(new Terrain());
    }

    @Override
    public void update (float deltaTime) {

    }

    @Override
    public void pause () {
    }

    @Override
    public void resume () {
    }

}