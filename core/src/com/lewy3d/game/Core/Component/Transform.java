package com.lewy3d.game.Core.Component;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.lewy3d.game.Core.GameObject;

public class Transform extends Component {

    public Vector3 position;
    public Quaternion rotation = new Quaternion();
    public Size size;

    public Transform(Vector3 position, float width, float height, float depth){
        this.position = position;

        this.size = new Size(width, height, depth);

    }
    public Transform(float x, float y, float z, float width, float height, float depth){
        position = new Vector3(x, y, z);

        this.size = new Size(width, height, depth);
    }

    public Transform(float x, float y, float z, Size size){
        position = new Vector3(x, y, z);

        this.size = size;
    }

    public Transform(Vector3 position, Size size){
        this.position = position;

        this.size = size;
    }

    public Transform(){
        position = new Vector3(0, 0, 0);

        this.size = new Size(0, 0, 0);
    }

    public void translate(float x, float y, float z){
        position.x += x;
        position.y += y;
        position.z += z;
    }

    @Override
    public void start() {

    }

    @Override
    public void update(GameObject gameObject, float deltaTime) {

    }

}
