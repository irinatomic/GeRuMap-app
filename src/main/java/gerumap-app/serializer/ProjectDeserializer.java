package dsw.gerumap.app.serializer;

import com.google.gson.*;
import dsw.gerumap.app.maprepository.composite.MapNode;

import java.lang.reflect.Type;

public class ProjectDeserializer implements JsonDeserializer<MapNode>, JsonSerializer<MapNode> {
    @Override
    public MapNode deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final JsonObject member = (JsonObject) jsonElement;
        final JsonElement typeS = get(member, "type");
        final JsonElement data = get(member, "data");
        final Type realType = typeName(typeS);

        return jsonDeserializationContext.deserialize(data, realType);
    }

    @Override
    public JsonElement serialize(MapNode objectNode, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonObject member = new JsonObject();
        member.addProperty("type", objectNode.getClass().getName());
        try{
            member.add("data", jsonSerializationContext.serialize(objectNode));
        } catch(JsonIOException e){
            System.out.println("ERROR " + objectNode);
            return null;
        }
        return member;
    }

    private Type typeName (final JsonElement typeElement){
        try {
            return Class.forName(typeElement.getAsString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private JsonElement get (final JsonObject wrapper, final String memeberName){
        final JsonElement elem = wrapper.get(memeberName);
        return elem;
    }
}
