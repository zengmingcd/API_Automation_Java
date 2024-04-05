package ca.zzsh.framework;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ConfigLoader {
    private final String CONFIG_PATH = "src/main/resources/config.json";
    private HashMap<String, Object> my_config;

    public ConfigLoader() throws IOException {
        File config_file = new File(CONFIG_PATH);
        ObjectMapper mapper = new ObjectMapper();
        my_config = mapper.readValue(config_file, new TypeReference<HashMap<String, Object>>(){});
    }

    public ConfigLoader(String configIndex) throws IOException {
        File config_file = new File(CONFIG_PATH);
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String,Object> json_map = mapper.readValue(config_file, new TypeReference<HashMap<String, Object>>() {});
        my_config = (HashMap<String, Object>) json_map.get(configIndex);
    }

    public Object get_by_key(String key){
        return my_config.get(key);
    }

}
