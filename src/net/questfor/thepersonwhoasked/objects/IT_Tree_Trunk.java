package net.questfor.thepersonwhoasked.objects;

import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Random;
import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;

public class IT_Tree_Trunk extends LivingEntity {
    public IT_Tree_Trunk(MainGame gpp, int col, int row) {
        super(gpp);
        this.worldx = GlobalGameThreadConfigs.tilesize * col;
        this.worldy = GlobalGameThreadConfigs.tilesize*row;
        EntityType = 3;
        hitbox = new Rectangle(0, 0, 0, 0);
        collision = false;
        LightSource = false;

    }
    public void HandleItems(){
        int I = new Random().nextInt(100)+1;
        if(I < 50){
            DropItems(new OBJ_MANA_CRYSTAL(gp));
            DropItems(new OBJHeart(gp));
        }else {
            DropItems(new OBJ_COIN_BRONZE(gp));
        }
    }

    @Override
    public void getImageInstance() {
        down1 = BufferedRenderer("TileEntity/trunk", GlobalGameThreadConfigs.tilesize, GlobalGameThreadConfigs.tilesize);
    }
    public LivingEntity getDestroyedForm(){return new IT_Tree_Trunk(gp, (int) (worldx/GlobalGameThreadConfigs.tilesize), (int) worldy/GlobalGameThreadConfigs.tilesize);}
}
