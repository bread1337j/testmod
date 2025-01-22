package net.bread.testmod.entity.projectile.Beacons;

import net.bread.testmod.entity.projectile.Ammunition.RailgunAmmo;
import net.bread.testmod.entity.projectile.TemplateBeacon;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;

public class RailgunBeacon extends TemplateBeacon {
    public RailgunBeacon(EntityType<? extends Snowball> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public RailgunBeacon(Level pLevel, LivingEntity pShooter) {
        super(EntityType.SNOWBALL, pShooter, pLevel);
    }

    public RailgunBeacon(Level pLevel, double pX, double pY, double pZ) {
        super(EntityType.SNOWBALL, pX, pY, pZ, pLevel);
    }

    @Override
    protected void doStuff() {
        //super.doStuff();
        RailgunAmmo ent = new RailgunAmmo(this.level(), this.getX(), 925+this.getY() ,this.getZ(), (int)this.getY()-75);
        ent.setOwner(this.getOwner());
        int offset1 = (int)(Math.random()*5)-2;
        int offset2 = (int)(Math.random()*5)-2;
        if(this.getX() > 0){
            ent.setPos(ent.getX()-75+offset1, ent.getY(), ent.getZ());
        }else{
            ent.setPos(ent.getX()+75+offset1, ent.getY(), ent.getZ());
        }
        if(this.getZ() > 0){
            ent.setPos(ent.getX(), ent.getY(), ent.getZ()-75+offset2);
        }else{
            ent.setPos(ent.getX(), ent.getY(), ent.getZ()+75+offset2);
        }

        this.level().addFreshEntity(ent);
        ent.setDeltaMovement(
                ((ent.getX()) > 0)? 2:-2,
                -100,
                ((ent.getZ()) > 0)? 2:-2
        );
        this.discard();

    }
}
