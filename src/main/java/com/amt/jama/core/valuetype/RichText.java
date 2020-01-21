package com.amt.jama.core.valuetype;

/**
 * @author WangWei
 */
public class RichText {
    private String value;

    public String getValue() {
        return value;
    }

    private String getValueWithRichText() {
        // 1. p, br, h, div,
       String value = this.value
               .replaceAll("</p>","</p>\r\n")
               .replaceAll("</br>","</br>\r\n")
               .replaceAll("</h1>","</h1>\r\n")
               .replaceAll("</h2>","</h2>\r\n")
               .replaceAll("</h3>","</h3>\r\n")
               .replaceAll("</h4>","</h4>\r\n")
               .replaceAll("</h5>","</h5>\r\n")
               .replaceAll("</h6>","</h6>\r\n")
               .replaceAll("</div>","</div>\r\n");
        // 3. 替换html标签
        return value.replaceAll("</?[^>]+>", "");
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
