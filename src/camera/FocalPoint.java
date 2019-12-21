package camera;

import entities.Player;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import render_engine.DisplayManager;

public class FocalPoint {

    private Vector3f position;
    private Player player;

    protected float pitchChange = 0;
    protected float angleChange = 0;

    private float cameraYaw = 0;
    private float cameraPitch = 0;

    private static final float RUN_SPEED = 50;

    private float current_speed = 0;

    public FocalPoint(Vector3f position) {
        this.position = position;
    }

    public FocalPoint(Vector3f position, Player player){
        this.position = position;
        this.player = player;
    }

    public void move(){
        checkInputs();
        float distance = current_speed * DisplayManager.getFrameTimeSeconds();
        float dx = (float) (distance * Math.sin(Math.toRadians(-cameraYaw)));
        float dz = (float) (distance * Math.cos(Math.toRadians(-cameraYaw)));
        float dy = (float) (distance * Math.sin(Math.toRadians(cameraPitch)));
        increasePosition(dx, dy, dz);
    }

    private void checkInputs(){
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            current_speed = -RUN_SPEED;
        }else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
            current_speed = RUN_SPEED;
        }else{
            current_speed *= 0.92;
        }
        float mouseX = Mouse.getDX();
        float mouseY = Mouse.getDY();
        angleChange = mouseX * 0.05f;
        pitchChange = mouseY * 0.15f;
    }

    public Vector3f getPosition() {
        return position;
    }

    protected void setCameraYaw(float yaw){
        cameraYaw = yaw;
    }

    protected void setCameraPitch(float pitch){
        cameraPitch = pitch;
    }

    public void increasePosition(float dx, float dy, float dz){
        this.position.x += dx;
        this.position.y += dy;
        this.position.z += dz;
    }
}
