package test.tools.selenium.constants;

import org.openqa.selenium.By;
import test.tools.selenium.mapping.MapMethodType;
import test.tools.selenium.mapping.MapValue;
import test.tools.selenium.mapping.Mapper;

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
        switch (mapValue.getByMethod()) {
            case "id":
                by = By.id(attr);
                break;
            case "name":
                by = By.name(attr);
                break;
            case "className":
                by = By.className(attr);
                break;
            case "tagName":
                by = By.tagName(attr);
                break;
            case "linkText":
                by = By.linkText(attr);
                break;
            case "partialLinkText":
                by = By.partialLinkText(attr);
                break;
            case "cssSelector":
                by = By.cssSelector(attr);
                break;
            case "xpath":
            default:
                by = By.xpath(String.format("//%s[%s='%s']",
                        mapValue.getTag(),
                        mapValue.getAttrName(),
                        mapValue.getAttrValue()));
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
        MapValue followingMapValue = createMapperConfiguration(followingAttr, followingMapMethodType);
        switch (mapValue.getByMethod()) {
            case "id":
                by = By.id(attr);
                break;
            case "name":
                by = By.name(attr);
                break;
            case "className":
                by = By.className(attr);
                break;
            case "tagName":
                by = By.tagName(attr);
                break;
            case "linkText":
                by = By.linkText(attr);
                break;
            case "partialLinkText":
                by = By.partialLinkText(attr);
                break;
            case "cssSelector":
                by = By.cssSelector(attr);
                break;
            case "xpath":
            default:
                by = By.xpath(String.format("//%s[%s='%s']/following::%s[%s='%s']",
                        mapValue.getTag(),
                        mapValue.getAttrName(),
                        mapValue.getAttrValue(),
                        followingMapValue.getTag(),
                        followingMapValue.getAttrName(),
                        followingMapValue.getAttrValue()));
                break;
        }
        return by;
    }

}
