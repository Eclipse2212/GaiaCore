package fr.gaiacraft.gaiacore.Util;

import fr.gaiacraft.gaiacore.database.DatabaseManager;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    private static Map<UUID, PlayerProfile> PlayerList = new HashMap<>();

    public static void AddPlayer(UUID uuid){
        PlayerProfile prof = DatabaseManager.LoadProfile(uuid);
        PlayerList.put(uuid, prof);
    }

    public static void AddNewPlayer(UUID uuid){
        PlayerProfile prof = new PlayerProfile(uuid);
        PlayerList.put(uuid, prof);
        DatabaseManager.CreateProfile(prof);
    }

    public static void RemovePlayer(UUID uuid){
        DatabaseManager.SaveProfile(PlayerList.remove(uuid));
    }

    public static String getProfileData(UUID uuid){
        return PlayerList.get(uuid).toString();
    }
}
