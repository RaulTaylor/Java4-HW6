package ru.geekbrains.stargame.ship;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.bullet.BulletPool;
import ru.geekbrains.stargame.engine.math.Rnd;

/**
 * Created by raultaylor on 13.02.18.
 */

public class EnemyShip extends Ship{

    private final float SHIP_HEIGHT = 0.11f;
    private final Vector2 v0 = new Vector2(0.0f, -0.2f);

    public EnemyShip(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("enemy2"), 1, 2, 2);
        setHeightProportion(SHIP_HEIGHT);
        this.bulletPool = bulletPool;
        this.shootSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        this.bulletRegion = atlas.findRegion("bulletEnemy");
        this.bulletHeight = 0.01f;
        this.bulletV.set(0, -0.5f);
        this.bulletDamage = 1;
        this.reloadInterval = 1.2f;
    }


    public void update(float delta){
        pos.mulAdd(v0,delta);
        reloadTimer +=delta;
        if(getTop()<worldBounds.getBottom()){
            setDestroyed(true);
        }
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
    }

    public void set(){
        setDestroyed(false);
        pos.set(Rnd.nextFloat(worldBounds.getLeft(),worldBounds.getRight()),0);
        if(getLeft()<worldBounds.getLeft()){
            setLeft(worldBounds.getLeft());
        }
        if(getRight()>worldBounds.getRight()){
            setRight(worldBounds.getRight());
        }
        setBottom(worldBounds.getTop());
    }
}
