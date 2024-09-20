package com.woogleFX.gameData.animation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;

public class AnimBinReader {

    private static final Logger logger = LoggerFactory.getLogger(AnimBinReader.class);


    private static int readInt(ByteArrayInputStream input) throws IOException {
        return ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getInt(0);
    }

    private static float readFloat(ByteArrayInputStream input) throws IOException {
        return ByteBuffer.wrap(input.readNBytes(4)).order(ByteOrder.LITTLE_ENDIAN).getFloat(0);
    }


    public static SimpleBinAnimation readSimpleBinAnimation(Path path, String name) {

        try {

            SimpleBinAnimation finalBinAnimation = new SimpleBinAnimation();

            finalBinAnimation.name = name;

            ByteArrayInputStream input = new ByteArrayInputStream(Files.readAllBytes(path));
            int int1 = readInt(input);
            input.skipNBytes(4);
            int secretCount = readInt(input);
            input.skipNBytes(4);
            int int5 = readInt(input);
            input.skipNBytes(4);
            int int7 = readInt(input);
            input.skipNBytes(4);
            int keyframeCount = readInt(input);
            input.skipNBytes(4);
            int imageDataCount = readInt(input);
            input.skipNBytes(4);
            int int13 = readInt(input);
            input.skipNBytes(4);
            int int15 = readInt(input);
            input.skipNBytes(4);
            int int17 = readInt(input);
            input.skipNBytes(4);
            int int19 = readInt(input);
            input.skipNBytes(12);
            int int23 = readInt(input);
            input.skipNBytes(28);
            int int31 = readInt(input);
            input.skipNBytes(4);
            int int33 = readInt(input);
            input.skipNBytes(4);
            int int35 = readInt(input);
            input.skipNBytes(4);
            int int37 = readInt(input);
            input.skipNBytes(4);
            int int39 = readInt(input);
            input.skipNBytes(4);
            int int41 = readInt(input);
            input.skipNBytes(4);
            int int43 = readInt(input);
            input.skipNBytes(4);
            int int45 = readInt(input);
            input.skipNBytes(4);
            int int47 = readInt(input);
            input.skipNBytes(28);

            finalBinAnimation.fps = readInt(input);

            finalBinAnimation.states = new SimpleBinAnimation.SimpleBinAnimationState[int1];
            for (int i = 0; i < int1; i++) {
                finalBinAnimation.states[i] = new SimpleBinAnimation.SimpleBinAnimationState();
                finalBinAnimation.states[i].globalIdHash = readInt(input);
                finalBinAnimation.states[i].localIdHash = readInt(input);
                finalBinAnimation.states[i].groupOffset = readInt(input);
            }

            finalBinAnimation.groups = new SimpleBinAnimation.SimpleBinAnimationGroup[secretCount];
            for (int i = 0; i < secretCount; i++) {
                finalBinAnimation.groups[i] = new SimpleBinAnimation.SimpleBinAnimationGroup();
                finalBinAnimation.groups[i].startingFrame = readInt(input);
                finalBinAnimation.groups[i].duration = readInt(input);
                finalBinAnimation.groups[i].sectionOffset = readInt(input);
                finalBinAnimation.groups[i].sectionLength = readInt(input);
                finalBinAnimation.groups[i].eventOffset = readInt(input);
                finalBinAnimation.groups[i].eventLength = readInt(input);
            }

            finalBinAnimation.sections = new SimpleBinAnimation.SimpleBinAnimationSection[int5];
            for (int i = 0; i < int5; i++) {
                finalBinAnimation.sections[i] = new SimpleBinAnimation.SimpleBinAnimationSection();
                finalBinAnimation.sections[i].type = readInt(input);
                finalBinAnimation.sections[i].elementOffset = readInt(input);
                finalBinAnimation.sections[i].elementLength = readInt(input);
            }

            finalBinAnimation.elements = new SimpleBinAnimation.SimpleBinAnimationElement[int7];
            for (int i = 0; i < int7; i++) {
                finalBinAnimation.elements[i] = new SimpleBinAnimation.SimpleBinAnimationElement();
                finalBinAnimation.elements[i].type = readInt(input);
                finalBinAnimation.elements[i].offset = readInt(input);
                finalBinAnimation.elements[i].frame = readInt(input);
            }

            finalBinAnimation.keyframes = new SimpleBinAnimation.SimpleBinAnimationKeyframe[keyframeCount];
            for (int i = 0; i < keyframeCount; i++) {
                finalBinAnimation.keyframes[i] = new SimpleBinAnimation.SimpleBinAnimationKeyframe();
                finalBinAnimation.keyframes[i].groupOffset = readInt(input);
                finalBinAnimation.keyframes[i].unknown1 = readInt(input);
                finalBinAnimation.keyframes[i].angleTopLeft = readFloat(input);
                finalBinAnimation.keyframes[i].angleBottomRight = readFloat(input);
                finalBinAnimation.keyframes[i].centerX = readFloat(input);
                finalBinAnimation.keyframes[i].centerY = readFloat(input);
                finalBinAnimation.keyframes[i].offsetX = readFloat(input);
                finalBinAnimation.keyframes[i].offsetY = readFloat(input);
                finalBinAnimation.keyframes[i].scaleX = readFloat(input);
                finalBinAnimation.keyframes[i].scaleY = readFloat(input);
                finalBinAnimation.keyframes[i].colorize = readInt(input);
                finalBinAnimation.keyframes[i].unknown2 = readFloat(input);
            }

            finalBinAnimation.parts = new SimpleBinAnimation.SimpleBinAnimationPart[imageDataCount];
            for (int i = 0; i < imageDataCount; i++) {
                finalBinAnimation.parts[i] = new SimpleBinAnimation.SimpleBinAnimationPart();
                finalBinAnimation.parts[i].imageIndex = readInt(input);
                finalBinAnimation.parts[i].angleTopLeft = readFloat(input);
                finalBinAnimation.parts[i].angleBottomRight = readFloat(input);
                finalBinAnimation.parts[i].centerX = readFloat(input);
                finalBinAnimation.parts[i].centerY = readFloat(input);
                finalBinAnimation.parts[i].offsetX = readFloat(input);
                finalBinAnimation.parts[i].offsetY = readFloat(input);
                finalBinAnimation.parts[i].scaleX = readFloat(input);
                finalBinAnimation.parts[i].scaleY = readFloat(input);
                finalBinAnimation.parts[i].colorize = readInt(input);
                finalBinAnimation.parts[i].unknown1 = readInt(input);
            }

            finalBinAnimation.externals = new SimpleBinAnimation.SimpleBinAnimationExternal[int13];
            for (int i = 0; i < int13; i++) {
                finalBinAnimation.externals[i] = new SimpleBinAnimation.SimpleBinAnimationExternal();
                finalBinAnimation.externals[i].globalIdHash = readInt(input);
                finalBinAnimation.externals[i].type = readInt(input);
                finalBinAnimation.externals[i].property12Offset = readInt(input);
                finalBinAnimation.externals[i].property9Offset = readInt(input);
                finalBinAnimation.externals[i].property9Length = readInt(input);
            }

            //finalBinAnimation.property8s = new SimpleBinAnimation.SimpleBinAnimationProperty8[int15];
            for (int i = 0; i < int15; i++) {
                input.skipNBytes(4 * 2);
                //finalBinAnimation.property8s[i] = new SimpleBinAnimation.SimpleBinAnimationProperty8();
                //finalBinAnimation.property8s[i].unknown1 = readInt(input);
                //finalBinAnimation.property8s[i].unknown2 = readInt(input);
            }

            finalBinAnimation.property9s = new SimpleBinAnimation.SimpleBinAnimationProperty9[int17];
            for (int i = 0; i < int17; i++) {
                finalBinAnimation.property9s[i] = new SimpleBinAnimation.SimpleBinAnimationProperty9();
                finalBinAnimation.property9s[i].type = readInt(input);
                finalBinAnimation.property9s[i].unknown1 = readFloat(input);
                finalBinAnimation.property9s[i].unknown2 = readFloat(input);
                finalBinAnimation.property9s[i].unknown3 = readFloat(input);
                finalBinAnimation.property9s[i].unknown4 = readFloat(input);
                finalBinAnimation.property9s[i].unknown5 = readFloat(input);
                finalBinAnimation.property9s[i].unknown6 = readFloat(input);
            }

            //finalBinAnimation.animationEvents = new SimpleBinAnimation.SimpleBinAnimationEvent[int19];
            for (int i = 0; i < int19; i++) {
                input.skipNBytes(4 * 3);
                //finalBinAnimation.animationEvents[i] = new SimpleBinAnimation.SimpleBinAnimationEvent();
                //finalBinAnimation.animationEvents[i].type = //FinalBinAnimation.AnimationEventType.values()[readInt(input)];
                //finalBinAnimation.animationEvents[i].frame = readInt(input);
                //finalBinAnimation.animationEvents[i].unknown1 = readInt(input);
            }

            //finalBinAnimation.property11s = new SimpleBinAnimation.SimpleBinAnimationProperty11[int23];
            for (int i = 0; i < int23; i++) {
                input.skipNBytes(4 * 10);
                //finalBinAnimation.property11s[i] = new SimpleBinAnimation.SimpleBinAnimationProperty11();
                //finalBinAnimation.property11s[i].globalIdHash = readInt(input);
                //finalBinAnimation.property11s[i].type = //FinalBinAnimation.AnimationProperty11Type.values()[readInt(input)];
                //finalBinAnimation.property11s[i].unknown1 = readInt(input);
                //finalBinAnimation.property11s[i].unknown2 = readInt(input);
                //finalBinAnimation.property11s[i].unknown3 = readInt(input);
                //finalBinAnimation.property11s[i].unknown4 = readInt(input);
                //finalBinAnimation.property11s[i].unknown5 = readInt(input);
                //finalBinAnimation.property11s[i].unknown6 = readInt(input);
                //finalBinAnimation.property11s[i].unknown7 = readInt(input);
            }

            finalBinAnimation.property12s = new SimpleBinAnimation.SimpleBinAnimationProperty12[int31];
            for (int i = 0; i < int31; i++) {
                finalBinAnimation.property12s[i] = new SimpleBinAnimation.SimpleBinAnimationProperty12();
                finalBinAnimation.property12s[i].type = readInt(input);
                finalBinAnimation.property12s[i].idHash = readInt(input);
                finalBinAnimation.property12s[i].attribute3 = readInt(input);
                finalBinAnimation.property12s[i].attribute4 = readInt(input);
                finalBinAnimation.property12s[i].attribute5 = readInt(input);
                finalBinAnimation.property12s[i].attribute6 = readFloat(input);
                finalBinAnimation.property12s[i].attribute7 = readInt(input);
                finalBinAnimation.property12s[i].attribute8 = readInt(input);
                finalBinAnimation.property12s[i].attribute9 = readInt(input);
                finalBinAnimation.property12s[i].attribute10 = readInt(input);
                finalBinAnimation.property12s[i].attribute11 = readInt(input);
                finalBinAnimation.property12s[i].attribute12 = readInt(input);
                finalBinAnimation.property12s[i].attribute13 = readInt(input);
                finalBinAnimation.property12s[i].attribute14 = readInt(input);
                finalBinAnimation.property12s[i].attribute15 = readInt(input);
                finalBinAnimation.property12s[i].attribute16 = readInt(input);
                finalBinAnimation.property12s[i].attribute17 = readInt(input);
                finalBinAnimation.property12s[i].attribute18 = readInt(input);
                finalBinAnimation.property12s[i].attribute19 = readInt(input);
                finalBinAnimation.property12s[i].attribute20 = readInt(input);
                finalBinAnimation.property12s[i].attribute21 = readInt(input);
                finalBinAnimation.property12s[i].attribute22 = readInt(input);
                finalBinAnimation.property12s[i].stringTableIndex = readInt(input);
            }

            //finalBinAnimation.property13s = new SimpleBinAnimation.SimpleBinAnimationProperty13[int33];
            for (int i = 0; i < int33; i++) {
                input.skipNBytes(4 * 7);
                //finalBinAnimation.property13s[i] = new SimpleBinAnimation.SimpleBinAnimationProperty13();
                //finalBinAnimation.property13s[i].attribute1 = readInt(input);
                //finalBinAnimation.property13s[i].attribute2 = readFloat(input);
                //finalBinAnimation.property13s[i].attribute3 = readInt(input);
                //finalBinAnimation.property13s[i].attribute4 = readInt(input);
                //finalBinAnimation.property13s[i].attribute5 = readInt(input);
                //finalBinAnimation.property13s[i].attribute6 = readInt(input);
                //finalBinAnimation.property13s[i].attribute7 = readInt(input);
            }

            //finalBinAnimation.property14s = new SimpleBinAnimation.SimpleBinAnimationProperty14[int35];
            for (int i = 0; i < int35; i++) {
                input.skipNBytes(4 * 39);
                //finalBinAnimation.property14s[i] = new SimpleBinAnimation.SimpleBinAnimationProperty14();
                //finalBinAnimation.property14s[i].attribute1 = readInt(input);
                //finalBinAnimation.property14s[i].attribute2 = readInt(input);
                //finalBinAnimation.property14s[i].attribute3 = readInt(input);
                //finalBinAnimation.property14s[i].attribute4 = readInt(input);
                //finalBinAnimation.property14s[i].attribute5 = readInt(input);
                //finalBinAnimation.property14s[i].attribute6 = readInt(input);
                //finalBinAnimation.property14s[i].attribute7 = readInt(input);
                //finalBinAnimation.property14s[i].attribute8 = readInt(input);
                //finalBinAnimation.property14s[i].attribute9 = readInt(input);
                //finalBinAnimation.property14s[i].attribute10 = readInt(input);
                //finalBinAnimation.property14s[i].attribute11 = readInt(input);
                //finalBinAnimation.property14s[i].attribute12 = readInt(input);
                //finalBinAnimation.property14s[i].attribute13 = readInt(input);
                //finalBinAnimation.property14s[i].attribute14 = readInt(input);
                //finalBinAnimation.property14s[i].attribute15 = readInt(input);
                //finalBinAnimation.property14s[i].attribute16 = readInt(input);
                //finalBinAnimation.property14s[i].attribute17 = readInt(input);
                //finalBinAnimation.property14s[i].attribute18 = readInt(input);
                //finalBinAnimation.property14s[i].attribute19 = readInt(input);
                //finalBinAnimation.property14s[i].attribute20 = readInt(input);
                //finalBinAnimation.property14s[i].attribute21 = readInt(input);
                //finalBinAnimation.property14s[i].attribute22 = readInt(input);
                //finalBinAnimation.property14s[i].attribute23 = readInt(input);
                //finalBinAnimation.property14s[i].attribute24 = readInt(input);
                //finalBinAnimation.property14s[i].attribute25 = readInt(input);
                //finalBinAnimation.property14s[i].attribute26 = readInt(input);
                //finalBinAnimation.property14s[i].attribute27 = readInt(input);
                //finalBinAnimation.property14s[i].attribute28 = readInt(input);
                //finalBinAnimation.property14s[i].attribute29 = readInt(input);
                //finalBinAnimation.property14s[i].attribute30 = readInt(input);
                //finalBinAnimation.property14s[i].attribute31 = readInt(input);
                //finalBinAnimation.property14s[i].attribute32 = readInt(input);
                //finalBinAnimation.property14s[i].attribute33 = readInt(input);
                //finalBinAnimation.property14s[i].attribute34 = readInt(input);
                //finalBinAnimation.property14s[i].attribute35 = readInt(input);
                //finalBinAnimation.property14s[i].attribute36 = readInt(input);
                //finalBinAnimation.property14s[i].attribute37 = readInt(input);
                //finalBinAnimation.property14s[i].attribute38 = readInt(input);
                //finalBinAnimation.property14s[i].attribute39 = readInt(input);
            }

            //finalBinAnimation.property15s = new SimpleBinAnimation.SimpleBinAnimationProperty15[int37];
            for (int i = 0; i < int37; i++) {
                input.skipNBytes(4 * 3);
                //finalBinAnimation.property15s[i] = new SimpleBinAnimation.SimpleBinAnimationProperty15();
                //finalBinAnimation.property15s[i].idHash1 = readInt(input);
                //finalBinAnimation.property15s[i].idHash2 = readInt(input);
                //finalBinAnimation.property15s[i].unknown1 = readInt(input);
            }

            finalBinAnimation.imageStringTableIndices = new int[int39];
            for (int i = 0; i < int39; i++) {
                finalBinAnimation.imageStringTableIndices[i] = readInt(input);
            }

            //finalBinAnimation.animationSoundStringTableIndices = new int[int41];
            for (int i = 0; i < int41; i++) {
                input.skipNBytes(4 * 1);
                //finalBinAnimation.animationSoundStringTableIndices[i] = readInt(input);
            }

            //finalBinAnimation.animationParticleStringTableIndices = new int[int43];
            for (int i = 0; i < int43; i++) {
                input.skipNBytes(4 * 1);
                //finalBinAnimation.animationParticleStringTableIndices[i] = readInt(input);
            }

            //finalBinAnimation.animationXMLDataStringTableIndices = new int[int45];
            for (int i = 0; i < int45; i++) {
                input.skipNBytes(4 * 1);
                //finalBinAnimation.animationXMLDataStringTableIndices[i] = readInt(input);
            }

            finalBinAnimation.stateAliasStringTableIndices = new int[int47];
            for (int i = 0; i < int47; i++) {
                finalBinAnimation.stateAliasStringTableIndices[i] = readInt(input);
            }

            //finalBinAnimation.property16s = new SimpleBinAnimation.SimpleBinAnimationProperty16[int1];
            for (int i = 0; i < int1; i++) {
                input.skipNBytes(4 * 2);
                //finalBinAnimation.property16s[i] = new SimpleBinAnimation.SimpleBinAnimationProperty16();
                //finalBinAnimation.property16s[i].idHash1 = readInt(input);
                //finalBinAnimation.property16s[i].idHash2 = readInt(input);
            }

            int stringCount = readInt(input);
            input.skipNBytes(4);

            if (stringCount > 10000 || stringCount < -10000) return finalBinAnimation;

            finalBinAnimation.stringDeclarations = new SimpleBinAnimation.SimpleBinAnimationStringDeclaration[stringCount];
            for (int i = 0; i < stringCount; i++) {
                finalBinAnimation.stringDeclarations[i] = new SimpleBinAnimation.SimpleBinAnimationStringDeclaration();
                finalBinAnimation.stringDeclarations[i].index = readInt(input);
                finalBinAnimation.stringDeclarations[i].type = readInt(input);
            }

            finalBinAnimation.stringDefinitions = new SimpleBinAnimation.SimpleBinAnimationStringDefinition[stringCount];
            for (int i = 0; i < stringCount; i++) {
                finalBinAnimation.stringDefinitions[i] = new SimpleBinAnimation.SimpleBinAnimationStringDefinition();
                String lang = new String(input.readNBytes(4));
                finalBinAnimation.stringDefinitions[i].language = lang.contains("\u0000") ? lang.substring(0, lang.indexOf("\u0000")) : lang;
                finalBinAnimation.stringDefinitions[i].stringTableIndex = readInt(input);
            }

            finalBinAnimation.stringTable = input.readAllBytes();

            return finalBinAnimation;

        } catch (IOException e) {
            logger.error("", e);
            return null;
        }

    }

}
