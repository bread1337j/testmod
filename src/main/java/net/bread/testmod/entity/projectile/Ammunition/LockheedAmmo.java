package net.bread.testmod.entity.projectile.Ammunition;

import net.bread.testmod.entity.ModEntities;
import net.bread.testmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class LockheedAmmo extends ThrowableItemProjectile {
    private int offset = 0;
    public LockheedAmmo(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public LockheedAmmo(Level pLevel, LivingEntity pShooter) {
        super(ModEntities.LOCKHEED_PROJ.get(), pShooter, pLevel);
        this.setItem(new ItemStack(this.getDefaultItem()));
    }
    public LockheedAmmo(Level pLevel, double pX, double pY, double pZ, int pOffset) {
        super(ModEntities.LOCKHEED_PROJ.get(), pX, pY, pZ, pLevel);
        this.setInvulnerable(true);
        this.setNoGravity(true);
        this.offset = pOffset;
    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.LockheedAmmunition.get();
    }

    @Override
    protected void onHit(HitResult pResult) {
        if (!this.level().isClientSide){
            for(int i=0; i<3; i++) {
                this.level().explode(this, this.getX()+(Math.random()*3)-1, this.getY()+(Math.random()*3)-1, this.getZ()+(Math.random()*3)-1, 2, false, Level.ExplosionInteraction.NONE);
            }
            this.discard();
        }
    }
    @Override
    public boolean shouldRenderAtSqrDistance(double pDistance) {
        double d0 = this.getBoundingBox().getSize() * (double)4.0F;
        if (Double.isNaN(d0)) {
            d0 = (double)4.0F;
        }

        d0 *= (double)512.0F;
        return pDistance < d0 * d0;
    }

    @Override
    public void tick() {
        if (this.getY() < (300+this.offset)) {
                this.setDeltaMovement(this.getDeltaMovement().x(), -4, this.getDeltaMovement().z());
        }

        super.tick();
    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }

}
