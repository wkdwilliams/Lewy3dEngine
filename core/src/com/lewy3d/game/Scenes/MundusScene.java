package com.lewy3d.game.Scenes;

/*
 * Copyright (c) 2016. See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.math.Vector3;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.Scene;
import com.lewy3d.game.Core.Environment.Terrain.mundus.runtime.Mundus;
import com.lewy3d.game.Core.GameWorld;

public class MundusScene extends GameWorld {

    private FPSLogger fpsLogger;
    private ModelBatch batch;

    private Mundus mundus;
    private Scene scene;

    private FirstPersonCameraController controller;

    @Override
    public void start() {
        batch = new ModelBatch();
        fpsLogger = new FPSLogger();

        // setup mundus & load our scene
        mundus = new Mundus(Gdx.files.internal("core/mundus"));
        mundus.init();
        scene = mundus.loadScene("Main Scene.mundus");

        // position cam
        scene.cam.position.set(230, 150, 190);
        scene.cam.direction.rotate(Vector3.Y, 70);
        scene.cam.direction.rotate(Vector3.Z, -20);

        // setup input
        controller = new FirstPersonCameraController(scene.cam);
        controller.setVelocity(200f);
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void update(float detlaTime) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        controller.update();
        scene.sceneGraph.update();
        scene.sceneGraph.render();

        fpsLogger.log();
    }

    @Override
    public void dispose() {
        mundus.dispose();
        batch.dispose();
    }
}
