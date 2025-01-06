package com.petrolpark.client.outline;

import java.util.Map;

import com.petrolpark.mixin.accessor.OutlineEntryAccessor;
import com.petrolpark.mixin.accessor.OutlinerAccessor;
import com.simibubi.create.foundation.outliner.Outline.OutlineParams;

import net.minecraft.world.phys.AABB;

public class Outliner extends com.simibubi.create.foundation.outliner.Outliner {
    
    public OutlineParams showBox(Object slot, AABB bb, int ttl) {
		createBoxIfMissing(slot, bb);
		CuboidOutline outline = getAndRefreshBox(slot, ttl);
		outline.set(bb);
		return outline.getParams();
	}

    private void createBoxIfMissing(Object slot, AABB bb) {
		if (!getOutlineMap().containsKey(slot) || !(getOutlineMap().get(slot).getOutline() instanceof CuboidOutline)) {
			CuboidOutline outline = new CuboidOutline().set(bb);
			getOutlineMap().put(slot, new OutlineEntry(outline));
		};
	};

    private CuboidOutline getAndRefreshBox(Object slot, int ttl) {
		OutlineEntry entry = getOutlineMap().get(slot);
		((OutlineEntryAccessor) entry).setTicksTillRemoval(ttl);
		return (CuboidOutline) entry.getOutline();
	}

    protected Map<Object, OutlineEntry> getOutlineMap() {
        return ((OutlinerAccessor) this).getOutlines();
    };
};
