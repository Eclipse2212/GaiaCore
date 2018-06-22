package fr.gaiacraft.gaiacore.util;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class customLogger {
    private static String prefix = "[GaiaCore] "; //Maybe some color in console?

    /**
     * Sends FINE log to console
     * @param msg Message to send to console
     */
    public static void logFine( String msg){
        Bukkit.getLogger().log(Level.FINE, prefix + msg);
    }

    /**
     * Sends INFO log to console
     * @param msg Message to send
     */
    public static void logInfo(String msg){
        String str = "[GaiaMetiers] " + msg;
        Bukkit.getLogger().log(Level.INFO, prefix + msg);
    }

    /**
     * Sends SEVERE log to console
     * @param msg Message to send
     */
    public static void logSevere(String msg){
        String str = "[GaiaMetiers] " + msg;
        Bukkit.getLogger().log(Level.SEVERE, prefix + msg);
    }

    /**
     * Sends WARNING log to console
     * @param msg Message to send
     */
    public static void logWarning(String msg){
        String str = "[GaiaMetiers] " + msg;
        Bukkit.getLogger().log(Level.WARNING, prefix + msg);
    }

}
