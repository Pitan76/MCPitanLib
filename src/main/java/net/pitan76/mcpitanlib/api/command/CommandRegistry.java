package net.pitan76.mcpitanlib.api.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.architectury.event.events.common.CommandRegistrationEvent;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.Commands;
import net.minecraft.commands.CommandSourceStack;
import net.pitan76.mcpitanlib.api.command.argument.*;
import net.pitan76.mcpitanlib.api.event.*;

import java.util.Map;

public class CommandRegistry {

    @Deprecated
    public static CommandBuildContext latestCommandRegistryAccess;

    public static void register(String name, LiteralCommand command) {
        CommandRegistrationEvent.EVENT.register((dispatcher, registry, environment) -> {
            latestCommandRegistryAccess = registry;

            CommandSettings settings = new CommandSettings();
            command.init(settings);

            LiteralArgumentBuilder<CommandSourceStack> builder = LiteralArgumentBuilder.<CommandSourceStack>literal(name).requires(settings::requires)
                    .executes(context -> {
                        ServerCommandEvent event = new ServerCommandEvent();
                        event.setContext(context);
                        command.execute(event);
                        return command.isSuccess;
                    });

            forArgsCmd(command, builder);

            //register(builder);
            dispatcher.register(builder);
        });
    }

    public static void register(LiteralArgumentBuilder<CommandSourceStack> builder) {
        CommandRegistrationEvent.EVENT.register((dispatcher, registry, environment) -> {
                    latestCommandRegistryAccess = registry;
                    dispatcher.register(builder);
                }
        );
    }

    private static <T extends ArgumentBuilder<CommandSourceStack, T>> void forArgsCmd(AbstractCommand<?> absCmd, ArgumentBuilder<CommandSourceStack, T> builder) {

        if (!absCmd.getArgumentCommands().isEmpty()) {
            // 引数コマンド
            for (Map.Entry<String, ? extends AbstractCommand<?>> argCmd : absCmd.getArgumentCommands().entrySet()) {
                ArgumentBuilder<CommandSourceStack, ?> nextBuilder = null;
                argCmd.getValue().init(new CommandSettings());

                if (argCmd.getValue() instanceof LiteralCommand) {
                    LiteralCommand command = (LiteralCommand) argCmd.getValue();
                    nextBuilder = Commands.literal(argCmd.getKey())
                            .executes(context -> {
                                        ServerCommandEvent event = new ServerCommandEvent();
                                        event.setContext(context);
                                        event.setCommand(command);
                                        command.execute(event);
                                        return command.isSuccess;
                                    }
                            );
                }

                if (argCmd.getValue() instanceof RequiredCommand) {
                    RequiredCommand<?> command = (RequiredCommand<?>) argCmd.getValue();

                    nextBuilder = Commands.argument(argCmd.getKey(), command.getArgumentType())
                            .executes(context -> {
                                        ServerCommandEvent event = new ServerCommandEvent();
                                        if (command instanceof IntegerCommand) {
                                            event = new IntegerCommandEvent();
                                        }
                                        if (command instanceof DoubleCommand) {
                                            event = new DoubleCommandEvent();
                                        }
                                        if (command instanceof FloatCommand) {
                                            event = new FloatCommandEvent();
                                        }
                                        if (command instanceof LongCommand) {
                                            event = new LongCommandEvent();
                                        }
                                        if (command instanceof BooleanCommand) {
                                            event = new BooleanCommandEvent();
                                        }
                                        if (command instanceof StringCommand) {
                                            event = new StringCommandEvent();
                                        }
                                        if (command instanceof EntityCommand) {
                                            event = new EntityCommandEvent();
                                        }
                                        if (command instanceof EntitiesCommand) {
                                            event = new EntitiesCommandEvent();
                                        }
                                        if (command instanceof PlayerCommand) {
                                            event = new PlayerCommandEvent();
                                        }
                                        if (command instanceof PlayersCommand) {
                                            event = new PlayersCommandEvent();
                                        }
                                        if (command instanceof ItemCommand) {
                                            event = new ItemCommandEvent();
                                        }
                                        if (command instanceof BlockCommand) {
                                            event = new BlockCommandEvent();
                                        }

                                        event.setContext(context);
                                        event.setCommand(command);
                                        command.execute(event);
                                        return command.isSuccess;
                                    }
                            );
                }
                forArgsCmd(argCmd.getValue(), nextBuilder);
                builder.then(nextBuilder);
            }
        }
    }
}