package org.developers.system;

import lombok.AllArgsConstructor;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@AllArgsConstructor
public class YamlReader {

    private String path;

    public Map<String, Map<String, Map<String, Object>>> read() throws IOException {
        Yaml yamlReader = new Yaml();
        return (Map<String, Map<String, Map<String, Object>>>) yamlReader.load(Files.newInputStream(Paths.get(path)));
    }
}
