package com.worldOfGoo.scene;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;

public class Buttongroup extends EditorObject {
    
    public Buttongroup(EditorObject _parent, GameVersion version) {
        super(_parent, "buttongroup", version);

        addAttribute("id", InputField._1_STRING)      .setDefaultValue("levelMarkerGroup").assertRequired();
        addAttribute("osx", InputField._1_POSITION).setDefaultValue("150,1.08")        .assertRequired();

        setMetaAttributes(MetaEditorAttribute.parse("id,osx"));

    }


    @Override
    public String getName() {
        return getAttribute("id").stringValue();
    }


    @Override
    public String[] getPossibleChildren() {
        return new String[]{"button"};
    }

}
