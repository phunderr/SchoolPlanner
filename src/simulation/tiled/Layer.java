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
    private JsonArray jsonValues;

    private JsonObject layer;

    private int height;
    private int width;


    public Layer(JsonObject layer, ArrayList<BufferedImage> bf, JsonArray jsonValues) {
        this.jsonValues = jsonValues;
        this.layer = layer;
        tilesets = bf;
        JsonArray data = layer.getJsonArray("data");
//        height = layer.getInt("height");
//        width = layer.getInt("width");
        tiles = new ArrayList<>();
        int index = 0;
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {

                tiles.add(new Tile(new Point2D.Double(x * 32, y * 32), getImageByIndex(data.getInt(index))));
                index++;
            }
        }
    }

    public BufferedImage getImageByIndex(int index) {
        int tileWidth = 32;
        int tileHeight = 32;
        int collumnWidth = 0;
        int firstGrid = 0;
        int tilecount = 0;
        int dataID = 0;
        BufferedImage layerImage = tilesets.get(0);
        if(index == 1){
            return layerImage.getSubimage(tileWidth * (index % 8), tileHeight * (index / 8), tileWidth, tileHeight);
        }

        for (int i = 0; i < tilesets.size(); i++){
            layerImage = tilesets.get(i);
            collumnWidth = Integer.parseInt((jsonValues.getJsonObject(i).get("columns").toString()));
            firstGrid = Integer.parseInt((jsonValues.getJsonObject(i).get("firstgid").toString()));
            tilecount = Integer.parseInt((jsonValues.getJsonObject(i).get("tilecount").toString()));
            dataID = firstGrid + tilecount;

            return index < dataID ? null : layerImage.getSubimage(tileWidth * (index % collumnWidth), tileHeight * (index % collumnWidth), tileWidth, tileHeight);
        }
        index -= 1;
        return index < 1 ? null : layerImage.getSubimage(tileWidth * (index % collumnWidth), tileHeight * (index % collumnWidth), tileWidth, tileHeight);
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
