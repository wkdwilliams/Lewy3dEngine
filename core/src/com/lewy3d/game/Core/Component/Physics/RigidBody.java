package com.lewy3d.game.Core.Component.Physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Transform;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.lewy3d.game.Core.Component.Component;
import com.lewy3d.game.Core.Component.UnfixedComponent;
import com.lewy3d.game.Core.Debug.Debug;
import com.lewy3d.game.Core.GameModel;
import com.lewy3d.game.Core.GameObject;

public class RigidBody extends Component implements UnfixedComponent {

    private BulletWrapper bulletWrapper;
    public boolean isKinematic = false;

    @Override
    public void start() {

    }

    @Override
    public void update(GameObject gameObject, float deltaTime) {

        if(bulletWrapper == null){
            GameModel model = (GameModel) gameObject;
            bulletWrapper = new BulletWrapper(model);
        }
        bulletWrapper.isKinematic = isKinematic;

        bulletWrapper.update(deltaTime);

    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
