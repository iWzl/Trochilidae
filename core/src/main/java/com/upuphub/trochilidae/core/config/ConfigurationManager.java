package com.upuphub.trochilidae.core.config;


import com.upuphub.trochilidae.core.config.resource.ResourceLoader;
import com.upuphub.trochilidae.core.config.resource.property.PropertiesResourceLoader;
import com.upuphub.trochilidae.core.config.resource.yaml.YamlResourceLoader;
import com.upuphub.trochilidae.core.factory.ConfigurationFactory;
import com.upuphub.trochilidae.core.logging.Logger;
import com.upuphub.trochilidae.core.logging.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private static final String TROCHILIDAE_PROFILES_ACTIVE_CONF= "trochilidae.profiles.active";

    private static final String TROCHILIDAE_ACTIVE_PREFIX= "application";

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

    @Override
    public Double getDouble(String key) {
        return configuration.getDouble(key);
    }

    @Override
    public Map<String, String> getAll() {
        return configuration.getAll();
    }


    public void loadConfigurationResources(ClassLoader classLoader) {
        for (String configName : Configuration.DEFAULT_CONFIG_NAMES) {
            URL url = classLoader.getResource(configName);
            Path resourcePath = null;
            if (!Objects.isNull(url)) {
                try{
                    resourcePath = Paths.get(url.toURI());
                } catch (URISyntaxException ignored) {continue;}
                String fileName = resourcePath.getFileName().toString();
                String fileNameEndWith = "";
                String profileActiveConfigKey = "";
                try {
                    if (fileName.endsWith(PROPERTIES_FILE_EXTENSION)) {
                        fileNameEndWith = PROPERTIES_FILE_EXTENSION;
                        ResourceLoader resourceLoader = new PropertiesResourceLoader();
                        Map<String, String> resourceConfigMap = resourceLoader.load(resourcePath);
                        profileActiveConfigKey = resourceConfigMap.getOrDefault(TROCHILIDAE_PROFILES_ACTIVE_CONF,"");
                        configuration.putAll(resourceConfigMap);
                    } else if (fileName.endsWith(YML_FILE_EXTENSION) || fileName.endsWith(YAML_FILE_EXTENSION)) {
                        fileNameEndWith = fileName.endsWith(YML_FILE_EXTENSION) ? YML_FILE_EXTENSION : YAML_FILE_EXTENSION;
                        ResourceLoader resourceLoader = new YamlResourceLoader();
                        Map<String, String> resourceConfigMap = resourceLoader.load(resourcePath);
                        profileActiveConfigKey = resourceConfigMap.getOrDefault(TROCHILIDAE_PROFILES_ACTIVE_CONF,"");
                        configuration.putAll(resourceConfigMap);
                    }
                    if(!"".equals(fileNameEndWith) && !"".equals(profileActiveConfigKey)){
                        loadChildConfigurationResources(classLoader,fileNameEndWith,profileActiveConfigKey);
                    }
                    configuration.putAll(ConfigurationFactory.runResourceConfigurationPostProcess(configuration.getAll()));
                }catch (IOException ex) {
                    logger.error("Failed to load configuration file, no correct configuration items were matched",ex);
                    System.exit(-1);
                }

            }
        }
    }

    private void loadChildConfigurationResources(ClassLoader classLoader,String fileNameEndWith,String profileActiveConfigKey){
        String configFileName = String.format("%s-%s%s",TROCHILIDAE_ACTIVE_PREFIX,profileActiveConfigKey,fileNameEndWith);
        URL url = classLoader.getResource(configFileName);
        if(!Objects.isNull(url)){
            try {
                Path resourcePath = Paths.get(url.toURI());
                String fileName = resourcePath.getFileName().toString();
                if (fileName.endsWith(PROPERTIES_FILE_EXTENSION)) {
                    ResourceLoader resourceLoader = new PropertiesResourceLoader();
                    Map<String, String> resourceConfigMap = resourceLoader.load(resourcePath);
                    configuration.putAll(resourceConfigMap);
                } else if (fileName.endsWith(YML_FILE_EXTENSION) || fileName.endsWith(YAML_FILE_EXTENSION)) {
                    ResourceLoader resourceLoader = new YamlResourceLoader();
                    Map<String, String> resourceConfigMap = resourceLoader.load(resourcePath);
                    configuration.putAll(resourceConfigMap);
                }
            }catch (URISyntaxException ignored){}
            catch (IOException ex){
                logger.error("Failed to load Child configuration file, no correct configuration items were matched",ex);
                System.exit(-1);
            }
        }
    }


    public void supplementApplicationConfigurations(Map<String, String> configurations){
        configuration.putAll(configurations);
    }

    @Override
    public Map<String, String> processingHandler(Map<String, String> resourceMap) {
        logger.info("Load Configuration Success - active : {}, size : {}",
                resourceMap.getOrDefault(TROCHILIDAE_PROFILES_ACTIVE_CONF,"ROOT"),resourceMap.size());
        resourceMap.forEach((key,value)->logger.info("{} : {}",key,value));
        return resourceMap;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}