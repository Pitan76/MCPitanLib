package net.pitan76.mcpitanlib.api.util;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.core.command.CommandResult;

public class CommandManagerUtil {
    public static CommandManager getCommandManager(MinecraftServer server) {
        return server.getCommandManager();
    }

    public static CommandResult execute(MinecraftServer server, String command) {
        CommandDispatcher<ServerCommandSource> dispatcher = getCommandManager(server).getDispatcher();
        ServerCommandSource source = server.getCommandSource();

        if (command.startsWith("/")) {
            command = command.substring(1);
        }

        CommandResult cr = new CommandResult();
        cr.setSuccess(false);
        cr.setSource(source);

        try {
            int result = dispatcher.execute(command, source);
            cr.setResult(result);
            cr.setSuccess(true);
        } catch (CommandSyntaxException e) {
            cr.setMessage(e.getMessage());
            cr.setErrorType(CommandResult.ErrorType.COMMAND_SYNTAX_ERROR);
        } catch (RuntimeException e) {
            cr.setMessage(e.getMessage());
            cr.setErrorType(CommandResult.ErrorType.RUNTIME_ERROR);
        }

        return cr;
    }

    public static CommandResult execute(ServerCommandSource source, String command) {
        CommandDispatcher<ServerCommandSource> dispatcher = source.getMinecraftServer().getCommandManager().getDispatcher();

        if (command.startsWith("/")) {
            command = command.substring(1);
        }

        CommandResult cr = new CommandResult();
        cr.setSuccess(false);
        cr.setSource(source);

        try {
            int result = dispatcher.execute(command, source);
            cr.setResult(result);
            cr.setSuccess(true);
        } catch (CommandSyntaxException e) {
            cr.setMessage(e.getMessage());
            cr.setErrorType(CommandResult.ErrorType.COMMAND_SYNTAX_ERROR);
        } catch (RuntimeException e) {
            cr.setMessage(e.getMessage());
            cr.setErrorType(CommandResult.ErrorType.RUNTIME_ERROR);
        }

        return cr;
    }

    public static CommandResult execute(Player player, String command) {
        return execute(getCommandSource(player), command);
    }

    public static ServerCommandSource getCommandSource(MinecraftServer server) {
        return server.getCommandSource();
    }

    public static ServerCommandSource getCommandSource(Player player) {
        return player.getEntity().getCommandSource();
    }

    public static ServerCommandSource withLevel(ServerCommandSource source, int level) {
        return source.withLevel(level);
    }
}
