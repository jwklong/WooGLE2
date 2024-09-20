package com.woogleFX.file.fileImport;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.gameData.level.GameVersion;

import java.util.ArrayList;
import java.util.Stack;

/** Opens the custom World of Goo 2 file format. */
public class ObjectGOOParser {

    static class AssetObject {

    }

    static class AssetObjectAttribute {

        String name;
        AssetObject value;

        AssetObjectAttribute(String name, AssetObject value) {
            this.name = name;
            this.value = value;
        }

    }

    static class AssetStringObject extends AssetObject {

        String value;

        AssetStringObject(String value) {
            this.value = value;
        }

    }

    static class AssetDoubleObject extends AssetObject {

        double value;

        AssetDoubleObject(double value) {
            this.value = value;
        }

    }

    static class AssetAssetObject extends AssetObject {

        final EditorObject value;

        AssetAssetObject(EditorObject value) {
            this.value = value;
        }

    }

    static class AssetArrayObject extends AssetObject {

        final ArrayList<AssetObject> value;

        public AssetArrayObject(ArrayList<AssetObject> value) {
            this.value = value;
        }

    }


    public static AssetObject readString(Stack<String> tokens) {
        String token = tokens.remove(0);
        if (!token.equals("\"")) {
            tokens.remove(0);
        } else {
            token = "";
        }
        return new AssetStringObject(token);
    }


    public static AssetObject readList(Class<? extends EditorObject> type, Stack<String> tokens, EditorObject parent) {

        ArrayList<AssetObject> assetObjects = new ArrayList<>();
        while (true) {

            String token = tokens.remove(0);

            switch (token) {

                case "]" -> {
                    return new AssetArrayObject(assetObjects);
                }

                case "[" -> {
                    assetObjects.add(readList(type, tokens, parent));
                }

                case "\"" -> {
                    assetObjects.add(readString(tokens));
                }

                case "{" -> {
                    if (type != null) assetObjects.add(readAsset(type, tokens, parent));
                }

                case "," -> {

                }

                default -> {
                    assetObjects.add(new AssetStringObject(token));
                }

            }

        }

    }

    public static AssetAssetObject readAsset(Class<? extends EditorObject> type, Stack<String> tokens, EditorObject parent) {

        EditorObject asset = ObjectCreator.create2(type, parent, GameVersion.VERSION_WOG2);

        String name = null;

        while (true) {

            String token = tokens.remove(0);

            switch (token) {

                case "}" -> {
                    return new AssetAssetObject(asset);
                }

                case "[" -> {
                    Class<? extends EditorObject> typeClass = null;
                    for (EditorAttribute editorAttribute : asset.getAttributes()) {
                        if (editorAttribute.getChildAlias() != null && editorAttribute.getName().equals(name)) {
                            typeClass = editorAttribute.getChildAlias();
                            break;
                        }
                    }
                    String total = "";
                    for (AssetObject assetObject : ((AssetArrayObject) readList(typeClass, tokens, asset)).value) {
                        if (assetObject instanceof AssetStringObject assetStringObject) {
                            total += assetStringObject.value + ",";
                        } else if (assetObject instanceof AssetAssetObject assetAssetObject) {
                            //assetAssetObject.value.setParent(asset);
                            assetAssetObject.value.setTypeID(name);
                        }
                    }
                    if (!total.isEmpty()) {
                        if (name != null) {
                            asset.setAttribute2(name, total.substring(0, total.length() - 1));
                        }
                    }
                    name = null;
                }

                case "\"" -> {
                    if (name != null) {
                        asset.setAttribute2(name, ((AssetStringObject)readString(tokens)).value);
                        name = null;
                    } else {
                        name = tokens.remove(0);
                        tokens.remove(0);
                    }
                }

                case "{" -> {
                    Class<? extends EditorObject> typeClass = null;
                    for (EditorAttribute editorAttribute : asset.getAttributes()) {
                        if (editorAttribute.getChildAlias() != null && editorAttribute.getName().equals(name)) {
                            typeClass = editorAttribute.getChildAlias();
                            break;
                        }
                    }
                    if (typeClass != null) {
                        AssetAssetObject assetAssetObject = readAsset(typeClass, tokens, asset);
                        //assetAssetObject.value.setParent(asset);
                        assetAssetObject.value.setTypeID(name);
                    }
                    name = null;
                }

                case ":" -> {

                }

                case "," -> {

                }

                default -> {
                    if (name != null) {
                        asset.setAttribute2(name, token);
                    }
                    name = null;
                }

            }

        }

    }


    public static <T extends EditorObject> T read(Class<T> assetType, String text) {

        String currentWord = "";

        Stack<String> tokens = new Stack<>();

        for (int index = 0; index < text.length(); index++) {

            char c = text.charAt(index);

            if (c == '{' || c == '}' || c == '[' || c == ']' || c == ':' || c == ',' || c == '"') {
                if (!currentWord.isEmpty()) tokens.add(currentWord);
                currentWord = "";
                tokens.add(String.valueOf(c));
            } else {
                if (c != '\n' && c != '\t' && c != ' ') {
                    currentWord += c;
                }
            }

        }

        tokens.remove(0);

        AssetAssetObject asset = readAsset(assetType, tokens, null);

        return (T) asset.value;

    }

}
