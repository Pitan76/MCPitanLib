package net.pitan76.mcpitanlib.api.command.fabric;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

import static net.pitan76.mcpitanlib.api.command.CommandRegistry.forArgsCmd;
import static net.pitan76.mcpitanlib.api.command.CommandRegistry.latestCommandRegistryAccess;

public class CommandRegistryImpl {

    public static void register(String name, LiteralCommand command) {
        CommandRegistrationCallback.EVENT.register((dispatcher, registry, environment) -> {
            latestCommandRegistryAccess = registry;

            CommandSettings settings = new CommandSettings();
            command.init(settings);

            LiteralArgumentBuilder<CommandSourceStack> builder = LiteralArgumentBuilder.<CommandSourceStack>literal(name).requires(settings::requires)
                    .executes(context -> {
                        ServerCommandEvent event = new ServerCommandEvent();
                        event.setContext(context);
                        command.execute(event);
                        return command.isSuccess;
                    });

            forArgsCmd(command, builder);

            //register(builder);
            dispatcher.register(builder);
        });
    }

    public static void register(LiteralArgumentBuilder<CommandSourceStack> builder) {
        CommandRegistrationCallback.EVENT.register((dispatcher, registry, environment) -> {
                    latestCommandRegistryAccess = registry;
                    dispatcher.register(builder);
                }
        );
    }
}
