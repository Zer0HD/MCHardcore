package com.zer0.hardcore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.zer0.hardcore.entities.EntityOrc;
import com.zer0.hardcore.entities.Goblin;
import com.zer0.hardcore.entities.ObsidianKnight;
import com.zer0.hardcore.entities.VillagerSoldier;
import com.zer0.hardcore.gui.GuiHUD;
import com.zer0.hardcore.handlers.MCHardcoreKeyHandler;
import com.zer0.hardcore.models.ModelGoblinModel;
import com.zer0.hardcore.models.OrcModel;
import com.zer0.hardcore.player.ExtendedPlayerProperties;
import com.zer0.hardcore.rendering.GoblinRenderer;
import com.zer0.hardcore.rendering.ObsidianKnightRenderer;
import com.zer0.hardcore.rendering.OrcRenderer;
import com.zer0.hardcore.rendering.VillagerSoldierRenderer;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends ServerProxy {
	
	public static void levelUpdate(int level, int entityId)
	{
		World world = Minecraft.getMinecraft().theWorld;
		EntityPlayer player = (EntityPlayer)world.getEntityByID(entityId);
		ExtendedPlayerProperties properties = ExtendedPlayerProperties.fetchProperties(player);
		properties.setLevel(level);
		player.refreshDisplayName();
	}
	
	public void registerRenderer()
	{
		RenderingRegistry.registerEntityRenderingHandler(ObsidianKnight.class, new ObsidianKnightRenderer(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(VillagerSoldier.class, new VillagerSoldierRenderer());
		RenderingRegistry.registerEntityRenderingHandler(Goblin.class, new GoblinRenderer(new ModelGoblinModel(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityOrc.class, new OrcRenderer(new OrcModel(), 0.5F));
		
		MinecraftForge.EVENT_BUS.register(new GuiHUD(Minecraft.getMinecraft()));
	}
	
	public void registerKeybinds()
	{
		FMLCommonHandler.instance().bus().register(new MCHardcoreKeyHandler());
	}
	
	public int addArmour(String armour)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armour);
	}

}
