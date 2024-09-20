package com.woogleFX.editorObjects.objectFunctions;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects._2_Positionable;
import com.woogleFX.editorObjects.attributes.dataTypes.Position;
import com.woogleFX.editorObjects.objectComponents.ObjectComponent;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.engine.SelectionManager;

public class ObjectDrag {

    public static void dragFromMouse(double mouseX, double mouseY, double dragSourceX, double dragSourceY) {

        ObjectComponent objectComponent = SelectionManager.getDragSettings().getObjectComponent();

        double prevX = objectComponent.getX();
        double prevY = objectComponent.getY();
        
        objectComponent.setX(mouseX - dragSourceX);
        objectComponent.setY(mouseY - dragSourceY);

        for (EditorObject object : LevelManager.getLevel().getSelected()) {
            if (object.containsObjectComponent(objectComponent))
                continue;
            
            if (object instanceof _2_Positionable positionable) {
                Position pos = positionable.getPosition();
                positionable.setPosition(pos.getX() + objectComponent.getX() - prevX,
                        pos.getY() - objectComponent.getY() + prevY);
            }
        }
        
    }

}
