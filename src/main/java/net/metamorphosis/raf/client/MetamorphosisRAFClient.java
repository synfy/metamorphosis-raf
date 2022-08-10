package net.metamorphosis.raf.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.metamorphosis.raf.common.MetamorphosisRAF;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MetamorphosisRAFClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(MetamorphosisRAF.STILL_MILK,MetamorphosisRAF.FLOWING_MILK, new SimpleFluidRenderHandler(
                new Identifier("minecraft:block/water_still"),
                new Identifier("minecraft:block/water_flow"),
                0xFFFFFF
        ));

        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getSolid(), MetamorphosisRAF.STILL_MILK, MetamorphosisRAF.FLOWING_MILK);
    }
}
