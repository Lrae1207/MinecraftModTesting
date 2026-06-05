package com.mod.illicit.custom.menu;

import com.mod.illicit.Illicit;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.Blocks;

public class DistillerScreen extends AbstractContainerScreen<DistillerMenu> {

    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(Illicit.MODID,"textures/gui/background/distiller.png");

    public DistillerScreen(DistillerMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blit(
                RenderPipelines.GUI_TEXTURED,
                TEXTURE,
                this.leftPos,
                this.topPos,
                0,
                0,
                this.imageWidth,
                this.imageHeight,
                176,
                166
        );

        super.extractContents(graphics, mouseX, mouseY, partialTick);
    }
}