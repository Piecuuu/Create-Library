package com.petrolpark.util;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.BiConsumer;

import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;

public class NetworkHelper {
    
    public static Vec3 readVec3(FriendlyByteBuf buffer) {
        return new Vec3(buffer.readDouble(), buffer.readDouble(), buffer.readDouble());
    };

    public static void writeVec3(FriendlyByteBuf buffer, Vec3 vec) {
        buffer.writeDouble(vec.x).writeDouble(vec.y).writeDouble(vec.z);
    };

    public static BlockFace readBlockFace(FriendlyByteBuf buffer) {
        return BlockFace.of(buffer.readBlockPos(), buffer.readEnum(Direction.class));
    };

    public static void writeBlockFace(FriendlyByteBuf buffer, BlockFace face) {
        buffer.writeBlockPos(face.getPos()).writeEnum(face.getFace());
    };

    public static <T> void writeList(FriendlyByteBuf buffer, List<T> list, BiConsumer<FriendlyByteBuf, T> writer) {
        buffer.writeVarInt(list.size());
        list.forEach(e -> writer.accept(buffer, e));
    };

    public static <T> List<T> readList(FriendlyByteBuf buffer, Function<FriendlyByteBuf, T> reader) {
        int size = buffer.readVarInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.apply(buffer));
        };
        return list;
    };
};
