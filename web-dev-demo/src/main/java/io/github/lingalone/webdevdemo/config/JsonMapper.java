package io.github.lingalone.webdevdemo.config;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author Locki
 * @date 8/17/2017
 */
public class JsonMapper {

    private final static Logger logger = LoggerFactory.getLogger(JsonMapper.class);
    private ObjectMapper objectMapper;
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public JsonMapper() {this(null);}

    public JsonMapper(Include include) {
        objectMapper = new ObjectMapper();
        // 设置输出时包含属性的风格
        if (include != null) {
            objectMapper.setSerializationInclusion(include);
        }

        // 属性值为Null或Empty时不序列化
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        // 设置反序列化时忽略在JSON字符串中存在但Java对象实际没有的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 是否数字也加引号
        objectMapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, false);
        // 去掉默认的时间戳格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置为中国北京时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 反序列化时，属性不存在的兼容处理
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 单引号处理
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    /**
     * Object可以是POJO，也可以是Collection或数组
     * 如果对象为Null, 返回"null"
     * 如果集合为空集合, 返回"[]"
     *
     * @param object
     * @return
     */
    public String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.warn("write to json string error: " + object, e);
            return null;
        }
    }

    /**
     * 反序列化POJO或简单Collection如List<String>
     *
     * 如果JSON字符串为Null或"null"字符串, 返回Null
     * 如果JSON字符串为"[]", 返回空集合
     * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String, JavaType)
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 反序列化复杂Collection如List<Bean>, contructCollectionType()或contructMapType()构造类型, 然后调用本函数
     */
    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return (T) objectMapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            logger.warn("parse json string error:" + jsonString, e);
            return null;
        }
    }

    /**
     * 构造Collection类型.
     */
    public JavaType contructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

    /**
     * 构造Map类型.
     */
    public JavaType contructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
        return objectMapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
    }

    /**
     * 当JSON里只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性
     */
    public void update(String jsonString, Object object) {
        try {
            objectMapper.readerForUpdating(object).readValue(jsonString);
        } catch (JsonProcessingException e) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        } catch (IOException e) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
        }
    }

    /**
     * 輸出JSONP格式數據
     */
    public String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }

    /**
     * 設定是否使用Enum的toString函數來讀寫Enum,
     * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
     * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
     */
    public void enableEnumUseToString() {
        objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
    }


    /**
     * 取出Mapper做进一步的设置或使用其他序列化API.
     */
    public ObjectMapper mapper() {
        return objectMapper;
    }
}
