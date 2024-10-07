package com.gepbird.moreslabs

import net.fabricmc.api.ModInitializer
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier


object SlabBlocks : ModInitializer {
    val POLISHED_ANDESITE_VERTICAL_SLAB: VerticalSlabBlock = register(
        "polished_andesite_vertical_slab",
        VerticalSlabBlock(AbstractBlock.Settings.copy(Blocks.POLISHED_ANDESITE))
    )
    override fun onInitialize() {
        // Initialization logic here
    }

    private fun register(name: String, block: VerticalSlabBlock): VerticalSlabBlock {
        val id: Identifier = Identifier(MoreSlabs.MOD_ID, name)
        val blockItem = BlockItem(block, Item.Settings())
        Registry.register(Registries.ITEM, id, blockItem)
        return Registry.register(Registries.BLOCK, Identifier(MoreSlabs.MOD_ID, name), block)
    }

}
