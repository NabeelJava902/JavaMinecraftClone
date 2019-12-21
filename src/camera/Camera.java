package camera;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import static utils.Maths.restrict;

public class Camera{

    private Vector3f position = new Vector3f(0, 0, 0);
    private FocalPoint focalPoint;
    private float pitch;
    private float yaw;
    private float roll;

    public Camera(FocalPoint focalPoint){
        this.focalPoint = focalPoint;
        Mouse.setGrabbed(true);
    }

    public void update(){
        position.x = focalPoint.getPosition().x;
        position.y = focalPoint.getPosition().y;
        position.z = focalPoint.getPosition().z + 20;
        pitch = restrict(pitch -= focalPoint.pitchChange, -90, 90);
        yaw += focalPoint.angleChange;
        if(yaw > 360 || yaw < -360){
            yaw = 0;
        }
        focalPoint.setCameraPitch(pitch);
        focalPoint.setCameraYaw(yaw);
    }

    public Vector3f getPosition() {
        return position;
    }
    public float getPitch() {
        return pitch;
    }
    public float getYaw() {
        return yaw;
    }
    public float getRoll() {
        return roll;
    }
}


































/*import org.lwjgl.util.vector.Vector3f;

public class Camera {

    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch;
    private float yaw;
    private float roll;

    private FocalPoint focalPoint;

    private float angleAroundPoint = 0;
    private float distanceFromPoint = 50;

    public Camera(FocalPoint focalPoint){
        this.focalPoint = focalPoint;
    }

    public void move(){
        calculateAngle();
        calculatePitch();
        float horizDist = calculateHorizontalDistance();
        float vertDist = calculateVerticalDistance();
        calculateCameraPosition(horizDist, vertDist);
    }

    private void calculateCameraPosition(float horizDist, float vertDist){
        float theta = focalPoint.getRotY() + angleAroundPoint;
        float offsetX = (float) (horizDist * Math.sin(Math.toRadians(theta)));
        float offsetZ = (float) (horizDist * Math.cos(Math.toRadians(theta)));
        this.position.x = focalPoint.getPosition().x - offsetX;
        this.position.y = focalPoint.getPosition().y + vertDist;
        this.position.z = focalPoint.getPosition().z - offsetZ;
        yaw = 180 - theta;
    }

    private float calculateVerticalDistance(){
        return (float)Math.sin(Math.toRadians(pitch)) * distanceFromPoint;
    }

    private float calculateHorizontalDistance(){
        return (float)Math.cos(Math.toRadians(pitch)) * distanceFromPoint;
    }

    private void calculatePitch(){
        float pitchChange = focalPoint.getRotY();
        pitch -= pitchChange;
    }

    private void calculateAngle(){
        float angleChange = focalPoint.getRotX();
        angleAroundPoint -= angleChange;
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}*/