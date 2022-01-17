package io.github.softwarecat.simplegameengine.render;

import io.github.softwarecat.simplegameengine.entity.Entity;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    protected Canvas canvas;
    protected GraphicsContext context;

    protected List<Entity> entities = new ArrayList<>();

    public Renderer(Canvas canvas) {
        this.canvas = canvas;
        this.context = canvas.getGraphicsContext2D();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        entity.start();
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void clearEntities() {
        entities.clear();
    }

    public void render(float deltaTime) {
        context.save();

        for (Entity entity : entities) {
            entity.update(deltaTime);

            transformContext(entity);

            entity.draw(context);
        }

        context.restore();
    }

    public void prepare(){
        context.setFill(Color.WHITE);
        context.fillRect(0,0, canvas.getWidth(),canvas.getHeight());
    }

    private void transformContext(Entity entity){
        context.translate(entity.transform.position.getX(), entity.transform.position.getY());
        context.rotate(entity.transform.rotation);
        context.scale(entity.transform.scale.getX(), entity.transform.scale.getY());
    }
}
