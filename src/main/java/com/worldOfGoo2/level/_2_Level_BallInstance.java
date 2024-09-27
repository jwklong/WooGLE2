package com.worldOfGoo2.level;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.editorObjects._2_Positionable;
import com.woogleFX.editorObjects.attributes.AttributeAdapter;
import com.woogleFX.editorObjects.attributes.EditorAttribute;
import com.woogleFX.editorObjects.attributes.InputField;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.editorButtons.FXEditorButtons_ShowHide;
import com.woogleFX.engine.undoHandling.userActions.ObjectDestructionAction;
import com.woogleFX.file.resourceManagers.ResourceManager;
import com.woogleFX.gameData.animation.SimpleBinAnimation;
import com.woogleFX.gameData.ball.BallManager;
import com.woogleFX.gameData.ball._2Ball;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.level.levelOpening.AssetLoader;
import com.worldOfGoo2.util.BallInstanceHelper;
import com.worldOfGoo2.util.BinAnimationHelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class _2_Level_BallInstance extends _2_Positionable {

    private _2_Level_TerrainGroup currentGroup = null;
    public _2_Level_TerrainGroup getCurrentGroup() {
        return currentGroup;
    }
    public void setCurrentGroup(_2_Level_TerrainGroup currentGroup) {
        this.currentGroup = currentGroup;
    }

    private _2Ball ball = null;
    public _2Ball getBall() {
        return ball;
    }
    public void updateBall() {
        String type = getAttribute("type").stringValue();
        ball = BallManager.get2Ball(type, getVersion());
        if (ball == null) {
            String invalidBallDescription = "Ball: " + type + " (version " + getVersion() + ")";
            if (!AssetLoader.failedResources.contains(invalidBallDescription))
                AssetLoader.failedResources.add(invalidBallDescription);
        }
    }


    private final ArrayList<_2_Level_Strand> strands = new ArrayList<>();
    public void addStrand(_2_Level_Strand strand) {
        strands.add(strand);
    }
    public void removeStrand(_2_Level_Strand strand) {
        strands.remove(strand);
    }
    public boolean containsStrand(_2_Level_Strand strand) {
        return strands.contains(strand);
    }
    public boolean hasStrands() {
        return !strands.isEmpty();
    }


    private final long randomSeed;
    public long getRandomSeed() {
        return randomSeed;
    }


    public _2_Level_BallInstance(EditorObject parent) {
        super(parent, "BallInstance", GameVersion.VERSION_WOG2);

        randomSeed = (long)(Math.random() * 10000000);

        addAttributeAdapter("typeEnum", BallInstanceHelper.ballTypeAttributeAdapter(this, "type", "typeEnum", null));
        addAttributeAdapter("terrainGroup", new AttributeAdapter("terrainGroup") {
            private final EditorAttribute attribute = new EditorAttribute("terrainGroup", InputField._2_NUMBER, _2_Level_BallInstance.this);
            
            @Override
            public EditorAttribute getValue() {
                attribute.setValue(getAttribute2("terrainGroup").stringValue());
                return attribute;
            }

            @Override
            public void setValue(String value) {
                if (currentGroup != null) {
                    currentGroup.removeBall(_2_Level_BallInstance.this);
                    currentGroup.update();
                }
                
                int newValue = Integer.parseInt(value);
                setAttribute2("terrainGroup", newValue);
                
                _2_Level level = ((WOG2Level) AssetManager.getAsset()).getLevel();
                ArrayList<EditorObject> terrainGroups = level.getChildren("terrainGroups");
                
                if (newValue >= 0 && newValue < terrainGroups.size()) {
                    currentGroup = (_2_Level_TerrainGroup)terrainGroups.get(newValue);
                    currentGroup.addBall(_2_Level_BallInstance.this);
                    currentGroup.update();
                }
            }
            
        });
    }


    public boolean isConnected(_2_Level_BallInstance other) {
        if (this == other) return false;
        for (_2_Level_Strand s : strands) if (s.getGoo1() == other || s.getGoo2() == other) return true;
        return false;
    }


    public void updateTerrainGroup() {
        if (currentGroup != null) currentGroup.update();
    }
    
    @Override
    public String getName() {
        String id = getAttribute("uid").stringValue();
        String type = getAttribute("type").stringValue();
        return id + ", " + type;
    }

    @Override
    public void onLoaded() {
        super.onLoaded();

        updateBall();

        getAttribute("discovered").addChangeListener((observable, oldValue, newValue) -> update());
        getAttribute("interactive").addChangeListener((observable, oldValue, newValue) -> update());
        getAttribute2("typeEnum").addChangeListener((observable, oldValue, newValue) -> updateBall());

        getAttribute("pos").addChangeListener((observable, oldValue, newValue) -> updateTerrainGroup());
    }

    @Override
    public void update() {

        if (!(AssetManager.getAsset() instanceof WOG2Level level)) return;

        String id = getAttribute("uid").stringValue();
        for (EditorObject object : level.getObjects()) if (object instanceof _2_Level_Strand strand) {
            if (id.equals(strand.getAttribute("ball1UID").stringValue())) strand.setGoo1(this);
            else if (id.equals(strand.getAttribute("ball2UID").stringValue())) strand.setGoo2(this);
            else continue;
            strand.update();
        }

        clearObjectComponents();

        addObjectComponents(BallInstanceHelper.generateBallObjectComponents(this));


        if (getBall() != null) {

            String animation = getBall().getObjects().get(0).getChildren("flashAnimation").get(0).getAttribute("flashAnimationId").stringValue();
            if (!animation.isEmpty()) {
                try {
                    SimpleBinAnimation flashAnim = ResourceManager.getFlashAnim(getBall().getResources(), animation, GameVersion.VERSION_WOG2);
                    String state = "";
                    if (getAttribute("type").stringValue().equals("LauncherL2B") || getAttribute("type").stringValue().equals("LauncherL2L")) {
                        if (getAttribute("discovered").booleanValue()) {
                            if (getAttribute("interactive").booleanValue()) state = "idle";
                            else state = "npc_idle";
                        } else {
                            if (getAttribute("interactive").booleanValue()) state = "sleep";
                            else state = "npc_sleep";
                        }
                    }
                    BinAnimationHelper.addBinAnimationAsObjectPositions(this, flashAnim, state);
                } catch (FileNotFoundException e) {
                    logger.error("", e);
                }
            }

        }

    }


    public boolean visibilityFunction() {

        if (AssetManager.getAsset().getVisibilitySettings().getShowGoos() == 0) return false;

        if (!getAttribute("type").stringValue().equals("Terrain")) return true;

        int terrainGroup = getAttribute("terrainGroup").intValue();
        if (terrainGroup < 0 || terrainGroup >= FXEditorButtons_ShowHide.comboBoxList.size()) return true;
        else return FXEditorButtons_ShowHide.comboBoxList.get(terrainGroup);

    }

    @Override
    public List<ObjectDestructionAction> onDelete() {

        if (currentGroup != null) currentGroup.removeBall(this);
        
        List<ObjectDestructionAction> outActions = new ArrayList<>();
        
        WOG2Level level = (WOG2Level) AssetManager.getAsset();
        for (EditorObject object : level.getObjects()) if (object instanceof _2_Level_Strand strand) {
            if (this != strand.getGoo1() && this != strand.getGoo2()) continue;

            int strandPosition = strand.getParent().getChildren().indexOf(strand);
            outActions.add(new ObjectDestructionAction(strand, strandPosition));

            if (this == strand.getGoo1()) strand.setGoo1(null);
            else strand.setGoo2(null);

        }
        
        return outActions;

    }

}

