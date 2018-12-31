package com.lewy3d.game.Scenes.Level1;

import com.badlogic.gdx.Gdx;
import com.lewy3d.game.Core.Camera.PostProcessor.PostProcessingProfile;
import com.lewy3d.game.Core.Camera.PostProcessor.bitfire.postprocessing.effects.*;

public class PostProcessorProfileExample extends PostProcessingProfile {

    @Override
    protected void init() {
        Bloom bloom = new Bloom( (int)(Gdx.graphics.getWidth() * 0.25f), (int)(Gdx.graphics.getHeight() * 0.25f) );
        bloom.setBloomIntesity(1f);

        addEffect(bloom);

        MotionBlur motionBlur = new MotionBlur();
        motionBlur.setBlurOpacity(0.7f);

        addEffect(motionBlur);

    }
}
