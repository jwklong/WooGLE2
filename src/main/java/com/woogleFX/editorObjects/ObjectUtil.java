package com.woogleFX.editorObjects;

import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.gameData.level.GameVersion;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Arrays;

public class ObjectUtil {

    public static Point2D rotate(Point2D input, double theta, Point2D center){

        double rotatedX = (input.getX() - center.getX()) * Math.cos(theta) - (input.getY() - center.getY()) * Math.sin(theta);
        double rotatedY = (input.getX() - center.getX()) * Math.sin(theta) + (input.getY() - center.getY()) * Math.cos(theta);

        return new Point2D(rotatedX + center.getX(), rotatedY + center.getY());

    }


    public static EditorObject deepClone(EditorObject editorObject, EditorObject parent) {

        EditorObject clone;
        if (editorObject.getVersion() == GameVersion.VERSION_WOG2)
            clone = ObjectCreator.create2(parent == null ? "_2_Level" : parent.getAttributeChildAlias().get(editorObject.getTypeID()), parent, editorObject.getVersion());
        else clone = ObjectCreator.create(editorObject.getType(), parent, editorObject.getVersion());
        clone.setTypeID(editorObject.getTypeID());

        for (EditorObject child : editorObject.getChildren()) {
            deepClone(child, clone);
        }

        if (editorObject.getParent() == null) cloneAllAttributes(editorObject, clone);

        return clone;
    }


    public static void cloneAllAttributes(EditorObject editorObject, EditorObject clone) {

        for (EditorAttribute attribute : editorObject.getAttributes()) {
            if (attribute.getType() != InputField._2_CHILD &&
                    attribute.getType() != InputField._2_CHILD_HIDDEN &&
                    attribute.getType() != InputField._2_LIST_CHILD &&
                    attribute.getType() != InputField._2_LIST_CHILD_HIDDEN)
                clone.setAttribute(attribute.getName(), attribute.stringValue());
        }

        int i = 0;
        for (EditorObject child : editorObject.getChildren()) {
            cloneAllAttributes(child, clone.getChildren().get(i));
            i++;
        }

    }


    public static boolean attributeContainsTag(String[] list, String tag) {
        return Arrays.asList(list).contains(tag);
    }

}
