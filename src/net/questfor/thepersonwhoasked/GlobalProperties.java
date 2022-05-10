package net.questfor.thepersonwhoasked;

import net.questfor.thepersonwhoasked.Maingam.GlobalGameThreadConfigs;
import net.questfor.thepersonwhoasked.Maingam.MainGame;
import net.questfor.thepersonwhoasked.entities.Mobs.green_slime;
import net.questfor.thepersonwhoasked.entities.NPCS.Old_Man;
import net.questfor.thepersonwhoasked.objects.*;
import net.questfor.thepersonwhoasked.tile_entites.IT_tree;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GlobalProperties {
    /*SETS THE LOCATION OF ENTITIES ON THERE WORLD*/
    static MainGame gp;

    public void Render(MainGame gpp) {
        this.gp = gpp;
    }

    public void setObjectRenderer() {
        /*RENDER OBJECTS*/
        int i = 6;
        int mapID = 0;
        GlobalGameThreadConfigs.obj[mapID][0] = new chest(gp);
        GlobalGameThreadConfigs.obj[mapID][0].worldx = gp.tilesize * 11;
        GlobalGameThreadConfigs.obj[mapID][0].worldy = gp.tilesize * 8;
        GlobalGameThreadConfigs.obj[mapID][0].inventory.add(new OBJkey(gp));
        GlobalGameThreadConfigs.obj[mapID][0].inventory.add(new OBJ_BRICK_WALL(gp));
        GlobalGameThreadConfigs.obj[mapID][0].inventory.add(new OBJ_BRICK_WALL(gp));
        GlobalGameThreadConfigs.obj[mapID][0].inventory.add(new OBJ_BRICK_WALL(gp));
        GlobalGameThreadConfigs.obj[mapID][0].inventory.add(new OBJ_BRICK_WALL(gp));
        GlobalGameThreadConfigs.obj[mapID][0].worldz = 0;
        GlobalGameThreadConfigs.obj[mapID][0].inventory.add(new OBJ_BRICK_WALL(gp));

            GlobalGameThreadConfigs.obj[mapID][1] = new OBJ_COIN_BRONZE(gp);
            GlobalGameThreadConfigs.obj[mapID][2] = new OBJkey(gp);
            GlobalGameThreadConfigs.obj[mapID][2].worldx = gp.tilesize * 25;
            GlobalGameThreadConfigs.obj[mapID][2].worldy = gp.tilesize * 19;
            GlobalGameThreadConfigs.obj[mapID][3] = new OBJkey(gp);
            GlobalGameThreadConfigs.obj[mapID][3].worldx = gp.tilesize * 23;
            GlobalGameThreadConfigs.obj[mapID][3].worldy = gp.tilesize * 19;
            GlobalGameThreadConfigs.obj[mapID][4] = new OBJ_IRON_AXE(gp);
            GlobalGameThreadConfigs.obj[mapID][4].worldx = gp.tilesize * 17;
            GlobalGameThreadConfigs.obj[mapID][4].worldy = gp.tilesize * 20;
            GlobalGameThreadConfigs.obj[mapID][5] = new OBJ_IRON_SWORD(gp);
            GlobalGameThreadConfigs.obj[mapID][5].worldx = gp.tilesize * 32;
            GlobalGameThreadConfigs.obj[mapID][5].worldy = gp.tilesize * 21;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJ_IRON_SWORD(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 35;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 23;
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJ_IRON_SWORD(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 30;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 23;
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJ_MANA_CRYSTAL(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 24;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 23;
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJ_IRON_AXE(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 25;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 23;
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJ_COIN_BRONZE(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 26;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 23;
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJHeart(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 24;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 21;
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJ_SHIELD_DIAMOND(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 24;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 22;
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new chest(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 21;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 23;
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_BRICK_WALL(gp));
            GlobalGameThreadConfigs.obj[mapID][i].inventory.add(new OBJ_POTION_HEALTH_1(gp));
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJ_POTION_HEALTH_1(gp);
            GlobalGameThreadConfigs.obj[mapID][i].worldx = gp.tilesize * 20;
            GlobalGameThreadConfigs.obj[mapID][i].worldy = gp.tilesize * 23;
            i++;
            GlobalGameThreadConfigs.obj[mapID][i] = new OBJdoor(gp, 12, 12);
            mapID = 1;
            GlobalGameThreadConfigs.obj[mapID][0] = new OBJdoor(gp, 12, 13);
        GlobalGameThreadConfigs.obj[mapID][1] = new chest(gp);
        GlobalGameThreadConfigs.obj[mapID][1].worldx = gp.tilesize * 11;
        GlobalGameThreadConfigs.obj[mapID][1].worldy = gp.tilesize * 8;
        GlobalGameThreadConfigs.obj[mapID][1].inventory.add(new OBJkey(gp));
        GlobalGameThreadConfigs.obj[mapID][1].inventory.add(new OBJ_POTION_HEALTH_1(gp));
        GlobalGameThreadConfigs.obj[mapID][1].inventory.add(new OBJ_IRON_AXE(gp));
        GlobalGameThreadConfigs.obj[mapID][1].inventory.add(new OBJ_POTION_HEALTH_1(gp));
        GlobalGameThreadConfigs.obj[mapID][1].inventory.add(new OBJ_BRICK_WALL(gp));
        GlobalGameThreadConfigs.obj[mapID][1].worldz = 0;
        GlobalGameThreadConfigs.obj[mapID][1].inventory.add(new OBJ_POTION_HEALTH_1(gp));

    }

    /*render NPCS and MONSTERS*/
    public void setNPCrenderers() {
        int mapID = 0;

            GlobalGameThreadConfigs.NPCS[mapID][0] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[mapID][0].worldx = gp.tilesize * 21;
        GlobalGameThreadConfigs.NPCS[mapID][0].worldy = gp.tilesize * 21;
        GlobalGameThreadConfigs.NPCS[mapID][0].worldz = 0;
        GlobalGameThreadConfigs.NPCS[mapID][1] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[mapID][1].worldx = gp.tilesize * 22;
        GlobalGameThreadConfigs.NPCS[mapID][1].worldy = gp.tilesize * 22;
        GlobalGameThreadConfigs.NPCS[mapID][1].worldz = 0;
        GlobalGameThreadConfigs.NPCS[mapID][2] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[mapID][2].worldx = gp.tilesize * 23;
        GlobalGameThreadConfigs.NPCS[mapID][2].worldy = gp.tilesize * 23;
        GlobalGameThreadConfigs.NPCS[mapID][2].worldz = 0;
        GlobalGameThreadConfigs.NPCS[mapID][3] = new Old_Man(gp);
        GlobalGameThreadConfigs.NPCS[mapID][3].worldx = gp.tilesize * 11;
        GlobalGameThreadConfigs.NPCS[mapID][3].worldy = gp.tilesize * 9;
        GlobalGameThreadConfigs.NPCS[mapID][3].worldz = 0;
        GlobalGameThreadConfigs.NPCS[mapID][3].speed = 0;
        GlobalGameThreadConfigs.NPCS[mapID][3].dialogues[1] = "Hello. its a nice day \n outside isnt it?";
        GlobalGameThreadConfigs.NPCS[mapID][3].dialogues[2] = "You should go to the main square,\n were all going to be there";
        GlobalGameThreadConfigs.NPCS[mapID][3].dialogues[3] = "OK bye!";
        mapID = 1;
            GlobalGameThreadConfigs.NPCS[mapID][0] = new Old_Man(gp);
            GlobalGameThreadConfigs.NPCS[mapID][0].worldx = gp.tilesize * 11;
            GlobalGameThreadConfigs.NPCS[mapID][0].worldy = gp.tilesize * 9;
            GlobalGameThreadConfigs.NPCS[mapID][0].worldz = 0;


}
    public  void setMonsterRenderers(){
        int mapID = 0;
        GlobalGameThreadConfigs.Monsters[mapID][0] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][0].worldx = gp.tilesize*23;
        GlobalGameThreadConfigs.Monsters[mapID][0].worldy = gp.tilesize*36;
        GlobalGameThreadConfigs.Monsters[mapID][0].worldz = 0;
        GlobalGameThreadConfigs.Monsters[mapID][1] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][1].worldx = gp.tilesize*21;
        GlobalGameThreadConfigs.Monsters[mapID][1].worldy = gp.tilesize*36;
        GlobalGameThreadConfigs.Monsters[mapID][2] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][2].worldx = gp.tilesize*22;
        GlobalGameThreadConfigs.Monsters[mapID][2].worldy = gp.tilesize*36;
        GlobalGameThreadConfigs.Monsters[mapID][3] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][3].worldx = gp.tilesize*22;
        GlobalGameThreadConfigs.Monsters[mapID][3].worldy = gp.tilesize*35;
        GlobalGameThreadConfigs.Monsters[mapID][4] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][4].worldx = gp.tilesize*22;
        GlobalGameThreadConfigs.Monsters[mapID][4].worldy = gp.tilesize*34;
        GlobalGameThreadConfigs.Monsters[mapID][5] = new green_slime(gp);
        GlobalGameThreadConfigs.Monsters[mapID][5].worldx = gp.tilesize*24;
        GlobalGameThreadConfigs.Monsters[mapID][5].worldy = gp.tilesize*35;
    }
    public void setTileEntityRenderers(){int i = 0; int mapID = 0;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 27, 12);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 28, 12);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 29, 12);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 30, 12);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 31, 12);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 32, 12);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 33, 12);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 26, 20);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 26, 21);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 26, 22);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 20, 20);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 20, 21);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 20, 22);i++;
        //bottom
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 22, 24);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 23, 24);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 24, 24);i++;
        //top
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 22, 18);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 23, 18);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 24, 18);i++;
        //hut
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 18, 40);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 17, 40);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 16, 40);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 15, 40);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 14, 40);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 13, 40);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 13, 41);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 12, 41);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 11, 41);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 10, 41);i++;
        GlobalGameThreadConfigs.Tentity[mapID][i] = new IT_tree(gp, 10, 40);i++;
    }

    public void setScreenRenderer() {
        GlobalGameThreadConfigs.tempscreen = new BufferedImage(MainGame.screenwidth, MainGame.screenheight, BufferedImage.TYPE_INT_ARGB);
        GlobalGameThreadConfigs.g2 = (Graphics2D)GlobalGameThreadConfigs.tempscreen.getGraphics();

    }

}
