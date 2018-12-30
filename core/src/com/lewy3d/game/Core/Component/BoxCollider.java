package com.lewy3d.game.Core.Component;

import com.lewy3d.game.Core.GameObject;

public class BoxCollider extends Component implements UnfixedComponent{

    Transform transform;

    @Override
    public void start() {
        transform = new Transform();
    }

    @Override
    public void update(GameObject gameObject, float deltaTime) {

    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
