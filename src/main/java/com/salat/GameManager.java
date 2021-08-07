package com.salat;

import java.time.LocalDate;
import java.util.HashMap;

public class GameManager {

    public enum GameType {
        DURAK
    }

    public static HashMap<String, Room> rooms = new HashMap<>();

    public static void createNewRoom(String playerName /*, String game, String name, String pAmount*/) {

        String authorName = playerName;
        rooms.put(authorName, new Room(GameType.DURAK, authorName + " " + LocalDate.now(), 2));
    }

    private static GameType getGameTypeFromString(String gameStr) {
        switch (gameStr.toLowerCase()) {
            case "durak": return GameType.DURAK;
            default: return GameType.DURAK;
        }
    }


}
