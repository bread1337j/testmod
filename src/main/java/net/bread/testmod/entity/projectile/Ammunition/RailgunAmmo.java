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

public class RailgunAmmo extends ThrowableItemProjectile {
    private boolean landed = false;
    private int hp = 10;
    private int offset = 0;
    public RailgunAmmo(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public RailgunAmmo(Level pLevel, LivingEntity pShooter) {
        super(ModEntities.RAILGUN_PROJ.get(), pShooter, pLevel);
    }

    public RailgunAmmo(Level pLevel, double pX, double pY, double pZ, int pOffset) {
        super(ModEntities.RAILGUN_PROJ.get(), pX, pY, pZ, pLevel);
        this.setNoGravity(true);
        this.landed = false;
        this.hp = 10;
        this.offset = pOffset;
    }
    @Override
    protected Item getDefaultItem() {
        return ModItems.RailgunAmmunition.get();
    }

    protected void onHit(HitResult pResult) {
        if (!this.level().isClientSide){
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), 5, true, Level.ExplosionInteraction.BLOCK);
            this.landed = true;
            this.hp--;
            if(this.hp <= 0){
                this.discard();
            }
        }
    }

    @Override
    public void tick() {

        if (this.getY() < (300+this.offset) && !landed) {
            this.setDeltaMovement(this.getDeltaMovement().x(), -4, this.getDeltaMovement().z());
        }
        if(this.landed){
            this.setDeltaMovement(this.getDeltaMovement().x(), -0.2*hp, this.getDeltaMovement().z());
        }

        super.tick();
    }

    @Override
    public boolean ignoreExplosion() {
        return true;
    }

}
