package com.salat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import com.salat.CardGame.Durak.*;

public class Room {

    private String roomName;
    private UUID roomId;
    private int playersAmount;
    private DurakGame game;
    public List<Player> players;


    public Room(GameManager.GameType game, String name, int playersAmount) {
        this.roomName = name;
        this.playersAmount = playersAmount;
        switch (game) {
            case DURAK: this.game = new DurakGame();
        }
        players = new ArrayList<>();
        roomId = UUID.randomUUID();

    }

    public String getStringRoomInfo() {
        StringBuilder playerList = new StringBuilder();
        playerList.append("Room name: ").append(roomName).append("\n")
                .append("Players: ").append(players.size()).append("/").append(playersAmount).append("\n");
        for (int i = 0; i < players.size(); i++) {
            playerList.append(i + 1).append(" - ").append(players.get(i).getName()).append(" ").append(players.get(i).isReady() ? "Ready" : "Is not ready").append("\n");
        }
        return playerList.toString();
    }

    public Player getPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public boolean checkReady() {
        for (Player player : players) {
            if (!player.isReady()) return false;
        }
        return true;
    }

    public DurakGame getGame() {
        return game;
    }

    public void setGame(DurakGame game) {
        this.game = game;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public int getPlayersAmount() {
        return playersAmount;
    }

    public void setPlayersAmount(int playersAmount) {
        this.playersAmount = playersAmount;
    }

}
