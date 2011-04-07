package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

//========
// BEGIN AUTOFOREST
//========
import net.minecraft.src.modoptionsapi.*;
//========
// END AUTOFOREST
//========

public class BlockLeaves extends BlockLeavesBase
{

    protected BlockLeaves(int i, int j)
    {
        super(i, j, Material.leaves, false);
        baseIndexInPNG = j;
        setTickOnLoad(true);
    }

    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getBlockMetadata(i, j, k);
        if((l & 1) == 1)
        {
            return ColorizerFoliage.getFoliageColorPine();
        }
        if((l & 2) == 2)
        {
            return ColorizerFoliage.getFoliageColorBirch();
        } else
        {
            iblockaccess.getWorldChunkManager().func_4069_a(i, k, 1, 1);
            double d = iblockaccess.getWorldChunkManager().temperature[0];
            double d1 = iblockaccess.getWorldChunkManager().humidity[0];
            return ColorizerFoliage.getFoliageColor(d, d1);
        }
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        int l = 1;
        int i1 = l + 1;
        if(world.checkChunksExist(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1))
        {
            for(int j1 = -l; j1 <= l; j1++)
            {
                for(int k1 = -l; k1 <= l; k1++)
                {
                    for(int l1 = -l; l1 <= l; l1++)
                    {
                        int i2 = world.getBlockId(i + j1, j + k1, k + l1);
                        if(i2 == Block.leaves.blockID)
                        {
                            int j2 = world.getBlockMetadata(i + j1, j + k1, k + l1);
                            world.setBlockMetadata(i + j1, j + k1, k + l1, j2 | 4);
                        }
                    }

                }

            }

        }
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(world.multiplayerWorld)
        {
            return;
        }
        int l = world.getBlockMetadata(i, j, k);
        if((l & 4) != 0)
        {
            byte byte0 = 4;
            int i1 = byte0 + 1;
            byte byte1 = 32;
            int j1 = byte1 * byte1;
            int k1 = byte1 / 2;
            if(adjacentTreeBlocks == null)
            {
                adjacentTreeBlocks = new int[byte1 * byte1 * byte1];
            }
            if(world.checkChunksExist(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1))
            {
                for(int l1 = -byte0; l1 <= byte0; l1++)
                {
                    for(int k2 = -byte0; k2 <= byte0; k2++)
                    {
                        for(int i3 = -byte0; i3 <= byte0; i3++)
                        {
                            int k3 = world.getBlockId(i + l1, j + k2, k + i3);
                            if(k3 == Block.wood.blockID)
                            {
                                adjacentTreeBlocks[(l1 + k1) * j1 + (k2 + k1) * byte1 + (i3 + k1)] = 0;
                                continue;
                            }
                            if(k3 == Block.leaves.blockID)
                            {
                                adjacentTreeBlocks[(l1 + k1) * j1 + (k2 + k1) * byte1 + (i3 + k1)] = -2;
                            } else
                            {
                                adjacentTreeBlocks[(l1 + k1) * j1 + (k2 + k1) * byte1 + (i3 + k1)] = -1;
                            }
                        }

                    }

                }

                for(int i2 = 1; i2 <= 4; i2++)
                {
                    for(int l2 = -byte0; l2 <= byte0; l2++)
                    {
                        for(int j3 = -byte0; j3 <= byte0; j3++)
                        {
                            for(int l3 = -byte0; l3 <= byte0; l3++)
                            {
                                if(adjacentTreeBlocks[(l2 + k1) * j1 + (j3 + k1) * byte1 + (l3 + k1)] != i2 - 1)
                                {
                                    continue;
                                }
                                if(adjacentTreeBlocks[((l2 + k1) - 1) * j1 + (j3 + k1) * byte1 + (l3 + k1)] == -2)
                                {
                                    adjacentTreeBlocks[((l2 + k1) - 1) * j1 + (j3 + k1) * byte1 + (l3 + k1)] = i2;
                                }
                                if(adjacentTreeBlocks[(l2 + k1 + 1) * j1 + (j3 + k1) * byte1 + (l3 + k1)] == -2)
                                {
                                    adjacentTreeBlocks[(l2 + k1 + 1) * j1 + (j3 + k1) * byte1 + (l3 + k1)] = i2;
                                }
                                if(adjacentTreeBlocks[(l2 + k1) * j1 + ((j3 + k1) - 1) * byte1 + (l3 + k1)] == -2)
                                {
                                    adjacentTreeBlocks[(l2 + k1) * j1 + ((j3 + k1) - 1) * byte1 + (l3 + k1)] = i2;
                                }
                                if(adjacentTreeBlocks[(l2 + k1) * j1 + (j3 + k1 + 1) * byte1 + (l3 + k1)] == -2)
                                {
                                    adjacentTreeBlocks[(l2 + k1) * j1 + (j3 + k1 + 1) * byte1 + (l3 + k1)] = i2;
                                }
                                if(adjacentTreeBlocks[(l2 + k1) * j1 + (j3 + k1) * byte1 + ((l3 + k1) - 1)] == -2)
                                {
                                    adjacentTreeBlocks[(l2 + k1) * j1 + (j3 + k1) * byte1 + ((l3 + k1) - 1)] = i2;
                                }
                                if(adjacentTreeBlocks[(l2 + k1) * j1 + (j3 + k1) * byte1 + (l3 + k1 + 1)] == -2)
                                {
                                    adjacentTreeBlocks[(l2 + k1) * j1 + (j3 + k1) * byte1 + (l3 + k1 + 1)] = i2;
                                }
                            }

                        }

                    }

                }

            }
            int j2 = adjacentTreeBlocks[k1 * j1 + k1 * byte1 + k1];
            if(j2 >= 0)
            {
                world.setBlockMetadataWithNotify(i, j, k, l & -5);
            } else
            {
                removeLeaves(world, i, j, k);
            }
			
        }		
		
