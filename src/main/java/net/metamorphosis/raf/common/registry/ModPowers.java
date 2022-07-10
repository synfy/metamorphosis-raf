package net.metamorphosis.raf.common.registry;

import java.util.Collections;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import virtuoel.pehkui.api.ScaleRegistries;

import net.metamorphosis.raf.common.power.*;
import net.metamorphosis.raf.common.MetamorphosisRAF;

public class ModPowers {

    public static final PowerFactory<Power> CHANGE_SIZE = new PowerFactory<>(new Identifier(MetamorphosisRAF.MOD_ID, "change_size"), new SerializableData().add("scale_types", SerializableDataTypes.IDENTIFIERS, Collections.singletonList(ScaleRegistries.getId(ScaleRegistries.SCALE_TYPES, ModScaleTypes.CHANGE_SIZE_TYPE))).add("scale", SerializableDataTypes.FLOAT), data -> (type, entity) -> new ChangeSize(type, entity, data.get("scale_types"), data.getFloat("scale"))).allowCondition();

    public static void init() {
        Registry.register(ApoliRegistries.POWER_FACTORY, CHANGE_SIZE.getSerializerId(), CHANGE_SIZE);
    }
}