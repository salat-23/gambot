import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.JDAImpl;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public class Main extends ListenerAdapter {

    private static final String TOKEN = "ODczMjY0NjgzMTY2MTA1NjYw.YQ15HA.WxqwXOcZIUxyhLhmjTcjsAImgg8";

    public static void main(String[] args) throws LoginException {

        JDABuilder jdaBuilder = JDABuilder.createDefault(TOKEN);

        jdaBuilder.setActivity(Activity.watching("you"));

        jdaBuilder.build();


    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        Message message = event.getMessage();

        event.getChannel().sendMessage("The message was sent by " + event.getAuthor() + "in " + event.getChannel().toString()).queue();
    }
}
