package com.worldOfGoo2.util;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.file.FileManager;
import com.woogleFX.file.fileImport.ObjectGOOParser;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.terrainTypes.TerrainTypeManager;
import com.worldOfGoo2.level._2_Level_TerrainGroup;
import com.worldOfGoo2.misc._2_ImageID;
import com.worldOfGoo2.terrain.BaseSettings;
import com.worldOfGoo2.terrain._2_Terrain_Collection;
import com.worldOfGoo2.terrain._2_Terrain_TerrainType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TerrainHelper {

    public static final Map<String, Image> terrainImageMap = new HashMap<>();
    public static final Map<String, ImageView> terrainImageCache = new HashMap<>();
    public static final Map<Integer, Color> terrainColorCache = new HashMap<>();

    public static ImageView terrainPreviewImage(String itemType) {
        if (terrainImageCache.get(itemType) != null && terrainImageCache.get(itemType).getImage() != null) {
            return terrainImageCache.get(itemType);
        }
        try {
            String uuid = "";
            for (var entry : ItemHelper.terrainTypeNameMap.entrySet()) {
                if (entry.getValue().equals(itemType)) {
                    uuid = entry.getKey();
                    break;
                }
            }

            if (uuid.isEmpty()) {
                return null;
            }

            Image img = terrainImageMap.get(uuid);

            ImageView view = new ImageView();
            view.setFitWidth(32);
            view.setFitHeight(32);
            view.setImage(img);
            WritableImage image = new WritableImage(32, 32);
            view.snapshot(null, image);
            view.setImage(image);

            terrainImageCache.put(itemType, view);

            return view;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void buildImageMap() {
        try {
            terrainImageCache.clear();
            File itemFile = new File(FileManager.getGameDir(GameVersion.VERSION_WOG2) + "/res/terrain/terrain.wog2");
            ArrayList<EditorObject> items = ObjectGOOParser.read(_2_Terrain_Collection.class, Files.readString(itemFile.toPath()), "terrain").getChildren();
            for (EditorObject item : items) {
                ArrayList<_2_ImageID> images = new ArrayList<>();

                for (var child : item.getChildren()) {
                    if (child instanceof BaseSettings baseSettings) {
                        for (var child2 : baseSettings.getChildren()) {
                            if (child2 instanceof _2_ImageID terrainImage) {
                                images.add(terrainImage);
                            }
                        }
                    }
                }

                if (!images.isEmpty()) {
                    try { // Safely discard any images that don't have a valid resource
                        if (images.get(0).getAttribute("imageId").imageValue(null, GameVersion.VERSION_WOG2) != null) {
                            TerrainHelper.terrainImageMap.put(item.getAttribute("uuid").stringValue(), images.get(0).getAttribute("imageId").imageValue(null, GameVersion.VERSION_WOG2));
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Paint averageColor(int index) {
        if (terrainColorCache.get(index) != null) {
            return terrainColorCache.get(index);
        }
        if (AssetManager.getAsset() instanceof WOG2Level level) {
            if (level.getLevel().getChildren("terrainGroups").isEmpty()) return new Color(0.5, 0.5, 0.5, 1);
            EditorObject terrainGroup = level.getLevel().getChildren("terrainGroups").get(index);
            var type = terrainGroup.getAttribute("type").stringValue();
            String uuid = "";
            for (var entry : ItemHelper.terrainTypeNameMap.entrySet()) {
                if (entry.getValue().equals(type)) {
                    uuid = entry.getKey();
                    break;
                }
            }
            Image image = terrainImageMap.get(uuid);
            if (image != null) {
                var color = image.getPixelReader().getColor((int)image.getWidth() / 2, (int)image.getHeight() / 2);
                terrainColorCache.put(index, color);
                return image.getPixelReader().getColor((int)image.getWidth() / 2, (int)image.getHeight() / 2);
            }
        }
        return new Color(0.5, 0.5, 0.5, 1);
    }


    public static Image buildTerrainImage(_2_Level_TerrainGroup terrainGroup) {

        String terrainType = terrainGroup.getAttribute("typeUuid").stringValue();

        _2_Terrain_TerrainType terrain = TerrainTypeManager.getTerrainType(terrainType);

        BaseSettings baseSettings = (BaseSettings) terrain.getChildren("baseSettings").get(0);

        String imageId = baseSettings.getChildren("image").get(0).getAttribute("imageId").stringValue();
        Image image;
        try {
            image = ResourceManager.getImage(null, imageId, GameVersion.VERSION_WOG2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return image;

    }
    
    public static <T> Set<T> subtract(T[] arr1, List<T> arr2){
        HashSet<T> map = new HashSet<>();
        for (int i = 0; i < arr1.length; i++)
            map.add(arr1[i]);
    
        for(int i = 0; i < arr2.size(); i++)
            if(map.contains(arr2.get(i)))
                map.remove(arr2.get(i));
        
        return map;
    }

}
