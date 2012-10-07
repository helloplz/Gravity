package com.gravity.player;

import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.google.common.collect.Lists;
import com.gravity.root.Renderer;

public class PlayerRenderer implements Renderer {
    private Player player;
    private Image bunnyPlayer;
    private Image bunnyPlayerRun1;
    private Image bunnyPlayerRun2;
    private Image bunnyPlayerRun3;
    private Image bunnyPlayerRun4;
    private Image bunnyPlayerRun5;
    private Image bunnyPlayerRun6;
    private Image bunnyPlayerBack;
    private Image bunnyPlayerRunBack1;
    private Image bunnyPlayerRunBack2;
    private Image bunnyPlayerRunBack3;
    private Image bunnyPlayerRunBack4;
    private Image bunnyPlayerRunBack5;
    private Image bunnyPlayerRunBack6;
    private Image bunnyPlayerJump1;
    private Image bunnyPlayerJump2;
    private Image bunnyPlayerJump3;
    private Image bunnyPlayerJump4;
    private Image bunnyPlayerJump5;
    private Image bunnyPlayerJump6;
    private Image bunnyPlayerDuck1;
    private Image bunnyPlayerDuck2;
    private Image bunnyPlayerDuck3;
    private float x;
    private float y;
    private Image lastImage;
    private List<Image> runningBunny;
    private List<Image> runningBackBunny;
    private int tweener;

    public PlayerRenderer(Player player) {
        this.player = player;
        try {
            bunnyPlayer = new Image("./assets/BunnyAssets/bunnyStand.png");
            bunnyPlayerRun1 = new Image("./assets/BunnyAssets/bunnyRun1.png");
            bunnyPlayerRun2 = new Image("./assets/BunnyAssets/bunnyRun2.png");
            bunnyPlayerRun3 = new Image("./assets/BunnyAssets/bunnyRun3.png");
            bunnyPlayerRun4 = new Image("./assets/BunnyAssets/bunnyRun4.png");
            bunnyPlayerRun5 = new Image("./assets/BunnyAssets/bunnyRun5.png");
            bunnyPlayerRun6 = new Image("./assets/BunnyAssets/bunnyRun6.png");
            bunnyPlayerJump1 = new Image("./assets/BunnyAssets/bunnyJump1.png");
            bunnyPlayerJump2 = new Image("./assets/BunnyAssets/bunnyJump2.png");
            bunnyPlayerJump3 = new Image("./assets/BunnyAssets/bunnyJump3.png");
            bunnyPlayerJump4 = new Image("./assets/BunnyAssets/bunnyJump4.png");
            bunnyPlayerJump5 = new Image("./assets/BunnyAssets/bunnyJump5.png");
            bunnyPlayerJump6 = new Image("./assets/BunnyAssets/bunnyJump6.png");
            bunnyPlayerDuck1 = new Image("./assets/BunnyAssets/bunnyDuck1.png");
            bunnyPlayerDuck2 = new Image("./assets/BunnyAssets/bunnyDuck2.png");
            bunnyPlayerDuck3 = new Image("./assets/BunnyAssets/bunnyDuck3.png");
            bunnyPlayerBack = new Image("./assets/BunnyAssets/bunnyStandBack.png");
            bunnyPlayerRunBack1 = new Image("./assets/BunnyAssets/bunnyRunBack1.png");
            bunnyPlayerRunBack2 = new Image("./assets/BunnyAssets/bunnyRunBack2.png");
            bunnyPlayerRunBack3 = new Image("./assets/BunnyAssets/bunnyRunBack3.png");
            bunnyPlayerRunBack4 = new Image("./assets/BunnyAssets/bunnyRunBack4.png");
            bunnyPlayerRunBack5 = new Image("./assets/BunnyAssets/bunnyRunBack5.png");
            bunnyPlayerRunBack6 = new Image("./assets/BunnyAssets/bunnyRunBack6.png");

        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
        runningBunny = Lists.newArrayList();
        runningBunny.add(bunnyPlayerRun1);
        runningBunny.add(bunnyPlayerRun2);
        runningBunny.add(bunnyPlayerRun3);
        runningBunny.add(bunnyPlayerRun4);
        runningBunny.add(bunnyPlayerRun5);
        runningBunny.add(bunnyPlayerRun6);

        runningBackBunny = Lists.newArrayList();
        runningBackBunny.add(bunnyPlayerRunBack1);
        runningBackBunny.add(bunnyPlayerRunBack2);
        runningBackBunny.add(bunnyPlayerRunBack3);
        runningBackBunny.add(bunnyPlayerRunBack4);
        runningBackBunny.add(bunnyPlayerRunBack5);
        runningBackBunny.add(bunnyPlayerRunBack6);
    }

    @Override
    public void render(Graphics g) {
        this.x = player.getPosition().x;
        this.y = player.getPosition().y + 512;
        if (tweener % 4 == 0) {
            if (lastImage == null) {
                g.drawImage(bunnyPlayer, this.x, this.y);
                lastImage = bunnyPlayer;
            } else if (player.getVelocity(0).x > 0) {
                g.drawImage((runningBunny.get(runningBunny.indexOf(lastImage) + 1)), this.x, this.y);
                lastImage = runningBunny.get(runningBunny.indexOf(lastImage) + 1);
                if (lastImage == bunnyPlayerRun6) {
                    lastImage = bunnyPlayerRun1;
                }
            } else if (player.getVelocity(0).x < 0) {
                g.drawImage((runningBackBunny.get(runningBackBunny.indexOf(lastImage) + 1)), this.x, this.y);
                lastImage = runningBackBunny.get(runningBackBunny.indexOf(lastImage) + 1);
                if (lastImage == bunnyPlayerRunBack6) {
                    lastImage = bunnyPlayerRunBack1;
                }
            } else {
                g.drawImage(bunnyPlayer, this.x, this.y);
                lastImage = bunnyPlayer;

            }

        }

        else {
            g.drawImage(lastImage, this.x, this.y);
        }
        tweener++;
    }
}
