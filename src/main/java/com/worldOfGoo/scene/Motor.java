package com.worldOfGoo.scene;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;

public class Motor extends EditorObject {

    public Motor(EditorObject _parent, GameVersion version) {
        super(_parent, "motor", version);

        addAttribute("body",     InputField._1_STRING)                            .assertRequired();
        addAttribute("speed",    InputField._1_NUMBER).setDefaultValue("-0.01").assertRequired();
        addAttribute("maxforce", InputField._1_NUMBER).setDefaultValue("20")   .assertRequired();

        setMetaAttributes(MetaEditorAttribute.parse("body,maxforce,speed,"));

    }


    @Override
    public String getName() {
        return getAttribute("body").stringValue();
    }

}
