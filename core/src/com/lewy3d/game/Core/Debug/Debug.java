package com.lewy3d.game.Core.Debug;

import com.lewy3d.game.Settings;

public class Debug {

    public static void log(String log){
        System.out.println("LOG: " + log);
    }

    public static void error(String error){
        if(Settings.SHOWERRORS) System.out.println("\u001B[31mERROR: " + error + "\u001b[0m");
    }

    public static void warning(String warning) { if(Settings.SHOWWARNINGS) System.out.println("\u001B[33mWARNING: " + warning + "\u001b[0m"); }

}
