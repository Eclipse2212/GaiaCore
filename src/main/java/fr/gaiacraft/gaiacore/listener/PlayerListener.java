package fr.gaiacraft.gaiacore.listener;


import fr.gaiacraft.gaiacore.util.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerListener implements Listener {
    private PlayerManager plrMgr = new PlayerManager();

    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent ev){
        UUID PlayerUUID = ev.getPlayer().getUniqueId();

        if(!ev.getPlayer().hasPlayedBefore()){
            //The player is new so we need to create a new entry in the DB
            plrMgr.AddNewPlayer(PlayerUUID);
        }
        else {
            plrMgr.AddPlayer(PlayerUUID);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent ev){
        plrMgr.RemovePlayer(ev.getPlayer().getUniqueId());

    }
}
