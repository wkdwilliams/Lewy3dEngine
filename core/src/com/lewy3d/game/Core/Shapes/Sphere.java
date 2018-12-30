package com.lewy3d.game.Core.Shapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.lewy3d.game.Core.GameModel;

public class Sphere extends GameModel {

    public Sphere(Color color, float radius){
        Model sphere = modelBuilder.createSphere(radius, radius, radius, 24, 24, new Material(ColorAttribute.createDiffuse(color)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);

        setModel(sphere);
    }

    public Sphere(Texture texture, float radius){
        TextureAttribute textureAttribute = new TextureAttribute(TextureAttribute.Diffuse, texture);

        model = modelBuilder.createSphere(radius, radius, radius, 24, 24, new Material(textureAttribute),
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
}
