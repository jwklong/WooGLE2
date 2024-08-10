package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.editorObjects.objectComponents.CircleComponent;
import com.woogleFX.editorObjects.objectComponents.ImageComponent;
import com.woogleFX.editorObjects.objectComponents.RectangleComponent;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.engine.fx.FXEditorButtons;
import com.woogleFX.gameData.ball.AtlasManager;
import com.woogleFX.gameData.ball.BallManager;
import com.woogleFX.gameData.ball._2Ball;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.level.levelOpening.LevelLoader;
import com.worldOfGoo.level.Strand;
import com.worldOfGoo2.ball._2_Ball_Image;
import com.worldOfGoo2.ball._2_Ball_Part;
import com.worldOfGoo2.util.BallInstanceHelper;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.image.BufferedImage;
import java.util.*;

public class _2_Level_BallInstance extends EditorObject {

    private _2Ball ball = null;
    public _2Ball getBall() {
        return ball;
    }

    private static final Map<Integer, String> map = new HashMap<>();
    private static final Map<String, Integer> map2 = new HashMap<>();

    static {

        map.put(1, "Common");
        map.put(2, "CommonAlbino");
        map.put(3, "Ivy");
        map.put(4, "Balloon");
        map.put(5, "Goolf_Single");
        map.put(6, "Anchor");
        map.put(7, "Launcher_L2B");
        map.put(8, "GooProduct");
        map.put(9, "Thruster");
        map.put(10, "Terrain");
        map.put(11, "Balloon_Eye");
        map.put(12, "Conduit");
        map.put(13, "Launcher_L2L");
        map.put(14, "GooProduct_White");
        map.put(15, "Grow");
        map.put(16, "BombSticky");
        map.put(17, "Rope");
        map.put(18, "Bouncy");
        map.put(19, "Fish");
        map.put(20, "TimeBug");
        map.put(23, "MatchStick");
        //map.put(24, "MatchStick");
        map.put(25, "Fireworks");
        map.put(26, "LightBall");
        map.put(27, "ThisWayUp_Bit");
        map.put(28, "ThisWayUp_Bit_Bit");
        map.put(29, "Adapter");
        map.put(30, "Winch");
        map.put(32, "Shrink");
        map.put(33, "Jelly");
        map.put(34, "Goolf");
        map.put(35, "ThisWayUp");
        map.put(36, "LiquidLevelExit");
        map.put(37, "Eye");
        map.put(38, "UtilAttachWalkable");

        map2.put("Common", 1);
        map2.put("CommonAlbino", 2);
        map2.put("Ivy", 3);
        map2.put("Balloon", 4);
        map2.put("Goolf_Single", 5);
        map2.put("Anchor", 6);
        map2.put("Launcher_L2B", 7);
        map2.put("GooProduct", 8);
        map2.put("Thruster", 9);
        map2.put("Terrain", 10);
        map2.put("Balloon_Eye", 11);
        map2.put("Conduit", 12);
        map2.put("Launcher_L2L", 13);
        map2.put("GooProduct_White", 14);
        map2.put("Grow", 15);
        map2.put("BombSticky", 16);
        map2.put("Rope", 17);
        map2.put("Bouncy", 18);
        map2.put("Fish", 19);
        map2.put("TimeBug", 20);
        map2.put("MatchStick", 23);
        //map2.put("MatchStick", 24);
        map2.put("Fireworks", 25);
        map2.put("LightBall", 26);
        map2.put("ThisWayUp_Bit", 27);
        map2.put("ThisWayUp_Bit_Bit", 28);
        map2.put("Adapter", 29);
        map2.put("Winch", 30);
        map2.put("Shrink", 32);
        map2.put("Jelly", 33);
        map2.put("Goolf", 34);
        map2.put("ThisWayUp", 35);
        map2.put("LiquidLevelExit", 36);
        map2.put("Eye", 37);
        map2.put("UtilAttachWalkable", 38);

    }


    private final long randomSeed;


