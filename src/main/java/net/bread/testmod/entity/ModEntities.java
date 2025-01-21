package net.bread.testmod.entity;

import net.bread.testmod.entity.projectile.Ammunition.GattlingAmmo;
import net.bread.testmod.entity.projectile.Ammunition.LockheedAmmo;
import net.bread.testmod.entity.projectile.Ammunition.NapalmAmmo;
import net.bread.testmod.entity.projectile.Ammunition.RailgunAmmo;
import net.bread.testmod.testmod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, testmod.MOD_ID);

    public static final RegistryObject<EntityType<RailgunAmmo>> RAILGUN_PROJ =
            ENTITY_TYPES.register("railgunprojectile", () -> EntityType.Builder.<RailgunAmmo>of(RailgunAmmo::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("railgunprojectile"));
    public static final RegistryObject<EntityType<GattlingAmmo>> GATTLING_PROJ =
            ENTITY_TYPES.register("gattlingprojectile", () -> EntityType.Builder.<GattlingAmmo>of(GattlingAmmo::new, MobCategory.MISC)
                    .sized(0.35f, 0.35f).build("gattlingprojectile"));
    public static final RegistryObject<EntityType<NapalmAmmo>> NAPALM_PROJ =
            ENTITY_TYPES.register("napalmprojectile", () -> EntityType.Builder.<NapalmAmmo>of(NapalmAmmo::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("napalmprojectile"));
    public static final RegistryObject<EntityType<LockheedAmmo>> LOCKHEED_PROJ =
            ENTITY_TYPES.register("lockheedprojectile", () -> EntityType.Builder.<LockheedAmmo>of(LockheedAmmo::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("lockheedprojectile"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
