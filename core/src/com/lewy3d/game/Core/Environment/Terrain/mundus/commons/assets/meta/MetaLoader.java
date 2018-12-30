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

package com.lewy3d.game.Core.Environment.Terrain.mundus.commons.assets.meta;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.lewy3d.game.Core.Environment.Terrain.mundus.commons.assets.AssetType;

/**
 *
 * @author Marcus Brummer
 * @version 26-10-2016
 */
public class MetaLoader {

    private final JsonReader reader = new JsonReader();

    public Meta load(FileHandle file) throws MetaFileParseException {
        Meta meta = new Meta(file);

        JsonValue json = reader.parse(file);
        parseBasics(meta, json);

        if(meta.getType() == AssetType.TERRAIN) {
            parseTerrain(meta, json.get(Meta.JSON_TERRAIN));
        } else if(meta.getType() == AssetType.MODEL) {
            parseModel(meta, json.get(Meta.JSON_MODEL));
        }

        return meta;
    }

    private void parseBasics(Meta meta, JsonValue jsonRoot) {
        meta.setVersion(jsonRoot.getInt(Meta.JSON_VERSION));
        meta.setLastModified(jsonRoot.getLong(Meta.JSON_LAST_MOD));
        meta.setUuid(jsonRoot.getString(Meta.JSON_UUID));
        meta.setType(AssetType.valueOf(jsonRoot.getString(Meta.JSON_TYPE)));
    }

    private void parseTerrain(Meta meta, JsonValue jsonTerrain) {
        if(jsonTerrain == null) return;

        final MetaTerrain terrain = new MetaTerrain();
        terrain.setSize(jsonTerrain.getInt(MetaTerrain.JSON_SIZE));
        terrain.setSplatmap(jsonTerrain.getString(MetaTerrain.JSON_SPLATMAP, null));
        terrain.setSplatBase(jsonTerrain.getString(MetaTerrain.JSON_SPLAT_BASE, null));
        terrain.setSplatR(jsonTerrain.getString(MetaTerrain.JSON_SPLAT_R, null));
        terrain.setSplatG(jsonTerrain.getString(MetaTerrain.JSON_SPLAT_G, null));
        terrain.setSplatB(jsonTerrain.getString(MetaTerrain.JSON_SPLAT_B, null));
        terrain.setSplatA(jsonTerrain.getString(MetaTerrain.JSON_SPLAT_A, null));

        meta.setTerrain(terrain);
    }

    private void parseModel(Meta meta, JsonValue jsonModel) {
        if(jsonModel == null) return;

        final MetaModel model = new MetaModel();
        final JsonValue materials = jsonModel.get(MetaModel.JSON_DEFAULT_MATERIALS);

        for(final JsonValue mat : materials) {
            System.out.println(mat.name);
            final String g3dbID = mat.name;
            final String assetUUID = materials.getString(g3dbID);
            model.getDefaultMaterials().put(g3dbID, assetUUID);
        }

        meta.setModel(model);
    }

}
