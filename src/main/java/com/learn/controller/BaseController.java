package com.learn.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.learn.exception.ErrorMessage;
import com.learn.exception.IllegalRequestException;
import com.learn.util.CommonUtil;
import com.learn.util.JsonUtil;
import com.learn.util.VerifyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/4/29
 * Time: 下午5:14
 */
@Controller
@Produces(MediaType.APPLICATION_JSON)
public class BaseController {

    protected static final String KEY_RESULT = "result";
    protected static final String KEY_LIST = "list";
    protected static final String KEY_TOTAL = "total";
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected static Map<String, Object> getResultMap() {
        return new HashMap<>();
    }

    protected void checkParamInt(Integer... params) {
        if (params == null) {
            return;
        }
        for (Integer param : params) {
            if (param == null || param <= 0) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_NOT_EXIST);
            }
        }
    }

    protected void checkParamLong(Long... params) {
        if (params == null) {
            return;
        }
        for (Long param : params) {
            if (param == null || param <= 0) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_NOT_EXIST);
            }
        }
    }

    protected void checkParamString(String... params) {
        if (params == null) {
            return;
        }
        for (String param : params) {
            if (param == null || param.length() == 0) {
                throw new IllegalRequestException(ErrorMessage.PARAMETER_NOT_EXIST);
            }
        }
    }

    protected void checkParamList(List list) {
        if (list == null || list.size() == 0) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_NOT_EXIST);
        }
    }

    protected long getOptionalParamLong(Long param, long defaultValue) {
        if (param == null) {
            return defaultValue;
        } else if (param < 0) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_OPTIONAL_ERROR);
        }
        return param;
    }

    protected long getOptionalParamLong(Long param, long min, long max, long defaultValue) {
        if (param == null) {
            return defaultValue;
        } else if (param < min || param > max) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_OPTIONAL_ERROR);
        }
        return param;
    }

    protected String getOptionalParamString(String param, String[] range, String defaultValue) {
        if (param == null || param.length() == 0) {
            return defaultValue;
        } else if (range != null && java.util.Arrays.asList(range).contains(param)) {
            return param.trim();
        } else {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_OPTIONAL_ERROR);
        }
    }

    protected int getOptionalParamInt(Integer param, int defaultValue) {
        if (param == null) {
            return defaultValue;
        } else if (param < 0) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_NOT_EXIST);
        }
        return param;
    }

    protected int getOptionalParamInt(Integer param, int min, int max, int defaultValue) {
        if (param == null) {
            return defaultValue;
        } else if (param < min || param > max) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_OPTIONAL_ERROR);
        }
        return param;
    }

    protected double getOptionalParamDouble(Double param, double min, double max, double defaultValue) {
        if (param == null) {
            return defaultValue;
        } else if (param < min || param > max) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_OUT_OF_RANGE);
        }
        return param;
    }

    protected int getJsonIntWithDefault(JsonNode node, String field, int defaultValue, boolean need) {
        return JsonUtil.asInt(node, field, defaultValue, need);
    }

    protected int getJsonInt(JsonNode node, String field, int min, int max, int defaultValue) {
        int res = JsonUtil.asInt(node, field, defaultValue, false);
        if (res < min || res > max) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_OUT_OF_RANGE);
        }
        return res;
    }

    protected Integer getJsonIntegerWithDefault(JsonNode node, String field, Integer defaultValue, boolean need) {
        return JsonUtil.asInteger(node, field, defaultValue, need);
    }

    protected int getJsonInt(JsonNode node, String field, boolean need) {
        return this.getJsonIntWithDefault(node, field, 0, need);
    }

    protected int getJsonInt(JsonNode node, String field, int min, int max, boolean need) {
        int value = this.getJsonIntWithDefault(node, field, 0, need);
        if (value < min || value > max) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_OUT_OF_RANGE);
        }
        return value;
    }

    protected Integer getJsonInteger(JsonNode node, String field, boolean need) {
        return this.getJsonIntegerWithDefault(node, field, null, need);
    }

    protected double getJsonDoubleWithDefault(JsonNode node, String field, double defaultValue, boolean need) {
        return JsonUtil.asDouble(node, field, defaultValue, need);
    }

    protected double getJsonDouble(JsonNode node, String field, boolean need) {
        return this.getJsonDoubleWithDefault(node, field, 0, need);
    }

    protected float getJsonFloatWithDefault(JsonNode node, String field, double defaultValue, boolean need) {
        double result = this.getJsonDoubleWithDefault(node, field, defaultValue, need);
        return (float) result;

    }

    protected float getJsonFloat(JsonNode node, String field, boolean need) {
        double result = this.getJsonDoubleWithDefault(node, field, 0, need);
        return (float) result;
    }

    protected int getJsonInt(JsonNode node, String field, boolean need, int maxLimit) {
        return Math.min(maxLimit, this.getJsonInt(node, field, need));
    }

    protected Long getJsonTime(JsonNode node, String field, boolean need) {
        JsonNode jsonNode = node.get(field);
        if (!CommonUtil.isEmpty(jsonNode)) {
            return jsonNode.asLong();
        }
        if (need) {
            throw new IllegalRequestException(ErrorMessage.PARAMETER_OPTIONAL_ERROR);
        }
        return null;
    }

    protected long getJsonLongWithDefault(JsonNode node, String field, long defaultValue, boolean need) {
        return JsonUtil.asLong(node, field, defaultValue, need);
    }

    protected long getJsonLong(JsonNode node, String field, boolean need) {
        return this.getJsonLongWithDefault(node, field, 0, need);
    }

    protected long getJsonLong(JsonNode node, String field, boolean need, long maxLimit) {
        return Math.min(maxLimit, this.getJsonInt(node, field, need));
    }

    protected long getJsonMyWid(JsonNode node, boolean need) {
        return this.getJsonLongWithDefault(node, "my_wid", 0, need);
    }

    protected long getJsonMyCompanyWid(JsonNode node, boolean need) {
        return this.getJsonLongWithDefault(node, "my_company_wid", 0, need);
    }

    protected String getJsonTextWithDefault(JsonNode node, String field, String defaultValue, boolean need) {
        String text = JsonUtil.asText(node, field, defaultValue, need);
        VerifyUtil.checkXssString(text);
        return text;
    }

    protected String getJsonTextWithDefault(JsonNode node, String field, String defaultValue, boolean need, int maxLength) {
        String text = JsonUtil.asText(node, field, defaultValue, need, maxLength);
        VerifyUtil.checkXssString(text);
        return text;
    }

    protected String getJsonText(JsonNode node, String field, boolean need) {
        String text = this.getJsonTextWithDefault(node, field, "", need);
        return text;
    }

    protected String getJsonTextUnCheckXss(JsonNode node, String field, boolean need) {
        return JsonUtil.asText(node, field, "", need);
    }

    protected String getJsonText(JsonNode node, String field, boolean need, int limitLength) {
        return this.getJsonTextWithDefault(node, field, "", need, limitLength);
    }

    protected JsonNode getNode(JsonNode node, String field, boolean need) {
        return JsonUtil.getNode(node, field, need);
    }

    protected JsonNode getJsonArray(JsonNode node, String field, boolean need) {
        return JsonUtil.asArray(node, field, need);
    }

    protected String asJsonText(JsonNode node, int maxLength) {
        return JsonUtil.asText(node, maxLength);
    }

    protected Map<String, Object> returnMap(String key, Object value) {
        Map<String, Object> map = new HashMap<>(1);
        map.put(key, value);
        return map;
    }

    protected List<Long> getJsonLongList(JsonNode json, String field, boolean need) {
        List<Long> list = new ArrayList<>();
        JsonNode node = this.getJsonArray(json, field, need);
        if (node != null)
            for (JsonNode item : node) {
                list.add(item.asLong());
            }
        return list;
    }

    protected List<Integer> getJsonIntegerList(JsonNode json, String field, boolean need) {
        List<Integer> list = new ArrayList<>();
        JsonNode node = this.getJsonArray(json, field, need);
        if (node != null)
            for (JsonNode item : node) {
                list.add(item.asInt());
            }
        return list;
    }

    protected List<String> getJsonStringList(JsonNode json, String field, boolean need) {
        List<String> list = new ArrayList<>();
        JsonNode node = this.getJsonArray(json, field, need);
        if (node != null)
            for (JsonNode item : node) {
                list.add(item.asText());
            }
        return list;
    }

    protected List<String> getJsonStringList(JsonNode json, String field, String property, boolean need) {
        List<String> list = new ArrayList<>();
        JsonNode node = this.getJsonArray(json, field, need);
        if (node != null)
            for (JsonNode item : node) {
                String value = JsonUtil.asText(item, property, null, false);
                value = value != null ? value : item.asText();
                if (value != null) {
                    list.add(value);
                }
            }
        return list;
    }

    protected Map<String, String> getJsonMap(JsonNode json, String field, boolean need) {
        Map<String, String> map = new HashMap<>();
        JsonNode node = this.getNode(json, field, need);
        if (node != null) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> next = fields.next();
                String key = next.getKey();
                JsonNode value = next.getValue();
                String text = JsonUtil.asText(value);
                map.put(key, text);
            }
        }
        return map;
    }

}
