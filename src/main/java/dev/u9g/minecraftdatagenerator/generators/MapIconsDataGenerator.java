package dev.u9g.minecraftdatagenerator.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.item.map.MapIcon;
import net.minecraft.text.Text;


public class MapIconsDataGenerator implements IDataGenerator {


    @Override
    public String getDataName() {
        return "mapIcons";
    }

    @Override
    public JsonArray generateDataJson() {
        JsonArray resultsArray = new JsonArray();
        for (MapIcon.Type value : MapIcon.Type.values()) {
		    JsonObject resultObject = new JsonObject();
            resultObject.addProperty("id", value.getId());
            resultObject.addProperty("name", value.asString());
            resultObject.addProperty("appearance", convertToTitleCase(value.asString().replace("_"," ")));
            resultObject.addProperty("visibleInItemFrame", value.isAlwaysRendered());
            resultObject.addProperty("isStructure", value.isStructure());
		    resultsArray.add(resultObject);
		}
        
        return resultsArray;
    }


    private static String convertToTitleCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }

}
