package simulation.tiled;

import javax.json.JsonObject;

public class ObjectLayer extends Layer {

    private int x, y;

    public ObjectLayer (JsonObject layer, int x, int y) {
        super(layer, null, null);
        this.x = x;
        this.y = y;
    }
}
