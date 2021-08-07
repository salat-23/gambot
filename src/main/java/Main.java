import CardGame.DurakGame;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class Main extends ListenerAdapter {

    public static void main(String[] args) throws LoginException
    {
        Scanner scanner = new Scanner(System.in);


        String token = scanner.nextLine();

        JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new Main())
                .setActivity(Activity.playing("Type !ping"))
                .build();
        System.out.println("The bot is online!");
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
}
