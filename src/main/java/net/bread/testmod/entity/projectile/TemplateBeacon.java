package net.bread.testmod.entity.projectile;

import net.bread.testmod.item.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class TemplateBeacon extends ThrowableItemProjectile {
    private int c = 0;
    private boolean landed = false;
    private float gravity = 0.03f;
    private double[] loc = new double[3];
    public TemplateBeacon(EntityType<? extends Snowball> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public TemplateBeacon(Level pLevel, LivingEntity pShooter) {
        super(EntityType.SNOWBALL, pShooter, pLevel);
    }

    public TemplateBeacon(Level pLevel, double pX, double pY, double pZ) {
        super(EntityType.SNOWBALL, pX, pY, pZ, pLevel);
    }

    public TemplateBeacon(EntityType<Snowball> snowball, LivingEntity pShooter, Level pLevel) {
        super(snowball, pShooter, pLevel);
    }

    public TemplateBeacon(EntityType<Snowball> snowball, double pX, double pY, double pZ, Level pLevel) {
        super(snowball, pX, pY, pZ, pLevel);
    }

    protected Item getDefaultItem() {
        return ModItems.AAAA.get();
    }

    protected void doStuff(){ //can be overriden
        this.level().explode(this, this.getX(), this.getY(), this.getZ(), 5, true, Level.ExplosionInteraction.BLOCK);
        this.discard();
    }

    @Override
    protected void onHit(HitResult pResult) {
        if (!this.level().isClientSide && !this.landed) {
            //this.setDeltaMovement(0, 0, 0);
            this.landed = true;
            System.out.println("Landed");
            this.gravity = 0.0f;
            this.setDeltaMovement(0,0 ,0);
            loc[0] = pResult.getLocation().x();
            loc[1] = pResult.getLocation().y();
            loc[2] = pResult.getLocation().z();
            this.setNoGravity(true);
            this.setInvulnerable(true);
        }
    }

    @Override
    public void move(MoverType pType, Vec3 pPos) {
        if(!landed){
            super.move(pType, pPos);
        }else{
            //do nothing lmao
        }
    }

    @Override
    public void tick() {
        //System.out.println(c);

        //vanilla tick
        if(!landed){
            super.tick();
        }



        //custom conditions here
        else{
            c++;
            System.out.println(c);
            //this.setPos(this.loc[0], this.loc[1], this.loc[2]);
            //this.setDeltaMovement(0, 0, 0);

            if(c>50){
                this.setPos(loc[0],loc[1],loc[2]);
                this.setDeltaMovement(0,0,0);//most optimized code ever award
                //do stuff ig
                doStuff();
                this.discard();
            }
        }
    }
    @Override
    protected float getGravity() {
        return this.gravity;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        //return super.hurt(pSource, pAmount);
        return false; //fuck you kb mechanics
    }
    @Override
    public boolean ignoreExplosion() {
        return true;
    }
}
