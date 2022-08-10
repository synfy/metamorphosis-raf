package net.metamorphosis.raf.common;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.metamorphosis.raf.command.RerollCommand;
import net.metamorphosis.raf.common.fluids.MilkFluid;
import net.metamorphosis.raf.common.items.MilkBottle;
import net.metamorphosis.raf.common.registry.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MetamorphosisRAF implements ModInitializer{
    public static final String MOD_ID = "metamorphosis-raf";

    public static FlowableFluid STILL_MILK;
    public static FlowableFluid FLOWING_MILK;
    public static Block MILK;
    public static Item MILK_BOTTLE = new MilkBottle(new FabricItemSettings().group(ItemGroup.FOOD).maxCount(16).food(new FoodComponent.Builder().hunger(0).saturationModifier(0).alwaysEdible().build()));

    @Override
    public void onInitialize() {
        ModScaleTypes.init();
        ModPowers.init();
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, registryAccess) -> {
            RerollCommand.register(dispatcher);
        });

        STILL_MILK = Registry.register(Registry.FLUID, new Identifier(MOD_ID, "milk"), new MilkFluid.Still());
        FLOWING_MILK = Registry.register(Registry.FLUID, new Identifier(MOD_ID,"flowing_milk"), new MilkFluid.Flowing());
        MILK = Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "milk"), new FluidBlock(STILL_MILK, FabricBlockSettings.copy(Blocks.WATER)){});
        Registry.register(Registry.ITEM, new Identifier(MOD_ID,"milk_bottle"), MILK_BOTTLE);
    }

}
