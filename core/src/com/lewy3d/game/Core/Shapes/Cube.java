package com.lewy3d.game.Core.Shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.lewy3d.game.Core.Debug.Debug;
import com.lewy3d.game.Core.GameModel;

public class Cube extends GameModel {

    public Cube(){
        model = modelBuilder.createBox(10, 10, 10,
                new Material(ColorAttribute.createDiffuse(Color.WHITE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        setModel(model);
    }

    public Cube(Color color, float width, float height, float depth){
        model = modelBuilder.createBox(width, height, depth,
                new Material(ColorAttribute.createDiffuse(color)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        setModel(model);
    }

    public Cube(Texture texture, float width, float height, float depth){
        TextureAttribute textureAttribute = new TextureAttribute(TextureAttribute.Diffuse, texture);

        model = modelBuilder.createBox(width, height, depth, new Material(textureAttribute),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal |
                        VertexAttributes.Usage.TextureCoordinates);

        model.manageDisposable(texture);

        setModel(model);
    }

    @Override
    protected void start(){

    }

    @Override
    protected void update(float deltaTime) {

    }

    /*@Override
    public void onCollision() {
        Debug.log("Cube");
    }*/
}
