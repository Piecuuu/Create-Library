package com.petrolpark.tube;

import java.util.List;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TubeStructuralBlockEntity extends SmartBlockEntity {

    protected BlockPos controllerPos;

    public TubeStructuralBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    };

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {};

    @Override
    public void destroy() {
        if (controllerPos != null) TubeBehaviour.get(getLevel(), controllerPos).ifPresent(TubeBehaviour::disconnect);
        super.destroy();
    };

    public void setController(BlockPos controllerPos) {
        this.controllerPos = controllerPos;
    };

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        if (tag.contains("ControllerPos", Tag.TAG_COMPOUND)) controllerPos = NbtUtils.readBlockPos(tag.getCompound("ControllerPos")).offset(getBlockPos());
        super.read(tag, clientPacket);
    };

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        if (controllerPos != null) tag.put("ControllerPos", NbtUtils.writeBlockPos(controllerPos.subtract(getBlockPos())));
        super.write(tag, clientPacket);
    };
    
};
