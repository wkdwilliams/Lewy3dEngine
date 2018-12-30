package com.lewy3d.game.Core.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.lewy3d.game.Core.Camera.Camera;
import com.lewy3d.game.Core.GameObject;
import com.lewy3d.game.Core.GameWorld;

public abstract class Text extends GameObject {

    private BitmapFont font;
    public String text = "";

    public Text(){
        font = new BitmapFont();
    }
    @Override
    protected void render() {
        //spriteBatch.begin();
        font.draw(GameWorld.spriteBatch, text, transform.position.x, transform.position.y);
        //spriteBatch.end();
    }

    public void setColor(Color color){
        font.setColor(color);
    }

    @Override
    protected void dispose() {
        font.dispose();
    }

}
