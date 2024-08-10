package com.woogleFX.editorObjects.objectCreators;

import com.woogleFX.editorObjects.EditorObject;
import com.woogleFX.engine.LevelManager;
import com.woogleFX.gameData.level.GameVersion;
import com.woogleFX.gameData.level.WOG1Level;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.level._Level;
import com.worldOfGoo.addin.*;
import com.worldOfGoo.ball.*;
import com.worldOfGoo.level.*;
import com.worldOfGoo.particle.*;
import com.worldOfGoo.resrc.*;
import com.worldOfGoo.scene.*;
import com.worldOfGoo.text.TextString;
import com.worldOfGoo.text.TextStrings;
import com.worldOfGoo2.environments.*;
import com.worldOfGoo2.misc._2_SoundEvent;
import com.worldOfGoo2.ball.*;
import com.worldOfGoo2.items.*;
import com.worldOfGoo2.level.*;
import com.worldOfGoo2.misc.*;

public class ObjectCreator {

    public static EditorObject getDefaultParent(String name) {

        WOG1Level level = (WOG1Level) LevelManager.getLevel();

        return switch(name) {

            case "linearforcefield", "radialforcefield", "particles",
                    "SceneLayer", "buttongroup", "button", "circle",
                    "rectangle", "hinge", "compositegeom", "label",
                    "line", "motor", "slider" -> level.getSceneObject();

            case "BallInstance", "camera", "endoncollision",
                    "endonmessage", "endonnogeom", "fire", "levelexit",
                    "loopsound", "music", "pipe", "poi", "signpost",
                    "Strand", "targetheight" -> level.getLevelObject();

            case "font" -> level.getResrcObject();

            default -> null;

        };

    }


    public static EditorObject create(String name, EditorObject _parent, GameVersion version) {

        _Level level = LevelManager.getLevel();

        if (level instanceof WOG2Level) return create2(name, _parent, version);

        EditorObject parent = (_parent != null || level == null) ? _parent : getDefaultParent(name);

        EditorObject toAdd = switch (name) {
            case "addin", "Addin_addin" -> new Addin(parent, version);
            case "Addin_id" -> new AddinID(parent, version);
            case "Addin_name" -> new AddinName(parent, version);
            case "Addin_type" -> new AddinType(parent, version);
            case "Addin_version" -> new AddinVersion(parent, version);
            case "Addin_description" -> new AddinDescription(parent, version);
            case "Addin_author" -> new AddinAuthor(parent, version);
            case "Addin_levels" -> new AddinLevels(parent, version);
            case "Addin_level" -> new AddinLevel(parent, version);
            case "Addin_dir" -> new AddinLevelDir(parent, version);
            case "Addin_wtf_name" -> new AddinLevelName(parent, version);
            case "Addin_subtitle" -> new AddinLevelSubtitle(parent, version);
            case "Addin_ocd" -> new AddinLevelOCD(parent, version);
            case "ambientparticleeffect" -> new Ambientparticleeffect(parent, version);
            case "axialsinoffset" -> new Axialsinoffset(parent, version);
            case "ball" -> new Ball(parent, version);
            case "BallInstance" -> new BallInstance(parent, version);
            case "ball_particles" -> new BallParticles(parent, version);
            case "ball_sound" -> new BallSound(parent, version);
            case "button" -> new Button(parent, version);
            case "buttongroup" -> new Buttongroup(parent, version);
            case "camera" -> new Camera(parent, version);
            case "circle" -> new Circle(parent, version);
            case "compositegeom" -> new Compositegeom(parent, version);
            case "detachstrand" -> new Detachstrand(parent, version);
            case "effects" -> new Effects(parent, version);
            case "endoncollision" -> new Endoncollision(parent, version);
            case "endonmessage" -> new Endonmessage(parent, version);
            case "endonnogeom" -> new Endonnogeom(parent, version);
            case "fire" -> new Fire(parent, version);
            case "font" -> new Font(parent, version);
            case "hinge" -> new Hinge(parent, version);
            case "label" -> new Label(parent, version);
            case "level" -> new Level(parent, version);
            case "levelexit" -> new Levelexit(parent, version);
            case "line" -> new Line(parent, version);
            case "linearforcefield" -> new Linearforcefield(parent, version);
            case "loopsound" -> new Loopsound(parent, version);
            case "marker" -> new Marker(parent, version);
            case "materials" -> new Materials(parent, version);
            case "material" -> new Material(parent, version);
            case "motor" -> new Motor(parent, version);
            case "music" -> new Music(parent, version);
            case "part" -> new Part(parent, version);
            case "particleeffect" -> new Particleeffect(parent, version);
            case "particle" -> new _Particle(parent, version);
            case "particles" -> new Particles(parent, version);
            case "pipe" -> new Pipe(parent, version);
            case "poi" -> new Poi(parent, version);
            case "rectangle" -> new Rectangle(parent, version);
            case "radialforcefield" -> new Radialforcefield(parent, version);
            case "ResourceManifest" -> new ResourceManifest(parent, version);
            case "Resources" -> new Resources(parent, version);
            case "Image" -> new ResrcImage(parent, version);
            case "scene" -> new Scene(parent, version);
            case "SceneLayer" -> new SceneLayer(parent, version);
            case "SetDefaults" -> new SetDefaults(parent, version);
            case "shadow" -> new Shadow(parent, version);
            case "signpost" -> new Signpost(parent, version);
            case "sinanim" -> new Sinanim(parent, version);
            case "sinvariance" -> new Sinvariance(parent, version);
            case "slider" -> new Slider(parent, version);
            case "sound", "Sound" -> new Sound(parent, version);
            case "splat" -> new Splat(parent, version);
            case "Strand" -> new Strand(parent, version);
            case "strand" -> new BallStrand(parent, version);
            case "string" -> new TextString(parent, version);
            case "strings" -> new TextStrings(parent, version);
            case "targetheight" -> new Targetheight(parent, version);
            case "Vertex" -> new Vertex(parent, version);

            default -> throw new RuntimeException("Attempted to create an invalid object: \"" + name + "\"");
        };

        if (parent != null) toAdd.setParent(parent);

        return toAdd;

    }

