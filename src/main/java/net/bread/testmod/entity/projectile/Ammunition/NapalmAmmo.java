package net.bread.testmod.entity.projectile.Ammunition;

import net.bread.testmod.entity.ModEntities;
import net.bread.testmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;

import java.util.stream.Stream;

public class NapalmAmmo extends ThrowableItemProjectile {
    private int offset = 0;
    public NapalmAmmo(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public NapalmAmmo(Level pLevel, LivingEntity pShooter) {
        super(ModEntities.NAPALM_PROJ.get(), pShooter, pLevel);
    }

    public NapalmAmmo(Level pLevel, double pX, double pY, double pZ, int offset) {
        super(ModEntities.NAPALM_PROJ.get(), pX, pY, pZ, pLevel);
        this.setNoGravity(true);
        this.offset = offset;
    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.NapalmAmmunition.get();
    }

    protected void onHit(HitResult pResult) {
        if (!this.level().isClientSide){

            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 1, true, Level.ExplosionInteraction.NONE);

            this.discard();
        }
    }

    @Override
    public void tick() {
        if (this.getY() < (300+offset)) {
            if (Math.abs(this.getDeltaMovement().y) > 25) {
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * (0.5), this.getDeltaMovement().z);
            } else if (this.getDeltaMovement().y > 5) {
                this.setDeltaMovement(this.getDeltaMovement().x, this.getDeltaMovement().y * (0.25), this.getDeltaMovement().z);
            } else {
                this.setDeltaMovement(this.getDeltaMovement().x(), -2, this.getDeltaMovement().z());
            }
        }

        super.tick();
    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }

}
