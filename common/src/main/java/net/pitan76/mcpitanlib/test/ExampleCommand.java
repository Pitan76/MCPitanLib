package net.pitan76.mcpitanlib.test;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.command.CommandSettings;
import net.pitan76.mcpitanlib.api.command.LiteralCommand;
import net.pitan76.mcpitanlib.api.command.argument.IntegerCommand;
import net.pitan76.mcpitanlib.api.event.IntegerCommandEvent;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public class ExampleCommand extends LiteralCommand {

    @Override
    public void init(CommandSettings settings) {
        addArgumentCommand("item", new LiteralCommand() {
            @Override
            public void init(CommandSettings settings) {
                addArgumentCommand(new IntegerCommand() {
                    @Override
                    public void execute(IntegerCommandEvent event) {
                        try {
                            event.getPlayer().offerOrDrop(new ItemStack(ExampleMod.EXAMPLE_ITEM.getOrNull(), event.getValue()));
                        } catch (CommandSyntaxException ignored) {

                        }
                    }

                    @Override
                    public String getArgumentName() {
                        return "count";
                    }
                });
            }

            @Override
            public void execute(ServerCommandEvent event) {
                try {
                    event.getPlayer().offerOrDrop(new ItemStack(ExampleMod.EXAMPLE_ITEM.getOrNull()));
                } catch (CommandSyntaxException ignored) {

                }
            }
        });
    }

    @Override
    public void execute(ServerCommandEvent event) {
        System.out.println(event.getInput());
    }
}
