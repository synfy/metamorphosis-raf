package net.metamorphosis.raf.command;

import com.mojang.brigadier.CommandDispatcher;

import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.origin.OriginRegistry;
import io.github.apace100.origins.registry.ModComponents;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.minecraft.server.command.CommandManager.literal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static net.minecraft.server.command.CommandManager.argument;

public class RerollCommand{

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("reroll").requires(cs -> cs.hasPermissionLevel(2))
                        .then(argument("targets", EntityArgumentType.players())
                                .executes(context -> {
                                    Collection<ServerPlayerEntity> targets = EntityArgumentType.getPlayers(context, "targets");
                                    for(ServerPlayerEntity target : targets){
                                        giveRandomOrigin(target);
                                        System.out.println("New Origin assigned!");
                                    }
                                    return 1;
                                })
                        )
        );
    }

    private static void giveRandomOrigin(PlayerEntity player){
        Identifier layerID = Identifier.tryParse("origins:origin");
        //get origin layer
        OriginLayer layer = OriginLayers.getLayer(layerID);
        //from that layer, get the list of origins
        List<Identifier> origins = layer.getOrigins();
        List<Origin> choosableOrigins = new ArrayList<>();
        //add choosable origins to new list
        for(Identifier id : origins){
            if(OriginRegistry.get(id).isChoosable()){
                choosableOrigins.add(OriginRegistry.get(id));
            }
        }
        //get the length of the choosable origin list
        //get random number in that range
        Random random = new Random();
        int randIndex = random.nextInt(choosableOrigins.size());
        //grab the origin at that index
        Origin origin = choosableOrigins.get(randIndex);
        //give the player the origin
        OriginComponent component = ModComponents.ORIGIN.get(player);
        component.setOrigin(layer, origin);
        OriginComponent.sync(player);
        boolean hadOriginBefore = component.hadOriginBefore();
        OriginComponent.partialOnChosen(player, hadOriginBefore, origin);
    }


}
