package com.worldOfGoo2.util;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.gameData.ball.AtlasManager;
import com.woogleFX.gameData.ball._2Ball;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG2Level;
import com.worldOfGoo2.ball._2_Ball_Image;
import com.worldOfGoo2.ball._2_Ball_Part;
import com.worldOfGoo2.level._2_Level_BallInstance;
import com.worldOfGoo2.level._2_Level_Strand;
import com.worldOfGoo2.level._2_Level_TerrainBall;
import com.worldOfGoo2.misc._2_ImageID;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class BallInstanceHelper {


    private static boolean part2CanBeUsed(_2_Level_BallInstance ballInstance, _2_Ball_Part part) {

        String state = "0";

        if (!ballInstance.getAttribute("discovered").booleanValue()) {
            state = "6";
        } else {
            for (EditorObject obj : ((WOG2Level) LevelManager.getLevel()).getObjects()) {
                if (obj instanceof _2_Level_Strand strand) {

                    String id = ballInstance.getAttribute("uid").stringValue();
                    String gb1 = strand.getAttribute("ball1UID").stringValue();
                    String gb2 = strand.getAttribute("ball2UID").stringValue();

                    if (id.equals(gb1) || id.equals(gb2)) {
                        state = "4";
                        break;
                    }

                }
            }
        }

        ArrayList<EditorObject> states = part.getChildren("states");
        if (states.isEmpty()) return true;
        else for (EditorObject stateObject : states) {
            if (stateObject.getAttribute("ballState").stringValue().equals(state)) return true;
        }
        return false;

    }


    private static BufferedImage getPartImageWoG2(_2_Ball_Part part, Random machine) {

        ArrayList<_2_Ball_Image> images = new ArrayList<>();
        for (EditorObject editorObject : part.getChildren()) if (editorObject instanceof _2_Ball_Image ball_image) images.add(ball_image);
        if (images.size() == 0) return null;

        String imageString = images.get((int)(images.size() * machine.nextDouble())).getChildren().get(0).getAttribute("imageId").stringValue();

        return AtlasManager.atlas.get(imageString);

    }


    private static BufferedImage getPartPupilImageWoG2(_2_Ball_Part part, Random machine) {

        ArrayList<_2_ImageID> pupilImages = new ArrayList<>();
        for (EditorObject editorObject : part.getChildren()) if (editorObject instanceof _2_ImageID ball_image) pupilImages.add(ball_image);
        if (pupilImages.size() == 0) return null;

        String pupilImageString = pupilImages.get((int)(pupilImages.size() * machine.nextDouble())).getAttribute("imageId").stringValue();

        return AtlasManager.atlas.get(pupilImageString);

    }


    public static Image createBallImageWoG2(_2_Level_BallInstance ballInstance, _2Ball ball, double _scaleX, double _scaleY, Random machine) {

        ArrayList<_2_Ball_Part> parts = new ArrayList<>();
        for (EditorObject child : ball.getObjects()) if (child instanceof _2_Ball_Part part) parts.add(part);
        parts.sort((o1, o2) -> (int)Math.signum(
                o1.getAttribute("layer").doubleValue() - o2.getAttribute("layer").doubleValue()));
        if (parts.isEmpty()) return null;

        // Create image bounds
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;

        double bodyMinX = Double.POSITIVE_INFINITY;
        double bodyMinY = Double.POSITIVE_INFINITY;
        double bodyMaxX = Double.NEGATIVE_INFINITY;
        double bodyMaxY = Double.NEGATIVE_INFINITY;

        String bodyPartID = ball.getObjects().get(0).getChildren("bodyPart")
                .get(0).getAttribute("partName").stringValue();

        record PartPosition(double x, double y, double scaleX, double scaleY, BufferedImage image) {

        }

        ArrayList<PartPosition> partPositions = new ArrayList<>();

        boolean thereWasABody = false;
        for (_2_Ball_Part part : parts) {

            if (ballInstance != null && !part2CanBeUsed(ballInstance, part)) continue;

            double sizeVariance = ball.getObjects().get(0).getAttribute("sizeVariance").doubleValue();

            Random machine2 = new Random(1);
            machine2.nextDouble();
            double random = machine2.nextDouble();

            boolean relative = part.getAttribute("scaleIsRelative").booleanValue();

            double scaleX = part.getAttribute("scale").doubleValue() * (relative ? 1 : 1 / _scaleX) * (1 + (random * 2 - 1) * sizeVariance);
            double scaleY = part.getAttribute("scale").doubleValue() * (relative ? 1 : 1 / _scaleY) * (1 + (random * 2 - 1) * sizeVariance);

            double partMinX = part.getAttribute("minX").doubleValue() / _scaleX;
            double partMinY = -part.getAttribute("minY").doubleValue() / _scaleY;
            double partMaxX = part.getAttribute("maxX").doubleValue() / _scaleX;
            double partMaxY = -part.getAttribute("maxY").doubleValue() / _scaleY;

            double partX = partMinX + (partMaxX - partMinX) * machine.nextDouble();
            double partY = partMinY + (partMaxY - partMinY) * machine.nextDouble();

            BufferedImage partImage = getPartImageWoG2(part, machine);
            if (partImage == null) continue;
            partPositions.add(new PartPosition(partX, partY, scaleX, scaleY, partImage));

            BufferedImage pupilImage = getPartPupilImageWoG2(part, machine);
            if (pupilImage != null)
                partPositions.add(new PartPosition(partX, partY, scaleX, scaleY, pupilImage));

            double partImageMinX = partX - partImage.getWidth() * scaleX / 2.0;
            double partImageMinY = partY - partImage.getHeight() * scaleY / 2.0;
            double partImageMaxX = partX + partImage.getWidth() * scaleX / 2.0;
            double partImageMaxY = partY + partImage.getHeight() * scaleY / 2.0;

            if (partImageMinX < minX) minX = partImageMinX;
            if (partImageMinY < minY) minY = partImageMinY;
            if (partImageMaxX > maxX) maxX = partImageMaxX;
            if (partImageMaxY > maxY) maxY = partImageMaxY;

            if (part.getAttribute("name").stringValue().equals(bodyPartID)) {
                thereWasABody = true;
                bodyMinX = partImageMinX;
                bodyMinY = partImageMinY;
                bodyMaxX = partImageMaxX;
                bodyMaxY = partImageMaxY;
            }

        }

        if (!thereWasABody) {
            bodyMinX = 0;
            bodyMinY = 0;
            bodyMaxX = 1;
            bodyMaxY = 1;
        }

        double paddingLeft = bodyMinX - minX;
        double paddingRight = maxX - bodyMaxX;
        paddingLeft = Math.max(paddingLeft, paddingRight);
        paddingRight = paddingLeft;
        minX = bodyMinX - paddingLeft;
        maxX = bodyMaxX + paddingRight;

        double paddingTop = bodyMinY - minY;
        double paddingBottom = maxY - bodyMaxY;
        paddingTop = Math.max(paddingTop, paddingBottom);
        paddingBottom = paddingTop;
        minY = bodyMinY - paddingTop;
        maxY = bodyMaxY + paddingBottom;

        if (maxX - minX <= -100000000) return null;

        BufferedImage image = new BufferedImage((int)(maxX - minX), (int)(maxY - minY), BufferedImage.TYPE_INT_ARGB);

        Graphics drawGraphics = image.createGraphics();

        for (PartPosition partPosition : partPositions) {

            BufferedImage partImage = partPosition.image;

            int imageX = (int)(partPosition.x - partImage.getWidth() * partPosition.scaleX / 2 - minX);
            int imageY = (int)(partPosition.y - partImage.getHeight() * partPosition.scaleY / 2 - minY);
            int imageWidth = (int)(partImage.getWidth() * partPosition.scaleX);
            int imageHeight = (int)(partImage.getHeight() * partPosition.scaleY);
            drawGraphics.drawImage(partImage, imageX, imageY, imageWidth, imageHeight, null);

        }

        drawGraphics.dispose();

        return SwingFXUtils.toFXImage(image, null);

    }


    public static void addTerrainBall(WOG2Level level, _2_Level_BallInstance ballInstance) {

        EditorObject terrainBall = ObjectCreator.create2("_2_Level_TerrainBall", level.getLevel(), GameVersion.VERSION_WOG2);
        terrainBall.setTypeID("terrainBalls");
        terrainBall.setAttribute("group", ballInstance.getAttribute2("terrainGroup").stringValue());
        level.getObjects().add(level.getLevel().getChildren("balls").indexOf(ballInstance), terrainBall);

    }

}
