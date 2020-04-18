import camera.Camera;
import camera.FocalPoint;
import entities.ThirdPersonPlayer;
import world.cube.CubeGenerator;
import entities.Entity;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import render_engine.DisplayManager;
import render_engine.Loader;
import render_engine.MasterRenderer;
import world.cube.CubeType;
import world.terrains.FlatTerrain;

import java.util.Random;

public class TestLoop {

    public static void main(String[] args){
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        MasterRenderer renderer = new MasterRenderer();
        FocalPoint focalPoint = new FocalPoint(null, new Vector3f(25, 25, 25), 1, 1, 1, 1);
        Random rand = new Random();
        CubeGenerator cubeGenerator = new CubeGenerator();

        Entity[] cubes = new Entity[100];
        for(int i=0; i<cubes.length; i++){
            float x = (float)rand.nextInt(40);
            float y = (float)rand.nextInt(40);
            float z = (float)rand.nextInt(40);
            int t = rand.nextInt(8);
            CubeType cubeType = null;
            switch(t){
                case 1: cubeType = CubeType.GRASS;
                    break;
                case 2: cubeType = CubeType.STONE;
                    break;
                case 3: cubeType = CubeType.IRON_ORE;
                    break;
                case 4: cubeType = CubeType.DIAMOND_ORE;
                    break;
                case 5: cubeType = CubeType.GOLD_ORE;
                    break;
                case 6: cubeType = CubeType.DIRT;
                    break;
                case 7 : cubeType = CubeType.TREE_LEAF;
                    break;
                case 0: cubeType = CubeType.TREE_BARK;
            }
            cubes[i] = cubeGenerator.addCube(cubeType, new Vector3f(x, y, z), 0, 0, 0, 1);
        }

        FlatTerrain flatTerrain = new FlatTerrain(30, 30, 20, 0, -10, CubeType.DIRT);
        Entity[] flatDirtTerrain = flatTerrain.generateTerrain();

        Entity playerSprite = cubeGenerator.addCube(CubeType.DIAMOND_ORE, new Vector3f(flatTerrain.getOriginX(), flatTerrain.getOriginY()+20, flatTerrain.getOriginZ()), 0, 0, 0, 1);
        ThirdPersonPlayer thirdPersonPlayer = new ThirdPersonPlayer(playerSprite.getModel(), playerSprite.getPosition(), 0, 0, 0, playerSprite.getScale());

        Camera camera = new Camera();
        camera.setPlayer(focalPoint);

        while(!Display.isCloseRequested()){
            camera.update();
            camera.getPlayer().move(flatTerrain);

            renderer.processEntity(thirdPersonPlayer);

            for(Entity cube: flatDirtTerrain){
                renderer.processEntity(cube);
            }

            renderer.render(camera);
            DisplayManager.updateDisplay();
        }
        renderer.clean();
        loader.clean();
        DisplayManager.closeDisplay();
    }
}