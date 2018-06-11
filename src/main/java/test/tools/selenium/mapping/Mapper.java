package test.tools.selenium.mapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Mapper {

    private static final String BASE_PATH = "src/test/resources/mapping/";
    private static final String BASE_EXTENSION = ".json";

    /**
     * Get Map Value
     *
     * @param mapMethodType Method type
     * @param sentence      Sentence with turkish chars
     * @return Json mapped value
     */
    public MapValue getMapValue(MapMethodType mapMethodType, String sentence) {
        return readMapValue(sentence, BASE_PATH + mapMethodType.getValue() + BASE_EXTENSION);
    }

    /**
     * Get Map Value
     *
     * @param sentence      Sentence with turkish chars
     * @param mapMethodType Method type
     * @return Json mapped value
     */
    public MapValue getMapValue(String sentence, MapMethodType mapMethodType) {
        return readMapValue(sentence, BASE_PATH + mapMethodType.getValue() + BASE_EXTENSION);
    }

    /**
     * Reads map value from json and clear turkish chars
     *
     * @param sentence Sentence with turkish chars
     * @param filename Filename
     * @return Map value
     */
    private MapValue readMapValue(String sentence, String filename) {
        String clearedAndUpperCase = clearTurkishCharsAndUpperCase(sentence);
        Map<String, MapValue> map = jsonToMap(filename);
        return map.getOrDefault(clearedAndUpperCase, new MapValue());
    }

    /**
     * Clear turkish chars And string to upper case
     *
     * @param str contains turkish chars
     * @return cleared turkish chars
     */
    private String clearTurkishCharsAndUpperCase(String str) {
        String returnStr = str;
        char[] turkishChars = new char[]{0x131, 0x130, 0xFC, 0xDC, 0xF6, 0xD6, 0x15F, 0x15E, 0xE7, 0xC7, 0x11F, 0x11E};
        char[] englishChars = new char[]{'i', 'I', 'u', 'U', 'o', 'O', 's', 'S', 'c', 'C', 'g', 'G'};
        for (int i = 0; i < turkishChars.length; i++) {
            returnStr = returnStr.replaceAll(new String(new char[]{turkishChars[i]}), new String(new char[]{englishChars[i]}));
        }
        return returnStr.toUpperCase(Locale.ENGLISH);
    }

    /**
     * Read json file and map
     *
     * @param filename filename
     * @return Json mapped hash map
     */
    private Map<String, MapValue> jsonToMap(String filename) {
        Gson gson = new Gson();
        Map<String, MapValue> map = new HashMap<>();
        try {
            map = gson.fromJson(new FileReader(filename), new TypeToken<Map<String, MapValue>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

}
