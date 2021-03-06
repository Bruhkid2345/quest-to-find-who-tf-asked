package net.questfor.thepersonwhoasked.Maingam;

import net.questfor.thepersonwhoasked.entities.LivingEntity;

import java.awt.*;
import java.util.Collections;
import java.util.Comparator;

public class GeneralHandler {
    public static void main(Graphics2D g2) {
        if(GlobalGameThreadConfigs.GameState == GlobalGameThreadConfigs.PlayState){
        //is the lead Renderer to handle all the different entites and objects.
        for (int i = 0; i < GlobalGameThreadConfigs.projectilelist[1].length; i++) {
            if (GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] != null) {
                if (GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i].alive) {
                    GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i].update();
                }
                if (!GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i].alive) {
                    GlobalGameThreadConfigs.projectilelist[MainGame.currentmap][i] = null;
                }
            }

        }

        for (int i = 0; i < GlobalGameThreadConfigs.particleList.size(); i++) {
            if (GlobalGameThreadConfigs.particleList.get(i) != null) {
                if (GlobalGameThreadConfigs.particleList.get(i).alive) {
                    GlobalGameThreadConfigs.particleList.get(i).update();
                }
                if (!GlobalGameThreadConfigs.particleList.get(i).alive) {
                    GlobalGameThreadConfigs.particleList.remove(i);
                }
            }
        }
    }
        Collections.sort(GlobalGameThreadConfigs.entitylist, new Comparator<LivingEntity>() {
            @Override
            public int compare(LivingEntity e1, LivingEntity e2) {
                int result;
                if(e1.Vehicle != e2.Vehicle){
                    result = Integer.compare( e1.Vehicle,  e2.Vehicle);
                }else{
                if (e1.worldz == e2.worldz) {
                    result = Integer.compare((int) e1.worldy, (int) e2.worldy);
                }else{
                    result = Integer.compare((int) e1.worldz, (int) e2.worldz);
                }}
                return result;
            }
        });
        for(int i = 0; i < GlobalGameThreadConfigs.entitylist.size(); i++) {

            GlobalGameThreadConfigs.entitylist.get(i).draw(g2);
        }
        GlobalGameThreadConfigs.entitylist.clear();
    }
}
