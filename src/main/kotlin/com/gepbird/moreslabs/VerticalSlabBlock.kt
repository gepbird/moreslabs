package com.gepbird.moreslabs

import com.mojang.serialization.MapCodec
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.block.ShapeContext
import net.minecraft.item.ItemPlacementContext
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.state.property.Properties

class VerticalSlabBlock(settings: Settings) : HorizontalFacingBlock(settings) {
    companion object {
        // The codec is required since 1.20.5 however not actually used in Minecraft yet.
        val CODEC: MapCodec<VerticalSlabBlock> = Block.createCodec(::VerticalSlabBlock)
    }
    init {
        defaultState = stateManager.defaultState.with(Properties.HORIZONTAL_FACING, Direction.NORTH)
    }
    override fun getCodec(): MapCodec<out VerticalSlabBlock> {
        return CODEC
    }
    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(Properties.HORIZONTAL_FACING)
    }
    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, ctx: ShapeContext): VoxelShape {
        val dir = state.get(FACING)
        return when (dir) {
            Direction.NORTH -> VoxelShapes.cuboid(0.0, 0.0, 0.5, 1.0, 1.0, 1.0)
            Direction.SOUTH -> VoxelShapes.cuboid(0.0, 0.0, 0.0, 1.0, 1.0, 0.5)
            Direction.EAST -> VoxelShapes.cuboid(0.0, 0.0, 0.0, 0.5, 1.0, 1.0)
            Direction.WEST -> VoxelShapes.cuboid(0.5, 0.0, 0.0, 1.0, 1.0, 1.0)
            else -> VoxelShapes.fullCube()
        }
    }
    override fun getPlacementState(ctx: ItemPlacementContext): BlockState {
        return super.getPlacementState(ctx)!!.with(Properties.HORIZONTAL_FACING, ctx.horizontalPlayerFacing.opposite)
    }
}
