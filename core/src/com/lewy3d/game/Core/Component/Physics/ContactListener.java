package com.lewy3d.game.Core.Component.Physics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.lewy3d.game.Core.Debug.Debug;

class ContactListener extends com.badlogic.gdx.physics.bullet.collision.ContactListener {

        private ModelInstance instance;

        public ContactListener(ModelInstance instance){
            this.instance = instance;
        }

        @Override
        public boolean onContactAdded (int userValue0, int partId0, int index0, boolean match0, int userValue1, int partId1,
                                       int index1, boolean match1) {
            if (match0)
                Debug.log("HIT");
                //((ColorAttribute)instances.get(userValue0).materials.get(0).get(ColorAttribute.Diffuse)).color.set(Color.WHITE);
            if (match1)
                Debug.log("HIT");
                //((ColorAttribute)instances.get(userValue1).materials.get(0).get(ColorAttribute.Diffuse)).color.set(Color.WHITE);
            return true;
        }
    }


