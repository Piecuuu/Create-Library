package com.petrolpark.registrate;

import java.util.function.Supplier;

import com.petrolpark.PetrolparkRegistries;
import com.petrolpark.badge.Badge;
import com.petrolpark.data.loot.numberprovider.entity.EntityNumberProvider;
import com.petrolpark.data.loot.numberprovider.entity.LootEntityNumberProviderType;
import com.petrolpark.data.loot.numberprovider.itemstack.ItemStackNumberProvider;
import com.petrolpark.data.loot.numberprovider.itemstack.LootItemStackNumberProviderType;
import com.petrolpark.recipe.ingredient.modifier.IngredientModifier;
import com.petrolpark.recipe.ingredient.modifier.IngredientModifierType;
import com.petrolpark.recipe.ingredient.randomizer.IngredientRandomizer;
import com.petrolpark.recipe.ingredient.randomizer.IngredientRandomizerType;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraft.world.level.storage.loot.providers.number.LootNumberProviderType;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraftforge.eventbus.api.IEventBus;

public class PetrolparkRegistrate extends AbstractRegistrate<PetrolparkRegistrate> {

    public PetrolparkRegistrate(String modid) {
        super(modid);
    };

    @Override
	public PetrolparkRegistrate registerEventListeners(IEventBus bus) {
		return super.registerEventListeners(bus);
	};

    public BadgeBuilder<Badge, PetrolparkRegistrate> badge(String name) {
        return badge(name, Badge::new);  
    };

    public <T extends Badge> BadgeBuilder<T, PetrolparkRegistrate> badge(String name, NonNullSupplier<T> factory) {
		return (BadgeBuilder<T, PetrolparkRegistrate>) entry(name, c -> BadgeBuilder.create(this, this, name, c, factory));
	};

    public RegistryEntry<LootItemConditionType> lootConditionType(String name, net.minecraft.world.level.storage.loot.Serializer<? extends LootItemCondition> serializer) {
        return simple(name, Registries.LOOT_CONDITION_TYPE, () -> new LootItemConditionType(serializer));
    };

    public RegistryEntry<LootNumberProviderType> lootNumberProviderType(String name, net.minecraft.world.level.storage.loot.Serializer<? extends NumberProvider> serializer) {
        return simple(name, Registries.LOOT_NUMBER_PROVIDER_TYPE, () -> new LootNumberProviderType(serializer));
    };

    public RegistryEntry<LootItemStackNumberProviderType> lootItemStackNumberProviderType(String name, net.minecraft.world.level.storage.loot.Serializer<? extends ItemStackNumberProvider> serializer) {
        return simple(name, PetrolparkRegistries.Keys.LOOT_ITEM_STACK_NUMBER_PROVIDER_TYPE, () -> new LootItemStackNumberProviderType(serializer));
    };
    
    public RegistryEntry<LootItemStackNumberProviderType> lootItemStackNumberProviderType(String name, Supplier<? extends ItemStackNumberProvider> simpleFactory) {
        return simple(name, PetrolparkRegistries.Keys.LOOT_ITEM_STACK_NUMBER_PROVIDER_TYPE, () -> new LootItemStackNumberProviderType(simpleFactory));
    };

    public RegistryEntry<LootEntityNumberProviderType> lootEntityNumberProviderType(String name, net.minecraft.world.level.storage.loot.Serializer<? extends EntityNumberProvider> serializer) {
        return simple(name, PetrolparkRegistries.Keys.LOOT_ENTITY_NUMBER_PROVIDER_TYPE, () -> new LootEntityNumberProviderType(serializer));
    };
    
    public RegistryEntry<LootEntityNumberProviderType> lootEntityNumberProviderType(String name, Supplier<? extends EntityNumberProvider> simpleFactory) {
        return simple(name, PetrolparkRegistries.Keys.LOOT_ENTITY_NUMBER_PROVIDER_TYPE, () -> new LootEntityNumberProviderType(simpleFactory));
    };

    public RegistryEntry<IngredientRandomizerType> ingredientRandomizerType(String name, net.minecraft.world.level.storage.loot.Serializer<? extends IngredientRandomizer> serializer) {
        return simple(name, PetrolparkRegistries.Keys.INGREDIENT_RANDOMIZER_TYPE, () -> new IngredientRandomizerType(serializer));
    };

    public RegistryEntry<IngredientModifierType> ingredientModifierType(String name, net.minecraft.world.level.storage.loot.Serializer<? extends IngredientModifier> serializer) {
        return simple(name, PetrolparkRegistries.Keys.INGREDIENT_MODIFIER_TYPE, () -> new IngredientModifierType(serializer));
    };
    
};
