package net.pitan76.mcpitanlib.api.lookup.block;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;

/**
 * よく使うBlockApiLookupをラップ済みの形で持っておくためのクラス。
 * <p>
 * {@code new BlockApiLookupWithDirection<>(ItemStorage.SIDED)} を毎回書かずに済む。
 * <p>
 * エネルギーはcommonの {@code EnergyLookup#ENERGY} を使うこと。
 * Team Reborn Energyが必須依存ではないため、生のlookupをここに定数として持つと
 * 未導入環境でクラスロードに失敗する。
 */
public class BlockApiLookups {

    /**
     * アイテムの搬入出。
     */
    public static final BlockApiLookupWithDirection<Storage<ItemVariant>> ITEM = BlockApiLookupWithDirection.ofDir(ItemStorage.SIDED);

    /**
     * 流体の搬入出。
     */
    public static final BlockApiLookupWithDirection<Storage<FluidVariant>> FLUID = BlockApiLookupWithDirection.ofDir(FluidStorage.SIDED);
}
