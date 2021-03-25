package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObjectsScreen extends BaseScreen {

    ObjectsScreen(MyGdxGame game) {
        super(game);
    }

    Random r = new Random();
    SpriteBatch spriteBatch;
    List<Globo> globoList;

    float gameTime;
    float alarmMoreBalls = 3;
    float alarmAddDifficult = 5;

    float moreSpeed =5;
    Texture background;

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        globoList = new ArrayList<>();
        globoList.add(new Globo());
        globoList.add(new Globo());
        globoList.add(new Globo());

        background = new Texture("background.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameTime += delta;

        // UPDATE
        for(Globo globo: globoList){
            globo.update(delta);
        }

        if(gameTime > alarmMoreBalls) {
            globoList.add(new Globo(moreSpeed));


            alarmMoreBalls = gameTime + r.nextInt(2)+0.5f;
        }

        if(gameTime > alarmAddDifficult){
            alarmAddDifficult = gameTime + 5;

            moreSpeed= moreSpeed+5;
        }



        for(Globo globo: globoList) {
            globo.movimientoLateral(gameTime);
        }




        // RENDER
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, 640, 480);
        for(Globo globo: globoList) globo.render(spriteBatch);
        spriteBatch.end();

    }


}