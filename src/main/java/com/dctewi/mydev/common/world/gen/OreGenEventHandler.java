package com.dctewi.mydev.common.world.gen;

import com.dctewi.mydev.common.block.MyDevBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OreGenEventHandler
{
    private static WorldGenerator worldGenMinable = new WorldGenMinable(MyDevBlocks.blockTewiOre.getDefaultState(), 16);

    public static void init()
    {
        MinecraftForge.ORE_GEN_BUS.register(OreGenEventHandler.class);
    }

    @SubscribeEvent
    public static void onGenerateMinable(OreGenEvent.GenerateMinable event)
    {
        if (event.getType() != OreGenEvent.GenerateMinable.EventType.IRON)
            return;

        if (!TerrainGen.generateOre(event.getWorld(), event.getRand(),
                worldGenMinable, event.getPos(),
                OreGenEvent.GenerateMinable.EventType.CUSTOM))
            return;

        for (int i = 0; i < 4; i++)
        {
            int posX = event.getPos().getX() + event.getRand().nextInt(16);
            int posY = 16 + event.getRand().nextInt(16);
            int posZ = event.getPos().getZ() + event.getRand().nextInt(16);

            BlockPos blockPos = new BlockPos(posX, posY, posZ);
            worldGenMinable.generate(event.getWorld(), event.getRand(), blockPos);
        }
    }
}
