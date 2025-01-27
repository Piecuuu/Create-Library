package com.petrolpark.data.loot.numberprovider;

import java.util.stream.DoubleStream;

import com.petrolpark.data.loot.PetrolparkLootNumberProviderTypes;

import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

public class MinNumberProvider extends FunctionNumberProvider {

    public MinNumberProvider(NumberProvider[] children) {
        super(children);
    };

    @Override
    public float apply(LootContext lootContext, DoubleStream childResults) {
        return (float)childResults.min().orElse(0f);
    };

    @Override
    public LootNumberProviderType getType() {
        return PetrolparkLootNumberProviderTypes.MIN.get();
    };
    
};
