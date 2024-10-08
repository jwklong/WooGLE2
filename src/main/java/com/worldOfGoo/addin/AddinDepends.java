package com.worldOfGoo.addin;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;

public class AddinDepends extends EditorObject {

    public AddinDepends(EditorObject _parent, GameVersion version) {
        super(_parent, "depends", version);

        addAttribute("ref", InputField._1_STRING)                    .assertRequired();
        addAttribute("min-version", InputField._1_NUMBER_POSITIVE).assertRequired();
        addAttribute("max-version", InputField._1_NUMBER_POSITIVE).assertRequired();
        
        setMetaAttributes(MetaEditorAttribute.parse("ref,min-version,max-version,"));

    }


    @Override
    public String getName() {
        return getAttribute("ref").stringValue();
    }

}
