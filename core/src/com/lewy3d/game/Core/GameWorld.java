package com.lewy3d.game.Core;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
import com.lewy3d.game.Core.Camera.Camera;
import com.lewy3d.game.Core.Environment.SkyBox;

import java.util.*;

public abstract class GameWorld implements Screen {

    public static   btDynamicsWorld                       dynamicsWorld;
    private         btDefaultCollisionConfiguration       collisionConfig;
    private         btCollisionDispatcher                 dispatcher;
    private         btDbvtBroadphase                      broadphase;
    private         btSequentialImpulseConstraintSolver   constraintSolver;

    public static ArrayList<GameObject> gameObjects;
    public static Environment           environment;
    public static ModelBatch            modelBatch;
    public static SpriteBatch           spriteBatch;

    private SkyBox      skyBox;
    private BitmapFont  noCameraText;

    public GameWorld(){

        modelBatch  = new ModelBatch();
        spriteBatch = new SpriteBatch();

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        gameObjects = new ArrayList<GameObject>();

        Bullet.init();
        collisionConfig     = new btDefaultCollisionConfiguration();
        dispatcher          = new btCollisionDispatcher(collisionConfig);
        broadphase          = new btDbvtBroadphase();
        constraintSolver    = new btSequentialImpulseConstraintSolver();

        dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher, broadphase, constraintSolver, collisionConfig);
        dynamicsWorld.setGravity(new Vector3(0, -10f, 0));  //Later... Make it so the developer can set the gravity

        skyBox = new SkyBox(
                Gdx.files.internal("core/skybox/px.jpg"),
                Gdx.files.internal("core/skybox/nx.jpg"),
                Gdx.files.internal("core/skybox/py.jpg"),
                Gdx.files.internal("core/skybox/ny.jpg"),
                Gdx.files.internal("core/skybox/pz.jpg"),
                Gdx.files.internal("core/skybox/nz.jpg")
        );

        noCameraText = new BitmapFont();
        noCameraText.setColor(Color.WHITE);

        start();
    }

    @Override
    public void render(float delta) {

        spriteBatch.setProjectionMatrix(getActiveCamera().getCamera().combined);

        if(getActiveCamera() == notFoundCamera){
            spriteBatch.begin();
            noCameraText.draw(spriteBatch, "No Active Camera", Gdx.graphics.getWidth()/2-50, Gdx.graphics.getHeight()/2);
            spriteBatch.end();
        }
        else {
            getActiveCamera().postProcessor.capture();
            skyBox.render(getActiveCamera().getCamera());
        }

        for(GameObject o : gameObjects){
            if(o.active){
                if(getActiveCamera() != notFoundCamera){
                    spriteBatch.begin();
                    modelBatch.begin(getActiveCamera().getCamera());
                    o.render();
                    spriteBatch.end();
                    modelBatch.end();
                }
                o.update(delta);
                o.updateComponents(delta);
            }
        }

        final float deltaWorld = Math.min(1f / 30f, delta);
        dynamicsWorld.stepSimulation(deltaWorld, 5, 1f / 60f);

        update(delta);

    }

    public abstract void start();
    public abstract void update(float deltaTime);

    public static void Instantiate(GameObject object){
        if(object.name == null){
            object.name = "GameObject " + (gameObjects.toArray().length+1);
        }

        object.start();

        gameObjects.add(object);
    }

    public static void Destroy(GameObject object){

        if(!gameObjects.contains(object)) return;

        ArrayList<GameObject> newList = new ArrayList<GameObject>();
        GameObject toDispose = GameObject.notFound;

        for(GameObject o : gameObjects){
            if(o != object){
                newList.add(o);
            }
            else{
                toDispose = o;
            }
        }

        gameObjects = newList;

        toDispose.dispose();

    }

    static private Camera notFoundCamera = new Camera();
    public static Camera getActiveCamera() {

        for (Camera camera : getCameras()) {
            if (camera.active) return camera;
        }
        return notFoundCamera;

    }

    static ArrayList<Camera> getCameras() {
        ArrayList<Camera> cameras = new ArrayList<Camera>();

        for (GameObject camera : gameObjects) {
            if (camera instanceof Camera) cameras.add((Camera) camera);
        }
        return cameras;
    }

    @Override
    public void resize(int width, int height) {
        for(Camera camera : getCameras()){
            camera.getViewport().update(width, height);

        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        dynamicsWorld.dispose();
        constraintSolver.dispose();
        broadphase.dispose();
        dispatcher.dispose();
        collisionConfig.dispose();
        skyBox.dispose();
    }

    @Override
    public void show() {

    }

    public class Position implements Component {

    }
}
