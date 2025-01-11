package com.petrolpark.mixin.compat.create.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.petrolpark.tube.ClientTubePlacementHandler;
import com.simibubi.create.content.equipment.goggles.GoggleOverlayRenderer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;

@Mixin(GoggleOverlayRenderer.class)
public class GoggleOverlayRendererMixin {
    
    @Inject(
        method = "Lcom/simibubi/create/content/equipment/goggles/GoggleOverlayRenderer;renderOverlay(Lnet/minecraftforge/client/gui/overlay/ForgeGui;Lnet/minecraft/client/gui/GuiGraphics;FII)V",
        at = @At("HEAD"),
        remap = false,
        cancellable = true
    )
    private static void inRenderOverlay(ForgeGui gui, GuiGraphics graphics, float partialTicks, int width, int height, CallbackInfo ci) {
        if (ClientTubePlacementHandler.active()) ci.cancel();
    };
};
