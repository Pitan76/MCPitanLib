package net.pitan76.mcpitanlib.api.network.v2;

import net.minecraft.network.FriendlyByteBuf;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.api.network.v2.args.ClientReceiveEvent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.network.CompatPacketByteBuf;

import java.util.function.Consumer;

public class ClientNetworking {
    public static void send(CompatIdentifier id, FriendlyByteBuf buf) {
        net.pitan76.mcpitanlib.api.network.ClientNetworking.send(id.toMinecraft(), buf);
    }

    public static void registerReceiver(CompatIdentifier id, Consumer<ClientReceiveEvent> consumer) {
        net.pitan76.mcpitanlib.api.network.ClientNetworking.registerReceiver(id.toMinecraft(), (client, player, buf) -> {
            consumer.accept(new ClientReceiveEvent(client, player, buf));
        });
    }

    public static void send(CompatIdentifier id, CompatPacketByteBuf buf) {
        send(id, buf.getRaw());
    }

    public static void send(CompatIdentifier id) {
        send(id, PacketByteUtil.create());
    }
}
