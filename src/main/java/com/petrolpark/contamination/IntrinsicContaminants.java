package com.petrolpark.contamination;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class IntrinsicContaminants {

    protected static final Map<Object, Set<Contaminant>> INTRINSIC_CONTAMINANTS = new HashMap<>();
    protected static final Map<Object, Set<Contaminant>> SHOWN_IF_ABSENT_CONTAMINANTS = new HashMap<>();

    public static void clear() {
        INTRINSIC_CONTAMINANTS.clear();
        SHOWN_IF_ABSENT_CONTAMINANTS.clear();  
    };

    protected static <OBJECT> Set<Contaminant> get(IContamination<OBJECT, ?> contamination) {
        return INTRINSIC_CONTAMINANTS.computeIfAbsent(contamination.getType(), obj -> withChildren(contamination.getContaminable().getIntrinsicContaminants(contamination.getType())));
    };

    protected static <OBJECT> Set<Contaminant> getShownIfAbsent(IContamination<OBJECT, ?> contamination) {
        return SHOWN_IF_ABSENT_CONTAMINANTS.computeIfAbsent(contamination.getType(), obj -> withChildren(contamination.getContaminable().getShownIfAbsentContaminants(contamination.getType())));
    };

    @SubscribeEvent
    public static final void onTagsUpdated(TagsUpdatedEvent event) {
        clear();
    };

    private static Set<Contaminant> withChildren(Set<Contaminant> contaminants) {
        return Stream.concat(contaminants.stream(), contaminants.stream().map(Contaminant::getChildren).flatMap(Set::stream)).collect(Collectors.toSet()); 
    };
    
};
