import camera.Camera;
import camera.FocalPoint;
import models.CubeModelGenerator;
import entities.Entity;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import render_engine.DisplayManager;
import render_engine.Loader;
import render_engine.MasterRenderer;

import static utils.CubeType.TREE_LEAF;

public class TestLoop {

    public static void main(String[] args){
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        MasterRenderer renderer = new MasterRenderer();
        FocalPoint focalPoint = new FocalPoint(new Vector3f(25, 25, 25));
        Camera camera = new Camera(focalPoint);
        CubeModelGenerator cubeModelGenerator = new CubeModelGenerator();

        Entity leafCube = new Entity(cubeModelGenerator.addCube(TREE_LEAF), new Vector3f(0, 0, 0), 0, 0, 0, 1);

        while(!Display.isCloseRequested()){
            focalPoint.move();
            camera.update();

            renderer.processEntity(leafCube);

            renderer.render(camera);
            DisplayManager.updateDisplay();
        }
        renderer.clean();
        loader.clean();
        DisplayManager.closeDisplay();
    }
}