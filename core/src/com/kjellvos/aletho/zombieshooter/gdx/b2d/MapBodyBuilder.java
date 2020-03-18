package com.kjellvos.aletho.zombieshooter.gdx.b2d;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.kjellvos.aletho.zombieshooter.gdx.Constants;

public class MapBodyBuilder {
    /**
     * Builds the walls of the map from the right map layer
     * @param map the tiled map containing the map objects
     * @param world the Box2D world in which the map objects should be placed
     */
    public static void buildShapes(Map map, World world) {
        MapObjects objects = map.getLayers().get("Walls").getObjects();

        for(MapObject object : objects) {

            if (object instanceof TextureMapObject) {
                continue;
            }

            Shape shape;

            if (object instanceof RectangleMapObject) {
                shape = getRectangle((RectangleMapObject)object);
            }
            else if (object instanceof PolygonMapObject) {
                shape = getPolygon((PolygonMapObject)object);
            }
            else if (object instanceof PolylineMapObject) {
                shape = getPolyline((PolylineMapObject)object);
            }
            else if (object instanceof CircleMapObject) {
                shape = getCircle((CircleMapObject)object);
            }
            else {
                continue;
            }

            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.StaticBody;
            Body body = world.createBody(bd);
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.shape = shape;
            fixtureDef.filter.categoryBits = Constants.CATEGORY_WALL;
            fixtureDef.filter.maskBits = Constants.MASK_WALL;
            body.createFixture(fixtureDef);

            shape.dispose();
        }
    }

    /**
     * Used in buildShapes, This builds the rectangle map objects
     * @param rectangleObject the map object
     * @return the initialised map object ready to be added to the world
     */
    private static PolygonShape getRectangle(RectangleMapObject rectangleObject) {
        Rectangle rectangle = rectangleObject.getRectangle();
        PolygonShape polygon = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width * 0.5f),
                (rectangle.y + rectangle.height * 0.5f ));
        polygon.setAsBox(rectangle.width * 0.5f,
                rectangle.height * 0.5f,
                size,
                0.0f);
        return polygon;
    }


    /**
     * Used in buildShapes, This builds the circular map objects
     * @param circleObject the map object
     * @return the initialised map object ready to be added to the world
     */
    private static CircleShape getCircle(CircleMapObject circleObject) {
        Circle circle = circleObject.getCircle();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circle.radius);
        circleShape.setPosition(new Vector2(circle.x, circle.y));
        return circleShape;
    }

    /**
     *  Used in buildShapes, This builds the polygon map objects
     * @param polygonObject the map object
     * @return the initialised map object ready to be added to the world
     */
    private static PolygonShape getPolygon(PolygonMapObject polygonObject) {
        PolygonShape polygon = new PolygonShape();
        float[] vertices = polygonObject.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            System.out.println(vertices[i]);
            worldVertices[i] = vertices[i];
        }

        polygon.set(worldVertices);
        return polygon;
    }

    /**
     *  Used in buildShapes, This builds the polygon(line) map objects
     * @param polylineObject the map object
     * @return the initialised map object ready to be added to the world
     */
    private static ChainShape getPolyline(PolylineMapObject polylineObject) {
        float[] vertices = polylineObject.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for (int i = 0; i < vertices.length / 2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i * 2];
            worldVertices[i].y = vertices[i * 2 + 1];
        }

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }
}
