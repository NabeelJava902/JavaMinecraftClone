package render_engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;

public class DisplayManager {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final int FPS_CAP = 120;

    private static long lastFrameTime;
    private static float delta;

    //TODO set up vsync and other various opengl features for display

    public static void createDisplay(){
        PixelFormat format = new PixelFormat();
        ContextAttribs attribs = new ContextAttribs()
                .withForwardCompatible(true);
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create(format, attribs);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        GL11.glViewport(0, 0, WIDTH, HEIGHT);
        lastFrameTime = getCurrentTime();
        Display.setTitle("Minecraft Clone!");
    }

    public static void updateDisplay(){
        Display.sync(FPS_CAP);
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime)/1000f;
        lastFrameTime = currentFrameTime;
    }

    public static float getFrameTimeSeconds(){
        return delta;
    }

    public static void closeDisplay(){
        Display.destroy();
    }

    private static long getCurrentTime(){
        return Sys.getTime()*1000/Sys.getTimerResolution();
    }
}
