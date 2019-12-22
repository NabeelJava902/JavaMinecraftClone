package textures;

import render_engine.Loader;

public class TextureAtlas {

    private static Loader loader = new Loader();

    public static ModelTexture grassTexture = new ModelTexture(loader.loadTexture("grass"));
    public static ModelTexture dirtTexture = new ModelTexture(loader.loadTexture("dirt"));
    public static ModelTexture stoneTexture = new ModelTexture(loader.loadTexture("stone"));
    public static ModelTexture ironOreTexture = new ModelTexture(loader.loadTexture("iron_ore"));
    public static ModelTexture diamondOreTexture = new ModelTexture(loader.loadTexture("diamond_ore"));
    public static ModelTexture goldOreTexture = new ModelTexture(loader.loadTexture("gold_ore"));
    public static ModelTexture treeBarkTexture = new ModelTexture(loader.loadTexture("tree_bark"));
    public static ModelTexture treeLeafTexture = new ModelTexture(loader.loadTexture("tree_leaf"));

}
