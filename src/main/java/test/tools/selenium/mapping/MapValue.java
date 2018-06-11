package test.tools.selenium.mapping;

import com.google.gson.Gson;

public class MapValue {

    private String tag;

    private String attrName;

    private String attrValue;

    private Integer index;

    private String byMethod;

    private Boolean isJsEnabled;

    private Boolean moreThanOne;

    public String getTag() {
        return tag;
    }

    public String getAttrName() {
        return attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public Integer getIndex() {
        return index;
    }

    public String getByMethod() {
        return byMethod;
    }

    public Boolean getIsJsEnabled() {
        return isJsEnabled;
    }

    public Boolean getMoreThanOne() {
        return moreThanOne;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
