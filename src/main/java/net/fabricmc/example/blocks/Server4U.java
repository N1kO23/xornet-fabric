package net.fabricmc.example.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class Server4U extends ServerBlock {
    public Server4U(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        float reduceVoxelSize = (1f/16f);
        return VoxelShapes.cuboid(reduceVoxelSize, 0f, reduceVoxelSize, 1f - reduceVoxelSize, 0.5f, 1f - reduceVoxelSize);
    }
}
