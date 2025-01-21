package net.bread.testmod.entity.projectile.Beacons;

import net.bread.testmod.entity.projectile.Ammunition.GattlingAmmo;
import net.bread.testmod.entity.projectile.Ammunition.LockheedAmmo;
import net.bread.testmod.entity.projectile.TemplateBeacon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class LockheedBeacon extends TemplateBeacon {
    private int c;
    private boolean landed;

    public LockheedBeacon(EntityType<? extends Snowball> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public LockheedBeacon(Level pLevel, LivingEntity pShooter) {
        super(EntityType.SNOWBALL, pShooter, pLevel);
    }

    public LockheedBeacon(Level pLevel, double pX, double pY, double pZ) {
        super(EntityType.SNOWBALL, pX, pY, pZ, pLevel);
    }

    @Override
    public void tick() {
        if(!landed){
            super.tick();
        }else {
            for(int i=0; i<2; i++){
                LockheedAmmo ent = new LockheedAmmo(this.level(), this.getX(), 1000+(this.getY()-60)*14, this.getZ(), (int) this.getY() / 3);
                ent.setOwner(this.getOwner());
                int offset1 = ((int) (Math.random() * 31) - 15) + ((int) (Math.random() * 31) - 15) + ((int) (Math.random() * 31) - 15);
                int offset2 = ((int) (Math.random() * 31) - 15) + ((int) (Math.random() * 31) - 15) + ((int) (Math.random() * 31) - 15);
                if (this.getX() > 0) {
                    ent.setPos(ent.getX() - 75 + offset1, ent.getY(), ent.getZ());
                } else {
                    ent.setPos(ent.getX() + 75 + offset1, ent.getY(), ent.getZ());
                }
                if (this.getZ() > 0) {
                    ent.setPos(ent.getX(), ent.getY(), ent.getZ() - 75 + offset2);
                } else {
                    ent.setPos(ent.getX(), ent.getY(), ent.getZ() + 75 + offset2);
                }

                this.level().addFreshEntity(ent);
                ent.setDeltaMovement(
                        ((ent.getX()) > 0) ? 2 : -2,
                        -100,
                        ((ent.getZ()) > 0) ? 2 : -2
                );
            }
            c++;
            if(c>800){
                this.discard();
            }
        }
    }
    @Override
    protected void onHit(HitResult pResult){
        this.landed = true;
    }
    @Override
    protected void doStuff() {
        //super.doStuff();
        this.setInvisible(true);
        if(c>800) {

            this.discard();
        }
    }
}