    public _2_Level_BallInstance(EditorObject parent) {
        super(parent, "BallInstance", GameVersion.VERSION_WOG2);

        addAttribute("typeEnum", InputField._2_BALL_TYPE);
        addAttribute("uid", InputField._2_UID);

        addAttribute("pos", InputField._2_CHILD_HIDDEN);
        putAttributeChildAlias("pos", "_2_Point");

        addAttribute("angle", InputField._2_NUMBER);

        addAttribute("terrainGroup", InputField._2_TERRAIN_GROUP_TYPE_INDEX);

        addAttribute("discovered", InputField._2_BOOLEAN);
        addAttribute("floatingWhileAsleep", InputField._2_BOOLEAN);
        addAttribute("interactive", InputField._2_BOOLEAN);
        addAttribute("wakeWithLiquid", InputField._2_BOOLEAN);
        addAttribute("exitPipeAlert", InputField._2_BOOLEAN);
        addAttribute("affectsAutoBounds", InputField._2_BOOLEAN);
        addAttribute("launcherLifespanMin", InputField._2_NUMBER);
        addAttribute("launcherLifespanMax", InputField._2_NUMBER);
        addAttribute("launcherForceFactor", InputField._2_NUMBER);
        addAttribute("launcherCanUseBalls", InputField._2_BOOLEAN);
        addAttribute("launcherKnockbackFactor", InputField._2_NUMBER);
        addAttribute("launcherMaxActive", InputField._2_NUMBER);
        addAttribute("launcherBallTypeToGenerate", InputField._2_BALL_TYPE);
        addAttribute("thrustForce", InputField._2_NUMBER);
        addAttribute("maxVelocity", InputField._2_NUMBER);
        addAttribute("stiffness", InputField._2_NUMBER);
        addAttribute("filled", InputField._2_BOOLEAN);
        addAttribute("detonationRadius", InputField._2_NUMBER);
        addAttribute("detonationForce", InputField._2_NUMBER);

        randomSeed = (long)(Math.random() * 10000000);

        String generalStuff = "typeEnum,uid,pos,angle,discovered,terrainGroup,";
        String moreSpecific = "?Interactions<floatingWhileAsleep,interactive,wakeWithLiquid,exitPipeAlert,affectsAutoBounds>";
        String other = "?Properties<thrustForce,maxVelocity,stiffness,filled,detonationRadius,detonationForce>";
        String launcher = "?Launcher Settings<launcherLifespanMin,launcherLifespanMax,launcherForceFactor,launcherCanUseBalls,launcherKnockbackFactor,launcherMaxActive,launcherBallTypeToGenerate>";
        setMetaAttributes(MetaEditorAttribute.parse(generalStuff + moreSpecific + launcher + other));


        EditorObject obj = this;

        addAttributeAdapter("pos", AttributeAdapter.pointAttributeAdapter(this, "pos", "pos"));

        EditorAttribute typeAttribute = new EditorAttribute("type", InputField._2_BALL_TYPE, obj);
        typeAttribute.addChangeListener((observable, oldValue, newValue) -> setType(newValue));
        addAttributeAdapter("typeEnum", new AttributeAdapter("type") {

            @Override
            public EditorAttribute getValue() {
                if (getAttribute2("typeEnum").stringValue().isEmpty()) return typeAttribute;
                typeAttribute.setValue(map.getOrDefault(getAttribute2("typeEnum").intValue(), "(None)"));
                return typeAttribute;
            }

            @Override
            public void setValue(String value) {
                typeAttribute.setValue(value);
                setAttribute2("typeEnum", map2.getOrDefault(value, 0));
            }

        });

        addAttributeAdapter("terrainGroup", new AttributeAdapter("terrainGroup") {

            @Override
            public EditorAttribute getValue() {
                WOG2Level level = (WOG2Level) LevelManager.getLevel();
                EditorObject terrainBall = level.getLevel().getChildren("terrainBalls").get(level.getLevel().getChildren("balls").indexOf(obj));
                EditorAttribute temp = new EditorAttribute("terrainGroup", InputField._2_BALL_TYPE, obj);
                temp.setValue(terrainBall.getAttribute("group").stringValue());
                return temp;
            }

            @Override
            public void setValue(String value) {
                WOG2Level level = (WOG2Level) LevelManager.getLevel();
                EditorObject terrainBall = level.getLevel().getChildren("terrainBalls").get(level.getLevel().getChildren("balls").indexOf(obj));
                setAttribute2("terrainGroup", value);
                terrainBall.setAttribute("group", value);
            }

        });

    }


