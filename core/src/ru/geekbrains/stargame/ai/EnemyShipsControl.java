package ru.geekbrains.stargame.ai;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.bullet.BulletPool;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;
import ru.geekbrains.stargame.ship.EnemyShip;
import ru.geekbrains.stargame.ship.EnemyShipPool;

/**
 * Created by raultaylor on 13.02.18.
 */

public class EnemyShipsControl {

    private final TextureAtlas atlas;
    private final BulletPool bulletPool;
    private final EnemyShipPool enemyShipPool;

    private Rect worldBounds;

    private float timer = 0;
    private float currentRND = 0;

    private final int MAX_ACTIVE_SHIP = 4;
    private final float MAX_INTERVAL = 2.5f;
    private final float MIN_INTERVAL = 0.5f;

    public EnemyShipsControl(TextureAtlas atlas, BulletPool bulletPool){
        this.bulletPool = bulletPool;
        this.atlas = atlas;
        enemyShipPool = new EnemyShipPool(atlas, bulletPool);
    }


    public void update(float delta){
        if(currentRND == 0){
            currentRND = Rnd.nextFloat(MIN_INTERVAL, MAX_INTERVAL);
        }
        timer+=delta;
        if(timer>currentRND && enemyShipPool.getNumberActiveObjects() < MAX_ACTIVE_SHIP){
            EnemyShip enemyShip = enemyShipPool.obtain();
            enemyShip.resize(worldBounds);
            enemyShip.set();
            currentRND = 0;
            timer = 0;
            System.out.println("Create enemyShip");
            enemyShipPool.debugLog();
        }
        enemyShipPool.updateActiveObjects(delta);
    }

    public EnemyShipPool getPool(){
        return enemyShipPool;
    }

    public void resize(Rect worldBounds){
        this.worldBounds = worldBounds;
        enemyShipPool.resize(worldBounds);
    }



}
