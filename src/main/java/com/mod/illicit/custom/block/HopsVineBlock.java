package com.mod.illicit.custom.block;

import com.mod.illicit.general.ModdedBlocks;
import com.mod.illicit.general.ModdedItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

public class HopsVineBlock extends CropBlock {
    public static final int MAX_AGE = 5;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);
    private static VoxelShape[] shapes = Block.boxes(7, age -> Block.column(16.0, 0.0, 4 + age * 3));

    public HopsVineBlock(Properties properties) {
        shapes[5] = Block.cube(16);
        super(properties);
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModdedItems.HOPS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.DIRT) || state.is(Blocks.COARSE_DIRT)) && !(state.is(Blocks.FARMLAND));
    }

    @Override
    protected VoxelShape getShape(final BlockState state, final BlockGetter level, final BlockPos pos, final CollisionContext context) {
        return this.shapes[this.getAge(state)];
    }

    @Override
    protected  void  createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(final BlockPlaceContext context) {
        BlockState below = context.getLevel().getBlockState(context.getClickedPos().below());

        if (below.is(this)) {
            return null;
        }

        return super.getStateForPlacement(context);
    }

    @Override
    protected void randomTick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        if ((level.getRawBrightness(pos, 0) >= 9) && (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, pos, state, random.nextInt((int)(25.0F / getGrowthSpeed(this, level, pos)) + 1) == 0))) {
            int age = this.getAge(state);
            if (age < MAX_AGE - 1) {
                level.setBlock(pos, this.getStateForAge(age + 1), 2);
                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos, state);
            } else if (level.isEmptyBlock(pos.above())) {
                int height = 1;

                while (level.getBlockState(pos.below(height)).is(this)) {
                    height++;
                }

                if (height < 6) {
                    level.setBlockAndUpdate(pos.above(), this.defaultBlockState());
                    level.setBlockAndUpdate(pos, this.getStateForAge(5));
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, pos.above(), this.defaultBlockState());
                }
            }
        }
    }

    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        BlockState below = level.getBlockState(pos.below());
        return below.is(Blocks.GRASS_BLOCK) || below.is(Blocks.DIRT) || below.is(Blocks.COARSE_DIRT) || below.is(ModdedBlocks.HOPS_VINE.get());
    }

    @Override
    public void growCrops(final Level level, final BlockPos pos, final BlockState state) {
        int age = Math.min(this.getMaxAge(), this.getAge(state) + this.getBonemealAgeIncrease(level));
        age = Math.min((MAX_AGE - 1), age);
        level.setBlock(pos, this.getStateForAge(age), 2);
    }
}