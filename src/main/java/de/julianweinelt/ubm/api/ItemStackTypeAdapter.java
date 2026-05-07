package de.julianweinelt.ubm.api;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

public class ItemStackTypeAdapter extends TypeAdapter<ItemStack> {
    @Override
    public void write(JsonWriter out, ItemStack value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }

        ResourceLocation id = value.getItem().getRegistryName();
        if (id == null) {
            out.nullValue();
            return;
        }
        out.value(id.getResourceDomain() + ":" + id.getResourcePath());
    }

    @Override
    public ItemStack read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return ItemStack.EMPTY;
        }

        Item item = Item.REGISTRY.getObject(new ResourceLocation(in.nextString()));
        if (item == null) {
            in.nextNull();
            return ItemStack.EMPTY;
        }
        return new ItemStack(item);
    }
}
