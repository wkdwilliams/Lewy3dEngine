package com.lewy3d.game.Core.Environment.Terrain;

import com.badlogic.gdx.Gdx;
import com.lewy3d.game.Core.GameModel;

import java.util.ArrayList;

public class Terrain extends GameModel {

    public float             grassRenderDistance = 50;
    private ArrayList<Grass> grasses;   //grasses... I know

    @Override
    protected void start() {

        grasses = new ArrayList<Grass>();

        generateGrass();
    }

    @Override
    protected void update(float deltaTime) {

    }

    @Override
    public void render(){
        super.render();

        for(Grass grass : grasses){
            grass.render();
        }
    }

    private void generateGrass(){
        for(int i = 0; i < 100; i++){
            grasses.add(new Grass());
        }
    }
}