		//========
		// BEGIN AUTOFOREST
		//========
		if(!world.multiplayerWorld) {
			attemptGrowth(world, i, j, k);
		}
		
	}
	
	/**
	* Attempt growth for leaves
	*/
	private void attemptGrowth(World world, int i, int j, int k) {
		// Mod options for autoforest
		ModOptions mo = ModOptionsAPI.getModOptions("AutoForest");
		// Attempt to grow a part of the forest (Sapling, apple)
		ModBooleanOption o = (ModBooleanOption) mo.getOption("ForestGrowth");
		boolean appleGrowth = ((ModBooleanOption) mo.getSubOption(mod_AutoForest.TREE_MENU_NAME)
								.getOption("ApplesGrow")).getValue();
		boolean cocoaGrowth = ((ModBooleanOption) mo.getSubOption(mod_AutoForest.TREE_MENU_NAME)
								.getOption("CocoaGrows")).getValue();
		// Sapling growth frequency
		double freq = getSaplingFreq(world, i, j, k);
		// Apple growth frequency
		double appleFreq = getAppleFreq(world, i, j, k);
		// Cocoa frequency
		double cocoaFreq = getCocoaFreq(world, i, j, k);
		if((o.getValue()) && (growth(freq))) {
			// Try to emit a sapling
			if(world.getBlockId(i, j + 1, k) == 0) {
				emitItem(world, i, j + 1, k, new ItemStack(Block.sapling, 1,
										world.getBlockMetadata(i, j, k) % 4));
			} 
		} else if((appleGrowth) && (growth(appleFreq))) {
			if((world.getBlockId(i, j - 1, k) == 0) && (appleCanGrow(world,i,j,k))) {
				emitItem(world, i, j - 1, k, new ItemStack(Item.appleRed));
			}
		} else if((cocoaGrowth) && (growth(cocoaFreq))) {
			String biomes[] = {"Rainforest"};
			if((world.getBlockId(i, j - 1, k) == 0) && (canGrow(world,i,j,k, biomes))) {
				System.out.println("COCOA GROWTH IN RAINFOREST ("+i+","+j+","+k+")");
				emitItem(world, i, j - 1, k, new ItemStack(Item.dyePowder, 1, 3));
			}
		}

    }
	
	/**
	* Emit a specific item
	*
	* @param item	Item to emit
	*/
	private void emitItem(World world, int i, int j, int k, ItemStack item) {
		EntityItem entityitem = new EntityItem(world, i, j, k, item);
		world.entityJoinedWorld(entityitem);
	}
	
	/** 
	* Attempts to emit an item at this location
	* Will emit either sapling, apple or cocoa
	*/
	public void grow(World world, int i, int j, int k) {
		Random rand = new Random();			
		String biomes[] = {"Rainforest"};
		int randInt = rand.nextInt(100);
		if((canGrow(world,i,j,k, biomes)) && (randInt < 10)) {
			emitItem(world, i, j - 1, k, new ItemStack(Item.dyePowder, 1, 3));
		} else if((appleCanGrow(world,i,j,k)) && (randInt < 25)) {
			emitItem(world, i, j - 1, k, new ItemStack(Item.appleRed));
		} else {
			emitItem(world, i, j + 1, k, new ItemStack(Block.sapling, 1,
										world.getBlockMetadata(i, j, k) % 4));
		}
	}
	
	/**
	* Check if an item can grow in this biome
	*
	* @param	biomes		List of biome names
	* @return	true if can
	*/
	private boolean canGrow(World world, int i, int j, int k, String[] biomes) {
		String curBiome = world.getBiomeName(i,k);
		for(String biome : biomes) {
			if(curBiome.equals(biome)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	* Check if an apple can grow here
	* 
	* @param	world	MC World
	* @param	i		X Coord
	* @param	j		Y Coord
	* @param	k		Z Coord
	* @return	True if it can
	*/
	private boolean appleCanGrow(World world, int i, int j, int k) {
		// Get Biome info
		String biome = world.getBiomeName(i,k);
		// Apples can grow in the named biomes
		return (biome.equals("Forest") || biome.equals("Swampland") || biome.equals("Rainforest")
				|| biome.equals("Seasonal Forest") || biome.equals("Shrubland"));
	}
	/**
	* Check if there has been growth in these leaves
	*
	* @param	freq		Frequency to spawn
	* @return	True if there is an item grown
	*/
	private boolean growth(double freq) {
		// average time, in mins, between sapling pawning.
		// Remember that each tree has between 13 and 30 leaves facing "up". 
		// make number ~15 times larger if you want to do
		// "average saplings per tree" rather than "leaf block"
		// Since tickRate() is 10, we only use 6 as the mult.
		double tmp = Math.random();
		//System.out.println("Rand: " + tmp + " against " + freq);
		/*if(tmp < 0.001D) {
			System.out.println(tmp);
		}*/
		return (tmp < freq);
	}
	
	private double getSaplingFreq(World world, int i, int j, int k) {
		double freq = 0.001;
		ModMultiOption o = (ModMultiOption) ModOptionsAPI
							.getModOptions(mod_AutoForest.MENU_NAME)
							.getSubOption(mod_AutoForest.SAPLING_MENU_NAME)
							.getOption("GrowthRate");
		String v = o.getValue();
		// 20 leaves per tree
		// 3 ticks per minute
		// 180 ticks per hour
		// 4320 ticks per day
		// Super slow wants one tree to reproduce every human day
		if(v.equals("SUPERSLOW")) {
			freq = 0.0000231481481D; 
		// Slow is a new tree per tree every 6 hours 
		} else if(v.equals("SLOW")) {
			freq = 0.0000925925926D; 
		// A tree every 3 hours
		} else if(v.equals("AVERAGE")) {
			freq = 0.000185185185D; 
		//
		} else if(v.equals("FAST")) {
			freq = 0.000555555556D;
		} else if(v.equals("SUPERFAST")) {
			freq = 0.0037037037D; // Each leaf @15 mins. Each tree ~1 min: EXTREMELY FAST
		} else if(v.equals("INSANE")) {
			freq = 9.5;
		}
		
		int tmp = mod_AutoForest.getBiomeModifier(world.getBiomeName(i,k), BiomeMod.SAPLING_SPAWN);
		
		freq = mod_AutoForest.applyBiomeModifier(freq, BiomeMod.SAPLING_SPAWN, world,i,k);
		
		//System.out.println("Modifier is " + tmp + " in biome " + world.getBiomeName(i,k) + " freq now " + freq);
		return freq;
	}
	
	/**
	* Returns apple freq
	*
	* @return	apple freq
	*/
	private double getAppleFreq(World world, int i, int j, int k) {
		ModMappedMultiOption o = (ModMappedMultiOption) ModOptionsAPI
							.getModOptions(mod_AutoForest.MENU_NAME)
							.getSubOption(mod_AutoForest.TREE_MENU_NAME)
							.getOption("AppleGrowthRate");
		double freq = (double) 1 / o.getValue();
		
		freq = mod_AutoForest.applyBiomeModifier(freq, BiomeMod.SAPLING_SPAWN, world,i,k);
		
		return freq;
	}

	/**
	* Returns cocoa growth rate
	*
	* @return	cocoa freq
	*/
	private double getCocoaFreq(World world, int i, int j, int k) {
		ModMappedMultiOption o = (ModMappedMultiOption) ModOptionsAPI
							.getModOptions(mod_AutoForest.MENU_NAME)
							.getSubOption(mod_AutoForest.TREE_MENU_NAME)
							.getOption("CocoaGrowthRate");
		return (double) 1 / o.getValue();
	}
	
	/**
	* Removes leaves from a tree when it's dying
	* Now integrated with NatureOverhaul
	* Removes leaves when forest growth is on at the rate
	* known to autoforest
	*/
    private void removeLeaves(World world, int i, int j, int k) {
		if(!world.multiplayerWorld) {
			boolean forestGrowth = ((ModBooleanOption) ModOptionsAPI
								.getModOptions(mod_AutoForest.MENU_NAME)
								.getOption("ForestGrowth")).getValue();
			boolean appleGrowth = ((ModBooleanOption) ModOptionsAPI
									.getModOptions(mod_AutoForest.MENU_NAME)
									.getSubOption(mod_AutoForest.TREE_MENU_NAME)
									.getOption("ApplesGrow")).getValue();
			if(forestGrowth) {
				// Use increased growth rate here
				if(growth(getSaplingFreq(world, i, j, k) * 120)) {
					emitItem(world, i, j, k, new ItemStack(Block.sapling, 1, 
											 world.getBlockMetadata(i, j, k) % 4));
				}
			} else {
				dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k));
			}
			
			// Apples grow independently
			if(appleGrowth) {
				if(growth(getAppleFreq(world, i, j, k))) {
					emitItem(world, i, j, k, new ItemStack(Item.appleRed));
				}
			}
		} else {
			dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k));
		}
        world.setBlockWithNotify(i, j, k, 0);
    }
	
	//========
	// END AUTOFOREST
	//========

    public int quantityDropped(Random random)
    {
        return random.nextInt(16) != 0 ? 0 : 1;
    }

    public int idDropped(int i, Random random)
    {
        return Block.sapling.blockID;
    }

    public boolean isOpaqueCube()
    {
        return !graphicsLevel;
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        if((j & 3) == 1)
        {
            return blockIndexInTexture + 80;
        } else
        {
            return blockIndexInTexture;
        }
    }

    public void setGraphicsLevel(boolean flag)
    {
        graphicsLevel = flag;
        blockIndexInTexture = baseIndexInPNG + (flag ? 0 : 1);
    }

    public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
        super.onEntityWalking(world, i, j, k, entity);
    }

    private int baseIndexInPNG;
    int adjacentTreeBlocks[];
}
