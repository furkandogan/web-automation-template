package test.tools.selenium.constants;

import test.tools.selenium.mapping.MapMethodType;
import test.tools.selenium.mapping.MapValue;
import test.tools.selenium.mapping.Mapper;
import org.openqa.selenium.By;

public abstract class XpathInjection {

    public static By by = null;
    public static MapValue mapValue = null;


    /**
     * Create map configuration from map
     *
     * @param attr
     * @param mapMethodType
     * @return
     */
    public static MapValue createMapperConfiguration(String attr, MapMethodType mapMethodType) {
        Mapper mapper = new Mapper();
        mapValue = mapper.getMapValue(attr, mapMethodType);
        return mapValue;
    }

    /**
     * Create xpath from json
     *
     * @param attr
     * @param mapMethodType
     * @return
     */
    public static By createXpath(String attr, MapMethodType mapMethodType) {
        MapValue mapValue = createMapperConfiguration(attr, mapMethodType);
        String attrValue = mapValue.getAttrValue();
        switch (mapValue.getByMethod()) {
            case "id":
                by = By.id(attrValue);
                break;
            case "name":
                by = By.name(attrValue);
                break;
            case "className":
                by = By.className(attrValue);
                break;
            case "tagName":
                by = By.tagName(attrValue);
                break;
            case "linkText":
                by = By.linkText(attrValue);
                break;
            case "partialLinkText":
                by = By.partialLinkText(attrValue);
                break;
            case "cssSelector":
                by = By.cssSelector(attrValue);
                break;
            case "xpath":
            default:
                by = By.xpath(String.format("//%s[%s='%s']",
                        mapValue.getTag(),
                        mapValue.getAttrName(),
                        attrValue));
                break;
        }
        return by;
    }

    /**
     * Create following xpath from json
     *
     * @param attr
     * @param followingAttr
     * @param mapMethodType
     * @param followingMapMethodType
     * @return
     */
    public static By createXpath(String attr, String followingAttr, MapMethodType mapMethodType, MapMethodType followingMapMethodType) {
        MapValue mapValue = createMapperConfiguration(attr, mapMethodType);
        String attrValue = mapValue.getAttrValue();
        MapValue followingMapValue = createMapperConfiguration(followingAttr, followingMapMethodType);
        String followingAttrValue = followingMapValue.getAttrValue();
        switch (mapValue.getByMethod()) {
            case "id":
                by = By.id(attrValue);
                break;
            case "name":
                by = By.name(attrValue);
                break;
            case "className":
                by = By.className(attrValue);
                break;
            case "tagName":
                by = By.tagName(attrValue);
                break;
            case "linkText":
                by = By.linkText(attrValue);
                break;
            case "partialLinkText":
                by = By.partialLinkText(attrValue);
                break;
            case "cssSelector":
                by = By.cssSelector(attrValue);
                break;
            case "xpath":
            default:
                by = By.xpath(String.format("//%s[%s='%s']/following::%s[%s='%s']",
                        mapValue.getTag(),
                        mapValue.getAttrName(),
                        attrValue,
                        followingMapValue.getTag(),
                        followingMapValue.getAttrName(),
                        followingAttrValue));
                break;
        }
        return by;
    }

}
