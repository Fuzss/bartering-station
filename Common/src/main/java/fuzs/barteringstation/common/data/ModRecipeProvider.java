package fuzs.barteringstation.common.data;

import fuzs.barteringstation.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.recipes.AbstractRecipeProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;

public class ModRecipeProvider extends AbstractRecipeProvider {

    public ModRecipeProvider(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    @Override
    public void buildRecipes() {
        ShapedRecipeBuilder.shaped(this.items,
                        RecipeCategory.DECORATIONS,
                        ModRegistry.BARTERING_STATION_BLOCK.value())
                .define('#', ItemTags.PLANKS)
                .define('@', Items.GOLD_INGOT)
                .pattern("@@")
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(Items.GOLD_INGOT), this.has(Items.GOLD_INGOT))
                .save(this.output);
    }
}
