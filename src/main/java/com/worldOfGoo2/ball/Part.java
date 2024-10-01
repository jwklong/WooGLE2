package com.worldOfGoo2.ball;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.objectComponents.ImageComponent;
import com.woogleFX.gameData.ball.AtlasManager;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.misc._2_ImageID;
import javafx.embed.swing.SwingFXUtils;

import java.awt.image.BufferedImage;

public class Part extends EditorObject {


    public Part(EditorObject parent) {
        super(parent, "Part", GameVersion.VERSION_WOG2);
    }

    @Override
    public void onLoaded() {

        javafx.scene.image.Image image = null;
        for (EditorObject editorObject : getChildren("images"))
            if (editorObject instanceof Image image1) {
                BufferedImage img = AtlasManager.atlas.get(image1.getChild("imageId").getAttribute("imageId").stringValue());
                if (img == null) continue;
                image = SwingFXUtils.toFXImage(img, null);
        }

        addComponent(image);

        if (!getAttribute("isEye").booleanValue()) return;

        javafx.scene.image.Image pupilImage = null;
        for (EditorObject editorObject : getChildren("pupilImageIds"))
            if (editorObject instanceof _2_ImageID image1) {
                BufferedImage pupilImg = AtlasManager.atlas.get(image1.getAttribute("imageId").stringValue());
                if (pupilImg == null) continue;
                pupilImage = SwingFXUtils.toFXImage(pupilImg, null);
            }

        addComponent(pupilImage);

    }


    private EditorObject getBodyPart() {
        String bodyPartName = getParent().getChild("bodyPart").getAttribute("partName").stringValue();
        for (EditorObject editorObject : getParent().getChildren("ballParts")) {
            if (editorObject.getAttribute("name").stringValue().equals(bodyPartName)) return editorObject;
        }
        return null;
    }


    private void addComponent(javafx.scene.image.Image finalImage) {

        addObjectComponent(new ImageComponent() {
            @Override
            public javafx.scene.image.Image getImage() {
                return finalImage;
            }

            @Override
            public double getX() {
                double minX = getAttribute("minX").doubleValue();
                double maxX = getAttribute("maxX").doubleValue();
                double minY = -getAttribute("minY").doubleValue();
                double maxY = -getAttribute("maxY").doubleValue();
                double x = (minX + maxX) / 2;
                double y = (minY + maxY) / 2;
                double theta = getAttribute("isRotating").booleanValue() ?
                        getBodyPart().getAttribute("rotation").doubleValue() : 0;
                return x * Math.cos(theta) - y * Math.sin(theta);
            }

            @Override
            public void setX(double _x) {
                double minX = getAttribute("minX").doubleValue();
                double maxX = getAttribute("maxX").doubleValue();
                double minY = -getAttribute("minY").doubleValue();
                double maxY = -getAttribute("maxY").doubleValue();
                double x = (minX + maxX) / 2;
                double theta = getAttribute("isRotating").booleanValue() ?
                        getBodyPart().getAttribute("rotation").doubleValue() : 0;
                setAttribute("minX", minX + (_x - x) * Math.cos(-theta));
                setAttribute("maxX", maxX + (_x - x) * Math.cos(-theta));
                setAttribute("minY", -(minY + (_x - x) * Math.sin(-theta)));
                setAttribute("maxY", -(maxY + (_x - x) * Math.sin(-theta)));
            }

            @Override
            public double getY() {
                double minX = getAttribute("minX").doubleValue();
                double maxX = getAttribute("maxX").doubleValue();
                double minY = -getAttribute("minY").doubleValue();
                double maxY = -getAttribute("maxY").doubleValue();
                double x = (minX + maxX) / 2;
                double y = (minY + maxY) / 2;
                double theta = getAttribute("isRotating").booleanValue() ?
                        getBodyPart().getAttribute("rotation").doubleValue() : 0;
                return x * Math.sin(theta) + y * Math.cos(theta);
            }

            @Override
            public void setY(double _y) {
                double minX = getAttribute("minX").doubleValue();
                double maxX = getAttribute("maxX").doubleValue();
                double minY = -getAttribute("minY").doubleValue();
                double maxY = -getAttribute("maxY").doubleValue();
                double y = (minY + maxY) / 2;
                double theta = getAttribute("isRotating").booleanValue() ?
                        getBodyPart().getAttribute("rotation").doubleValue() : 0;
                setAttribute("minX", minX + (_y - y) * -Math.sin(-theta));
                setAttribute("maxX", maxX + (_y - y) * -Math.sin(-theta));
                setAttribute("minY", -(minY + (_y - y) * Math.cos(-theta)));
                setAttribute("maxY", -(maxY + (_y - y) * Math.cos(-theta)));
            }

            @Override
            public double getScaleX() {
                double width = getParent().getAttribute("width").doubleValue();
                double scale = getAttribute("scale").doubleValue();
                if (getAttribute("scaleIsRelative").booleanValue())
                    scale *= width / ((ImageComponent)getBodyPart().getObjectComponents()[0]).getImage().getWidth();
                return scale;
            }

            @Override
            public void setScaleX(double scaleX) {
                double width = getParent().getAttribute("width").doubleValue();
                double scale = 1;
                if (getAttribute("scaleIsRelative").booleanValue())
                    scale *= width / ((ImageComponent)getBodyPart().getObjectComponents()[0]).getImage().getWidth();
                setAttribute("scale", scaleX / scale);
            }

            @Override
            public double getScaleY() {
                double height = getParent().getAttribute("height").doubleValue();
                double scale = getAttribute("scale").doubleValue();
                if (getAttribute("scaleIsRelative").booleanValue())
                    scale *= height / ((ImageComponent)getBodyPart().getObjectComponents()[0]).getImage().getHeight();
                return scale;
            }

            @Override
            public void setScaleY(double scaleY) {
                double height = getParent().getAttribute("height").doubleValue();
                double scale = 1;
                if (getAttribute("scaleIsRelative").booleanValue())
                    scale *= height / ((ImageComponent)getBodyPart().getObjectComponents()[0]).getImage().getHeight();
                setAttribute("scale", scaleY / scale);
            }

            @Override
            public double getRotation() {
                return -getAttribute("rotation").doubleValue();
            }

            @Override
            public void setRotation(double rotation) {
                setAttribute("rotation", -rotation);
            }

            @Override
            public double getDepth() {
                return 0;
            }
        });

    }

}
