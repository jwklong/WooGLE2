package com.woogleFX.gameData.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.LevelManager;
import com.worldOfGoo2.level._2_Level;

import java.util.ArrayList;

/** A level from World of Goo 2.
 * What... is this? */
public class WOG2Level extends _Level {

    private final _2_Level level;
    public _2_Level getLevel() {
        return level;
    }


    private final ArrayList<EditorObject> objects;
    public ArrayList<EditorObject> getObjects() {
        return objects;
    }

    public WOG2Level(ArrayList<EditorObject> objects) {
        super(GameVersion.VERSION_WOG2);

        this.objects = objects;

        this.level = (_2_Level)objects.get(0);

        setCurrentlySelectedSection("Terrain");

        LevelManager.setLevel(this);

        //for (EditorObject object : objects) {
        //    object.update();
        //}

        setOffsetX(500);
        setZoom(10);

    }

}
