package com.woogleFX.editorObjects.objectCreators;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.objectComponents.ObjectComponent;
import com.woogleFX.engine.fx.hierarchy.FXHierarchy;
import com.woogleFX.engine.fx.FXPropertiesView;
import com.woogleFX.engine.SelectionManager;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.editorObjects.ObjectManager;
import com.woogleFX.engine.undoHandling.UndoManager;
import com.woogleFX.engine.undoHandling.userActions.ObjectCreationAction;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.level._Level;
import com.worldOfGoo.addin.Addin;
import com.worldOfGoo.level.*;
import com.worldOfGoo.resrc.ResourceManifest;
import com.worldOfGoo.resrc.ResrcImage;
import com.worldOfGoo.resrc.SetDefaults;
import com.worldOfGoo.resrc.Sound;
import com.worldOfGoo.scene.*;
import com.worldOfGoo.text.TextString;
import com.worldOfGoo.text.TextStrings;
import com.worldOfGoo2.level._2_Level_BallInstance;
import com.worldOfGoo2.level._2_Level_TerrainBall;
import com.worldOfGoo2.util.BallInstanceHelper;
import javafx.geometry.Point2D;

import java.util.HashSet;
import java.util.Set;

public class ObjectAdder {


    public static void autoPipe() {
        // TODO add undo events for the whole pipe

        WOG1Level level = (WOG1Level)LevelManager.getLevel();

        /* Identify the level exit. If there is none, don't auto pipe. */
        for (EditorObject EditorObject : level.getLevel().toArray(new EditorObject[0])) {
            if (EditorObject instanceof Levelexit levelexit) {

                /* Calculate the point closest to the scene from the level exit. */
                double distanceToLeft = Math
                        .abs(levelexit.getAttribute("pos").positionValue().getX() - level.getSceneObject().getAttribute("minx").doubleValue());
                double distanceToRight = Math
                        .abs(levelexit.getAttribute("pos").positionValue().getX() - level.getSceneObject().getAttribute("maxx").doubleValue());
                double distanceToTop = Math
                        .abs(levelexit.getAttribute("pos").positionValue().getY() - level.getSceneObject().getAttribute("miny").doubleValue());
                double distanceToBottom = Math
                        .abs(levelexit.getAttribute("pos").positionValue().getY() - level.getSceneObject().getAttribute("maxy").doubleValue());

                Point2D closestPoint;
                if (distanceToLeft <= distanceToRight && distanceToLeft <= distanceToTop
                        && distanceToLeft <= distanceToBottom) {
                    closestPoint = new Point2D(level.getSceneObject().getAttribute("minx").doubleValue(),
                            levelexit.getAttribute("pos").positionValue().getY());
                } else if (distanceToRight <= distanceToTop && distanceToRight <= distanceToBottom) {
                    closestPoint = new Point2D(level.getSceneObject().getAttribute("maxx").doubleValue(),
                            levelexit.getAttribute("pos").positionValue().getY());
                } else if (distanceToTop <= distanceToBottom) {
                    closestPoint = new Point2D(levelexit.getAttribute("pos").positionValue().getX(),
                            level.getSceneObject().getAttribute("miny").doubleValue());
                } else {
                    closestPoint = new Point2D(levelexit.getAttribute("pos").positionValue().getX(),
                            level.getSceneObject().getAttribute("maxy").doubleValue());
                }

                /* Delete the old pipe. */
                for (EditorObject maybePipe : level.getLevel().toArray(new EditorObject[0])) {
                    if (maybePipe instanceof Pipe) {
                        ObjectManager.deleteItem(level, maybePipe, false);
                    }
                }

                /*
                 * Create a pipe with a vertex at the level exit and at the scene intersection.
                 */
                EditorObject pipe = (EditorObject)ObjectCreator.create("pipe", level.getLevelObject(), level.getVersion());
                EditorObject vertex1 = (EditorObject)ObjectCreator.create("Vertex", pipe, level.getVersion());
                if (vertex1 == null) return;
                vertex1.setAttribute("x", levelexit.getAttribute("pos").positionValue().getX());
                vertex1.setAttribute("y", levelexit.getAttribute("pos").positionValue().getY());

                EditorObject vertex2 = (EditorObject)ObjectCreator.create("Vertex", pipe, level.getVersion());
                if (vertex2 == null) return;
                vertex2.setAttribute("x", closestPoint.getX());
                vertex2.setAttribute("y", closestPoint.getY());

                level.getLevel().add(pipe);
                level.getLevel().add(vertex1);
                level.getLevel().add(vertex2);

            }
        }
    }


