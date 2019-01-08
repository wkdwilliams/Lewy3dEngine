package com.lewy3d.game;

import com.badlogic.gdx.Input;
import com.lewy3d.game.Scenes.Level1.Level1;

public class Settings {

    /*
    Change these values to suit your needs
     */

    public static final String  NAME                    = "Lewy3D";
    public static final boolean SHOWERRORS              = true;
    public static final boolean SHOWWARNINGS            = true;
    public static boolean       SHOWSCENEVIEW           = true;
    public static final boolean CAPTURECURSOR           = true;
    public static final boolean CAPTURECURSORONCLICK    = true;
    public static final int     UNCAPTURECURSORBUTTON   = Input.Keys.ESCAPE;
    public static final Class   startingScene           = Level1.class;
    

}
