package net.pitan76.mcpitanlib.api.command.fabric;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.pitan76.mcpitanlib.api.command.CommandRegistry;
import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public class CommandRegistryImpl {
    public static void register(String name, LiteralCommand command) {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            CommandSettings settings = new CommandSettings();
            command.init(settings);

            LiteralArgumentBuilder<ServerCommandSource> builder = LiteralArgumentBuilder.<ServerCommandSource>literal(name)
                    .requires(settings::requires)
                    .executes(context -> {
                        ServerCommandEvent event = new ServerCommandEvent();
                        event.setContext(context);
                        command.execute(event);
                        return command.isSuccess;
                    });

            CommandRegistry.forArgsCmd(command, builder);

            dispatcher.register(builder);
        });
    }

    public static void register(LiteralArgumentBuilder<ServerCommandSource> builder) {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(builder));
    }
}
