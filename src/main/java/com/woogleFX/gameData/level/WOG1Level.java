package com.woogleFX.gameData.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.ObjectUtil;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.worldOfGoo.level.Signpost;
import com.worldOfGoo.scene.Label;
import com.worldOfGoo.text.TextString;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/** A level from World of Goo 1.
 * Has scene, level, and resrc as different files. */
public class WOG1Level extends _Level {

    private final ArrayList<EditorObject> scene;
    public ArrayList<EditorObject> getScene() {
        return scene;
    }
    public EditorObject getSceneObject() {
        return scene.get(0);
    }

    private final ArrayList<EditorObject> level;
    public ArrayList<EditorObject> getLevel() {
        return level;
    }
    public EditorObject getLevelObject() {
        return level.get(0);
    }

    private final ArrayList<EditorObject> resrc;
    public ArrayList<EditorObject> getResrc() {
        return resrc;
    }
    public EditorObject getResrcObject() {
        return resrc.get(0);
    }

    private final ArrayList<EditorObject> addin;
    public ArrayList<EditorObject> getAddin() {
        return addin;
    }
    public EditorObject getAddinObject() {
        return addin.get(0);
    }

    private final ArrayList<EditorObject> text;
    public ArrayList<EditorObject> getText() {
        return text;
    }
    public EditorObject getTextObject() {
        return text.get(0);
    }


    public WOG1Level(ArrayList<EditorObject> scene,
                  ArrayList<EditorObject> level,
                  ArrayList<EditorObject> resrc,
                  ArrayList<EditorObject> addin,
                  ArrayList<EditorObject> text,
                  GameVersion version) {

        super(version);

        this.scene = scene;
        this.level = level;
        this.resrc = resrc;
        this.addin = addin;
        this.text = text;

        LevelManager.setLevel(this);

        for (EditorObject object : addin) object.getTreeItem().setExpanded(true);
        for (EditorObject object : text) object.getTreeItem().setExpanded(true);

        reAssignSetDefaultsToAllResources();
        cameraToMiddleOfLevel();

        for (EditorObject editorObject : scene)
            if (editorObject instanceof Label label) {
                tryToAddText(label.getAttribute("text").stringValue());
            }

        for (EditorObject editorObject : level)
            if (editorObject instanceof Signpost signpost) {
                tryToAddText(signpost.getAttribute("text").stringValue());
            }

    }


    private void tryToAddText(String id) {
        TextString textString;
        try {
            textString = ResourceManager.getText(null, id, getVersion());
        } catch (FileNotFoundException ignored) {
            return;
        }
        boolean notAlreadyHere = false;
        for (EditorObject object : text) {
            if (object instanceof TextString) {
                if (object.getAttribute("id").stringValue().equals(id)) {
                    notAlreadyHere = true;
                    break;
                }
            }
        }
        if (!notAlreadyHere) {
            ObjectUtil.deepClone(textString, text.get(0));
            text.add(textString);
        }
    }

}
