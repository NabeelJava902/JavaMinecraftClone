package entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import world.terrains.FlatTerrain;

public class FirstPersonPlayer extends Player{

    public FirstPersonPlayer(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    @Override
    public void move(FlatTerrain terrain) {

    }

    @Override
    protected void checkInputs() {

    }

    @Override
    protected void checkCollisions() {

    }
}
