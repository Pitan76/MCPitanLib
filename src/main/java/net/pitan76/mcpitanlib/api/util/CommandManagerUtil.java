package net.pitan76.mcpitanlib.api.util;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.permissions.LevelBasedPermissionSet;
import net.minecraft.server.permissions.PermissionLevel;
import net.minecraft.server.MinecraftServer;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.core.command.CommandResult;

public class CommandManagerUtil {
    public static Commands getCommandManager(MinecraftServer server) {
        return server.getCommands();
    }

    public static CommandResult execute(MinecraftServer server, String command) {
        CommandDispatcher<CommandSourceStack> dispatcher = getCommandManager(server).getDispatcher();
        CommandSourceStack source = server.createCommandSourceStack();

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

    public static CommandResult execute(CommandSourceStack source, String command) {
        CommandDispatcher<CommandSourceStack> dispatcher = source.getServer().getCommands().getDispatcher();

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

    public static CommandSourceStack getCommandSource(MinecraftServer server) {
        return server.createCommandSourceStack();
    }

    public static CommandSourceStack getCommandSource(Player player) {
        return player.getEntity().createCommandSourceStackForNameResolution((ServerLevel) player.getWorld());
    }

    public static CommandSourceStack withLevel(CommandSourceStack source, int level) {
        return source.withPermission(LevelBasedPermissionSet.forLevel(PermissionLevel.byId(level)));
    }
}