    public static EditorObject addObject(String name) {
        return addObject(name, null);
    }


    public static EditorObject addObject(String name, EditorObject parent) {

        if (LevelManager.getLevel().getVersion() == GameVersion.VERSION_WOG1_OLD || LevelManager.getLevel().getVersion() == GameVersion.VERSION_WOG1_NEW) {

            WOG1Level level = (WOG1Level) LevelManager.getLevel();
            if (level == null) return null;

            if (parent == null) parent = switch (name) {

                case "linearforcefield", "radialforcefield", "particles",
                        "SceneLayer", "buttongroup", "button", "circle",
                        "rectangle", "hinge", "compositegeom", "label",
                        "line", "motor", "slider" -> level.getSceneObject();

                case "Strand", "camera", "poi", "music", "loopsound",
                        "endoncollision", "endonnogeom", "endonmessage",
                        "targetheight", "fire", "levelexit", "pipe",
                        "signpost", "Vertex" -> level.getLevelObject();

                case "resrcimage", "sound", "setdefaults" -> level.getResrcObject().getChildren().get(0);

                case "textstring" -> level.getTextObject();

                default -> null;

            };

            EditorObject obj = ObjectCreator.create(name, parent, level.getVersion());
            adjustObject(obj);

            EditorObject absoluteParent = parent;
            while (absoluteParent != null && absoluteParent.getParent() != null)
                absoluteParent = absoluteParent.getParent();

            if (absoluteParent instanceof Scene) level.getScene().add(obj);
            else if (absoluteParent instanceof Level) level.getLevel().add(obj);
            else if (absoluteParent instanceof ResourceManifest) level.getResrc().add(obj);
            else if (absoluteParent instanceof Addin) level.getAddin().add(obj);
            else if (absoluteParent instanceof TextStrings) level.getText().add(obj);

            addAnything(obj);

            return obj;

        } else {

            return null;

        }

    }


    public static EditorObject addObject2(String name, String typeID, EditorObject parent) {

        WOG2Level level = (WOG2Level) LevelManager.getLevel();
        if (level == null) return null;

        EditorObject obj = ObjectCreator.create2(name, parent, level.getVersion());
        adjustObject(obj);
        obj.setTypeID(typeID);

        level.getObjects().add(obj);

        addAnything(obj);

        return obj;

    }


    public static void addAnything(EditorObject obj) {
        adjustObject(obj);

        FXHierarchy.getHierarchy().getSelectionModel().select(obj.getTreeItem());
        obj.update();
        EditorObject[] selected = new EditorObject[]{obj};
        LevelManager.getLevel().setSelected(selected);
        FXPropertiesView.changeTableView(selected);

        if (obj instanceof _2_Level_BallInstance ballInstance)
            BallInstanceHelper.addTerrainBall((WOG2Level)LevelManager.getLevel(), ballInstance);

        UndoManager.registerChange(new ObjectCreationAction(obj, obj.getParent().getChildren().indexOf(obj)));
    }

