package com.salat;

import com.salat.CardGame.Durak.Player;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class GameManager {

    public enum GameType {
        DURAK
    }

    public static HashMap<String, Room> rooms = new HashMap<>();

    public static void createNewRoom(String playerName /*, String game, String name, String pAmount*/) {

        String authorName = playerName;
        rooms.put(authorName, new Room(GameType.DURAK, authorName + "_" + LocalDate.now(), 2));
    }

    private static GameType getGameTypeFromString(String gameStr) {
        switch (gameStr.toLowerCase()) {
            case "durak": return GameType.DURAK;
            default: return GameType.DURAK;
        }
    }

    public static Room getRoomByPlayerName(String playerName) {
        for (Map.Entry<String, Room> entry : rooms.entrySet()) {
            for (Player player : entry.getValue().players) {
                if (player.getName().equals(playerName)) return entry.getValue();
            }
        }
        return null;
    }

    public static String getRoomListAsString() {
        if (rooms.size() == 0) return "There are no rooms";
        StringBuilder roomsBuilder = new StringBuilder();

        for (Map.Entry<String, Room> entry : rooms.entrySet()) {
            roomsBuilder.append("Name ").append(entry.getKey()).append(" - ").append(entry.getValue().getRoomName()).append("\n");
        }

        return roomsBuilder.toString();
    }


}