    public static EditorObject create2(String name, EditorObject parent, GameVersion version) {

        _Level level = LevelManager.getLevel();

        EditorObject toAdd = switch (name) {

            case "_2_Level_Ball" -> new _2_Level_BallInstance(parent);
            case "_2_Level_CameraKeyFrame" -> new _2_Level_CameraKeyFrame(parent);
            case "_2_Level_TerrainGroup" -> new _2_Level_TerrainGroup(parent);
            case "_2_Level_TerrainBall" -> new _2_Level_TerrainBall(parent);
            case "_2_Level_Pin" -> new _2_Level_Pin(parent);
            case "_2_Level_Item" -> new _2_Level_Item(parent);
            case "_2_Level_Strand" -> new _2_Level_Strand(parent);
            case "_2_Level_UserVariable" -> new _2_Level_UserVariable(parent);
            case "_2_Item_Collection" -> new _2_Item_Collection(parent);
            case "_2_Item_Object" -> new _2_Item_Object(parent);
            case "_2_Item" -> new _2_Item(parent);
            case "_2_Item_Geometry" -> new _2_Item_Geometry(parent);
            case "_2_Item_UserVariable" -> new _2_Item_UserVariable(parent);
            case "_2_Item_LaserSettings" -> new _2_Item_LaserSettings(parent);
            case "_2_Item_Body" -> new _2_Item_Body(parent);
            case "_2_Item_ColorFX" -> new _2_Item_ColorFX(parent);
            case "_2_Item_Limit" -> new _2_Item_Limit(parent);
            case "_2_Item_Sound" -> new _2_Item_Sound(parent);
            case "_2_Item_CollisionSound" -> new _2_Item_CollisionSound(parent);
            case "_2_Level" -> new _2_Level(parent);
            case "_2_Item_ParticleEffect" -> new _2_Item_ParticleEffect(parent);
            case "_2_Item_Point" -> new _2_Item_Point(parent);
            case "_2_Item_Alternates" -> new _2_Item_Alternates(parent);

            case "SetDefaults" -> new SetDefaults(parent, version);
            case "ResourceManifest" -> new ResourceManifest(parent, version);
            case "Resources" -> new Resources(parent, version);
            case "Sound" -> new Sound(parent, version);
            case "Image" -> new ResrcImage(parent, version);

            case "_2_Ball" -> new _2_Ball(parent);
            case "_2_Ball_Axis" -> new _2_Ball_Axis(parent);
            case "_2_Ball_BallType" -> new _2_Ball_BallType(parent);
            case "_2_Ball_Image" -> new _2_Ball_Image(parent);
            case "_2_Ball_Part" -> new _2_Ball_Part(parent);
            case "_2_Ball_ParticleEffect" -> new _2_Ball_ParticleEffect(parent);
            case "_2_Ball_SoundEvent" -> new _2_Ball_SoundEvent(parent);
            case "_2_Ball_SplatImageID" -> new _2_Ball_SplatImageID(parent);
            case "_2_Ball_StateAnimation" -> new _2_Ball_StateAnimation(parent);
            case "_2_Ball_StrandType" -> new _2_Ball_StrandType(parent);
            case "_2_Ball_StructureFillingLayer" -> new _2_Ball_StructureFillingLayer(parent);
            case "_2_Ball_Type" -> new _2_Ball_Type(parent);
            case "_2_Ball_BallShape" -> new _2_Ball_Shape(parent);
            case "_2_Ball_Material" -> new _2_Ball_Material(parent);
            case "_2_Ball_BodyPart" -> new _2_BodyPart(parent);
            case "_2_Ball_AttenuationFunction" -> new _2_Ball_AttenuationFunction(parent);
            case "_2_Ball_FlashAnimation" -> new _2_Ball_FlashAnimation(parent);
            case "_2_Ball_PartAnimation" -> new _2_Ball_PartAnimation(parent);
            case "_2_Ball_State" -> new _2_Ball_State(parent);
            case "_2_SoundEvent" -> new _2_SoundEvent(parent);
            case "_2_Ball_ParticleEffectV2" -> new _2_Ball_ParticleEffectV2(parent);
            case "_2_Layer" -> new _2_Layer(parent);

            case "_2_Environment" -> new _2_Environment(parent);
            case "_2_Environment_Gradient" -> new _2_Environment_Gradient(parent);
            case "_2_Environment_ForegroundFX" -> new _2_Environment_ForegroundFX(parent);
            case "_2_Environment_Layer" -> new _2_Environment_Layer(parent);
            case "_2_Environment_LiquidOverride" -> new _2_Environment_LiquidOverride(parent);
            case "_2_Environment_Sound" -> new _2_Environment_Sound(parent);
            case "_2_Environment_StableFluidsBloomFactor" -> new _2_Environment_StableFluidsBloomFactor(parent);

            case "_2_Point" -> new _2_Point(parent);

            case "_2_ImageID" -> new _2_ImageID(parent);
            case "_2_SoundID" -> new _2_SoundID(parent);
            case "_2_Color" -> new _2_Color(parent);
            case "_2_UUID" -> new _2_UUID(parent);
            case "_2_LiquidType" -> new _2_LiquidType(parent);

            default -> null;//throw new RuntimeException("Attempted to create an invalid object: \"" + name + "\"");
        };

        if (toAdd == null) {
            System.out.println("Invalid object type: " + name + " (parent: " + (parent == null ? "null" : parent.getType() + " (parent: " + (parent.getParent() == null ? "null" : parent.getParent().getType() + ")" + " (parent: " + (parent.getParent().getParent() == null ? "null" : parent.getParent().getParent().getType() + ")") + ")")));
            toAdd = new _2_Point(parent);
        }

        if (parent != null) toAdd.setParent(parent);

        // System.out.println(name);

        return toAdd;

    }

}
