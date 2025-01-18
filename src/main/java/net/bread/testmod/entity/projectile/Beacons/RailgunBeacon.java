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
        RailgunAmmo ent = new RailgunAmmo(this.level(), this.getX()+Math.random(), this.getY()+1000,this.getZ()+Math.random());
        this.level().addFreshEntity(ent);
        ent.setDeltaMovement(0, -100, 0);

    }
}
