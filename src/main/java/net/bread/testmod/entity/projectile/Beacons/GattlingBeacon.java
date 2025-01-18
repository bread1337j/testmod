package net.bread.testmod.entity.projectile.Beacons;

import net.bread.testmod.entity.projectile.Ammunition.GattlingAmmo;
import net.bread.testmod.entity.projectile.Ammunition.RailgunAmmo;
import net.bread.testmod.entity.projectile.TemplateBeacon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;

public class GattlingBeacon extends TemplateBeacon {
    private int c;
    private boolean landed;

    public GattlingBeacon(EntityType<? extends Snowball> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public GattlingBeacon(Level pLevel, LivingEntity pShooter) {
        super(EntityType.SNOWBALL, pShooter, pLevel);
    }

    public GattlingBeacon(Level pLevel, double pX, double pY, double pZ) {
        super(EntityType.SNOWBALL, pX, pY, pZ, pLevel);
    }

    @Override
    public void tick() {
        if(!landed){
            super.tick();
        }else {
            if(c%3==0){
                GattlingAmmo ent = new GattlingAmmo(this.level(), this.getX(), this.getY() + 1000, this.getZ(), (int) this.getY() / 3);
                int offset1 = ((int) (Math.random() * 21) - 10) + ((int) (Math.random() * 21) - 10) + ((int) (Math.random() * 21) - 10);
                int offset2 = ((int) (Math.random() * 21) - 10) + ((int) (Math.random() * 21) - 10) + ((int) (Math.random() * 21) - 10);
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
            System.out.println(c);
        }
    }
    @Override
    protected void onHit(HitResult pResult){
        this.landed = true;
    }
    @Override
    protected void doStuff() {
        //super.doStuff();

        if(c>200) {

            this.discard();
        }
    }
}
