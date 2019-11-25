package camera;

import entities.Player;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class FocalPoint {

    private Vector3f position;
    private float rotX, rotY, rotZ;
    private Player player = null;

    private static final float RUN_SPEED = 50; //units per second

    private float currentSpeed = 0;
    private Vector2f viewAngleChange;

    public FocalPoint(Vector3f position, float rotX, float rotY, float rotZ, Player player){
        this.player = player;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
    }

    public FocalPoint(Vector3f position, float rotX, float rotY, float rotZ) {
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
    }

    public void move(){
        if(player != null){
            checkInputs();
            this.rotX += viewAngleChange.x;
            this.rotY += viewAngleChange.y;
        }
    }

    private void checkInputs(){
        /*switch(Keyboard.getEventKey()){
            case Keyboard.KEY_W: currentSpeed = RUN_SPEED;
                break;
            case Keyboard.KEY_S: currentSpeed = -RUN_SPEED;
                break;
        }*/
        viewAngleChange.x = Mouse.getDX() * 0.3f;
        viewAngleChange.y = Mouse.getDY() * 0.1f;

    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }
}
