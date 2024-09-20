package com.woogleFX.gameData.level;

import java.util.ArrayList;

import com.woogleFX.editorObjects.Asset;
import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.fx.FXContainers;
import com.worldOfGoo.resrc.*;

public abstract class _Level extends Asset {

    public _Level(GameVersion version, ArrayList<EditorObject> addin) {
        super(version);
        this.addin = addin;
    }


    public void reAssignSetDefaultsToAllResources() {

        SetDefaults currentSetDefaults = null;

        for (EditorObject EditorObject : ((WOG1Level)this).getResrc()) {

            if (EditorObject instanceof SetDefaults setDefaults) {
                currentSetDefaults = setDefaults;
            }

            else if (EditorObject instanceof FlashAnim flashAnim) {
                flashAnim.setSetDefaults(currentSetDefaults);
            } else if (EditorObject instanceof ResrcImage resrcImage) {
                resrcImage.setSetDefaults(currentSetDefaults);
            } else if (EditorObject instanceof Sound sound) {
                sound.setSetDefaults(currentSetDefaults);
            } else if (EditorObject instanceof Font font) {
                font.setSetDefaults(currentSetDefaults);
            }

        }

    }


    private final ArrayList<EditorObject> addin;
    public ArrayList<EditorObject> getAddin() {
        return addin;
    }
    public EditorObject getAddinObject() {
        return addin.get(0);
    }


    @Override
    public void resetCamera() {


        EditorObject sceneObject = ((WOG1Level)this).getScene().get(0);

        double sceneWidth = sceneObject.getAttribute("maxx").doubleValue() - sceneObject.getAttribute("minx").doubleValue();
        double sceneHeight = sceneObject.getAttribute("maxy").doubleValue() - sceneObject.getAttribute("miny").doubleValue();

        double middleX = (sceneObject.getAttribute("minx").doubleValue() + sceneObject.getAttribute("maxx").doubleValue()) / 2;
        double middleY = (sceneObject.getAttribute("miny").doubleValue() + sceneObject.getAttribute("maxy").doubleValue()) / 2;

        double canvasWidth = FXContainers.getSplitPane().getDividers().get(0).getPosition() * FXContainers.getSplitPane().getWidth();
        double canvasHeight = FXContainers.getSplitPane().getHeight();

        setOffsetX(canvasWidth / 2 - middleX);
        setOffsetY(canvasHeight / 2 + middleY);

        double zoomX = canvasWidth / sceneWidth;
        double zoomY = canvasHeight / sceneHeight;

        setZoom(Math.min(Math.abs(zoomX), Math.abs(zoomY)));

        setOffsetX((getOffsetX() - canvasWidth / 2) * getZoom() + canvasWidth / 2);
        setOffsetY((getOffsetY() - canvasHeight / 2) * getZoom() + canvasHeight / 2);
    }

}
