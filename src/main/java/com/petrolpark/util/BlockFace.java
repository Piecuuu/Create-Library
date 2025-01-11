package com.petrolpark.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;

public class BlockFace extends Pair<BlockPos, Direction> {

    public static BlockFace of(BlockPos pos, Direction face) {
        return new BlockFace(pos, face);
    };

    public BlockFace(BlockPos first, Direction second) {
        super(first, second);
    };

    public BlockPos getPos() {
		return getFirst();
	};

	public Direction getFace() {
		return getSecond();
	};

    public BlockPos getConnectedPos() {
        return getPos().relative(getFace());
    };

    public BlockFace getOpposite() {
        return new BlockFace(getConnectedPos(), getFace().getOpposite());
    };

    public Vec3 getCenter() {
        return Vec3.atCenterOf(getPos()).add(new Vec3(getFace().step()).scale(0.5f));
    };
    
};
