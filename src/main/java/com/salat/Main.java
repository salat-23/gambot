package com.salat;

import com.salat.CardGame.Durak.DurakGame;
import com.salat.GameManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;
import java.util.List;
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
        guild.upsertCommand("viewdeck", "displays your deck").queue();
        guild.upsertCommand("create", "displays your deck").queue();
                /*.addOption(OptionType.STRING, "name", "Room name")
                .addOption(OptionType.INTEGER, "playeramount", "Players amount")
                .addOption(OptionType.STRING, "gametype", "Type or name of the game").queue();*/
        guild.upsertCommand("ready", "Changes your ready flag in the current room").queue();
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
            String userName = event.getUser().getName();
            GameManager.createNewRoom(event.getUser().getName());
            Room room = GameManager.rooms.get(userName);
            event.reply("The game by " + userName + " has been created with id: " + room.getRoomId() +
                    "\nRoom name: " + room.getRoomName() +
                    "\nPlayers amount " + room.getPlayersAmount()).queue();
        }
    }
}
