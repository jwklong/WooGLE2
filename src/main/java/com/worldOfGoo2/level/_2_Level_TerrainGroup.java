package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.editorObjects.attributes.MetaEditorAttribute;
import com.woogleFX.editorObjects.objectComponents.TerrainMeshComponent;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.FXPropertiesView;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.terrainTypes.TerrainTypeManager;
import com.worldOfGoo2.misc._2_Point;
import com.worldOfGoo2.terrain._2_Terrain_TerrainType;
import com.worldOfGoo2.util.ItemHelper;

import java.util.ArrayList;

public class _2_Level_TerrainGroup extends EditorObject {
    private ArrayList<_2_Level_BallInstance> balls = new ArrayList<>();
    
    public _2_Level_TerrainGroup(EditorObject parent) {
        super(parent, "TerrainGroup", GameVersion.VERSION_WOG2);

        addAttribute("textureOffset", InputField._2_CHILD_HIDDEN).setChildAlias(_2_Point.class);
        addAttributeAdapter("textureOffset", AttributeAdapter.pointAttributeAdapter(this, "textureOffset", "textureOffset"));


        setMetaAttributes(MetaEditorAttribute.parse("textureOffset,typeUuid,typeIndex,sortOffset,depth,foreground,collision,destructable,buildable,occluder,"));

        EditorAttribute temp = new EditorAttribute("type", InputField._2_TERRAIN_GROUP_TYPE, this).assertRequired();
        addAttributeAdapter("typeUuid", new AttributeAdapter("type") {

            @Override
            public EditorAttribute getValue() {

                if (getAttribute2("typeUuid").stringValue().isEmpty()) return temp;
                temp.setValue(ItemHelper.getTerrainTypeActualName(getAttribute2("typeUuid").stringValue()));
                return temp;

            }

            @Override
            public void setValue(String value) {
                temp.setValue(value);
                _2_Terrain_TerrainType terrainType = TerrainTypeManager.getTerrainType(value);
                setAttribute2("typeUuid", terrainType.getAttribute("uuid").stringValue());
                if (AssetManager.getAsset().getSelected().length > 0 && _2_Level_TerrainGroup.this == AssetManager.getAsset().getSelected()[0]) {
                    FXPropertiesView.changeTableView(AssetManager.getAsset().getSelected());
                }
                update();
            }

        });

    }

    @Override
    public String getName() {
        if (AssetManager.getAsset() != null && AssetManager.getAsset() instanceof WOG2Level level) {
            for (int i = 0; i < level.getLevel().getChildren("terrainGroups").size(); i++) {
                if (level.getLevel().getChildren("terrainGroups").get(i) == this) {
                    return i + ", " + this.getAttribute("type").stringValue();
                }
            }
        }
        return "";
    }

    @Override
    public void onLoaded() {
        super.onLoaded();

        EditorObject textureOffset = getChildren("textureOffset").get(0);
        textureOffset.getAttribute("x").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("textureOffset", newValue + "," + getAttribute2("textureOffset").positionValue().getY()));
        textureOffset.getAttribute("y").addChangeListener((observable, oldValue, newValue) ->
                setAttribute2("textureOffset", getAttribute2("textureOffset").positionValue().getX() + "," + newValue));
        setAttribute2("textureOffset", textureOffset.getAttribute("x").stringValue() + "," + textureOffset.getAttribute("y").stringValue());

        getAttribute("type").addChangeListener((observable, oldValue, newValue) -> update());

        int thisIndex = ((WOG2Level)AssetManager.getAsset()).getLevel().getChildren("terrainGroups").indexOf(this);
        for (EditorObject ball : ((WOG2Level)AssetManager.getAsset()).getLevel().getChildren("balls")) {
            if (ball.getAttribute("terrainGroup").intValue() == thisIndex) {
                addBall((_2_Level_BallInstance) ball);
                ((_2_Level_BallInstance) ball).setCurrentGroup(this);
            }
        }
        update();

    }
    
    @Override
    public void postInit() {
        update();
    }

    @Override
    public void update() {

        clearObjectComponents();

        if (TerrainTypeManager.getTerrainType(getAttribute("typeUuid").stringValue()) != null)
            addObjectComponent(new TerrainMeshComponent(this, getStrands(), balls.toArray(_2_Level_BallInstance[]::new)));

    }

    private _2_Level_Strand[] getStrands() {
        ArrayList<_2_Level_Strand> strands = new ArrayList<>();
        
        for (EditorObject object : ((WOG2Level) AssetManager.getAsset()).getLevel().getChildren("strands")) {
            _2_Level_Strand strand = (_2_Level_Strand)object;
            _2_Level_BallInstance ballInstance = strand.getGoo1();
            
            if (ballInstance != null && ballInstance.getCurrentGroup() == _2_Level_TerrainGroup.this && ballInstance.containsStrand(strand)) {
                strands.add(strand);
            }
        }
        
        return strands.toArray(_2_Level_Strand[]::new);
    }
    
    public void addBall(_2_Level_BallInstance ballInstance) {
        if (!balls.contains(ballInstance))
            balls.add(ballInstance);
    }
    
    public void removeBall(_2_Level_BallInstance ballInstance) {
        balls.remove(ballInstance);
    }
    
    public ArrayList<_2_Level_BallInstance> getBalls() {
        return balls;
    }
}
