import camera.Camera;
import camera.FocalPoint;
import entities.Light;
import world.cube.CubeGenerator;
import entities.Entity;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import render_engine.DisplayManager;
import render_engine.Loader;
import render_engine.MasterRenderer;
import world.cube.CubeType;

import java.util.Random;

public class TestLoop {

    public static void main(String[] args){
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        MasterRenderer renderer = new MasterRenderer();
        FocalPoint focalPoint = new FocalPoint(new Vector3f(25, 25, 25));
        Camera camera = new Camera(focalPoint);
        Random rand = new Random();
        CubeGenerator cubeGenerator = new CubeGenerator();
        Light sun = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

        Entity[] leafCubes = new Entity[100];
        for(int i=0; i<leafCubes.length; i++){
            float x = (float)rand.nextInt(40);
            float y = (float)rand.nextInt(40);
            float z = (float)rand.nextInt(40);
            leafCubes[i] = cubeGenerator.addCube(CubeType.TREE_LEAF, new Vector3f(x, y, z), 0, 0, 0, 1);
        }

        while(!Display.isCloseRequested()){
            focalPoint.move();
            camera.update();

            for(Entity leafCube : leafCubes){
                renderer.processEntity(leafCube);
            }

            renderer.render(camera);
            DisplayManager.updateDisplay();
        }
        renderer.clean();
        loader.clean();
        DisplayManager.closeDisplay();
    }
}