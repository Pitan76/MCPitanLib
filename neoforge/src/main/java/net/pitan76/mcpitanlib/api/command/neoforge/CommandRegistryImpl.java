package net.pitan76.mcpitanlib.api.command.neoforge;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static net.pitan76.mcpitanlib.api.command.CommandRegistry.forArgsCmd;
import static net.pitan76.mcpitanlib.api.command.CommandRegistry.latestCommandRegistryAccess;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = "mcpitanlib")
public class CommandRegistryImpl {

    private static final List<Consumer<RegisterCommandsEvent>> commandRegistrations = new CopyOnWriteArrayList<>();

    public static void register(String name, LiteralCommand command) {
        commandRegistrations.add(event -> {
            latestCommandRegistryAccess = event.getBuildContext();

            CommandSettings settings = new CommandSettings();
            command.init(settings);

            LiteralArgumentBuilder<CommandSourceStack> builder = LiteralArgumentBuilder.<CommandSourceStack>literal(name)
                    .requires(settings::requires)
                    .executes(context -> {
                        ServerCommandEvent cmdEvent = new ServerCommandEvent();
                        cmdEvent.setContext(context);
                        command.execute(cmdEvent);
                        return command.isSuccess;
                    });

            forArgsCmd(command, builder);

            event.getDispatcher().register(builder);
        });
    }

    public static void register(LiteralArgumentBuilder<CommandSourceStack> builder) {
        commandRegistrations.add(event -> {
            latestCommandRegistryAccess = event.getBuildContext();
            event.getDispatcher().register(builder);
        });
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        for (Consumer<RegisterCommandsEvent> registration : commandRegistrations) {
            registration.accept(event);
        }
    }
}