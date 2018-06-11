package test.tools.selenium.mapping;

public enum MapMethodType {

    CLICK_OBJECT("click-object"),
    ELEMENT_DISPLAY("element-display"),
    FILL_INPUT("fill-input"),
    PAGE_LOADED("page-loaded"),
    SELECT_OPTION("select-option"),
    DRAG_OBJECT("drag-object"),
    FRAME_AND_WINDOW_OBJECT("frame-and-window-object");

    private final String value;

    private MapMethodType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
