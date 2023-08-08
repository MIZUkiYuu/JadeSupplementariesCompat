package me.mizukiyuu.jadesupplementariescompat.mixin;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import snownee.jade.addon.core.DistanceProvider;
import snownee.jade.api.Accessor;
import snownee.jade.api.ITooltip;
import snownee.jade.api.Identifiers;
import snownee.jade.api.config.IPluginConfig;

import static snownee.jade.addon.core.DistanceProvider.*;

@Mixin(DistanceProvider.class)
public class DistanceProviderMixin {
    @Inject(
            method = "append(Lsnownee/jade/api/ITooltip;Lsnownee/jade/api/Accessor;Lnet/minecraft/core/BlockPos;Lsnownee/jade/api/config/IPluginConfig;)V",
            at = @At("HEAD"),
            cancellable = true)

    @Unique
    public void jadesupplementariescompat$append(ITooltip tooltip, Accessor<?> accessor, BlockPos pos, IPluginConfig config, CallbackInfo ci) {
        boolean distance = config.get(Identifiers.CORE_DISTANCE);
        String distanceVal = distance ? distance(accessor) : null;
        Component distanceMsg = distance ? Component.translatable("narration.jade.distance", distanceVal) : null;
        if (config.get(Identifiers.CORE_COORDINATES)) {
            if (config.get(Identifiers.CORE_REL_COORDINATES) && Screen.hasControlDown()) {
                jadesupplementariescompat$xyz(tooltip, pos.subtract(new BlockPos(accessor.getPlayer().getEyePosition())));
            } else {
                jadesupplementariescompat$xyz(tooltip, pos);
            }

            if (distance) {
                tooltip.append(0, tooltip.getElementHelper().text(Component.translatable("jade.distance1", new Object[]{distanceVal})).message(distanceMsg));
            }
        } else if (distance) {
            tooltip.append(0, tooltip.getElementHelper().text(Component.translatable("jade.distance2", distanceVal)).message(distanceMsg));
        }
        ci.cancel();
    }

    @Unique
    private void jadesupplementariescompat$xyz(ITooltip tooltip, Vec3i pos) {
        Component display = Component.translatable("jade.blockpos", display(pos.getX(), 15702682), display(pos.getY(), 10868391), display(pos.getZ(), 9489145));
        Component narrate = Component.translatable("narration.jade.blockpos", narrate(pos.getX()), narrate(pos.getY()), narrate(pos.getZ()));
        tooltip.append(0, tooltip.getElementHelper().text(display).message(narrate));
    }

}
