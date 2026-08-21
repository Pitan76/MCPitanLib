package net.pitan76.mcpitanlib.api.util.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtSizeTracker;
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
    public static NbtCompound read(Path path) throws IOException {
        return NbtIo.read(path);
    }

    public static NbtCompound read(File file) throws IOException {
        return read(file.toPath());
    }

    public static NbtCompound read(DataInput input) throws IOException {
        return NbtIo.readCompound(input, NbtSizeTracker.ofUnlimitedBytes());
    }

    public static NbtCompound read(InputStream input) throws IOException {
        return read((DataInput) new DataInputStream(input));
    }

    /**
     * 非圧縮のNBTファイルへ書き出す。
     * @param nbt NbtCompound
     * @param path ファイルパス
     */
    public static void write(NbtCompound nbt, Path path) throws IOException {
        NbtIo.write(nbt, path);
    }

    public static void write(NbtCompound nbt, File file) throws IOException {
        write(nbt, file.toPath());
    }

    public static void write(NbtCompound nbt, DataOutput output) throws IOException {
        NbtIo.writeCompound(nbt, output);
    }

    public static void write(NbtCompound nbt, OutputStream output) throws IOException {
        write(nbt, (DataOutput) new DataOutputStream(output));
    }

    /**
     * gzip圧縮されたNBTファイルを読み込む。
     * @param path ファイルパス
     * @return NbtCompound
     */
    public static NbtCompound readCompressed(Path path) throws IOException {
        return NbtIo.readCompressed(path, NbtSizeTracker.ofUnlimitedBytes());
    }

    public static NbtCompound readCompressed(File file) throws IOException {
        return readCompressed(file.toPath());
    }

    public static NbtCompound readCompressed(InputStream input) throws IOException {
        return NbtIo.readCompressed(input, NbtSizeTracker.ofUnlimitedBytes());
    }

    /**
     * gzip圧縮してNBTファイルへ書き出す。
     * @param nbt NbtCompound
     * @param path ファイルパス
     */
    public static void writeCompressed(NbtCompound nbt, Path path) throws IOException {
        NbtIo.writeCompressed(nbt, path);
    }

    public static void writeCompressed(NbtCompound nbt, File file) throws IOException {
        writeCompressed(nbt, file.toPath());
    }

    public static void writeCompressed(NbtCompound nbt, OutputStream output) throws IOException {
        NbtIo.writeCompressed(nbt, output);
    }
}
