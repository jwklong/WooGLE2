package com.woogleFX.editorObjects.objectComponents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.woogleFX.editorObjects.attributes.dataTypes.Position;
import com.woogleFX.engine.AssetManager;
import com.woogleFX.engine.fx.FXEditorButtons;
import com.woogleFX.gameData.level.WOG2Level;
import com.woogleFX.gameData.terrainTypes.TerrainTypeManager;
import com.worldOfGoo2.level._2_Level_BallInstance;
import com.worldOfGoo2.level._2_Level_Strand;
import com.worldOfGoo2.level._2_Level_TerrainGroup;
import com.worldOfGoo2.terrain.BaseSettings;
import com.worldOfGoo2.terrain._2_Terrain_TerrainType;
import com.worldOfGoo2.util.TerrainHelper;

import javafx.scene.image.Image;

public class TerrainMeshComponent extends MeshComponent {
    private _2_Level_Strand[] strands;
    private _2_Level_BallInstance[] balls;
    private _2_Level_TerrainGroup terrainGroup;
    private Image image;
    private BaseSettings baseSettings;
    
    public TerrainMeshComponent(_2_Level_TerrainGroup terrainGroup, _2_Level_Strand[] strands,
            _2_Level_BallInstance[] balls) {
        this.strands = strands;
        this.balls = balls;
        this.terrainGroup = terrainGroup;
        
        image = TerrainHelper.buildTerrainImage(terrainGroup);

        String terrainType = terrainGroup.getAttribute("typeUuid").stringValue();
        _2_Terrain_TerrainType terrain = TerrainTypeManager.getTerrainType(terrainType);
        baseSettings = (BaseSettings) terrain.getChildren("baseSettings").get(0);
    }

    @Override
    public Face[] getMesh() {
        ArrayList<_2_Level_BallInstance[]> tris = new ArrayList<>();
        
        // Iterate through all strands and gooballs to find triangles
        Set<Integer> existingTriHashes = new HashSet<>();
        
        for (int i = 0; i < strands.length; i++) {
            _2_Level_Strand strand = strands[i];
            
            for (int j = 0; j < balls.length; j++) {
                _2_Level_BallInstance ballInstance = balls[j];
                
                _2_Level_BallInstance goo1 = strand.getGoo1();
                _2_Level_BallInstance goo2 = strand.getGoo2();

                if (goo1 == null || goo2 == null) continue;
                
                if (goo1.isConnected(ballInstance) && goo2.isConnected(ballInstance)) {
                    int hash = ballInstance.hashCode() + goo1.hashCode() + goo2.hashCode();

                    if (!existingTriHashes.contains(hash)) {
                        tris.add(new _2_Level_BallInstance[] {
                            ballInstance,
                            goo1,
                            goo2,
                        });
                        existingTriHashes.add(hash);
                    }
                }
            }
        }
        
        // Merge triangles into bigger polygons
        ArrayList<ArrayList<_2_Level_BallInstance>> mergedPolygons = new ArrayList<>();
        while (tris.size() > 0) {
            ArrayList<_2_Level_BallInstance> polygon = new ArrayList<>(Arrays.asList(tris.remove(tris.size() - 1)));
            
            boolean addedVertices;
            do {
                addedVertices = false;
                for (int i = tris.size() - 1; i >= 0; i--) {
                    Set<_2_Level_BallInstance> difference = TerrainHelper.subtract(tris.get(i), polygon);
                    
                    if (difference.size() == 1) {
                        tris.remove(i);
                        polygon.add(difference.iterator().next());
                        addedVertices = true;
                    }
                }
            } while(addedVertices);
            
            mergedPolygons.add(polygon);
        }
        
        // Create polygons by walking gooballs' contours
        Face[] faces = mergedPolygons.stream().map(this::createPolygon).toArray(Face[]::new);
        return faces;
    }
    
    private Face createPolygon(List<_2_Level_BallInstance> vertices) {
        // Get highest vertex
        _2_Level_BallInstance highest = vertices
            .stream()
            .max((a, b) -> Double.compare(a.getPosition().getY(), b.getPosition().getY()))
            .get();
        
        _2_Level_BallInstance current = highest;
        _2_Level_BallInstance previous = null;
        
        double prevAbsoluteAngle = 0.0;
        ArrayList<_2_Level_BallInstance> path = new ArrayList<>();
        int i = 0;
        do {
            path.add(current);
            
            // Step forward one vertex
            Position currentPos = current.getPosition();
            
            // Find the next vertex with the smallest angle
            // relative to the angle to the previous vertex
            double minRelativeAngle = Double.POSITIVE_INFINITY;
            double minAbsoluteAngle = Double.POSITIVE_INFINITY;
            _2_Level_BallInstance minVertex = null;
            
            for (_2_Level_BallInstance vertex : vertices) {
                if (vertex == current || vertex == previous || !current.isConnected(vertex))
                    continue;
                
                Position vertexPos = vertex.getPosition();
                
                double absoluteAngle = Math.atan2(
                    vertexPos.getY() - currentPos.getY(),
                    vertexPos.getX() - currentPos.getX()
                );
                
                double relativeAngle = absoluteAngle - prevAbsoluteAngle;
                while (relativeAngle < 0) relativeAngle += Math.TAU;
                while (relativeAngle >= Math.TAU) relativeAngle += Math.TAU;
                
                if (relativeAngle < minRelativeAngle) {
                    minRelativeAngle = relativeAngle;
                    minAbsoluteAngle = absoluteAngle;
                    minVertex = vertex;
                }
            }
            
            previous = current;
            current = minVertex;
            prevAbsoluteAngle = minAbsoluteAngle + Math.PI;
            i++;
        } while (current != highest && i < 100 && current != null);
        
        
        double[] xPositions = new double[path.size()];
        double[] yPositions = new double[path.size()];
        
        for (i = 0; i < path.size(); i++) {
            Position position = path.get(i).getPosition();
            xPositions[i] = position.getX();
            yPositions[i] = -position.getY();
        }
        
        return new Face(xPositions, yPositions, path.size());
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public double getX() {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }

    @Override
    public double getScaleX() {
        return baseSettings.getAttribute("metersToUv").doubleValue() / image.getWidth();
    }

    @Override
    public double getScaleY() {
        return baseSettings.getAttribute("metersToUv").doubleValue() / image.getHeight();
    }

    @Override
    public double getDepth() {
        return ((WOG2Level)AssetManager.getAsset()).getLevel().getChildren("terrainGroups").indexOf(terrainGroup) * -0.0001 + (!terrainGroup.getAttribute("foreground").booleanValue() || !terrainGroup.getAttribute("collision").booleanValue() ? -1 : 1) * 10000;
    }

    @Override
    public boolean isVisible() {

        if (AssetManager.getAsset().getVisibilitySettings().getShowGoos() != 2) return false;

        int terrainGroupId = ((WOG2Level) AssetManager.getAsset()).getLevel().getChildren("terrainGroups").indexOf(terrainGroup);
        if (terrainGroupId < 0 || terrainGroupId >= FXEditorButtons.comboBoxList.size()) return true;
        else return FXEditorButtons.comboBoxList.get(terrainGroupId);

    }
}
