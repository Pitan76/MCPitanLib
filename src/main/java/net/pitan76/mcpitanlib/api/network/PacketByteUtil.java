package net.pitan76.mcpitanlib.api.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.core.BlockPos;
import net.pitan76.mcpitanlib.api.util.TextUtil;

import java.util.Map;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class PacketByteUtil {
    public static FriendlyByteBuf create() {
        return new FriendlyByteBuf(Unpooled.buffer());
    }


    public static FriendlyByteBuf empty() {
        return new FriendlyByteBuf(Unpooled.EMPTY_BUFFER);
    }

    public static <K, V> Map<K, V> readMap(FriendlyByteBuf buf, Function<FriendlyByteBuf, K> keyParser, Function<FriendlyByteBuf, V> valueParser) {
        return buf.readMap(keyParser::apply, valueParser::apply);
    }

    public static <K, V> void writeMap(FriendlyByteBuf buf, Map<K, V> map) {
        writeMap(buf, map, PacketByteUtil::writeVar, PacketByteUtil::writeVar);
    }

    public static <K, V> void writeMap(FriendlyByteBuf buf, Map<K, V> map, BiConsumer<FriendlyByteBuf, K> keySerializer, BiConsumer<FriendlyByteBuf, V> valueSerializer) {
        buf.writeMap(map, keySerializer::accept, valueSerializer::accept);
    }

    public static void writeVar(FriendlyByteBuf buf, Object obj) {
        if (obj instanceof String) {
            String s = (String) obj;
            buf.writeUtf(s);
        }
        if (obj instanceof Integer) {
            int i = (int) obj;
            buf.writeInt(i);
        }
        if (obj instanceof Long) {
            long l = (long) obj;
            buf.writeLong(l);
        }
        if (obj instanceof Short) {
            short s = (short) obj;
            buf.writeShort(s);
        }
        if (obj instanceof Boolean) {
            boolean b = (boolean) obj;
            buf.writeBoolean(b);
        }
        if (obj instanceof Byte) {
            byte b = (byte) obj;
            buf.writeByte(b);
        }
        if (obj instanceof CompoundTag) {
            CompoundTag nbt = (CompoundTag) obj;
            buf.writeNbt(nbt);
        }
        if (obj instanceof ItemStack) {
            ItemStack stack = (ItemStack) obj;
            buf.writeJsonWithCodec(ItemStack.CODEC, stack);
        }
        if (obj instanceof Identifier) {
            Identifier identifier = (Identifier) obj;
            buf.writeIdentifier(identifier);
        }
        if (obj instanceof Float) {
            Float f = (Float) obj;
            buf.writeFloat(f);
        }
        if (obj instanceof UUID) {
            UUID uuid = (UUID) obj;
            buf.writeUUID(uuid);
        }
        if (obj instanceof Component) {
            Component text = (Component) obj;
            buf.writeUtf(text.getString());
        }
        if (obj instanceof BlockPos) {
            BlockPos pos = (BlockPos) obj;
            buf.writeBlockPos(pos);
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            writeMap(buf, map);
        }
    }

    public static FriendlyByteBuf writeNbt(FriendlyByteBuf buf, CompoundTag nbt) {
        return buf.writeNbt(nbt);
    }

    public static CompoundTag readNbt(FriendlyByteBuf buf) {
        return buf.readNbt();
    }

    public static FriendlyByteBuf writeItemStack(FriendlyByteBuf buf, ItemStack stack) {
        buf.writeJsonWithCodec(ItemStack.CODEC, stack);
        return buf;
    }

    public static ItemStack readItemStack(FriendlyByteBuf buf) {
        return buf.readLenientJsonWithCodec(ItemStack.CODEC);
    }

    public static FriendlyByteBuf writeIdentifier(FriendlyByteBuf buf, Identifier identifier) {
        return buf.writeIdentifier(identifier);
    }

    public static Identifier readIdentifier(FriendlyByteBuf buf) {
        return buf.readIdentifier();
    }

    public static FriendlyByteBuf writeText(FriendlyByteBuf buf, Component text) {
        return buf.writeUtf(text.getString());
    }

    public static Component readText(FriendlyByteBuf buf) {
        return TextUtil.literal(buf.readUtf());
    }

    public static FriendlyByteBuf writeBlockPos(FriendlyByteBuf buf, BlockPos pos) {
        return buf.writeBlockPos(pos);
    }

    public static BlockPos readBlockPos(FriendlyByteBuf buf) {
        return buf.readBlockPos();
    }

    public static FriendlyByteBuf writeUuid(FriendlyByteBuf buf, UUID uuid) {
        return buf.writeUUID(uuid);
    }

    public static UUID readUuid(FriendlyByteBuf buf) {
        return buf.readUUID();
    }

    public static FriendlyByteBuf writeVarInt(FriendlyByteBuf buf, int i) {
        return buf.writeVarInt(i);
    }

    public static int readVarInt(FriendlyByteBuf buf) {
        return buf.readVarInt();
    }

    public static FriendlyByteBuf writeVarLong(FriendlyByteBuf buf, long l) {
        return buf.writeVarLong(l);
    }

    public static long readVarLong(FriendlyByteBuf buf) {
        return buf.readVarLong();
    }

    public static ByteBuf writeBoolean(FriendlyByteBuf buf, boolean b) {
        return buf.writeBoolean(b);
    }

    public static boolean readBoolean(FriendlyByteBuf buf) {
        return buf.readBoolean();
    }

    public static ByteBuf writeByte(FriendlyByteBuf buf, byte b) {
        return buf.writeByte(b);
    }

    public static byte readByte(FriendlyByteBuf buf) {
        return buf.readByte();
    }

    public static ByteBuf writeShort(FriendlyByteBuf buf, short s) {
        return buf.writeShort(s);
    }

    public static short readShort(FriendlyByteBuf buf) {
        return buf.readShort();
    }

    public static ByteBuf writeInt(FriendlyByteBuf buf, int i) {
        return buf.writeInt(i);
    }

    public static int readInt(FriendlyByteBuf buf) {
        return buf.readInt();
    }

    public static ByteBuf writeLong(FriendlyByteBuf buf, long l) {
        return buf.writeLong(l);
    }

    public static long readLong(FriendlyByteBuf buf) {
        return buf.readLong();
    }

    public static ByteBuf writeFloat(FriendlyByteBuf buf, float f) {
        return buf.writeFloat(f);
    }

    public static float readFloat(FriendlyByteBuf buf) {
        return buf.readFloat();
    }

    public static ByteBuf writeDouble(FriendlyByteBuf buf, double d) {
        return buf.writeDouble(d);
    }

    public static double readDouble(FriendlyByteBuf buf) {
        return buf.readDouble();
    }

    public static FriendlyByteBuf writeByteArray(FriendlyByteBuf buf, byte[] bytes) {
        return buf.writeByteArray(bytes);
    }

    public static byte[] readByteArray(FriendlyByteBuf buf) {
        return buf.readByteArray();
    }

    public static FriendlyByteBuf writeString(FriendlyByteBuf buf, String s) {
        return buf.writeUtf(s);
    }

    public static String readString(FriendlyByteBuf buf) {
        return buf.readUtf();
    }

    public static FriendlyByteBuf writeIntArray(FriendlyByteBuf buf, int[] ints) {
        return buf.writeVarIntArray(ints);
    }

    public static int[] readIntArray(FriendlyByteBuf buf) {
        return buf.readVarIntArray();
    }

    public static FriendlyByteBuf writeLongArray(FriendlyByteBuf buf, long[] longs) {
        return buf.writeLongArray(longs);
    }

    public static long[] readLongArray(FriendlyByteBuf buf) {
        return buf.readLongArray();
    }

    public static Tag readUnlimitedNbt(FriendlyByteBuf buf) {
        return buf.readNbt(NbtAccounter.unlimitedHeap());
    }
}


