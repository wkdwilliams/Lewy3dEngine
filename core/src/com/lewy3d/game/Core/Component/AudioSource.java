package com.lewy3d.game.Core.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.lewy3d.game.Core.GameObject;

public class AudioSource extends Component implements UnfixedComponent{

    private Sound sound;
    public String source = "";

    @Override
    public void start() {

    }

    @Override
    public void update(GameObject gameObject, float deltaTime) {
        //if(Files.exists(Gdx.files.internal(source)),LinkOption.)

        //sound = Gdx.audio.newSound(Gdx.files.getFileHandle(source, Files.FileType.Absolute));
    }

    @Override
    public void setActive(boolean active) {
        this.active = active;
    }
}
