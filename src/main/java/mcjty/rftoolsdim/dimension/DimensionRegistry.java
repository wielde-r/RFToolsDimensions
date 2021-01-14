package mcjty.rftoolsdim.dimension;

import mcjty.rftoolsdim.RFToolsDim;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;

public class DimensionRegistry {

    public static final ResourceLocation BIOMES_ID = new ResourceLocation(RFToolsDim.MODID, "biomes");

    public static final ResourceLocation VOID_ID = new ResourceLocation(RFToolsDim.MODID, "void");
    public static final RegistryKey<DimensionType> VOID_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, VOID_ID);

    public static final ResourceLocation WAVES_ID = new ResourceLocation(RFToolsDim.MODID, "waves");
    public static final RegistryKey<DimensionType> WAVES_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, WAVES_ID);

    public static final ResourceLocation FLAT_ID = new ResourceLocation(RFToolsDim.MODID, "flat");
    public static final RegistryKey<DimensionType> FLAT_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, FLAT_ID);

    public static final ResourceLocation NORMAL_ID = new ResourceLocation(RFToolsDim.MODID, "normal");
    public static final RegistryKey<DimensionType> NORMAL_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, NORMAL_ID);
}