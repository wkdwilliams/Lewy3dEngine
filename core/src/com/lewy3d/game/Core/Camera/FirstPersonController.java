package com.lewy3d.game.Core.Camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector3;

public class FirstPersonController extends Player {

    private final float VELOCITY = 50;
    public float sensitivity = 0.5f;

    @Override
    protected void start() {
        super.start();


    }

    @Override
    public void update(float deltaTime) {
        setLookDirection(); //Pan/rotate the camera as you move the mouse
        move();             //Move the player based on W, S, A, D

    }

    private void setLookDirection(){
        Vector3 tmp            = new Vector3();
        float degreesPerPixel  = sensitivity;
        float deltaX           = -Gdx.input.getDeltaX() * degreesPerPixel;
        float deltaY           = -Gdx.input.getDeltaY() * degreesPerPixel;

        playerCamera.getCamera().direction.rotate(playerCamera.getCamera().up, deltaX);
        tmp.set(playerCamera.getCamera().direction).crs(playerCamera.getCamera().up).nor();
        playerCamera.getCamera().direction.rotate(tmp, deltaY);
    }

    private void move(){

        Vector3 tmp = new Vector3();

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            tmp.set(playerCamera.getCamera().direction).nor().scl(Gdx.graphics.getDeltaTime() * VELOCITY);
            tmp.y = 0;
            playerCamera.getCamera().position.add(tmp);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            tmp.set(playerCamera.getCamera().direction).nor().scl(-Gdx.graphics.getDeltaTime() * VELOCITY);
            tmp.y = 0;
            playerCamera.getCamera().position.add(tmp);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            tmp.set(playerCamera.getCamera().direction).crs(playerCamera.getCamera().up)
                    .nor().scl(-Gdx.graphics.getDeltaTime() * VELOCITY);
            tmp.y = 0;
            playerCamera.getCamera().position.add(tmp);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            tmp.set(playerCamera.getCamera().direction)
                    .crs(playerCamera.getCamera().up).nor().scl(Gdx.graphics.getDeltaTime() * VELOCITY);
            tmp.y = 0;
            playerCamera.getCamera().position.add(tmp);
        }
    }
}