    @Override
    public String getName() {
        String id = getAttribute("uid").stringValue();
        String type = "";//getAttribute("type").stringValue();
        return id + ", " + type;
    }


    private void updateStrands() {

        if (LevelManager.getLevel() == null) return;

        for (EditorObject object : ((WOG2Level)LevelManager.getLevel()).getObjects()) if (object instanceof _2_Level_Strand strand) {

            String id = getAttribute("uid").stringValue();
            String gb1 = strand.getAttribute("ball1UID").stringValue();
            String gb2 = strand.getAttribute("ball2UID").stringValue();

            if (id.equals(gb1)) strand.setGoo1(this);

            if (id.equals(gb2)) strand.setGoo2(this);

        }

        refreshObjectPositions();

    }


    private void setType(String type) {

        if (LevelManager.getLevel() == null) return;

        this.ball = BallManager.get2Ball(type, getVersion());
        if (ball == null) {
            if (!LevelLoader.failedResources.contains("Ball: " + getAttribute("type").stringValue() + " (version " + getVersion() + ")")) {
                LevelLoader.failedResources.add("Ball: " + getAttribute("type").stringValue() + " (version " + getVersion() + ")");
            }
        }

        String id = getAttribute("uid").stringValue();
        for (EditorObject object : ((WOG2Level)LevelManager.getLevel()).getObjects()) if (object instanceof _2_Level_Strand strand) {
            String gb1 = strand.getAttribute("ball1UID").stringValue();
            String gb2 = strand.getAttribute("ball2UID").stringValue();
            if (gb1.equals(id) || gb2.equals(id)) {
                //strand.setStrand(null);
                strand.update();
            }
        }

        refreshObjectPositions();

    }


