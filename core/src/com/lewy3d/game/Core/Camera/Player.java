package com.lewy3d.game.Core.Camera;

import com.lewy3d.game.Core.GameObject;
import com.lewy3d.game.Core.GameWorld;

import java.util.Random;

public abstract class Player extends GameObject {

    public Camera playerCamera;

    public Player(){

        Random nameNum = new Random();

        playerCamera = new Camera();
        playerCamera.name = "PlayerCamera " + nameNum.nextInt(9999);
        playerCamera.showInSceneView = false;
        playerCamera.getCamera().position.set(transform.position);

    }

    @Override
    protected void start(){
        GameWorld.Instantiate(playerCamera);
    }

    @Override
    public void render() {
        playerCamera.transform.position = playerCamera.getCamera().position;
        transform.position = playerCamera.transform.position;
    }

    public Camera getCamera(){
        return playerCamera;
    }

    @Override
    protected void dispose() {
        GameWorld.Destroy(playerCamera);
    }
}
