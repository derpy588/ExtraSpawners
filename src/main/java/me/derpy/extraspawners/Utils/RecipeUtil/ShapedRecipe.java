package me.derpy.extraspawners.Utils.RecipeUtil;

import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ShapedRecipe {
    ItemStack output;
    List<ItemStack> topRow;
    List<ItemStack> middleRow;
    List<ItemStack> bottomRow;

    public ShapedRecipe(ItemStack output, List<ItemStack> topRow, List<ItemStack> middleRow, List<ItemStack> bottomRow) {
        this.output = output;
        this.topRow = topRow;
        this.middleRow = middleRow;
        this.bottomRow = bottomRow;
    }

}
