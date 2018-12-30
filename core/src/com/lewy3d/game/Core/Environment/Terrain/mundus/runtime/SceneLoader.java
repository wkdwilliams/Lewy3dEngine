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

package com.lewy3d.game.Core.Environment.Terrain.mundus.runtime;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.Scene;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.assets.AssetManager;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.assets.MaterialAsset;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.assets.ModelAsset;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.assets.TerrainAsset;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.importer.JsonScene;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.scene3d.GameObject;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.scene3d.InvalidComponentException;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.scene3d.SceneGraph;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.scene3d.components.ModelComponent;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.scene3d.components.TerrainComponent;

/**
 * @author Marcus Brummer
 * @version 27-10-2016
 */
public class SceneLoader {

    private Mundus mundus;
    private AssetManager assetManager;

    private FileHandle root;

    public SceneLoader(Mundus mundus, FileHandle scenesRoot) {
        this.mundus = mundus;
        this.assetManager = mundus.getAssetManager();
        this.root = scenesRoot;
    }

    public Scene load(String name) {
        final JsonReader reader = new JsonReader();
        final JsonValue json = reader.parse(root.child(name));

        Scene scene = new Scene();
        scene.setId(json.getInt(JsonScene.ID));
        scene.setName(json.getString(JsonScene.NAME));

        // game objects
        for(JsonValue go : json.get(JsonScene.GAME_OBJECTS)) {
            scene.sceneGraph.addGameObject(convertGameObject(scene.sceneGraph, go));
        }

        return scene;
    }

    private GameObject convertGameObject(SceneGraph sceneGraph, JsonValue jsonGo) {
        final GameObject go = new GameObject(sceneGraph, jsonGo.getString(JsonScene.GO_NAME, ""),
                jsonGo.getInt(JsonScene.GO_ID));
        go.active = jsonGo.getBoolean(JsonScene.GO_ACTIVE, true);
        // TODO tags

        // model component
        JsonValue modelComp = jsonGo.get(JsonScene.GO_MODEL_COMPONENT);
        if(modelComp != null) {
            ModelComponent mc = new ModelComponent(go, mundus.getShaders().getModelShader());
            mc.setModel((ModelAsset) assetManager.findAssetByID(modelComp.getString(JsonScene.MODEL_COMPONENT_MODEL_ID)), false);

            JsonValue mats = modelComp.get(JsonScene.MODEL_COMPONENT_MATERIALS);
            for(JsonValue mat : mats.iterator()) {
                mc.getMaterials().put(mat.name, (MaterialAsset) assetManager.findAssetByID(mats.getString(mat.name)));
            }
            mc.applyMaterials();

            try {
                go.addComponent(mc);
            } catch (InvalidComponentException e) {
                e.printStackTrace();
            }
        }

        // TODO terrain component
        JsonValue terrainComp = jsonGo.get(JsonScene.GO_TERRAIN_COMPONENT);
        if(terrainComp != null) {
            TerrainComponent tc = new TerrainComponent(go, mundus.getShaders().getTerrainShader());
            tc.setTerrain((TerrainAsset)
                    assetManager.findAssetByID(terrainComp.getString(JsonScene.TERRAIN_COMPONENT_TERRAIN_ID)));
            try {
                go.addComponent(tc);
            } catch (InvalidComponentException e) {
                e.printStackTrace();
            }
        }

        // transformation
        final float[] transform = jsonGo.get(JsonScene.GO_TRANSFORM).asFloatArray();
        go.setLocalPosition(transform[0], transform[1], transform[2]);
        go.setLocalRotation(transform[3], transform[4], transform[5], transform[6]);
        go.setLocalScale(transform[7], transform[8], transform[9]);

        // children
        JsonValue children = jsonGo.get(JsonScene.GO_CHILDREN);
        if(children != null) {
            for(JsonValue c : children) {
                go.addChild(convertGameObject(sceneGraph, c));
            }
        }

        return go;
    }


}
