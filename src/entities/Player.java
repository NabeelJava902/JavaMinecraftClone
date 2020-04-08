package entities;

import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import world.terrains.FlatTerrain;

public abstract class Player extends Entity{

    protected float current_speed = 0;

    public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        super(model, position, rotX, rotY, rotZ, scale);
    }

    public abstract void move(FlatTerrain terrain);

    protected abstract void checkInputs();

    protected abstract void checkCollisions();
}
