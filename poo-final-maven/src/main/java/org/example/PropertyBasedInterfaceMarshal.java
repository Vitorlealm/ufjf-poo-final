package org.example;

import com.google.gson.*;
import org.example.produtos.Produto;

import java.lang.reflect.Type;

public class PropertyBasedInterfaceMarshal implements
        JsonDeserializer<Produto>, JsonSerializer<Produto> {

    private static final String CLASS_META_KEY = "type";

    @Override
    public Produto deserialize(JsonElement jsonElement, Type type,
                                  JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        String className = jsonObj.get(CLASS_META_KEY).getAsString();
        try {
            Class<?> clz = Class.forName(className);
            Produto produto = jsonDeserializationContext.deserialize(jsonElement, clz);
            return produto;
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(Produto object, Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonEle = jsonSerializationContext.serialize(object, object.getClass());
        jsonEle.getAsJsonObject().addProperty(CLASS_META_KEY,
                object.getClass().getCanonicalName());
        return jsonEle;
    }

}
