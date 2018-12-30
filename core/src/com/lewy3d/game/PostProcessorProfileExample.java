package com.lewy3d.game;

import com.badlogic.gdx.Gdx;
import com.lewy3d.game.Core.Camera.PostProcessor.PostProcessingProfile;
import com.lewy3d.game.Core.Camera.PostProcessor.bitfire.postprocessing.effects.Bloom;

public class PostProcessorProfileExample extends PostProcessingProfile {

    @Override
    protected void init() {
        Bloom bloom = new Bloom( (int)(Gdx.graphics.getWidth() * 0.25f), (int)(Gdx.graphics.getHeight() * 0.25f) );
        bloom.setBloomIntesity(1f);

        addEffect(bloom);
    }
}
