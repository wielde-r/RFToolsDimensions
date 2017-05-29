package mcjty.rftoolsdim.dimensions.world.terrain.lost;

import mcjty.rftoolsdim.varia.GeometryTools;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DamageArea {

    private final long seed;
    private final List<Explosion> explosions = new ArrayList<>();
    private final AxisAlignedBB chunkBox;
    public final boolean damaged[];

    public DamageArea(long seed, int chunkX, int chunkZ) {
        this.seed = seed;
        chunkBox = new AxisAlignedBB(chunkX * 16, 0, chunkZ * 16, chunkX * 16 + 15, 256, chunkZ * 16 + 15);

        for (int cx = chunkX - 5; cx <= chunkX + 5; cx++) {
            for (int cz = chunkZ - 5; cz <= chunkZ + 5; cz++) {
                Explosion explosion = getExplosionAt(cx, cz);
                if (explosion != null) {
                    if (intersectsWith(explosion.getCenter(), explosion.getRadius())) {
                        explosions.add(explosion);
                    }
                }
            }
        }
        damaged = new boolean[16 * 16 * 256];
        for (int i = 0; i < damaged.length; i++) {
            damaged[i] = false;
        }
    }

    public IBlockState damageBlock(IBlockState b, IBlockState replacement, Random rand, float damage, int index, Style style) {
        if (rand.nextFloat() <= damage) {
            if (damage < .5f && style.canBeDamagedToIronBars(b)) {
                if (rand.nextFloat() < .8f) {
                    b = Blocks.IRON_BARS.getDefaultState();
                } else {
                    damaged[index] = true;
                    b = replacement;
                }
            } else {
                damaged[index] = true;
                b = replacement;
            }
        }
        return b;
    }

    private boolean intersectsWith(BlockPos center, int radius) {
        double dmin = GeometryTools.squaredDistanceBoxPoint(chunkBox, center);
        return dmin <= radius * radius;
    }

    private Explosion getExplosionAt(int chunkX, int chunkZ) {
        Random rand = new Random(seed + chunkZ * 295075153L + chunkX * 797003437L);
        rand.nextFloat();
        rand.nextFloat();
        if (rand.nextFloat() < .005f) {
            return new Explosion(17 + rand.nextInt(4 * 16), new BlockPos(chunkX * 16 + rand.nextInt(16), 70 + rand.nextInt(50), chunkZ * 16 + rand.nextInt(16)));
        }
        return null;
    }


    // Get a number indicating how much damage this point should get. 0 Means no damage
    public float getDamage(int x, int y, int z) {
        float damage = 0.0f;
        for (Explosion explosion : explosions) {
            double sq = explosion.getCenter().distanceSq(x, y, z);
            if (sq < explosion.getSqradius()) {
                double d = Math.sqrt(sq);
                damage += 3.0f * (explosion.getRadius() - d) / explosion.getRadius();
            }
        }
        return damage;
    }
}
