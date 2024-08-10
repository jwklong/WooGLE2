package com.worldOfGoo.resrc;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;

public class SetDefaults extends EditorObject {

    public SetDefaults(EditorObject _parent, GameVersion version) {
        super(_parent, "SetDefaults", version);

        addAttribute("path", InputField._1_STRING).setDefaultValue("./").assertRequired();
        addAttribute("idprefix", InputField._1_STRING)                  .assertRequired();

        setMetaAttributes(MetaEditorAttribute.parse("path,idprefix,"));

    }


    @Override
    public String getName() {
        return getAttribute("path").stringValue();
    }

}
