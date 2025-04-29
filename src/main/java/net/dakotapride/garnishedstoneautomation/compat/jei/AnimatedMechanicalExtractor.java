package net.dakotapride.garnishedstoneautomation.compat.jei;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create.foundation.gui.AllGuiTextures;
import net.dakotapride.garnishedstoneautomation.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import static net.createmod.catnip.lang.LangBuilder.resolveBuilders;

public class AnimatedMechanicalExtractor extends AnimatedKinetics {

    @Override
    public void draw(GuiGraphics graphics, int xOffset, int yOffset) {
        PoseStack matrixStack = graphics.pose();
        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, 0);
        AllGuiTextures.JEI_SHADOW.render(graphics, -16, 13);
        matrixStack.translate(-2, 18, 0);
        int scale = 22;

        blockElement(AllPartialModels.SHAFTLESS_COGWHEEL)
                .rotateBlock(22.5, getCurrentAngle() * 2, 0)
                .scale(scale)
                .render(graphics);

        blockElement(ModBlocks.MECHANICAL_EXTRACTOR.getDefaultState())
                .rotateBlock(22.5, 22.5, 0)
                .scale(scale)
                .render(graphics);

        graphics.drawString(Minecraft.getInstance().font, getHeatingRequirementComponent("text.garnishedstoneautomation.heating_required"),
                -40, 20, 0xE88300, false);

        matrixStack.popPose();
    }

    public static MutableComponent getHeatingRequirementComponent(String key, Object... args) {
        return Component.translatable(key, resolveBuilders(args));
    }

}
