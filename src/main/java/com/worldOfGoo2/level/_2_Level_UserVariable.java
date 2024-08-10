package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.gameData.level.GameVersion;

public class _2_Level_UserVariable extends EditorObject {

    public _2_Level_UserVariable(EditorObject parent) {
        super(parent, "UserVariable", GameVersion.VERSION_WOG2);

        addAttribute("value", InputField._2_NUMBER);

        setMetaAttributes(MetaEditorAttribute.parse("value,"));

    }


    @Override
    public String getName() {

        return ((_2_Level_Item)getParent()).getItem().getChildren("userVariables").get(getParent().getChildren("userVariables").indexOf(this)).getAttribute("name").stringValue();

    }

}
