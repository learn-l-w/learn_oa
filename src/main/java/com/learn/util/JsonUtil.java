package com.learn.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.learn.exception.ErrorMessage;
import com.learn.exception.IllegalRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午22:52
 */
public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static JsonNode getNode(JsonNode node, String field, boolean need) {
        JsonNode fieldNode = node.get(field);
        if (need && isNullNode(fieldNode)) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_NOT_EXIST + field);
        }
        return fieldNode;
    }

    public static int asInt(JsonNode node, String field, int defaultValue, boolean need) {
        JsonNode fieldNode = getNode(node, field, need);
        if (need) {
            if (fieldNode.isTextual()) {
                try {
                    int value = Integer.parseInt(fieldNode.asText());
                    return value;
                } catch (NumberFormatException e) {
                    throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
                }
            } else if (!fieldNode.isInt()) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
            }
        }
        if (isNullNode(fieldNode)) {
            return defaultValue;
        } else {
            return fieldNode.asInt(defaultValue);
        }
    }

    public static Integer asInteger(JsonNode node, String field, Integer defaultValue, boolean need) {
        JsonNode fieldNode = getNode(node, field, need);
        if (need) {
            if (fieldNode.isTextual()) {
                try {
                    int value = Integer.parseInt(fieldNode.asText());
                    return value;
                } catch (NumberFormatException e) {
                    throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
                }
            } else if (!fieldNode.isInt()) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
            }
        }
        if (isNullNode(fieldNode)) {
            return defaultValue;
        } else {
            return fieldNode.asInt();
        }
    }

    public static double asDouble(JsonNode node, String field, double defaultValue, boolean need) {
        JsonNode fieldNode = getNode(node, field, need);
        if (need) {
            if (fieldNode.isTextual()) {
                try {
                    double value = Double.parseDouble(fieldNode.asText());
                    return value;
                } catch (NumberFormatException e) {
                    throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
                }
            } else if (fieldNode.isNumber()) {
                return fieldNode.asDouble();
            } else if (!fieldNode.isFloatingPointNumber()) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
            }
        }
        return fieldNode == null ? defaultValue : fieldNode.asDouble(defaultValue);
    }


    private static boolean isNullNode(JsonNode node) {
        if (node == null || node.isNull()) {
            return true;
        }
        return false;
    }

    public static long asLong(JsonNode node, String field, long defaultValue, boolean need) {
        JsonNode fieldNode = getNode(node, field, need);
        if (need) {
            if (fieldNode.isTextual()) {
                try {
                    long value = Long.parseLong(fieldNode.asText());
                    return value;
                } catch (NumberFormatException e) {
                    throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
                }
            } else if (!fieldNode.isLong() && !fieldNode.isInt()) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
            }
        }
        return fieldNode == null ? defaultValue : fieldNode.asLong(defaultValue);
    }

    public static String asText(JsonNode node, String field, String defaultValue, boolean need) {
        JsonNode fieldNode = getNode(node, field, need);
        String text = asText(fieldNode);
        if (need) {
            if (text == null || text.length() == 0) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
            }
            return text;
        }
        return text == null ? defaultValue : text;
    }

    public static String asText(JsonNode node) {
        if (node == null) {
            return null;
        }
        return node.asText().trim();
    }


    public static String asText(JsonNode node, String field, String defaultValue, boolean need, int maxLength) {
        String value = asText(node, field, defaultValue, need);
        if (value != null && value.length() > maxLength) {
            VerifyUtil.checkXssString(value);
            value = value.substring(0, maxLength);
        }
        return value;
    }

    public static JsonNode asArray(JsonNode node, String field, boolean need) {
        JsonNode fieldNode = getNode(node, field, need);
        if (need) {
            if (!fieldNode.isArray()) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
            }
        }
        return fieldNode;
    }

    public static String getJsonEmail(JsonNode node, String field, boolean need) {
        String email = asText(node, field, "", need);
        if (need) {
            if (email.trim().length() == 0) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
            }
            VerifyUtil.checkEmail(email);
        }
        return email;
    }

    public static String getJsonMobile(JsonNode node, String field, boolean need) {
        String mobile = asText(node, field, "", need);
        if (need) {
            if (mobile.trim().length() == 0) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_TYPE_ERROR + field);
            }
            VerifyUtil.checkMobile(mobile);
        }
        return mobile;
    }

    /**
     * 删除json节点
     *
     * @param json
     * @param tagPath 用 :分隔
     */
    public static void emptyJsonNode(JsonNode json, String tagPath) {
        if (CommonUtil.isEmpty(json) || (CommonUtil.isEmpty(tagPath))) {
            return;
        }
        List<JsonNode> nodes = new ArrayList<JsonNode>();
        String[] path = tagPath.split(":");
        getJsonNode(json, path, nodes, 1);
        for (JsonNode node : nodes) {
            if (node instanceof ObjectNode) {
                ((ObjectNode) node).remove(path[path.length - 1]);
            }
        }

    }

    public static void getJsonNode(JsonNode node, String[] path, List<JsonNode> nodes, int nextIndex) {
        if (CommonUtil.isEmpty(node)) {
            return;
        }
        // 是路径的最后就直接取值
        if (nextIndex == path.length) {
            if (node.isArray()) {
                for (int i = 0; i < node.size(); i++) {
                    JsonNode child = node.get(i).get(path[nextIndex - 1]);
                    if (CommonUtil.isEmpty(child)) {
                        continue;
                    }
                    nodes.add(node.get(i));
                }
            } else {
                JsonNode child = node.get(path[nextIndex - 1]);
                if (!CommonUtil.isEmpty(child)) {
                    nodes.add(node);
                }
            }
            return;
        }
        // 判断是Node下是集合还是一个节点
        node = node.get(path[nextIndex - 1]);
        if (node != null && node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                getJsonNode(node.get(i), path, nodes, nextIndex + 1);
            }
        } else {
            getJsonNode(node, path, nodes, nextIndex + 1);
        }
    }

    public static JsonNode jsonFromString(String string) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser jp = factory.createParser(string);
        return mapper.readTree(jp);
    }

    public static Map jsonStr2Map(String jsonStr) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, Map.class);
    }

    public static String asText(JsonNode node, int maxLength) {
        String value = asText(node);
        if (value != null && value.length() > maxLength) {
            VerifyUtil.checkXssString(value);
            value = value.substring(0, maxLength);
        }
        return value;
    }

    public static String toJson(Object object) {

        return toJson(object, false, false);
    }

    public static String toJson(Object object, boolean useAnnotation, boolean ignoreNull) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (useAnnotation) {
            objectMapper.configure(MapperFeature.USE_ANNOTATIONS, true);
        }
        if (ignoreNull) {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        String json = null;
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.error("Obj toJson error:", e);
        }
        return json;
    }

    public static JsonNode toNode(Object object) {
        String jsonStr;
        if (object instanceof String) {
            jsonStr = (String) object;
        } else {
            jsonStr = toJson(object);
        }
        try {
            return jsonFromString(jsonStr);
        } catch (IOException e) {
            logger.error("Obj toNode error:", e);
            return null;
        }
    }

    public static JsonNode toNode(Object object, boolean useAnnotation, boolean ignoreNull) {
        String jsonStr;
        if (object instanceof String) {
            jsonStr = (String) object;
        } else {
            jsonStr = toJson(object, useAnnotation, ignoreNull);
        }
        try {
            return jsonFromString(jsonStr);
        } catch (IOException e) {
            logger.error("Obj toNode error:", e);
            return null;
        }
    }

    public static <T> T fromJson(String content, Class<T> valueType) {
        ObjectMapper mapper = new ObjectMapper();
        mapper = mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper = mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T result = null;
        try {
            result = mapper.readValue(content, valueType);
        } catch (IOException e) {
            logger.error("from json error", e);
        }
        return result;
    }

    public static String readGenItemValue(JsonNode jsonNode) {
        if (jsonNode == null || jsonNode.isNull()) {
            return "";
        }
        JsonNode idNode = jsonNode.get("value");
        return idNode == null ? "" : idNode.asText();
    }
}
