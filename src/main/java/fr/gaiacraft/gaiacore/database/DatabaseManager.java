package fr.gaiacraft.gaiacore.database;

import com.google.gson.Gson;
import fr.gaiacraft.gaiacore.Util.PlayerProfile;
import fr.gaiacraft.gaiacore.Util.customLogger;

import java.sql.*;
import java.util.UUID;

public class DatabaseManager {
    private  Connection connection;
    private  String TableName = "GaiaMetiers";
    private  Gson gson = new Gson();


    /**
     * Connect to Mysql database
     *
     * @param url      URL to the database/table (in config location)
     * @param username Username to use
     * @param password Password to use (preferably get from config)
     */
    public void Connect(String url, String username, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Force load Jdbc driver
        } catch (ClassNotFoundException e) {
            customLogger.logSevere("Driver MySql unavailable");
            return;
        }
        try { //Another try catch to get any SQL errors (for example connections errors)

            connection = DriverManager.getConnection(url, username, password);

			/*
			with the method getConnection() from DriverManager, we're trying to set
			the connection's url, username, password to the variables we made earlier and
			trying to get a connection at the same time. JDBC allows us to do this.
			*/
        } catch (SQLException e) { //catching errors
            customLogger.logSevere("Unable to connect to remote database");
        }

        CreateTable();
    }

    /**
     * Create an empty Table in Database. Use only at plugin boot
     */
    private void CreateTable() {
        try {
            PreparedStatement state = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "
                    + TableName
                    + "(UUID text, donnees text)"
            );
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Unload MySQL connection
     */
    public void Unload() {
        try { //using a try catch to catch connection errors (like wrong sql password...)
            if (connection != null && !connection.isClosed()) {

			    /*
				checking if connection isn't null to
				avoid receiving a nullpointer
				*/

                connection.close(); //closing the connection field variable.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create empty player profile
     *
     * @param profile PlayerProfile to save in Database
     */
    public void CreateProfile(PlayerProfile profile) {
        UUID PlayerUUID = profile.getUUID();

        final String json = gson.toJson(profile);
        try {
            PreparedStatement state = connection.prepareStatement("INSERT INTO "
                    + TableName
                    + " (UUID, donnees) VALUES (?, ?)"
            ); //DONE: INSERT values in sql
            state.setString(1, PlayerUUID.toString());//DONE: Add data into table
            state.setString(2, json);
            state.executeUpdate();
            state.close();

        } catch (SQLException e) {
            customLogger.logWarning("Une erreur est survenue lors de la création d'un profil joueur!");
            e.printStackTrace();
        }
    }

    /**
     * Load player profile from database
     * @param PlayerUUID UUID of desired player
     * @return Player profile loaded from DB
     */
    public PlayerProfile LoadProfile(UUID PlayerUUID) {
        //DONE: Load profile from SQL and return it (make it public static PlayerProfile LoadProfile(UUID) )
        PlayerProfile PLrProfile;
        try {
            PreparedStatement state = connection.prepareStatement("SELECT * FROM " +
                    TableName +
                    " WHERE UUID = ?");

            state.setString(1, PlayerUUID.toString());
            ResultSet set = state.executeQuery();

            if(set.next()){
                //has profile
                String profile = set.getString("donnees");
                PLrProfile = gson.fromJson(profile, PlayerProfile.class);
                return PLrProfile;
            }else{
                //no profile
                PLrProfile = new PlayerProfile(PlayerUUID);
                CreateProfile(PLrProfile);
                return PLrProfile;
            }



        } catch (SQLException e) {
            customLogger.logWarning("A player has played before but has no profile.");
            PLrProfile = new PlayerProfile(PlayerUUID);
            CreateProfile(PLrProfile);
            return PLrProfile;
        }
        //TODO: If player has played but no profile, create one
    }

    public void SaveProfile(PlayerProfile profile){
        //TODO: make saving logic w/ update SQL query
        UUID PlayerUUID = profile.getUUID();

        final String json = gson.toJson(profile);
        try {
            PreparedStatement state = connection.prepareStatement("UPDATE "
                    + TableName
                    + " SET donnees = ? WHERE UUID = ?"
            ); //DONE: INSERT values in sql
            state.setString(2, PlayerUUID.toString());//DONE: Add data into table
            state.setString(1, json);
            state.executeUpdate();
            state.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