    /**
     * Changes the id attribute of a BallInstance to give it a unique ID.
     * IDs are given in the form of "goo[number]".
     *
     * @param obj The BallInstance to modify.
     */
    public static void fixGooBall(EditorObject obj) {

        if (LevelManager.getLevel() instanceof WOG1Level level) {

            // Create an array to store which id numbers are already taken by BallInstances.
            boolean[] taken = new boolean[level.getLevel().size()];

            // Loop over all BallInstances in the level.
            for (EditorObject ball : level.getLevel()) {
                if (ball instanceof BallInstance) {

                    // Check if the ball's ID is "goo[number]".
                    // If it is, flag that number as already taken.
                    String id = ball.getAttribute("id").stringValue();
                    if (id.length() > 3 && id.startsWith("goo")) {
                        try {
                            taken[Integer.parseInt(id.substring(3))] = true;
                        } catch (Exception ignored) {
                        }
                    }
                }
            }

            // Find the smallest available number to use as an ID and set the ball's ID
            // attribute accordingly.
            int count = 0;
            while (taken[count]) {
                count++;
            }
            obj.setAttribute("id", "goo" + count);

        } else if (LevelManager.getLevel() instanceof WOG2Level level) {

            // Create an array to store which id numbers are already taken by BallInstances.
            Set<String> taken = new HashSet<>();

            // Loop over all BallInstances in the level.
            for (EditorObject ball : level.getObjects()) {
                if (ball instanceof _2_Level_BallInstance) {

                    // Check if the ball's ID is "goo[number]".
                    // If it is, flag that number as already taken.
                    String id = ball.getAttribute("uid").stringValue();
                    taken.add(id);

                }
            }

            // Find the smallest available number to use as an ID and set the ball's ID
            // attribute accordingly.
            int count = 0;
            while (taken.contains(String.valueOf(count))) {
                count++;
            }
            obj.setAttribute("uid", count);

        }

    }

    /**
     * Changes the id attribute of a text string to give it a unique ID.
     * IDs are given in the form of "TEXT_[level name]_STR[number]".
     *
     * @param obj The string to modify.
     */
    public static void fixString(EditorObject obj) {

        WOG1Level level = (WOG1Level)LevelManager.getLevel();

        // Create an array to store which id numbers are already taken by strings.
        boolean[] taken = new boolean[level.getText().size()];

        // Loop over all text strings in the level.
        for (EditorObject string : level.getText()) {
            if (string instanceof TextString) {

                // Check if the string's ID is "TEXT_[level name]_STR[number]".
                // If it is, flag that number as already taken.
                String id = string.getAttribute("id").stringValue();
                if (id.length() > 9 + level.getLevelName().length()
                        && id.startsWith("TEXT_" + level.getLevelName().toUpperCase() + "_STR")) {
                    try {
                        taken[Integer.parseInt(id.substring(9 + level.getLevelName().length()))] = true;
                    } catch (Exception ignored) {
                    }
                }
            }
        }

        // Find the smallest available number to use as an ID and set the string's ID
        // attribute accordingly.
        int count = 0;
        while (taken[count]) {
            count++;
        }
        obj.setAttribute("id", "TEXT_" + level.getLevelName().toUpperCase() + "_STR" + count);
    }


    public static void adjustObjectLocation(EditorObject object) {

        _Level level = LevelManager.getLevel();

        // Create the object at the mouse position
        double objectX = (SelectionManager.getMouseX() - level.getOffsetX()) / level.getZoom();
        double objectY = (SelectionManager.getMouseY() - level.getOffsetY()) / level.getZoom();

        if (object.getObjectComponents().length > 0) {
            ObjectComponent objectComponent = object.getObjectComponents()[0];
            objectComponent.setX(objectX);
            objectComponent.setY(objectY);
        }

    }


    private static void adjustObject(EditorObject object) {

        adjustObjectLocation(object);

        if (LevelManager.getLevel() instanceof WOG2Level) return;

        WOG1Level level = (WOG1Level)LevelManager.getLevel();

        if (object instanceof Rectangle rectangle) {
            rectangle.setAttribute("static", true);
        } else if (object instanceof Circle circle) {
            circle.setAttribute("static", true);
        } else if (object instanceof Compositegeom compositegeom) {
            compositegeom.setAttribute("static", true);
        } else if (object instanceof TextString textString) {
            textString.setAttribute("id", "TEXT_" + level.getLevelName().toUpperCase() + "_STR0");
            textString.setAttribute("text", "");
            fixString(textString);
        } else if (object instanceof ResrcImage resrcImage) {
            resrcImage.setAttribute("id", "IMAGE_SCENE_" + level.getLevelName().toUpperCase() + "_IMG0");
            resrcImage.setAttribute("path", "");
        } else if (object instanceof Sound sound) {
            sound.setAttribute("id", "SOUND_LEVEL_" + level.getLevelName().toUpperCase() + "_SND0");
            sound.setAttribute("path", "");
        } else if (object instanceof SetDefaults setDefaults) {
            setDefaults.setAttribute("path", "./");
            setDefaults.setAttribute("idprefix", "");
        }

    }

}
