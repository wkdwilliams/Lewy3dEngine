package com.lewy3d.game.Core.Camera.PostProcessor;

import com.lewy3d.game.Core.Camera.PostProcessor.bitfire.postprocessing.PostProcessorEffect;

import java.util.ArrayList;

public abstract class PostProcessingProfile {

    private ArrayList<PostProcessorEffect> effects;

    public PostProcessingProfile(){
        effects = new ArrayList<PostProcessorEffect>();

        init();
    }

    protected abstract void init();

    protected void addEffect(PostProcessorEffect effect){
        effects.add(effect);
    }

    public ArrayList<PostProcessorEffect> getEffects(){
        return effects;
    }

}
