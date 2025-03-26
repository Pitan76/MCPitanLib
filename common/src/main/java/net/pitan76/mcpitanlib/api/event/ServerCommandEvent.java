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

    public ServerCommandSource getSource() {
        return getContext().getSource();
    }

    public PlayerEntity getPlayerEntity() throws CommandSyntaxException {
        return getSource().getPlayer();
    }

    public Player getPlayer() throws CommandSyntaxException {
        return new Player(getPlayerEntity());
    }

    public World getWorld() {
        return getSource().getWorld();
    }

    public Entity getEntity() {
        return getSource().getEntity();
    }

    public String getInput() {
        return getContext().getInput();
    }

    public Command<ServerCommandSource> getContextCommand() {
        return getContext().getCommand();
    }

    public CommandContext<ServerCommandSource> getChild() {
        return getContext().getChild();
    }

    public CommandContext<ServerCommandSource> getLastChild() {
        return getContext().getLastChild();
    }

    public StringRange getRange() {
        return getContext().getRange();
    }

    // Text
    public void sendSuccess(Text message, boolean broadcastToOps) {
        getSource().sendFeedback(() -> message, broadcastToOps);
    }

    public void sendFailure(Text message) {
        getSource().sendError(message);
    }

    public void sendSuccess(Text message) {
        sendSuccess(message, false);
    }

    // String (Formatted)
    public void sendSuccess(String message, boolean broadcastToOps) {
        sendSuccess(TextUtil.convert(message), broadcastToOps);
    }

    public void sendSuccess(String message) {
        sendSuccess(message, false);
    }

    public void sendFailure(String message) {
        sendFailure(TextUtil.convert(message));
    }

    // Translatable
    public void sendSuccessWithTranslatable(String message, boolean broadcastToOps) {
        sendSuccess(TextUtil.convertWithTranslatable(message), broadcastToOps);
    }

    public void sendSuccessWithTranslatable(String message) {
        sendSuccessWithTranslatable(message, false);
    }

    public void sendFailureWithTranslatable(String message) {
        sendFailure(TextUtil.convertWithTranslatable(message));
    }

    // Raw
    public void sendSuccessRaw(String message, boolean broadcastToOps) {
        sendSuccess(TextUtil.literal(message), broadcastToOps);
    }

    public void sendSuccessRaw(String message) {
        sendSuccessRaw(message, false);
    }

    public void sendFailureRaw(String message) {
        sendFailure(TextUtil.literal(message));
    }

    public boolean isClient() {
        return WorldUtil.isClient(getWorld());
    }
}
