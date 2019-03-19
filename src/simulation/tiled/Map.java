package simulation.tiled;

import simulation.NPC.Character;
import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Map {

    private int width;
    private int height;
    private ArrayList<BufferedImage> tilesetSubImages;

    private ArrayList<BufferedImage> sprites;

    private ArrayList<Layer> layers;
    private ArrayList<JsonObject> layers2;

    public Map(String fileName) {
        init();
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));
        JsonObject root = reader.readObject();

        width = root.getInt("width");
        height = root.getInt("height");
        JsonArray layersArray = root.getJsonArray("layers");
        JsonArray tileset = root.getJsonArray("tilesets");
        sprites = new ArrayList<>();
        int layerAmount = root.getJsonArray("layers").size();


        //load the layers
        try {
            for (int i = 0; i < tileset.size(); i++){
                sprites.add(ImageIO.read(getClass().getResourceAsStream(tileset.getJsonObject(i).get("image").toString())));
            }
            for (int i = 0; i < tileset.size(); i++) {

                int tilecount = Integer.parseInt((root.getJsonArray("tilesets").getJsonObject(i).get("tilecount").toString()));
                int collumnWidth = Integer.parseInt((root.getJsonArray("tilesets").getJsonObject(i).get("columns").toString()));

                    for (int j = 0; j < tilecount; j++){
                    BufferedImage layerImage = sprites.get(i);
                    tilesetSubImages.add(layerImage.getSubimage(32 * (j % collumnWidth), 32 * (j / collumnWidth), 32,32));
                }
            }
            for (int i = 0; i < layerAmount; i++) {
                if (layersArray.getJsonObject(i).getJsonString("type").toString().equals("tilelayer")){
                    layers.add(new Layer(root.getJsonArray("layers").getJsonObject(i), tilesetSubImages));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void draw(Graphics2D g) {
        for (Layer layer:layers) {
            layer.draw(g);
        }
        }



    public void init() {
        layers = new ArrayList<>();
        tilesetSubImages = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
