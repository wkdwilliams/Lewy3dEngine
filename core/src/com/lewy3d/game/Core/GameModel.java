package com.lewy3d.game.Core;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;
import com.lewy3d.game.Core.Camera.Camera;


public abstract class GameModel extends GameObject {

    public ModelInstance        instance;
    public ModelBuilder         modelBuilder;
    public ModelLoader          modelLoader;
    public Model                model;
    public AnimationController  animationController;

    public GameModel(){

        modelBuilder = new ModelBuilder();

        modelBuilder.begin();
        modelBuilder.manage(model);
        modelBuilder.end();

        animationController = new AnimationController(instance);

    }

    @Override
    public void render() {

        transform.position = instance.transform.getTranslation(new Vector3());
        GameWorld.modelBatch.render(instance, GameWorld.environment);

    }

    public void setTranslation(float x, float y, float z){
        transform.position.x = x;
        transform.position.y = y;
        transform.position.z = z;
        instance.transform.setToTranslation(x, y, z);
    }

    public void setTranslation(Vector3 position){
        transform.position = position;
        instance.transform.setToTranslation(position);
    }

    @Override
    protected void dispose() {
        model.dispose();
    }

    protected void setModel(Model model){
        this.instance = new ModelInstance(model);
        this.instance.transform.setToTranslation(transform.position);
    }

    protected void loadModel(String modelPath){
        if(modelLoader == null){
            modelLoader = new G3dModelLoader(new UBJsonReader());
        }

        //TODO: Must set a not found model if the model file cannot be found!
        //pseudocode: if(file_exists(modelPath)) return notfoundModel

        model = modelLoader.loadModel(Gdx.files.getFileHandle(modelPath, Files.FileType.Internal));

    }

}
