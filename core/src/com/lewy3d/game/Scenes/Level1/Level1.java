package com.lewy3d.game.Scenes.Level1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.lewy3d.game.Core.*;
import com.lewy3d.game.Core.Camera.FirstPersonController;
import com.lewy3d.game.Core.Component.Physics.RigidBody;
import com.lewy3d.game.Core.Debug.Debug;
import com.lewy3d.game.Core.Shapes.Cube;
import com.lewy3d.game.Core.Shapes.Sphere;
import com.lewy3d.game.Scenes.Level2.Level2;

public class Level1 extends GameWorld {

    @Override
    public void start() {
        //We will use a textured cube as the ground 👍
        Cube ground = new Cube(new Texture("core/textures/ground1.jpg"), 200, 5, 200);
        ground.setTranslation(0, -20, 0);
        ground.name = "Ground";

        Cube box1 = new Cube(Color.BLUE, 10, 10, 10);
        box1.transform.position.z = -40;
        box1.name = "Box1";

        Cube box2 = new Cube(Color.RED, 10, 10, 10);
        box2.name = "Box2";
        box2.transform.position.x = 30;
        box2.transform.position.y = 50;

        Cube box3 = new Cube(Color.PURPLE, 10, 10, 10);
        box3.name = "Box3";
        box3.transform.position.x = 60;
        box3.active = false;    //Not active, so will not render

        Sphere ball1 = new Sphere(Color.BLUE, 10);
        ball1.name = "Ball1";
        ball1.transform.position.x = -30;
        ball1.transform.position.y = 50;
        ball1.addComponent(new RigidBody());

        InfoText info = new InfoText();
        info.name = "Info";
        info.transform.position = new Vector3(20, Gdx.graphics.getHeight() - 30, 0);
        info.setColor(Color.RED);

        FirstPersonController player = new FirstPersonController();
        //Set the name and position of the player
        player.name = "Player";
        player.getCamera().postProcessor.applyProfile(new PostProcessorProfileExample());

        Instantiate(ground);
        Instantiate(player);
        Instantiate(box1);
        Instantiate(box2);
        Instantiate(box3);
        Instantiate(ball1);
        Instantiate(info);

        Debug.log("Created the ground, a blue cube, a red cube, a purple cube which is not active");
        Debug.log("A blue ball containing the rigidbody component and a info text object showing the FPS and instructions.");
        Debug.log("Lastly, we created a first person character controller.");
    }

    @Override
    public void update(float deltaTime) {

        //The code below will destroy/remove the object from the game when the mouse is clicked
        if (Gdx.input.justTouched()) Destroy(GameObject.Find("Box1"));

        //The code below will load Level 2 when F is pressed
        if(Gdx.input.isKeyPressed(Input.Keys.F)) SceneManager.LoadScene(new Level2());

    }

}
