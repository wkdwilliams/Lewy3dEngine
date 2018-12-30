package com.lewy3d.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.lewy3d.game.Core.Debug.Debug;
import com.lewy3d.game.Core.Debug.SceneView;
import com.lewy3d.game.Core.GameWorld;
import com.lewy3d.game.Core.SceneManager;
import com.lewy3d.game.Scenes.Level1;

public class MainGame extends ApplicationAdapter {

    /*
    TODO: In the GameObject class on line 76... fix the remove component method
    TODO: Finish/fix rigidbody component
    TODO: finish the audiosource component
    TODO: add ability to change skybox
    TODO: create terrain and populate terrain with grass
     */

	@Override
	public void create () {

		Gdx.input.setCursorCatched(Settings.CAPTURECURSOR);

		//Start the starting scene specified in the settings
		try {
			SceneManager.LoadScene((GameWorld)Settings.startingScene.newInstance());
		}
		catch (InstantiationException e){Debug.error(e.getMessage());}
		catch (IllegalAccessException e){Debug.error(e.getMessage());}

		if(Settings.SHOWSCENEVIEW) new SceneView();

	}

	@Override
	public void render () {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT | GL30.GL_DEPTH_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Settings.UNCAPTURECURSORBUTTON)) Gdx.input.setCursorCatched(false);
        if(Gdx.input.justTouched() && Settings.CAPTURECURSORONCLICK) Gdx.input.setCursorCatched(true);

        SceneManager.render();
	}
	
	@Override
	public void dispose () {


	}

}
