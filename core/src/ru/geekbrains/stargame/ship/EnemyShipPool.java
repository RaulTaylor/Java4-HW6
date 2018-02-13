package ru.geekbrains.stargame.ship;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.bullet.BulletPool;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.pool.SpritesPool;

/**
 * Created by raultaylor on 13.02.18.
 */

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private final TextureAtlas atlas;
    private final BulletPool bulletPool;

    public EnemyShipPool(TextureAtlas atlas,BulletPool bulletPool){
        this.atlas = atlas;
        this.bulletPool = bulletPool;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(atlas, bulletPool);
    }


    public void resize(Rect worldBounds){
        for(EnemyShip ship: activeObjects){
            ship.resize(worldBounds);
        }
    }

    @Override
    public void debugLog() {
        System.out.println(activeObjects.size() + " / "+ freeObjects.size());
        super.debugLog();
    }
}
