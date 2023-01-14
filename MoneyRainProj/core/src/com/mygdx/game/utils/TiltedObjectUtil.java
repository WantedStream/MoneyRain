package com.mygdx.game.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class TiltedObjectUtil {
    public static void parseTileObjectLayer(World world, MapObjects mapObjects){
        for (MapObject object: mapObjects){
            Shape shape;
            if(object instanceof PolylineMapObject){
                shape=createPolyLine((PolylineMapObject) object);
            }
            else{
                continue;
            }

            Body body;
            BodyDef bodyDef=new BodyDef();
            bodyDef.type= BodyDef.BodyType.StaticBody;
            body=world.createBody(bodyDef);
            body.createFixture(shape,1.0f);
            shape.dispose();
        }
    }
    private static ChainShape createPolyLine(PolylineMapObject polyline){
        float[] vertices =polyline.getPolyline().getTransformedVertices();
        Vector2[] worldVertices= new Vector2[vertices.length/2];
        for (int i=0;i< worldVertices.length;i++){
            worldVertices[i]= new Vector2(vertices[i*2]/Constants.PIXLE_PER_METER,vertices[i*2+1]/Constants.PIXLE_PER_METER);
        }
        ChainShape chainShape= new ChainShape();
        chainShape.createChain(worldVertices);
        return chainShape;
    }
}
