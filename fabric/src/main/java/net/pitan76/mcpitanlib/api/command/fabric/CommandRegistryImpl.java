package net.pitan76.mcpitanlib.api.command.fabric;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;

public class CommandRegistryImpl {
    public static void register(final LiteralArgumentBuilder<ServerCommandSource> builder) {
        CommandRegistrationCallback.EVENT.register(new CommandRegistrationCallback() {
            @Override
            public void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
                dispatcher.register(builder);
            }
        });
    }
}
