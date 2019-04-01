package simulation.tiled;

import org.jfree.fx.FXGraphics2D;
import simulation.NPC.Character;
import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Map {

    private int width;
    private int height;
    private ArrayList<BufferedImage> tilesetSubImages;

    private ArrayList<BufferedImage> sprites;

    private ArrayList<Layer> layers;
    public static ArrayList<Location> locations;
    private ArrayList<JsonObject> layers2;
    private BufferedImage finalImage = new BufferedImage(100*32,100*32,BufferedImage.TYPE_INT_ARGB);

    public Map(String fileName) {
        init();
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));
        JsonObject root = reader.readObject();
        ArrayList<JsonObject> finalObjects = new ArrayList<>();
        JsonArray layerArray = root.getJsonArray("layers");
        JsonArray finalObjectArray = null;
        int layerAmount = root.getJsonArray("layers").size();
        for (int i = 0; i < layerAmount; i++){
            if (layerArray.getJsonObject(i).getJsonString("type").toString().equals("objectgroup")){
                JsonArray objectArray = layerArray.getJsonObject(i).getJsonArray("objects");
                finalObjectArray = objectArray;
            }
        }
        System.out.println(finalObjectArray);
        locations = new ArrayList<>();

        for (int i = 0; i < finalObjectArray.size(); i++) {
//                Location currentLocation = new Location(Integer.parseInt(finalObjectArray.getJsonObject(i).getJsonString("x").toString()),Integer.parseInt(finalObjectArray.getJsonObject(i).getJsonString("y").toString()),finalObjectArray.getJsonObject(i).getJsonString("name").getString());
            Location currentlocation = new Location(((finalObjectArray.getJsonObject(i).getInt("x"))),((finalObjectArray.getJsonObject(i).getInt("y"))),finalObjectArray.getJsonObject(i).getJsonString("name").getString()
                                                    , finalObjectArray.getJsonObject(i).getInt("width"), finalObjectArray.getJsonObject(i).getInt("height"));
            locations.add(currentlocation);
        }

        //
        width = root.getInt("width");
        height = root.getInt("height");
        JsonArray layersArray = root.getJsonArray("layers");
        JsonArray tileset = root.getJsonArray("tilesets");
        sprites = new ArrayList<>();




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
                if (layersArray.getJsonObject(i).getJsonString("type").toString().equals("tilelayer") && layersArray.getJsonObject(i).getBoolean("visible")){
                    layers.add(new Layer(root.getJsonArray("layers").getJsonObject(i), tilesetSubImages));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        getFinalMapImage();
    }

    public void getFinalMapImage(){
        Graphics g = finalImage.getGraphics();
        try {
            for (Layer layer : layers) {
                for (Tile tile : layer.getTiles()) {
                    g.drawImage(tile.getBf(),(int)tile.getLocation().getX(),(int)tile.getLocation().getY(),null);
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



    public void draw(FXGraphics2D g) {
        g.drawImage(finalImage,0,0,null);
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
