package com.woogleFX.gameData.items;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.gui.alarms.ErrorAlarm;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.fileImport.ObjectGOOParser;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.items._2_Item;
import com.worldOfGoo2.items._2_Item_Collection;
import com.worldOfGoo2.util.ItemHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ItemManager {

    public static final Map<String, _2_Item> itemMap = new HashMap<>();
    public static _2_Item getItem(String id) {

        _2_Item item = itemMap.get(id);
        if (item != null) return item;

        try {
            for (File itemFile : new File(FileManager.getGameDir(GameVersion.VERSION_WOG2) + "/res/items").listFiles())
                if (itemFile.getName().endsWith(".wog2")) {
                _2_Item_Collection item2 = ObjectGOOParser.read(_2_Item_Collection.class, Files.readString(itemFile.toPath()), "items");
                Stack<EditorObject> stack = new Stack<>();
                stack.addAll(item2.getChildren());
                while (!stack.empty()) {
                    EditorObject item3 = stack.pop();
                    item3.update();
                    stack.addAll(item3.getChildren());
                }
                ItemHelper.itemNameMap.put(item2.getChildren().get(0).getAttribute("uuid").stringValue(), item2.getChildren().get(0).getAttribute("name").stringValue());
                itemMap.put(item2.getChildren().get(0).getAttribute("name").stringValue(), (_2_Item) item2.getChildren().get(0));
            }
            System.out.println(ItemHelper.itemNameMap);
            return itemMap.get(id);
        } catch (IOException e) {
            ErrorAlarm.show(e);
            return null;
        }

    }

}
