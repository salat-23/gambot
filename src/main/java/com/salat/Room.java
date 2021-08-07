package com.salat;

import java.util.Random;
import java.util.UUID;
import com.salat.CardGame.Durak.*;

public class Room {

    private String roomName;
    private UUID roomId;
    private int playersAmount;
    private Game game;



    public Room(GameManager.GameType game, String name, int playersAmount) {
        this.roomName = name;
        this.playersAmount = playersAmount;
        switch (game) {
            case DURAK: this.game = new DurakGame();
        }
        roomId = UUID.randomUUID();

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
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
