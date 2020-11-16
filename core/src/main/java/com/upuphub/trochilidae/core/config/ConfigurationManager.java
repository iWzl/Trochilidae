package com.upuphub.trochilidae.core.config;


import com.upuphub.trochilidae.core.config.resource.ResourceLoader;
import com.upuphub.trochilidae.core.config.resource.property.PropertiesResourceLoader;
import com.upuphub.trochilidae.core.config.resource.yaml.YamlResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 配置类信息管理器
 *
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-16 18:00
 **/
public class ConfigurationManager implements Configuration,ResourceConfigurationPostProcess{
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationManager.class);

    private static final String PROPERTIES_FILE_EXTENSION = ".properties";

    private static final String YAML_FILE_EXTENSION = ".yaml";

    private static final String YML_FILE_EXTENSION = ".yml";

    private final Configuration configuration;

    public ConfigurationManager(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public int getInt(String key) {
        return configuration.getInt(key);
    }

    @Override
    public String getString(String key) {
        return configuration.getString(key);
    }

    @Override
    public Boolean getBoolean(String key) {
        return configuration.getBoolean(key);
    }

    @Override
    public Long getLong(String key) {
        return configuration.getLong(key);
    }

    public void loadConfigurationResources(Class<?> bootstrapClass) {
        ClassLoader classLoader = bootstrapClass.getClassLoader();
        List<Path> filePaths = new ArrayList<>();
        for (String configName : Configuration.DEFAULT_CONFIG_NAMES) {
            URL url = classLoader.getResource(configName);
            if (!Objects.isNull(url)) {
                try {
                    filePaths.add(Paths.get(url.toURI()));
                } catch (URISyntaxException ignored) {}
            }
        }
        try {
            for (Path resourcePath : filePaths) {
                String fileName = resourcePath.getFileName().toString();
                if (fileName.endsWith(PROPERTIES_FILE_EXTENSION)) {
                    ResourceLoader resourceLoader = new PropertiesResourceLoader();
                    configuration.putAll(resourceLoader.load(resourcePath));
                } else if (fileName.endsWith(YML_FILE_EXTENSION) || fileName.endsWith(YAML_FILE_EXTENSION)) {
                    ResourceLoader resourceLoader = new YamlResourceLoader();
                    configuration.putAll(resourceLoader.load(resourcePath));
                }
            }
        } catch (IOException ex) {
            logger.error("Failed to load configuration file, no correct configuration items were matched");
            System.exit(-1);
        }
    }

    @Override
    public Map<String, String> handler(Map<String, String> resourceMap) {
        return resourceMap;
    }
}
