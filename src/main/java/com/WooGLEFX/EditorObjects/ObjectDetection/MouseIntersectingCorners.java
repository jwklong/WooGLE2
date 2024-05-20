package com.WooGLEFX.EditorObjects.ObjectDetection;

import com.WooGLEFX.EditorObjects.ObjectPosition;
import com.WooGLEFX.EditorObjects.ObjectCollision.AnchorCollider;
import com.WooGLEFX.EditorObjects.ObjectCollision.CircleCollider;
import com.WooGLEFX.EditorObjects.ObjectCollision.ImageCollider;
import com.WooGLEFX.EditorObjects.ObjectCollision.RectangleCollider;
import com.WooGLEFX.EditorObjects.EditorObject;
import com.WooGLEFX.Structures.SimpleStructures.DragSettings;

public class MouseIntersectingCorners {

    public static DragSettings mouseIntersectingCorners(EditorObject editorObject, double mouseX, double mouseY) {

        for (ObjectPosition objectPosition : editorObject.getObjectPositions()) {

            DragSettings dragSettings = forPosition(objectPosition, mouseX, mouseY);
            if (dragSettings != DragSettings.NULL) return dragSettings;

        }

        return DragSettings.NULL;

    }


    public static DragSettings forPosition(ObjectPosition objectPosition, double mouseX, double mouseY) {

        if (!objectPosition.isVisible()) return DragSettings.NULL;

        switch (objectPosition.getId()) {

            case ObjectPosition.POINT, ObjectPosition.LINE -> {
                return DragSettings.NULL;
            }
            case ObjectPosition.RECTANGLE, ObjectPosition.RECTANGLE_HOLLOW -> {
                return RectangleCollider.mouseIntersectingCorners(objectPosition, mouseX, mouseY);
            }
            case ObjectPosition.CIRCLE, ObjectPosition.CIRCLE_HOLLOW -> {
                return CircleCollider.mouseIntersectingCorners(objectPosition, mouseX, mouseY);
            }
            case ObjectPosition.ANCHOR -> {
                return AnchorCollider.mouseIntersectingCorners(objectPosition, mouseX, mouseY);
            }
            case ObjectPosition.IMAGE -> {
                return ImageCollider.mouseIntersectingCorners(objectPosition, mouseX, mouseY);
            }

        }

        throw new RuntimeException("Invalid object position id: " + objectPosition.getId());

    }

}
