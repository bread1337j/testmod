package net.bread.testmod.item;

import net.bread.testmod.entity.projectile.Beacons.RailgunBeacon;
import net.bread.testmod.testmod;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SnowballItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.locks.Lock;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, testmod.MOD_ID);


    public static final RegistryObject<Item> SEEL = ITEMS.register( "seel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HARP_SEEL = ITEMS.register("harp_seel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<seelgrenade> SEEL_GRENADE = ITEMS.register("seel_grenade",
            ()-> new seelgrenade(new Item.Properties()));
    public static final RegistryObject<seelgun> SEEL_GUN = ITEMS.register("seel_gun",
            ()-> new seelgun(new Item.Properties()));
    public static final RegistryObject<TestThrowable> AAAA = ITEMS.register("aaaa",
            ()-> new TestThrowable(new Item.Properties()));
    public static final RegistryObject<Gattling> Gattling = ITEMS.register("gattling_stratagem",
            () -> new Gattling(new Item.Properties()));
    public static final RegistryObject<Railgun> Railgun = ITEMS.register("railgun_stratagem",
            () -> new Railgun(new Item.Properties()));
    public static final RegistryObject<Napalm> Napalm = ITEMS.register("napalm_stratagem",
            () -> new Napalm(new Item.Properties()));
    public static final RegistryObject<Lockheed> Lockheed = ITEMS.register("lockheed_stratagem",
            () -> new Lockheed(new Item.Properties()));

    //this is just bloat TBH but idk how else to do this
    public static final RegistryObject<GattlingAmmunition> GattlingAmmunition = ITEMS.register("gattlingproj",
            () -> new GattlingAmmunition(new Item.Properties()));
    public static final RegistryObject<RailgunAmmunition> RailgunAmmunition = ITEMS.register("railgunproj",
            () -> new RailgunAmmunition(new Item.Properties()));
    public static final RegistryObject<NapalmAmmunition> NapalmAmmunition = ITEMS.register("napalmproj",
            () -> new NapalmAmmunition(new Item.Properties()));
    public static final RegistryObject<LockheedAmmunition> LockheedAmmunition = ITEMS.register("lockheedproj",
            () -> new LockheedAmmunition(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }


}
