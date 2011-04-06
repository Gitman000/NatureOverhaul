package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class mod_Snow extends BaseMod
{

	public static int SnowMode = PReader.PropInt("/mods/CJ/SnowMod.properties", "Snow Mode", "1", "# 0 Disables snow\n# 1 Enables snow in cold biomes only (or everywhere if playing on SMP)\n# 2 Enables snow everywhere.\n");
	public static int SnowRate = PReader.PropInt("/mods/CJ/SnowMod.properties", "Snow Frequency", "5", "# 1 is fastest, 10 is slowest. Default is 5\n");
	public static int SnowPerTick = PReader.PropInt("/mods/CJ/SnowMod.properties", "Number of Blocks", "8", "# Number of snow blocks that should be placed in the chunk at the same time (Max = 30 - Default = 8)\n");
	//public static boolean Mayhem = PReader.PropBoolean("/SnowMod.properties", "Mayhem Mode", "False", "# True = enabled, False = disabled\n");
	//public static int SnowLayers = PReader.PropInt("/SnowMod.properties", "Snow Layers", "5", "# Number of snow blocks that should be placed on top of each other in mayhem mode (Max = 15, Default = 5)\n");
	
	public static double Offset;
	
    public mod_Snow()
    {
    /*	if(Mayhem)
    	{
    		Offset = -1.0D;
    		SnowMode = 2;
    		SnowRate = 1;
    		SnowPerTick = 50;
    	} else*/
    	{
    		if (SnowMode < 1) Offset = 1.0D;
    		else if (SnowMode > 1) Offset = -1.0D;
    		else Offset = 0.0D;
    	
    		if(SnowRate < 1) SnowRate = 1 ;
        	else if(SnowRate > 10) SnowRate = 10;
    	
    		if(SnowPerTick < 1) SnowPerTick = 1 ;
        	else if(SnowPerTick > 15) SnowPerTick = 15;
    	
    		//if(SnowLayers < 1) SnowLayers = 1 ;
        	//else if(SnowLayers > 15) SnowLayers = 15;
    	}
    	System.out.printf("SnowMod v0.3 by CJ - Loaded.\n");
		System.out.printf("Snow Mode = %d\n", SnowMode);
		System.out.printf("Snow Proba = %d\n", SnowRate);
		System.out.printf("Snow Per Tick = %d\n", SnowPerTick);
		//System.out.printf("Mayhem Mode = %b\n", Mayhem);
		//System.out.printf("Snow Layers = %d\n\n", SnowLayers);
    }

    public String Version()
    {
        return "1.3_01 MLv5";
    }
}
