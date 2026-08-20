package net.pitan76.mcpitanlib.api.command.forge;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class CommandRegistryImpl {
    private static final List<LiteralArgumentBuilder<ServerCommandSource>> builders = new CopyOnWriteArrayList<LiteralArgumentBuilder<ServerCommandSource>>();

    public static void register(LiteralArgumentBuilder<ServerCommandSource> builder) {
        builders.add(builder);
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        for (LiteralArgumentBuilder<ServerCommandSource> builder : builders) {
            event.getDispatcher().register(builder);
        }
    }
}
