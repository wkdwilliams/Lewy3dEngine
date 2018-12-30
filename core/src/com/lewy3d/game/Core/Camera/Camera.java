package com.lewy3d.game.Core.Camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lewy3d.game.Core.Camera.PostProcessor.bitfire.postprocessing.PostProcessor;
import com.lewy3d.game.Core.Camera.PostProcessor.bitfire.postprocessing.effects.Bloom;
import com.lewy3d.game.Core.GameObject;
import com.lewy3d.game.PostProcessorProfileExample;

public class Camera extends GameObject{

    private PerspectiveCamera camera;
    private Viewport viewport;

    public PostProcessor postProcessor;

    public Camera(){
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.near = 0.001f;
        camera.far = 300f;
        camera.update();

        viewport = new FitViewport(1280, 720, camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport.apply();

        postProcessor = new PostProcessor(false, false, true);
    }

    @Override
    public void start() {

    }

    @Override
    protected void update(float deltaTime) {
        camera.position.set(transform.position);
        camera.update();
    }

    @Override
    protected void render() {
        postProcessor.render();
    }

    @Override
    protected void dispose() {
        postProcessor.dispose();
    }

    public Viewport getViewport(){
        return viewport;
    }

    public PerspectiveCamera getCamera(){
        return camera;
    }
}
