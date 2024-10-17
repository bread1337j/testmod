package net.bread.testmod.entity.projectile;

import net.bread.testmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class grenade extends ThrowableItemProjectile {

    public grenade(EntityType<? extends Snowball> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public grenade(Level pLevel, LivingEntity pShooter) {
        super(EntityType.SNOWBALL, pShooter, pLevel);
    }

    public grenade(Level pLevel, double pX, double pY, double pZ) {
        super(EntityType.SNOWBALL, pX, pY, pZ, pLevel);
    }


    protected Item getDefaultItem() {
        return ModItems.SEEL_GRENADE.get();
    }

    @Override
    protected void onHit(HitResult pResult) {
        if (!this.level().isClientSide){
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 5, true, Level.ExplosionInteraction.BLOCK);
            this.discard();
        }
    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }
}
