package com.upuphub.trochilidae.web.common.util;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

/**
 * @author Inspiration S.P.A Leo
 * @date create time 2020-11-05 12:55
 **/
public class ObjectUtil {
    /**
     * convert from String to a target type
     *
     * @param targetType the type to be converted
     * @param s          the string to be converted
     * @throws NumberFormatException When string to number, if string is not a number,then throw NumberFormatException
     */
    public static Object convert(Class<?> targetType, String s) {
        PropertyEditor editor = PropertyEditorManager.findEditor(targetType);
        editor.setAsText(s);
        return editor.getValue();
    }
}
