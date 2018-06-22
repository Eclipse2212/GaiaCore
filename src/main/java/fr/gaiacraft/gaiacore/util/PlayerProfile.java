package fr.gaiacraft.gaiacore.util;

import java.util.UUID;

public class PlayerProfile {

    private UUID uuid;
    private String name; //Maybe useless
    private String[] jobs;
    private int[] XP;

    /**
     *  Create new profile
     * @param playerUUID UniqueId of player
     */
    public PlayerProfile(UUID playerUUID){
        this.uuid = playerUUID;
        this.jobs = new String[]{"Vagabond","undefined","undefined"};
        this.XP = new int[]{0, 0, 0};

    }

    public UUID getUUID() {
        return uuid;
    }

    public void AddXP(int value, int pos){

        if(value >= 0 && value <= 2)
            this.XP[pos] += value;
    }

    /**
     * Removes fixed XP from job slot
     * @param value XP to remove (POSITIVE VALUE ONLY)
     * @param pos Slot to apply changes
     */
    public void SubXP(int value, int pos){

        if((pos >= 0 && pos <= 2) && value > 0)
            this.XP[pos] -= value;
        
    }

    /**
     * Function to change Job /!\ WARNING changing job reset XP of the slot
     * @param job Job from Jobs enum
     * @param pos Job slot to change
     */
    public void SetJob(Jobs job, int pos){
        //DONE: Make parameter from ENUM-only

        this.jobs[pos] = job.name;
        this.XP[pos] = 0;
    }

    public void GetJob(int pos){

    }

    public enum Jobs{
        WANDERER(0, "Vagabond"),
        LUMBERJACK(1, "Bûcheron"),
        MINER(2, "Mineur"),
        ROGUE(3, "Assassin"),
        TRADER(4, "Marchand"),
        THIEF(5, "Voleur"),
        CRAFTMAN(6, "Artisan"),
        BLACKSMITH(7, "Forgeron"),
        FARMER(8, "Paysan"),
        FISHERMAN(9, "Pêcheur"),
        GUARD(10, "Garde"),
        BREEDER(11, "Eleveur"),
        MAGE(12, "Enchanteur");

        private String name;
        private int id;

        Jobs(int ID, String nom){
            this.name = nom;
            this.id = ID;
        }

        public String getName(){
            return this.name;
        }

        public int getId(){
			return this.id;
        }
    }
}
