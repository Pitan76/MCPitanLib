package net.pitan76.mcpitanlib.api.util.nbt;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtAccounter;
import net.minecraft.nbt.NbtIo;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 * NBTのバイナリ入出力を行うユーティリティ。
 * バージョンによってNbtIoのシグネチャが異なるため、ここで吸収する。
 */
public class NbtIoUtil {

    /**
     * 非圧縮のNBTファイルを読み込む。
     * @param path ファイルパス
     * @return NbtCompound
     */
    public static CompoundTag read(Path path) throws IOException {
        return NbtIo.read(path);
    }

    public static CompoundTag read(File file) throws IOException {
        return read(file.toPath());
    }

    public static CompoundTag read(DataInput input) throws IOException {
        return NbtIo.read(input, NbtAccounter.unlimitedHeap());
    }

    public static CompoundTag read(InputStream input) throws IOException {
        return read((DataInput) new DataInputStream(input));
    }

    /**
     * 非圧縮のNBTファイルへ書き出す。
     * @param nbt NbtCompound
     * @param path ファイルパス
     */
    public static void write(CompoundTag nbt, Path path) throws IOException {
        NbtIo.write(nbt, path);
    }

    public static void write(CompoundTag nbt, File file) throws IOException {
        write(nbt, file.toPath());
    }

    public static void write(CompoundTag nbt, DataOutput output) throws IOException {
        NbtIo.write(nbt, output);
    }

    public static void write(CompoundTag nbt, OutputStream output) throws IOException {
        write(nbt, (DataOutput) new DataOutputStream(output));
    }

    /**
     * gzip圧縮されたNBTファイルを読み込む。
     * @param path ファイルパス
     * @return NbtCompound
     */
    public static CompoundTag readCompressed(Path path) throws IOException {
        return NbtIo.readCompressed(path, NbtAccounter.unlimitedHeap());
    }

    public static CompoundTag readCompressed(File file) throws IOException {
        return readCompressed(file.toPath());
    }

    public static CompoundTag readCompressed(InputStream input) throws IOException {
        return NbtIo.readCompressed(input, NbtAccounter.unlimitedHeap());
    }

    /**
     * gzip圧縮してNBTファイルへ書き出す。
     * @param nbt NbtCompound
     * @param path ファイルパス
     */
    public static void writeCompressed(CompoundTag nbt, Path path) throws IOException {
        NbtIo.writeCompressed(nbt, path);
    }

    public static void writeCompressed(CompoundTag nbt, File file) throws IOException {
        writeCompressed(nbt, file.toPath());
    }

    public static void writeCompressed(CompoundTag nbt, OutputStream output) throws IOException {
        NbtIo.writeCompressed(nbt, output);
    }
}
