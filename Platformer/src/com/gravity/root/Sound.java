package com.gravity.root;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public final class Sound {

    private Sound() {
    }

    private static final Music gameMusic;

    static {
        try {
            gameMusic = new Music("./assets/Sound/Forminas.wav");
        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playBGM() {
        gameMusic.loop();
    }
}
