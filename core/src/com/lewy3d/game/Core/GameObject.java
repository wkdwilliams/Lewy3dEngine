package com.lewy3d.game.Core;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.lewy3d.game.Core.Camera.Camera;
import com.lewy3d.game.Core.Component.Component;
import com.lewy3d.game.Core.Component.Transform;
import com.lewy3d.game.Core.Debug.Debug;
import com.lewy3d.game.Core.Component.Size;

import java.util.ArrayList;

public abstract class GameObject {

    public Transform    transform;
    public boolean      active           = true;
    public boolean      showInSceneView  = true;
    public String       tag              = "Untagged";
    public String       name;

    public ArrayList<Component> components = new ArrayList<Component>();

    public GameObject(Vector3 position, float width, float height, float depth){
        transform = new Transform(position, width, height, depth);

    }
    public GameObject(float x, float y, float z, float width, float height, float depth){
        transform = new Transform(x, y, z, width, height, depth);

    }

    public GameObject(float x, float y, float z, Size size){
        transform = new Transform(x, y, z, size);

    }

    public GameObject(Vector3 position, Size size){
        transform = new Transform(position, size);

    }

    public GameObject(){

        transform = new Transform();
    }

    protected abstract void start();
    protected abstract void render();
    protected abstract void update(float deltaTime);
    public void onCollision(){}
    protected abstract void dispose();

    public void updateComponents(float deltaTime){
        for (Component com : components){
            if(com.isActive()) com.update(this, deltaTime);
        }
    }

    public void addComponent(Component component){
        //Check if the component has already been added
        for (Component com : components){
            if(com.getClass().getSimpleName().equals(component.getClass().getSimpleName())){
                Debug.error("Component " + com.getClass().getSimpleName() + " has already been added to this object");
                return;
            }
        }

        components.add(component);
    }

    /*public void removeComponent(Class component){

        int toRemove = 999;

        for (int i = 0; i < components.toArray().length; i++){
            if(components.toArray().getClass().getSimpleName().equals(component.getSimpleName())){
                toRemove = i;
                break;
            }
        }

        if(toRemove == 999){
            Debug.error("Cannot remove component because this game object does not have the specified component added");
        }
        else{
            components.remove(toRemove);
            Debug.log("Removed");
        }

        Debug.log("Finished. toRemove = " + toRemove);
    }*/

    //Static methods
    //An empty static game object to return if an object cannot be found with the specified name
    static GameObject notFound;

    public static GameObject Find(String name) {

        for (GameObject object : GameWorld.gameObjects) {
            if (object.name.equals(name)) {
                return object;
            }
        }

        Debug.error("Object " + name + " cannot be found");
        return notFound;

    }

    public static ArrayList<GameObject> FindGameObjectsWithTag(String tag) {

        ArrayList<GameObject> objects = new ArrayList<GameObject>();

        for (GameObject object : GameWorld.gameObjects) {
            if (object.tag.equals(tag)) {
                objects.add(object);
            }
        }

        if(objects.size() == 0) Debug.error("No objects with tag '" + tag + "' could be found");

        return objects;

    }

    //Empty game object

    public static class Empty extends GameObject{

        @Override
        protected void start() {

        }

        @Override
        protected void render() {

        }

        @Override
        protected void update(float deltaTime) {

        }

        @Override
        protected void dispose() {

        }
    }

}
