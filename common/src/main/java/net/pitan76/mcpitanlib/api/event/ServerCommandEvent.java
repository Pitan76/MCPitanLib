package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class ServerCommandEvent extends CommandEvent<ServerCommandSource> {

    public CommandContext<ServerCommandSource> getContext() {
        return context;
    }

    public void setContext(CommandContext<ServerCommandSource> context) {
        this.context = context;
    }

    public PlayerEntity getPlayerEntity() throws CommandSyntaxException {
        return context.getSource().getPlayer();
    }

    public Player getPlayer() throws CommandSyntaxException {
        return new Player(getPlayerEntity());
    }

    public World getWorld() {
        return context.getSource().getWorld();
    }

    public Entity getEntity() {
        return context.getSource().getEntity();
    }

    public String getInput() {
        return context.getInput();
    }

    public Command<ServerCommandSource> getContextCommand() {
        return context.getCommand();
    }

    public CommandContext<ServerCommandSource> getChild() {
        return context.getChild();
    }

    public CommandContext<ServerCommandSource> getLastChild() {
        return context.getLastChild();
    }

    public StringRange getRange() {
        return context.getRange();
    }

    public void sendSuccess(Text message, boolean broadcastToOps) {
        context.getSource().sendFeedback(message, broadcastToOps);
    }

    public void sendFailure(Text message) {
        context.getSource().sendError(message);
    }

    public void sendSuccess(Text message) {
        sendSuccess(message, false);
    }

    public void sendSuccess(String message, boolean broadcastToOps) {
        sendSuccess(TextUtil.literal(message), broadcastToOps);
    }

    public void sendSuccess(String message) {
        sendSuccess(TextUtil.literal(message));
    }

    public void sendFailure(String message) {
        sendFailure(TextUtil.literal(message));
    }

    public boolean isClient() {
        return WorldUtil.isClient(getWorld());
    }
}
