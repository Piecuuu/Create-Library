package com.petrolpark.advancement;

import com.petrolpark.RequiresCreate;
import com.simibubi.create.foundation.advancement.SimpleCreateTrigger;

import net.minecraft.resources.ResourceLocation;

@RequiresCreate
public class SimpleAdvancementTrigger extends SimpleCreateTrigger {

    private ResourceLocation trueID;

    public SimpleAdvancementTrigger(ResourceLocation id) {
        super(id.getPath());
        trueID = id;
    };

    @Override
    public ResourceLocation getId() {
        return trueID;
    };
    
};
