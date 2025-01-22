package net.bread.testmod.item;

import net.bread.testmod.breadmod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, breadmod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SEEL_TAB = CREATIVE_MODE_TABS.register("seel_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HARP_SEEL.get()))
                    .title(Component.translatable("creativetab.seel_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.SEEL.get());
                        pOutput.accept(ModItems.HARP_SEEL.get());
                        pOutput.accept(ModItems.SEEL_GRENADE.get());
                        pOutput.accept(ModItems.Railgun.get());
                        pOutput.accept(ModItems.Gattling.get());
                        pOutput.accept(ModItems.Napalm.get());
                        pOutput.accept(ModItems.Lockheed.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
