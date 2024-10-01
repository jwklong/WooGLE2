package com.woogleFX.gameData.terrainTypes;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.fileImport.ObjectGOOParser;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.terrain._2_Terrain_Collection;
import com.worldOfGoo2.terrain._2_Terrain_TerrainType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TerrainTypeManager {

    private static final Map<String, _2_Terrain_TerrainType> terrainTypeMap = new HashMap<>();
    public static _2_Terrain_TerrainType getTerrainType(String id) {

        _2_Terrain_TerrainType terrainType = terrainTypeMap.get(id);
        if (terrainType != null) return terrainType;

        try {
            File itemFile = new File(FileManager.getGameDir(GameVersion.VERSION_WOG2) + "/res/terrain/terrain.wog2");
            _2_Terrain_Collection terrainType2 = ObjectGOOParser.read(_2_Terrain_Collection.class, Files.readString(itemFile.toPath()), "terrain");
            Stack<EditorObject> stack = new Stack<>();
            stack.addAll(terrainType2.getChildren());
            while (!stack.empty()) {
                EditorObject item = stack.pop();
                if (item instanceof _2_Terrain_TerrainType terrainType1) terrainTypeMap.put(terrainType1.getAttribute("name").stringValue(), terrainType1);
                item.update();
                stack.addAll(item.getChildren());
            }
            return terrainTypeMap.get(id);
        } catch (IOException e) {
            ErrorAlarm.show(e);
            return null;
        }

    }

}
