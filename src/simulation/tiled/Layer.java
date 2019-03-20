package simulation.tiled;

import com.sun.istack.internal.Nullable;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Layer {

    private ArrayList<Tile> tiles;
    private BufferedImage[] cutImages;
    private ArrayList<BufferedImage> tilesets;
    private JsonArray data;
    private ArrayList<Integer> dataArray;

    private JsonObject layer;

    private int height;
    private int width;


    public Layer(JsonObject layer, ArrayList<BufferedImage> bf) {
        this.data = layer.getJsonArray("data");
        this.layer = layer;
        tilesets = bf;
        this.dataArray = new ArrayList<>();
        for (int i = 0; i < 10000; i++){
                dataArray.add(data.getInt(i));
        }
        tiles = new ArrayList<>();
        int index = 0;
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                if ( dataArray.get(index) != 0)
                    tiles.add(new Tile(new Point2D.Double(x * 32, y * 32), tilesets.get(dataArray.get(index) -1)));
                index++;
            }
        }
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void draw(Graphics2D g) {
        //if ( layerImage != null ){
        for (Tile tile : tiles) {
            g.drawImage(tile.getBf(),(int)tile.getLocation().getX(),(int)tile.getLocation().getY(),null);
        }
        //}
    }
}
