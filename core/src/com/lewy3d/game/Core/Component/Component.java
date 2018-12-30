package com.lewy3d.game.Core.Component;

import com.lewy3d.game.Core.GameObject;

public abstract class Component {

    protected boolean active = true;

    public boolean isActive(){
        return active;
    }

    public abstract void update(GameObject gameObject, float deltaTime);
    public abstract void start();

}
