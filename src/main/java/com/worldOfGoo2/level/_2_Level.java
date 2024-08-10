package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.editorObjects.objectComponents.ImageComponent;
import com.woogleFX.editorObjects.objectComponents.RectangleComponent;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.engine.renderer.Depth;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.environments._2_Environment;
import com.worldOfGoo2.environments._2_Environment_Layer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.FileNotFoundException;

public class _2_Level extends EditorObject {

    public _2_Level(EditorObject parent) {
        super(parent, "Level", GameVersion.VERSION_WOG2);

        addAttribute("version", InputField._2_NUMBER).assertRequired();
        addAttribute("type", InputField._2_LEVEL_TYPE).assertRequired();
        addAttribute("forceFadeEOL", InputField._2_BOOLEAN);
        addAttribute("skin", InputField._2_SKIN);
        addAttribute("uuid", InputField._2_UUID).assertRequired();
        addAttribute("title", InputField._2_STRING).assertRequired();
        addAttribute("gameLevel", InputField._2_GAME_LEVEL);
        addAttribute("startingUID", InputField._2_UID);
        addAttribute("island", InputField._2_ISLAND_ID);
        addAttribute("environmentId", InputField._2_NUMBER).assertRequired();
        addAttribute("backgroundId", InputField._2_BACKGROUND_ID).assertRequired();

        addAttribute("gravity", InputField._2_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("gravity", "_2_Point");
        addAttributeAdapter("gravity", AttributeAdapter.pointAttributeAdapter(this, "gravity", "gravity"));

        addAttribute("boundsTopRight", InputField._2_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("boundsTopRight", "_2_Point");
        addAttributeAdapter("boundsTopRight", AttributeAdapter.pointAttributeAdapter(this, "boundsTopRight", "boundsTopRight"));

        addAttribute("boundsBottomLeft", InputField._2_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("boundsBottomLeft", "_2_Point");
        addAttributeAdapter("boundsBottomLeft", AttributeAdapter.pointAttributeAdapter(this, "boundsBottomLeft", "boundsBottomLeft"));

        addAttribute("initialCameraPos", InputField._2_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("initialCameraPos", "_2_Point");
        addAttributeAdapter("initialCameraPos", AttributeAdapter.pointAttributeAdapter(this, "initialCameraPos", "initialCameraPos"));

        addAttribute("initialCameraZoom", InputField._2_NUMBER).assertRequired();
        addAttribute("cameraAutoBounds", InputField._2_BOOLEAN).assertRequired();
        addAttribute("ballsRateRequired", InputField._2_NUMBER).assertRequired();
        addAttribute("musicId", InputField._2_MUSIC_ID).assertRequired();
        addAttribute("ambienceId", InputField._2_SOUND_ID).assertRequired();
        addAttribute("musicOffset", InputField._2_NUMBER).assertRequired();
        addAttribute("ambienceOffset", InputField._2_NUMBER).assertRequired();
        addAttribute("liquidScale", InputField._2_NUMBER);
        addAttribute("pretickSeconds", InputField._2_NUMBER).assertRequired();
        addAttribute("conduitSuckVolume", InputField._2_NUMBER);
        addAttribute("fireSfxVolume", InputField._2_NUMBER);

        addAttribute("balls", InputField._2_LIST_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("balls", "_2_Level_Ball");

        addAttribute("strands", InputField._2_LIST_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("strands", "_2_Level_Strand");

        addAttribute("terrainGroups", InputField._2_LIST_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("terrainGroups", "_2_Level_TerrainGroup");

        addAttribute("items", InputField._2_LIST_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("items", "_2_Level_Item");

        addAttribute("pins", InputField._2_LIST_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("pins", "_2_Level_Pin");

        addAttribute("initialCameraKeyframes", InputField._2_LIST_CHILD_HIDDEN).assertRequired();
        putAttributeChildAlias("initialCameraKeyframes", "_2_Level_CameraKeyFrame");

        addAttribute("terrainBalls", InputField._2_LIST_CHILD).assertRequired();
        putAttributeChildAlias("terrainBalls", "_2_Level_TerrainBall");

        addAttribute("levelEnvironmentEffects", InputField._2_LIST_STRING).assertRequired();
        addAttribute("enableTimeBugs", InputField._2_BOOLEAN).assertRequired();
        addAttribute("timebugMoves", InputField._2_NUMBER);

        String general = "version,type,uuid,title,gameLevel,startingUID,island,";
        String visuals = "?Visuals<environmentId,backgroundId,levelEnvironmentEffects,skin,liquidScale>";
        String sound = "?Sound<musicId,ambienceId,musicOffset,ambienceOffset,conduitSuckVolume,fireSfxVolume>";
        String camera = "?Camera<boundsTopRight,boundsBottomLeft,initialCameraPos,initialCameraZoom,cameraAutoBounds,forceFadeEOL>";
        String gameplay = "?Gameplay<gravity,ballsRateRequired,pretickSeconds,enableTimeBugs,timebugMoves>";
        setMetaAttributes(MetaEditorAttribute.parse(general + gameplay + visuals + sound + camera));


        setTypeID("_2_Level");

    }


    @Override
    public String[] getPossibleChildren() {
        return new String[] { "_2_Level_Ball", "_2_Level_Strand", "_2_Level_TerrainGroup", "_2_Level_Item", "_2_Level_Pin", "_2_Level_CameraKeyFrame", "_2_Level_TerrainBall" };
    }


    @Override
    public String[] getPossibleChildrenTypeIDs() {
        return new String[] { "balls", "strands", "terrainGroups", "items", "pins", "initialCameraKeyframes", "terrainBalls" };
    }


    @Override
    public void onLoaded() {
        super.onLoaded();

        EditorObject gravity = getChildren("gravity").get(0);
        setAttribute2("gravity", gravity.getAttribute("x").stringValue() + "," + gravity.getAttribute("y").stringValue());
        gravity.getAttribute("x").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("gravity", newValue + "," + getAttribute2("gravity").positionValue().getY()));
        gravity.getAttribute("y").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("gravity", getAttribute2("gravity").positionValue().getX() + "," + newValue));

        EditorObject boundsTopRight = getChildren("boundsTopRight").get(0);
        setAttribute2("boundsTopRight", boundsTopRight.getAttribute("x").stringValue() + "," + boundsTopRight.getAttribute("y").stringValue());
        boundsTopRight.getAttribute("x").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("boundsTopRight", newValue + "," + getAttribute2("boundsTopRight").positionValue().getY()));
        boundsTopRight.getAttribute("y").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("boundsTopRight", getAttribute2("boundsTopRight").positionValue().getX() + "," + newValue));

        EditorObject boundsBottomLeft = getChildren("boundsBottomLeft").get(0);
        setAttribute2("boundsBottomLeft", boundsBottomLeft.getAttribute("x").stringValue() + "," + boundsBottomLeft.getAttribute("y").stringValue());
        boundsBottomLeft.getAttribute("x").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("boundsBottomLeft", newValue + "," + getAttribute2("boundsBottomLeft").positionValue().getY()));
        boundsBottomLeft.getAttribute("y").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("boundsBottomLeft", getAttribute2("boundsBottomLeft").positionValue().getX() + "," + newValue));

        EditorObject initialCameraPos = getChildren("initialCameraPos").get(0);
        setAttribute2("initialCameraPos", initialCameraPos.getAttribute("x").stringValue() + "," + initialCameraPos.getAttribute("y").stringValue());
        initialCameraPos.getAttribute("x").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("initialCameraPos", newValue + "," + getAttribute2("initialCameraPos").positionValue().getY()));
        initialCameraPos.getAttribute("y").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("initialCameraPos", getAttribute2("initialCameraPos").positionValue().getX() + "," + newValue));

        addObjectComponent(new RectangleComponent() {
            public double getX() {
                double minx = boundsBottomLeft.getAttribute("x").doubleValue();
                double maxx = boundsTopRight.getAttribute("x").doubleValue();
                return (minx + maxx) / 2;
            }

            public void setX(double x) {
                double width = getWidth();
                boundsBottomLeft.setAttribute("x", x - width / 2);
                boundsTopRight.setAttribute("x", x + width / 2);
            }

            public double getY() {
                double miny = -boundsBottomLeft.getAttribute("y").doubleValue();
                double maxy = -boundsTopRight.getAttribute("y").doubleValue();
                return (miny + maxy) / 2;
            }

            public void setY(double y) {
                double height = getHeight();
                boundsBottomLeft.setAttribute("y", -y - height / 2);
                boundsTopRight.setAttribute("y", -y + height / 2);
            }

            public double getWidth() {
                double minx = boundsBottomLeft.getAttribute("x").doubleValue();
                double maxx = boundsTopRight.getAttribute("x").doubleValue();
                return Math.abs(maxx - minx);
            }

            public void setWidth(double width) {
                double x = getX();
                boundsBottomLeft.setAttribute("x", x - width / 2);
                boundsTopRight.setAttribute("x", x + width / 2);
            }

            public double getHeight() {
                double miny = -boundsBottomLeft.getAttribute("y").doubleValue();
                double maxy = -boundsTopRight.getAttribute("y").doubleValue();
                return Math.abs(maxy - miny);
            }

            public void setHeight(double height) {
                double y = getY();
                boundsBottomLeft.setAttribute("y", -y - height / 2);
                boundsTopRight.setAttribute("y", -y + height / 2);
            }

            public double getDepth() {
                return Depth.SCENE;
            }

            public double getEdgeSize() {
                return 0.1;
            }

            public Paint getBorderColor() {
                return new Color(0.0, 0.0, 0.0, 1.0);
            }

            public boolean isEdgeOnly() {
                return true;
            }

            public Paint getColor() {
                return new Color(0.0, 0.0, 0.0, 0.0);
            }

            public boolean isRotatable() {
                return false;
            }
        });

        updateObjectPositions();
        getAttribute("backgroundId").addChangeListener((observable, oldValue, newValue) -> updateObjectPositions());

    }


    private void updateObjectPositions() {

        clearObjectPositions();

        try {

            if (getAttribute("backgroundId").stringValue().isEmpty()) return;

            _2_Environment environment = ResourceManager.getEnvironment(null, getAttribute("backgroundId").stringValue(), GameVersion.VERSION_WOG2);

            for (EditorObject part : environment.getChildren()) if (part instanceof _2_Environment_Layer) {

                Image image = part.getAttribute("imageName").imageValue(null, GameVersion.VERSION_WOG2);

                double partRotation = 0;

                EditorObject boundsTopRight = getChildren("boundsTopRight").get(0);
                EditorObject boundsBottomLeft = getChildren("boundsBottomLeft").get(0);

                addObjectComponent(new ImageComponent() {
                    public double getX() {
                        return (boundsTopRight.getAttribute("x").doubleValue() + boundsBottomLeft.getAttribute("x").doubleValue()) / 2;
                    }
                    public double getY() {
                        return (-boundsTopRight.getAttribute("y").doubleValue() - boundsBottomLeft.getAttribute("y").doubleValue()) / 2;
                    }
                    public double getRotation() {
                        return partRotation;
                    }
                    public double getScaleX() {
                        double dx = boundsTopRight.getAttribute("x").doubleValue() - boundsBottomLeft.getAttribute("x").doubleValue();
                        return dx / image.getWidth();
                    }
                    public double getScaleY() {
                        double dy = boundsTopRight.getAttribute("y").doubleValue() - boundsBottomLeft.getAttribute("y").doubleValue();
                        return dy / image.getHeight();
                    }
                    public double getDepth() {
                        //System.out.println(part.getAttribute("depth").doubleValue());
                        //return part.getAttribute("depth").doubleValue();
                        return -100000;
                    }
                    public double getAlpha() {
                        return 1.0; // part.getAttribute("imageAlpha").doubleValue();
                    }
                    public Image getImage() {
                        return image;
                    }
                    public boolean isVisible() {
                        return LevelManager.getLevel().getVisibilitySettings().isShowSceneBGColor();
                    }
                    public boolean isResizable() {
                        return false;
                    }
                    public boolean isDraggable() {
                        return false;
                    }
                    public boolean isRotatable() {
                        return false;
                    }
                    public boolean isSelectable() {
                        return false;
                    }
                });

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
