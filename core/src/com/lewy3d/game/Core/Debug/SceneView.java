package com.lewy3d.game.Core.Debug;

import com.badlogic.gdx.Gdx;
import com.lewy3d.game.Core.GameObject;
import com.lewy3d.game.Core.GameWorld;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SceneView {

    public SceneView(){
        JFrame frame = new JFrame("Lewy3D Scene View");
        frame.setFocusableWindowState(false);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, Toolkit.getDefaultToolkit().getScreenSize().height);

        final JEditorPane textArea = new JEditorPane("text/html", "");
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.black);

        frame.getContentPane().add(textArea); // Adds Button to content pane of frame
        frame.setVisible(true);

        new Thread(new Runnable(){

            private String objects;

            public void run() {
                for(;;) {

                    for (GameObject gameObject : GameWorld.gameObjects) {
                        if(!gameObject.showInSceneView) continue;

                        objects += "<b>"+gameObject.name + "</b>";
                        objects += "\tX: " + gameObject.transform.position.x + " Y: " + gameObject.transform.position.y
                                + " Z: " + gameObject.transform.position.z + "\n";
                        objects += "_______________________________________________<br>";
                    }

                    textArea.setText(objects);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    }
                    catch (InterruptedException e){}

                    objects = "";
                }

            }
        }).start();
    }

}
