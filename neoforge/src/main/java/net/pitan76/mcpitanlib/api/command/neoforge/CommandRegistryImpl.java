package net.pitan76.mcpitanlib.api.command.neoforge;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.command.CommandRegistry;
import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class CommandRegistryImpl {

    private static final Map<String, LiteralCommand> commands = new LinkedHashMap<>();
    private static final List<LiteralArgumentBuilder<ServerCommandSource>> builders = new ArrayList<>();

    public static void register(String name, LiteralCommand command) {
        commands.put(name, command);
    }

    public static void register(LiteralArgumentBuilder<ServerCommandSource> builder) {
        builders.add(builder);
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandRegistry.latestCommandRegistryAccess = event.getBuildContext();

        for (Map.Entry<String, LiteralCommand> entry : commands.entrySet()) {
            LiteralCommand command = entry.getValue();

            CommandSettings settings = new CommandSettings();
            command.init(settings);

            LiteralArgumentBuilder<ServerCommandSource> builder = LiteralArgumentBuilder.<ServerCommandSource>literal(entry.getKey())
                    .requires(settings::requires)
                    .executes(context -> {
                        ServerCommandEvent commandEvent = new ServerCommandEvent();
                        commandEvent.setContext(context);
                        command.execute(commandEvent);
                        return command.isSuccess;
                    });

            CommandRegistry.forArgsCmd(command, builder);

            event.getDispatcher().register(builder);
        }

        for (LiteralArgumentBuilder<ServerCommandSource> builder : builders) {
            event.getDispatcher().register(builder);
        }
    }
}