    @Override
    public void onLoaded() {
        super.onLoaded();

        EditorObject pos = getChildren("pos").get(0);
        setAttribute2("pos", pos.getAttribute("x").stringValue() + "," + pos.getAttribute("y").stringValue());
        pos.getAttribute("x").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("pos", newValue + "," + getAttribute2("pos").positionValue().getY()));
        pos.getAttribute("y").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("pos", getAttribute2("pos").positionValue().getX() + "," + newValue));

    }


    @Override
    public void update() {

        setType(getAttribute("type").stringValue());

        updateStrands();

    }


    public void refreshObjectPositions() {

        clearObjectPositions();

        if (ball != null) {
            int i = 0;

            ArrayList<_2_Ball_Image> images = new ArrayList<>();
            for (EditorObject editorObject : ball.getObjects()) if (editorObject instanceof _2_Ball_Part && editorObject.getAttribute("name").stringValue().equals(ball.getObjects().get(0).getChildren("bodyPart").get(0).getAttribute("partName").stringValue())) for (EditorObject child : editorObject.getChildren())
                if (child instanceof _2_Ball_Image ball_image) images.add(ball_image);

            double _scaleX = 1;
            double _scaleY = 1;
            if (!images.isEmpty()) {

                String imageString = images.get(0).getChildren().get(0).getAttribute("imageId").stringValue();

                BufferedImage image = AtlasManager.atlas.get(imageString);

                int _width = image.getWidth();
                int _height = image.getHeight();

                double width = ball.getObjects().get(0).getAttribute("width").doubleValue();
                double height = ball.getObjects().get(0).getAttribute("height").doubleValue();

                _scaleX = width / _width;
                _scaleY = height / _height;

            }

            Image image = BallInstanceHelper.createBallImageWoG2(this, ball, _scaleX, _scaleY, new Random(randomSeed));

            EditorObject pos = getChildren("pos").get(0);

            double final_scaleX = _scaleX;
            double final_scaleY = _scaleY;
            addObjectComponent(new ImageComponent() {
                public double getX() {
                    return pos.getAttribute("x").doubleValue();
                }
                public void setX(double x) {
                    pos.setAttribute("x", x);
                }
                public double getY() {
                    return -pos.getAttribute("y").doubleValue();
                }
                public void setY(double y) {
                    pos.setAttribute("y", -y);
                }
                public double getRotation() {
                    return -Math.toRadians(getAttribute("angle").doubleValue());
                }
                public void setRotation(double rotation) {
                    setAttribute("angle", -Math.toDegrees(rotation));
                }
                public double getScaleX() {
                    return final_scaleX;
                }
                public double getScaleY() {
                    return final_scaleY;
                }
                public double getDepth() {
                    return 0.000001;
                }
                public Image getImage() {
                    return image;
                }
                public boolean isVisible() {
                    return !getAttribute("type").stringValue().equals("Terrain") && LevelManager.getLevel().getVisibilitySettings().getShowGoos() == 2;
                }
                public boolean isResizable() {
                    return false;
                }
            });

        }

        boolean isCircle = true; //ball == null || ball.getShapeType().equals("circle");

        EditorObject pos = getChildren("pos").get(0);

        if (isCircle) addObjectComponent(new CircleComponent() {
            public double getX() {
                return pos.getAttribute("x").doubleValue();
            }
            public void setX(double x) {
                pos.setAttribute("x", x);
            }
            public double getY() {
                return -pos.getAttribute("y").doubleValue();
            }
            public void setY(double y) {
                pos.setAttribute("y", -y);
            }
            public double getRotation() {
                return -Math.toRadians(getAttribute("angle").doubleValue());
            }
            public void setRotation(double rotation) {
                setAttribute("angle", -Math.toDegrees(rotation));
            }
            public double getRadius() {
                return 0.2;
                // return ball.getShapeSize() / 2;
            }
            public double getEdgeSize() {
                return 0.05;
            }
            public boolean isEdgeOnly() {
                return true;
            }
            public Paint getBorderColor() {
                if (ball == null) {
                    return new Color(0.5, 0.25, 0.25, 1.0);
                } else {
                    return new Color(0.5 ,0.5, 0.5, 1);
                }
            }
            public Paint getColor() {
                return new Color(0, 0, 0, 0);
            }
            public double getDepth() {
                return 0.000001;
            }
            public boolean isVisible() {
                return (ball == null || getAttribute("type").stringValue().equals("Terrain") && visibilityFunction()) || LevelManager.getLevel().getVisibilitySettings().getShowGoos() == 1;
            }
            public boolean isResizable() {
                return false;
            }
        });

        else addObjectComponent(new RectangleComponent() {
            public double getX() {
                return pos.getAttribute("x").doubleValue();
            }

            public void setX(double x) {
                double y = pos.getAttribute("y").doubleValue();
                pos.setAttribute("x", x + "," + y);
            }

            public double getY() {
                return -pos.getAttribute("y").doubleValue();
            }

            public void setY(double y) {
                pos.setAttribute("y", -y);
            }

            public double getRotation() {
                return -Math.toRadians(getAttribute("angle").doubleValue());
            }

            public void setRotation(double rotation) {
                setAttribute("angle", -Math.toDegrees(rotation));
            }

            public double getWidth() {
                return 0.2;
                //return ball.getShapeSize();
            }

            public double getHeight() {
                return 0.2;
                //return ball.getShapeSize2();
            }

            public double getEdgeSize() {
                return 0.05;
            }

            public boolean isEdgeOnly() {
                return true;
            }

            public Paint getBorderColor() {
                if (ball == null) {
                    return new Color(0.5, 0.25, 0.25, 1.0);
                } else {
                    return new Color(0.5, 0.5, 0.5, 1);
                }
            }

            public Paint getColor() {
                return new Color(0, 0, 0, 0);
            }

            public double getDepth() {
                return 0.000001;
            }

            public boolean isVisible() {
                return (ball == null || getAttribute("type").stringValue().equals("Terrain") && visibilityFunction()) || LevelManager.getLevel().getVisibilitySettings().getShowGoos() == 1;
            }

            public boolean isResizable() {
                return false;
            }
        });

    }


    public boolean visibilityFunction() {
        if (LevelManager.getLevel().getVisibilitySettings().getShowGoos() == 0) return false;
        if (!getAttribute("type").stringValue().equals("Terrain")) return true;
        String selected = ((ComboBox<String>) FXEditorButtons.buttonViewTerrainGroup.getGraphic()).getSelectionModel().getSelectedItem();
        return (selected.equals("All") || selected.equals(getAttribute("terrainGroup").stringValue()));
    }

}

