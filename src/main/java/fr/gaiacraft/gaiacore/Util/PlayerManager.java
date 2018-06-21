package fr.gaiacraft.gaiacore.Util;

import fr.gaiacraft.gaiacore.database.DatabaseManager;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    private DatabaseManager dB = new DatabaseManager();
    private Map<UUID, PlayerProfile> PlayerList = new HashMap<>();

    public void AddPlayer(UUID uuid){
        PlayerProfile prof = dB.LoadProfile(uuid);
        PlayerList.put(uuid, prof);
    }

    public void AddNewPlayer(UUID uuid){
        PlayerProfile prof = new PlayerProfile(uuid);
        PlayerList.put(uuid, prof);
        dB.CreateProfile(prof);
    }

    public void RemovePlayer(UUID uuid){
        dB.SaveProfile(PlayerList.remove(uuid));
    }

    public String getProfileData(UUID uuid){
        return PlayerList.get(uuid).toString();
    }
}
