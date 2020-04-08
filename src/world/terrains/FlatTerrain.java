package world.terrains;

import entities.Entity;
import org.lwjgl.util.vector.Vector3f;
import world.cube.CubeGenerator;
import world.cube.CubeType;

public class FlatTerrain {

    private int length, width;
    private float originX, originY, originZ;
    private CubeType cubeType;

    public FlatTerrain(int length, int width, float originX, float originY, float originZ, CubeType cubeType) {
        this.length = length;
        this.width = width;
        this.originX = originX;
        this.originY = originY;
        this.originZ = originZ;
        this.cubeType = cubeType;
    }

    public Entity[] generateTerrain(){
        CubeGenerator generator = new CubeGenerator();
        Entity[] cubes = new Entity[length*width]; //TODO change to 2d array
        float xPos = originX;
        float yPos = originY;
        float zPos = originZ;
        int count = 0;
        for(int x=0; x<length; x++){
            for(int y=0; y<width; y++){
                cubes[count] = generator.addCube(cubeType, new Vector3f(xPos, yPos, zPos), 0, 0, 0, 1);
                count++;
                xPos += 1f;
            }
            xPos = originX;
            zPos += 1f;
        }

        cubes[0] = generator.addCube(CubeType.TREE_LEAF, new Vector3f(originX, originY, originZ), 0, 0, 0, 1);
        return cubes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getOriginX() {
        return originX;
    }

    public void setOriginX(float originX) {
        this.originX = originX;
    }

    public float getOriginY() {
        return originY;
    }

    public void setOriginY(float originY) {
        this.originY = originY;
    }

    public float getOriginZ() {
        return originZ;
    }

    public void setOriginZ(float originZ) {
        this.originZ = originZ;
    }
}
