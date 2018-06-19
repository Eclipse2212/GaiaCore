package fr.gaiacraft.gaiacore.Util;

import java.util.UUID;

public class playerProfile {

    private UUID uuid;
    private String name; //Maybe useless
    private String[] jobs;
    private int[] XP;

    /**
     *  Create new profile
     * @param playerUUID UniqueId of player
     */
    public playerProfile(UUID playerUUID){
        uuid = playerUUID;
        jobs = new String[]{"Vagabond","undefined","undefined"};
        XP = new int[]{0, 0, 0};

    }

    public UUID getUUID() {
        return uuid;
    }

    public void AddXP(int value, int pos){

        if(value >= 0 && value <= 2)
            XP[pos] += value;
    }

    public void SubXP(int value, int pos){

        if(value >= 0 && value <= 2)
            XP[pos] -= value;
    }


    public void SetJob(Jobs job, int pos){
        //DONE: Make parameter from ENUM-only

        jobs[pos] = job.name;
    }

    public void GetJob(int pos){

    }

    public enum Jobs{
        LUMBERJACK("Bûcheron"),
        MINER("Mineur"),
        ROGUE("Assassin"),
        TRADER("Marchand"),
        THIEF("Voleur"),
        CRAFTMAN("Artisan"),
        BLACKSMITH("Forgeron"),
        FARMER("Paysan"),
        FISHERMAN("Pêcheur"),
        GUARD("Garde"),
        BREEDER("Eleveur"),
        MAGE("Enchanteur");

        private String name;

        Jobs(String nom){
            this.name = nom;
        }

        public String ToString(){
            return name;
        }
    }
}
