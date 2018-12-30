package com.lewy3d.game.Core.Component.Physics;

import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.physics.bullet.dynamics.*;
import com.badlogic.gdx.physics.bullet.linearmath.btMotionState;
import com.lewy3d.game.Core.Debug.Debug;
import com.lewy3d.game.Core.GameModel;
import com.lewy3d.game.Core.GameWorld;

class BulletWrapper {

    private ContactListener contactListener;

    private btRigidBody body;
    private MotionState motionState;
    private btCollisionShape shape;
    private btRigidBody.btRigidBodyConstructionInfo constructionInfo;
    private Vector3 localInertia = new Vector3();

    public GameModel gameModel;

    private float mass = 1;

    final static short OBJECT_FLAG = 1 << 9;

    public boolean isKinematic = false;

    public BulletWrapper(GameModel gameModel){

        this.gameModel = gameModel;

        motionState = new MotionState();
        motionState.transform = gameModel.instance.transform;

        contactListener = new ContactListener(gameModel);

        float x = gameModel.instance.transform.getScaleX();
        float y = gameModel.instance.transform.getScaleY();
        float z = gameModel.instance.transform.getScaleZ();

        shape = new btBoxShape(new Vector3(50, 2, 50));

        if (mass > 0f) shape.calculateLocalInertia(mass, localInertia);
        else localInertia.set(0, 0, 0);

        constructionInfo = new btRigidBody.btRigidBodyConstructionInfo(mass, null, shape, localInertia);

        body = new btRigidBody(constructionInfo);
        body.setMotionState(motionState);
        body.setUserValue(GameWorld.gameObjects.size());
        body.proceedToTransform(gameModel.instance.transform);
        body.setContactCallbackFlag(OBJECT_FLAG);
        body.setContactCallbackFilter(OBJECT_FLAG);
        if(isKinematic) body.setCollisionFlags(body.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_KINEMATIC_OBJECT);
        else body.setCollisionFlags(body.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_CUSTOM_MATERIAL_CALLBACK);

        GameWorld.dynamicsWorld.addRigidBody(body);

    }

    public void update(float delta){
        if(isKinematic)
            body.setCollisionFlags(body.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_KINEMATIC_OBJECT);
        else
            body.setCollisionFlags(body.getCollisionFlags() | btCollisionObject.CollisionFlags.CF_CUSTOM_MATERIAL_CALLBACK);

    }

    public static class MotionState extends btMotionState {
        Matrix4 transform;

        @Override
        public void getWorldTransform(Matrix4 worldTrans) {
            worldTrans.set(transform);
        }

        @Override
        public void setWorldTransform(Matrix4 worldTrans) {
            transform.set(worldTrans);
        }
    }

    static class ContactListener extends com.badlogic.gdx.physics.bullet.collision.ContactListener {

        private GameModel gameModel;

        public ContactListener(GameModel gameModel){
            this.gameModel = gameModel;
        }

        @Override
        public boolean onContactAdded (int userValue0, int partId0, int index0, boolean match0, int userValue1, int partId1,
                                       int index1, boolean match1) {
            //if (match0)
                gameModel.onCollision();
            //((ColorAttribute)instances.get(userValue0).materials.get(0).get(ColorAttribute.Diffuse)).color.set(Color.WHITE);
            //if (match1)
                //Debug.log("HIT");
            //((ColorAttribute)instances.get(userValue1).materials.get(0).get(ColorAttribute.Diffuse)).color.set(Color.WHITE);
            return true;
        }
    }

}

