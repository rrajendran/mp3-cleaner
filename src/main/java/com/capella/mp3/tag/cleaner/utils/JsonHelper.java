package com.capella.mp3.tag.cleaner.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;

public final class JsonHelper {
    private static ObjectMapper objectMapper = ObjectMapperFactory.getInstance();

    private JsonHelper() {
    }

    public static InputStream readJson(String fileName) {
        return JsonHelper.class.getClassLoader().getResourceAsStream(fileName);
    }

    public static <T> T jsonToObject(InputStream inputStream, Class<T> className) throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(inputStream, className);
    }

    public static <T> T jsonToObject(String json, Class<T> className) throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(json.getBytes(), className);
    }

    public static String toString(Object object) throws JsonGenerationException, JsonMappingException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        objectMapper.writeValue(baos, object);
        return new String(baos.toByteArray(), "UTF-8");
    }

    public static Object getJsonPath(String json, String path) {
        String object = null;
        if (!StringUtils.isEmpty(json) && !StringUtils.isEmpty(path)) {
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
            object = (String) JsonPath.read(document, path, new Predicate[0]);
        }

        return object;
    }
}
