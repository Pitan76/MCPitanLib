package net.pitan76.mcpitanlib.api.util.nbt;

import net.minecraft.nbt.NbtCompound;
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
     * @param file ファイル
     * @return NbtCompound
     */
    public static NbtCompound read(File file) throws IOException {
        return NbtIo.read(file);
    }

    public static NbtCompound read(Path path) throws IOException {
        return read(path.toFile());
    }

    public static NbtCompound read(DataInput input) throws IOException {
        return NbtIo.read(input);
    }

    public static NbtCompound read(InputStream input) throws IOException {
        return read((DataInput) new DataInputStream(input));
    }

    /**
     * 非圧縮のNBTファイルへ書き出す。
     * @param nbt NbtCompound
     * @param file ファイル
     */
    public static void write(NbtCompound nbt, File file) throws IOException {
        NbtIo.write(nbt, file);
    }

    public static void write(NbtCompound nbt, Path path) throws IOException {
        write(nbt, path.toFile());
    }

    public static void write(NbtCompound nbt, DataOutput output) throws IOException {
        NbtIo.write(nbt, output);
    }

    public static void write(NbtCompound nbt, OutputStream output) throws IOException {
        write(nbt, (DataOutput) new DataOutputStream(output));
    }

    /**
     * gzip圧縮されたNBTファイルを読み込む。
     * @param file ファイル
     * @return NbtCompound
     */
    public static NbtCompound readCompressed(File file) throws IOException {
        return NbtIo.readCompressed(file);
    }

    public static NbtCompound readCompressed(Path path) throws IOException {
        return readCompressed(path.toFile());
    }

    public static NbtCompound readCompressed(InputStream input) throws IOException {
        return NbtIo.readCompressed(input);
    }

    /**
     * gzip圧縮してNBTファイルへ書き出す。
     * @param nbt NbtCompound
     * @param file ファイル
     */
    public static void writeCompressed(NbtCompound nbt, File file) throws IOException {
        NbtIo.writeCompressed(nbt, file);
    }

    public static void writeCompressed(NbtCompound nbt, Path path) throws IOException {
        writeCompressed(nbt, path.toFile());
    }

    public static void writeCompressed(NbtCompound nbt, OutputStream output) throws IOException {
        NbtIo.writeCompressed(nbt, output);
    }
}
