package net.bread.testmod.item;

import net.bread.testmod.entity.projectile.grenade;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class seelgun extends BowItem {
    public static final int MAX_DRAW_DURATION = 20;
    public static final int DEFAULT_RANGE = 15;
    private int usedur = 0;
    public seelgun(Item.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if (pLivingEntity instanceof Player pPlayer) {
            grenade proj = new grenade(pLevel, pLivingEntity);
            proj.setItem(new ItemStack(ModItems.SEEL_GRENADE.get()));
            //pLevel.addFreshEntity(Objects.requireNonNull(grenade.createEntity(pLevel, pLivingEntity, pStack)));
            //grenade.

            //proj.shoot(player.getX(), player.getY(), player.getZ(), 1, 1);
            //proj.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, (float)(1.0 / pRemainingUseDuration), (float)0.1*pRemainingUseDuration);
            usedur += 1;
            System.out.println(usedur);
            proj.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 0.01F*usedur, (100F / usedur) + 0.1F);

            pLevel.addFreshEntity(proj);
        }
    }


    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        usedur = 1;
    }
}
