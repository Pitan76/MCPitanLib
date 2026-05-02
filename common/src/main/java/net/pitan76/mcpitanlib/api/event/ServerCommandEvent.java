package net.pitan76.mcpitanlib.api.event;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.StringRange;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.world.entity.Entity;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

public class ServerCommandEvent extends CommandEvent<CommandSourceStack> {

    public CommandContext<CommandSourceStack> getContext() {
        return context;
    }

    public void setContext(CommandContext<CommandSourceStack> context) {
        this.context = context;
    }

    public CommandSourceStack getSource() {
        return getContext().getSource();
    }

    public net.minecraft.world.entity.player.Player getPlayerEntity() throws CommandSyntaxException {
        return getSource().getPlayer();
    }

    public Player getPlayer() throws CommandSyntaxException {
        return new Player(getPlayerEntity());
    }

    public Level getWorld() {
        return getSource().getLevel();
    }

    public Entity getEntity() {
        return getSource().getEntity();
    }

    public String getInput() {
        return getContext().getInput();
    }

    public Command<CommandSourceStack> getContextCommand() {
        return getContext().getCommand();
    }

    public CommandContext<CommandSourceStack> getChild() {
        return getContext().getChild();
    }

    public CommandContext<CommandSourceStack> getLastChild() {
        return getContext().getLastChild();
    }

    public StringRange getRange() {
        return getContext().getRange();
    }

    // Text
    public void sendSuccess(Component message, boolean broadcastToOps) {
        getSource().sendSuccess(() -> message, broadcastToOps);
    }

    public void sendFailure(Component message) {
        getSource().sendFailure(message);
    }

    public void sendSuccess(Component message) {
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

    public void sendSuccess(TextComponent message, boolean broadcastToOps) {
        sendSuccess(message.getText(), broadcastToOps);
    }

    public void sendSuccess(TextComponent message) {
        sendSuccess(message.getText());
    }

    public void sendFailure(TextComponent message) {
        sendFailure(message.getText());
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(getWorld());
    }

    public EntityWrapper getEntityWrapper() {
        return EntityWrapper.of(getEntity());
    }
}
