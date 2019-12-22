package models;

import render_engine.Loader;
import utils.CubeType;
import utils.exceptions.NullTexturedCubeException;

import static textures.TextureAtlas.grassTexture;
import static textures.TextureAtlas.dirtTexture;
import static textures.TextureAtlas.stoneTexture;
import static textures.TextureAtlas.ironOreTexture;
import static textures.TextureAtlas.diamondOreTexture;
import static textures.TextureAtlas.goldOreTexture;
import static textures.TextureAtlas.treeBarkTexture;
import static textures.TextureAtlas.treeLeafTexture;

public class CubeGenerator {

    private Loader loader = new Loader();

    private float[] vertices = {
            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,0.5f,-0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            0.5f,0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            -0.5f,-0.5f,0.5f,
            -0.5f,0.5f,0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f

    };

    private float[] textureCoords = {

            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0


    };

    private int[] indices = {
            0,1,3,
            3,1,2,
            4,5,7,
            7,5,6,
            8,9,11,
            11,9,10,
            12,13,15,
            15,13,14,
            16,17,19,
            19,17,18,
            20,21,23,
            23,21,22

    };

    public TexturedModel addCube(CubeType cubeType){
        RawModel cube = loader.loadToVAO(vertices, textureCoords, indices);
        TexturedModel texturedCube = null;

        try {
            switch (cubeType) {
                case GRASS:
                    texturedCube = new TexturedModel(cube, grassTexture);
                    break;
                case DIRT:
                    texturedCube = new TexturedModel(cube, dirtTexture);
                    break;
                case STONE:
                    texturedCube = new TexturedModel(cube, stoneTexture);
                    break;
                case IRON_ORE:
                    texturedCube = new TexturedModel(cube, ironOreTexture);
                    break;
                case DIAMOND_ORE:
                    texturedCube = new TexturedModel(cube, diamondOreTexture);
                    break;
                case GOLD_ORE:
                    texturedCube = new TexturedModel(cube, goldOreTexture);
                    break;
                case TREE_BARK:
                    texturedCube = new TexturedModel(cube, treeBarkTexture);
                    break;
                case TREE_LEAF:
                    texturedCube = new TexturedModel(cube, treeLeafTexture);
                    break;
            }
        }catch (NullTexturedCubeException e){
            texturedCube = new TexturedModel(cube, e.setDefaultTexture());
            e.printStackTrace();
        }

        return texturedCube;
    }
}
