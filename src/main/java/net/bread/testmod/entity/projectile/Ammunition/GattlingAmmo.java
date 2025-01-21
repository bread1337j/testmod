package net.bread.testmod.entity.projectile.Ammunition;

import net.bread.testmod.entity.ModEntities;
import net.bread.testmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class GattlingAmmo extends ThrowableItemProjectile {
    private int offset = 0;
    public GattlingAmmo(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public GattlingAmmo(Level pLevel, LivingEntity pShooter) {
        super(ModEntities.GATTLING_PROJ.get(), pShooter, pLevel);
    }
    public GattlingAmmo(Level pLevel, double pX, double pY, double pZ, int offset) {
        super(ModEntities.GATTLING_PROJ.get(), pX, pY, pZ, pLevel);
        this.setInvulnerable(true);
        this.setNoGravity(true);
        this.offset = offset;
    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.GattlingAmmunition.get();
    }

    @Override
    protected void onHit(HitResult pResult) {
        if (!this.level().isClientSide){
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 3, true, Level.ExplosionInteraction.BLOCK);
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
        if (this.getY() < (300+offset)) {
            if (Math.abs(this.getDeltaMovement().y) > 25) {
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * (0.5), this.getDeltaMovement().z);
            } else if (this.getDeltaMovement().y > 5) {
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * (0.25), this.getDeltaMovement().z);
            } else {
                this.setDeltaMovement(this.getDeltaMovement().x(), -4, this.getDeltaMovement().z());
            }
        }

        super.tick();
    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }


}
