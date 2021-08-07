package com.salat;

import com.salat.CardGame.Durak.DurakGame;
import com.salat.CardGame.Durak.Player;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;
import java.util.Objects;
import java.util.Scanner;

public class Main extends ListenerAdapter {

    private static String token;

    public static void main(String[] args) throws LoginException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        token = scanner.nextLine();

        JDA jda = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Main())
                .setActivity(Activity.playing("Type !ping"))
                .build().awaitReady();

        upsertCommands(jda);

        System.out.println("The bot is online!");
    }

    private static void upsertCommands(JDA jda) {
        Guild guild = jda.getGuildById("803235882286579752");
        guild.upsertCommand("view_deck", "displays your deck").queue();
        guild.upsertCommand("create", "displays your deck").queue();
                /*.addOption(OptionType.STRING, "name", "Room name")
                .addOption(OptionType.INTEGER, "players_amount", "Players amount")
                .addOption(OptionType.STRING, "game_type", "Type or name of the game").queue();*/
        guild.upsertCommand("ready", "Changes your ready flag in the current room").queue();
        guild.upsertCommand("join", "Joins the room").addOption(OptionType.STRING, "room_name", "The name of room you want to join").queue();
        guild.upsertCommand("room", "Shows room info").queue();
        guild.upsertCommand("show_rooms", "Shows rooms list").queue();
        guild.upsertCommand("start", "Sets the game started").queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals("!testdeck"))
        {
            DurakGame game = new DurakGame();
            MessageChannel channel = event.getChannel();
            channel.sendMessage(game.getDeckInfo()).queue();
        }
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {

        if (event.getName().equals("create")) {
            String userName = event.getUser().getAsTag();
            GameManager.createNewRoom(userName);
            Room room = GameManager.rooms.get(userName);
            event.reply("The game by " + userName + " has been created with id: " + room.getRoomId() +
                    "\nRoom name: " + room.getRoomName() +
                    "\nPlayers amount " + room.getPlayersAmount()).queue();
            room.players.add(new Player(userName));
        }
        if (event.getName().equals("join")) {
            String roomName = event.getOption("room_name").getAsString();
            if (roomName == null) {
                event.reply("The room is null " + roomName).queue();
                return;
            }
            if (!GameManager.rooms.containsKey(roomName)) {
                event.reply("The room does not exist " + roomName).queue();
                return;
            }
            Room room = GameManager.rooms.get(roomName);
            room.players.add(new Player(event.getUser().getAsTag()));
            event.reply(event.getUser().getAsTag() + " has joined " + roomName).queue();
        }
        if (event.getName().equals("room")) {
            Room room = GameManager.getRoomByPlayerName(event.getUser().getAsTag());
            if (room == null) {
                event.reply("You are not in the room right now").queue();
                return;
            }
            String roomInfo = room.getStringRoomInfo();

            event.reply(roomInfo).queue();
        }
        if (event.getName().equals("show_rooms")) {
            event.reply(GameManager.getRoomListAsString()).queue();
        }
        if (event.getName().equals("ready")) {
            Room room = GameManager.getRoomByPlayerName(event.getUser().getAsTag());
            if (room == null) {
                event.reply("You are not in the room right now").queue();
                return;
            }
            String playerName = event.getUser().getAsTag();
            Player player = room.getPlayerByName(playerName);
            player.setReady(!player.isReady());
            event.reply("Your status now is " + (player.isReady() ? "ready" : "not ready")).setEphemeral(true).queue();
        }
        if (event.getName().equals("start")) {
            Room room = GameManager.getRoomByPlayerName(event.getUser().getAsTag());
            if (room == null)  {
                event.reply("You are not in the room right now").queue();
                return;
            }
            CheckAndSetReady(event, room);
        }

    }

    public static void CheckAndSetReady(SlashCommandEvent event, Room room) {
        if (!room.checkReady()) event.reply("Not all players are ready!").queue();

        DurakGame game = room.getGame();
        game.setStarted(true);
        event.reply("The game has been started!").setEphemeral(true).queue();
    }
}
