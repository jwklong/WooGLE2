package com.woogleFX.gameData.environments;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.fileImport.ObjectGOOParser;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.environments._2_Environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class EnvironmentManager {

    private static final Map<String, _2_Environment> environmentMap = new HashMap<>();
    public static _2_Environment getEnvironment(String id) {

        _2_Environment environment = environmentMap.get(id);
        if (environment != null) return environment;

        try {
            File itemFile = new File(FileManager.getGameDir(GameVersion.VERSION_WOG2) + "/res/environments/" + id + ".wog2");
            _2_Environment environment2 = ObjectGOOParser.read(_2_Environment.class, Files.readString(itemFile.toPath()), "environment");
            Stack<EditorObject> stack = new Stack<>();
            stack.add(environment2);
            while (!stack.empty()) {
                EditorObject item = stack.pop();
                item.update();
                stack.addAll(item.getChildren());
            }
            environmentMap.put(id, environment2);
            return environment2;
        } catch (IOException e) {
            ErrorAlarm.show(e);
            return null;
        }

    }

}
