package net.metamorphosis.raf.common.registry;

import java.util.Map;

import net.metamorphosis.raf.common.MetamorphosisRAF;
import net.minecraft.util.Identifier;
import virtuoel.pehkui.api.*;

public class ModScaleTypes {
    private static final ScaleType[] CHANGE_SIZE_TYPES = {ScaleTypes.WIDTH, ScaleTypes.HEIGHT, ScaleTypes.DROPS, ScaleTypes.VISIBILITY};

    public static final ScaleModifier CHANGE_SIZE_MODIFIER = register(ScaleRegistries.SCALE_MODIFIERS, new TypedScaleModifier(() -> ModScaleTypes.CHANGE_SIZE_TYPE));

    public static final ScaleType CHANGE_SIZE_TYPE = register(ScaleRegistries.SCALE_TYPES, ScaleType.Builder.create().addDependentModifier(CHANGE_SIZE_MODIFIER).affectsDimensions().build());

    private static <T> T register(Map<Identifier, T> registry, T entry) {
        return ScaleRegistries.register(registry, new Identifier(MetamorphosisRAF.MOD_ID, "change_size"), entry);
    }

    public static void init() {
        for (ScaleType type : CHANGE_SIZE_TYPES) {
            type.getDefaultBaseValueModifiers().add(CHANGE_SIZE_MODIFIER);
        }
    }
}