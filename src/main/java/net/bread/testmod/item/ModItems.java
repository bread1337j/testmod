package net.bread.testmod.item;

import net.bread.testmod.testmod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, testmod.MOD_ID);


    public static final RegistryObject<Item> SEEL = ITEMS.register( "seel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HARP_SEEL = ITEMS.register("harp_seel",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
