package mcjty.rftoolsdim.setup;


import mcjty.lib.gui.GenericGuiContainer;
import mcjty.rftoolsdim.entities.DimensionalBlobRender;
import mcjty.rftoolsdim.entities.EntitySetup;
import mcjty.rftoolsdim.modules.dimensionbuilder.DimensionBuilderSetup;
import mcjty.rftoolsdim.modules.dimensionbuilder.client.GuiDimensionBuilder;
import mcjty.rftoolsdim.modules.enscriber.EnscriberSetup;
import mcjty.rftoolsdim.modules.enscriber.client.GuiEnscriber;
import mcjty.rftoolsdim.modules.workbench.WorkbenchSetup;
import mcjty.rftoolsdim.modules.workbench.client.GuiWorkbench;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        DeferredWorkQueue.runLater(() -> {
            GenericGuiContainer.register(DimensionBuilderSetup.CONTAINER_DIMENSION_BUILDER.get(), GuiDimensionBuilder::new);
            GenericGuiContainer.register(WorkbenchSetup.CONTAINER_WORKBENCH.get(), GuiWorkbench::new);
            GenericGuiContainer.register(EnscriberSetup.CONTAINER_ENSCRIBER.get(), GuiEnscriber::new);
        });
        RenderingRegistry.registerEntityRenderingHandler(EntitySetup.DIMENSIONAL_BLOB.get(), DimensionalBlobRender.FACTORY);
    }
}