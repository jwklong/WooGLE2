package com.woogleFX.file.fileExport;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.objectCreators.ObjectCreator;
import com.woogleFX.gameData.level.GameVersion;
import com.worldOfGoo2.level._2_Level_TerrainBall;

import java.util.ArrayList;

public class GOOWriter {

    public static void recursiveGOOExport(StringBuilder exportBuilder, EditorObject object, int spaces) {

        exportBuilder.append("{\n").append("\t".repeat(spaces + 1));

        for (EditorAttribute editorAttribute : object.getAttributes()) {

            if ((editorAttribute.actualValue().isEmpty() && (editorAttribute.getChildAlias() == null || object.getChildren(editorAttribute.getName()).isEmpty())) && !editorAttribute.getRequired()) continue;

            if (editorAttribute.getChildAlias() != null || editorAttribute.getType() == InputField._2_LIST_CHILD || editorAttribute.getType() == InputField._2_LIST_CHILD_HIDDEN) {

                exportBuilder.append("\"").append(editorAttribute.getName()).append("\":\t");
                if (editorAttribute.getType() == InputField._2_LIST_CHILD || editorAttribute.getType() == InputField._2_LIST_CHILD_HIDDEN) exportBuilder.append("[");

                ArrayList<EditorObject> children = object.getChildren(editorAttribute.getName());
                if (editorAttribute.getName().equals("terrainBalls")) {
                    for (EditorObject ball : object.getChildren("balls")) {
                        EditorObject terrainBall = ObjectCreator.create2(_2_Level_TerrainBall.class, null, "terrainBalls", GameVersion.VERSION_WOG2);
                        terrainBall.setAttribute("group", ball.getAttribute("terrainGroup").stringValue());
                        children.add(terrainBall);
                    }
                }

                boolean any = false;
                for (EditorObject child : children) {

                    any = true;

                    recursiveGOOExport(exportBuilder, child, spaces + 2);
                    exportBuilder.append(", ");

                }

                if (any && exportBuilder.charAt(exportBuilder.length() - 1) != '{') {
                    exportBuilder.deleteCharAt(exportBuilder.length() - 1);
                    exportBuilder.deleteCharAt(exportBuilder.length() - 1);
                }

                if (editorAttribute.getType() == InputField._2_LIST_CHILD || editorAttribute.getType() == InputField._2_LIST_CHILD_HIDDEN) {
                    exportBuilder.append("]");
                }
                exportBuilder.append(",\n").append("\t".repeat(spaces + 1));

            } else {

                if (editorAttribute.getType() == InputField._2_LIST_STRING || editorAttribute.getType() == InputField._2_LIST_NUMBER) {
                    exportBuilder.append("\"").append(editorAttribute.getName()).append("\":\t[");
                    if (editorAttribute.listValue().length != 0) {
                        for (String v : editorAttribute.listValue()) {
                            if (editorAttribute.getType() == InputField._2_LIST_NUMBER) {
                                exportBuilder.append(v).append(", ");
                            } else {
                                exportBuilder.append("\"").append(v).append("\", ");
                            }
                        }
                        exportBuilder.deleteCharAt(exportBuilder.length() - 1);
                        exportBuilder.deleteCharAt(exportBuilder.length() - 1);
                    }
                    exportBuilder.append("],\n").append("\t".repeat(spaces + 1));
                } else {
                    String value = editorAttribute.stringValue();
                    if (editorAttribute.getType() == InputField._2_STRING || editorAttribute.getType() == InputField._2_UUID || editorAttribute.getType() == InputField._2_ENVIRONMENT_ID || editorAttribute.getType() == InputField._2_MUSIC_ID || editorAttribute.getType() == InputField._2_SOUND_ID || editorAttribute.getType() == InputField._2_ITEM_TYPE || editorAttribute.getType() == InputField._2_BACKGROUND_ID || editorAttribute.getType() == InputField._2_AMBIENCE_ID) {
                        value = "\"" + value + "\"";
                    }
                    exportBuilder.append("\"").append(editorAttribute.getName()).append("\":\t").append(value).append(",\n").append("\t".repeat(spaces + 1));
                }

            }

        }

        exportBuilder.deleteCharAt(exportBuilder.length() - 3 - spaces);
        exportBuilder.append("}");

    }

}
