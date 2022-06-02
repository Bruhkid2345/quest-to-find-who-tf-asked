package net.questfor.thepersonwhoasked.entities;
import net.questfor.thepersonwhoasked.Maingam.*;
import net.questfor.thepersonwhoasked.entities.AI.Path;
import net.questfor.thepersonwhoasked.tile.Tilemanager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;


//is the parent class for all entities including player
public class LivingEntity extends Data {
    static final long serialVersionUID = -6942014L;
    //WORLD//
    public MainGame gp;
    public transient Path path;
    public  double worldx;
    public  double worldy;
    public boolean goingup;
    public  double worldz = 4;
    public double speed;
    public boolean hascolided = false;
    //HEALTH//
    public int maxhealth;
    public int health;
    public int isred = 1;
    public boolean alive = true;
    public boolean dying = false;
    public int regenerationcooldown = 0;
    //RENDERER//
    public transient BufferedImage image, image2, image3;
    public transient  BufferedImage up1, up2, down1, down2, down3, left1, left2, right1, right2;
    public transient BufferedImage attackup1, attackup2, attackdown1, attackdown2, attackdown3,
            attackleft1, attackleft2, attackright1, attackright2;
    boolean drawingpath = false;
    //MOVEMENT AND ANIMATION//
    public String direction = "down";
    public int spritecounter = 0;
    public int spritenumber = 1;
    int animationLength = 0;
    //HITBOX//
    public Rectangle hitbox;
    public int i = 0;
    public int jumpstate = 0;
    public int jumpaction = 0;
    public Rectangle attackHitbox = new Rectangle(0, 0, 0, 0);
    public int hitboxdefaultx, hitboxdefaulty;
    public boolean collision = false;
    public boolean hitboxe = false;
    //AI//
    public int actionLock = 0;
    public  boolean isup = false;
    public boolean Hostile = false;
    public boolean frozen = false;
    public double taskx = 0, tasky = 0;
    public boolean forgiveondeath = true;
    public boolean onpath = false;
    public LivingEntity target;
    public double distancex = 0, distancey = 0;
    public double previoustaskx = 1, previoustasky = 1;
    public int HostileTime = 0;
    //ATTACK//
    public boolean invincible = false;
    public int hitTime = 0;
    //DATA//
    public int primepowercool = 0;
    public int EntityType; //0 = player, 1 = monster, 2 = NPC, 3 = Item, 4 = Object.
    public String name = "";
    public ArrayList<LivingEntity> inventory = new ArrayList<>();
    public int MaxMana;
    public int Mana;
    public int Ammo;
    public Projectile projectile;
    public int cantalk = 0;
    public String dialogues[] = new String[20];
    public int dialogueIndex = 0;
    public int level;
    public int strength;
    public int hunger;
    public int defence;
    public int dexterity;
    public int attackspeed;
    public int XP;
    public int MaxXP;
    public int bobux;

    public LivingEntity currentweapon;
    public LivingEntity currentshield;
    public int TrueAttackDamage;
    /**OBJECT DATA**/
    public int AttackValue = 1;
    public int stacksize = 1;
    public int maxstacksize = 1;
    public int defenceValue = 1;
    public int Value = 1;
    public boolean first, secound;
    public int inventorysize = 20;
    public String description = "";
    public int Type = 0;
    public int frames = 2;
    public int Type_sword = 1, Type_constumable = 2, Type_pickaxe = 3, Type_armor = 4, Type_projectile = 5, Type_Current = 6, Type_axe = 7, Type_shovel = 8;
    public int UseCost;
    //SMELTING
    public boolean fuel; public boolean smeltable; public LivingEntity Outcome; public boolean smelting = false; public int cool = 0; public int maxcool = 50; public int colspeed = 1; public int hasfinushedcol = 0;
    //RECIPE
    public boolean[] slot =  new boolean[9];
    public boolean NBTDATA = false;
    public boolean jumping = false;

    //FUNCTIONS//
    public LivingEntity(MainGame gpp){
        this.gp = gpp;
        path = new Path();
    }
    public void setAction(){}
    public void getImageInstance(){}
    public void increasecool(){
        cool += colspeed;
        if(cool == maxcool){
            hasfinushedcol = 1;
        }

    }
    public void updatehitbox() {
        if (up1 != null) {
            if (worldz < 0){
                hitbox.width = up1.getWidth() - 16;
                hitbox.height = up1.getHeight() - 16;
            }
        }
    }


    public void getAttackInstance(){}
    public void Angry(){}
    public void makemeHostile(LivingEntity Target){
        Hostile = true;
        target = Target;
    }
    public void speak(){
        //dialogue functions

        UI.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction){
            case "up" -> {direction = "down";}
            case "down" -> {direction = "up"; }
            case "right" -> {direction = "left"; }
            case "left" -> {direction = "right"; }
        }
    }
    public void update() {
        updateimage();
        if (health > 0){
            Regenerate();
    }else{
            dying = true;
        }

        if (MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.Monsters) || MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.Tentity) || MainGame.hregister.worldzentityreturn(this, GlobalGameThreadConfigs.NPCS) || MainGame.hregister.worldzobjectreturn(this) || MainGame.hregister.returntileworldz(this)) {
            if (!isup) {
                worldz--;
                getImageInstance();
                updatehitbox();
                getAttackInstance();
            }
        }
        /*AI for Monsters And NPCS*/
        setAction();
        checkCollision();
        if(hitboxe){
            if(!jumping) {
                jumping = true;
                isup = true;
            }
        }
        if(primepowercool < 30){
            primepowercool++;
        }
        if(dialogues[dialogueIndex] == null){
            cantalk++;
        }
        if(cantalk > 30){
            cantalk = 0;
            dialogueIndex = 0;
        }
        if (!hitboxe) {
            switch (direction){
                case"up":
                    worldy = worldy - speed;
                    break;
                case "down":
                    worldy = worldy + speed;
                    break;

                case "right":
                    worldx = worldx + speed;
                    break;
                case "left":
                    worldx = worldx - speed;
                    break;
            }
        }
        spritecounter++;
        if (spritecounter > 12) {
            if (spritenumber == 1) {
                spritenumber = 2;
            } else if (spritenumber == 2) {
                spritenumber = 1;
            }
            spritecounter = 0;
        }
        if(invincible){
            hitTime++;
            if(hitTime > 40){
                invincible = false;
                hitTime = 0;
            }
        }
        if(health <= 0){
            dying = true;
        }
    }

    public void updateimage() {
        if(up1 == null && image == null && down1 == null){
            getImageInstance();
        }
    }
    public void set(){}

    public void AttackNPC(int trueAttackDamage, int npcindex) {
        if(!GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].invincible){
            int damage = trueAttackDamage - GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].defence;
            if(damage <= 0){
                damage = 0;
            }
            GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].health-= damage;
            if(GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].name.equals("Old man")){
            UI.addMessages("Help me im being hurt!");}else if(GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].name.equals("Helper")){
                GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].Hostile = true;
            }
            GlobalGameThreadConfigs.NPCS[MainGame.currentmap][npcindex].invincible = true;
            gp.playsound(5);
        }
    }

    public void HandleItems(){}
    public void DropItems(LivingEntity droppedItem){
        for(int i = 0; i < GlobalGameThreadConfigs.obj[1].length; i++){
            if(GlobalGameThreadConfigs.obj[MainGame.currentmap][i] == null) {
                GlobalGameThreadConfigs.obj[MainGame.currentmap][i] = droppedItem;
                int I = new Random().nextInt(100) + 1;
                if (I > 50){
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldx = worldx + gp.tilesize/2;
            } else {
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldx = worldx - gp.tilesize/2;
                }
                I = new Random().nextInt(100) + 1;
                if(I > 50){
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldy = worldy + gp.tilesize/2;
                }else {
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][i].worldy = worldy - gp.tilesize/2;
                }
                break;
            }
        }
    }
    private void Regenerate() {
        if(!Hostile) {
            regenerationcooldown++;
            if (regenerationcooldown > 200) {
                if (health < maxhealth){
                    health++;
                regenerationcooldown = 0;
            }
        }
        }
    }
    public void Use(LivingEntity target){}
    public void AttackPLayer(int trueAttackDamage){
        if(!MainGame.player.invincible){
            int damage = trueAttackDamage - gp.player.defence;
            if(damage < 0){
                damage = 0;
            }
            MainGame.player.health-= damage;
            UI.addMessages("You have been hit! health is now to "+gp.player.health);
            MainGame.player.invincible = true;
            gp.playsound(5);
        }
    }
    public void draw(Graphics2D g2){
        if(path == null){
            path = new Path();
        }

        //RENDERER
        try {
            double screenX = (worldx - MainGame.player.worldx + MainGame.player.screenX);
            double screenY = worldy - MainGame.player.worldy + MainGame.player.screenY;
            if ((worldx + MainGame.tilesize > MainGame.player.worldx - MainGame.player.screenX &&
                    (worldx - MainGame.tilesize < MainGame.player.worldx + MainGame.player.screenX))
                    && worldy + MainGame.tilesize > MainGame.player.worldy - MainGame.player.screenY &&
                    (worldy - MainGame.tilesize < MainGame.player.worldy + MainGame.player.screenY)) {
                BufferedImage image = null;
                switch (direction) {
                    case "up":
                        if (spritenumber == 1) {
                            image = up1;
                        } else if (spritenumber == 2) {
                            image = up2;
                        }
                        break;
                    case "down":
                        if (spritenumber == 1) {
                            image = down1;
                        } else if (spritenumber == 2) {
                            image = down2;
                        }
                        break;
                    case "right":
                        if (spritenumber == 1) {
                            image = right1;
                        } else if (spritenumber == 2) {
                            image = right2;
                        }
                        break;
                    case "left":
                        if (spritenumber == 1) {
                            image = left1;
                        } else if (spritenumber == 2) {
                            image = left2;
                        }
                        break;
                }
                if(jumping){
                    jumpaction++;
                    if(jumpaction < 25){
                        if(isup) {
                            if (gp.hregister.checkWALL(this) && gp.hregister.checkentitywall(Math.round(worldx/gp.tilesize), Math.round(worldy/gp.tilesize), worldz, GlobalGameThreadConfigs.NPCS, this) && gp.hregister.checkentitywall(Math.round(worldx/gp.tilesize), Math.round(worldy/gp.tilesize), worldz,  GlobalGameThreadConfigs.Monsters, this) && gp.hregister.checkentitywall(Math.round(worldx/gp.tilesize), Math.round(worldy/gp.tilesize), worldz,  GlobalGameThreadConfigs.Tentity, this)) {
                                if (jumpaction == 1) {
                                    worldz++;
                                }
                                jumpstate++;
                                image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                            }
                        }
                    }else{
                        isup = false;
                        jumpstate--;
                        try {
                            image = scaleimage(image, image.getWidth() + jumpstate, image.getHeight() + jumpstate);
                        }catch (Exception e){
                            System.out.println("Catched null pointer exeption");
                        }
                        if(jumpstate < 0) {
                            jumpaction = 0;
                            jumping = false;
                            getAttackInstance();
                            getImageInstance();
                            updatehitbox();
                        }

                    }
                }
                //HP BAR
                if ((EntityType == 2 || EntityType == 1) && Hostile) {
                    double onescale = gp.tilesize / maxhealth;
                    double HPValue = onescale * health;
                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect((int) (screenX - 1), (int) screenY - 16, gp.tilesize + 2, 12);
                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect((int) screenX, (int) screenY - 15, (int) HPValue, 10);
                }
                if (Hostile) {
                    HostileTime++;
                    if (HostileTime > 2400) {
                        HostileTime = 0;
                        Hostile = false;
                    }
                }
                if (drawingpath){
                    g2.setColor(Color.red);
                for (int i = 0; i < path.pathlist.size(); i++) {
                    int worldx = path.pathlist.get(i).col * gp.tilesize;
                    int worldy = path.pathlist.get(i).row * gp.tilesize;
                    double screenx = (worldx - MainGame.player.worldx + MainGame.player.screenX);
                    double screeny = worldy - MainGame.player.worldy + MainGame.player.screenY;
                    g2.fillRect((int) screenx, (int) screeny, gp.tilesize, gp.tilesize);
                }
            }
                if(EntityType != 4){
                if (invincible) {
                    for (int y = 0; y < image.getHeight(); y++) {
                        for (int x = 0; x < image.getWidth(); x++) {
                            int p = image.getRGB(x, y);
                            int a = (p >> 24) & 0xff;
                            int r = (p >> 16) & 0xff;
                            p = (a << 24) | (r << 16) | (0 << 8) | 0;
                            image.setRGB(x, y, p);
                            isred = 2;
                        }
                    }
                }
                if (isred == 2) {
                    if (invincible == false) {
                        getImageInstance();
                        isred = 1;
                    }
                }
            }
                if (dying) {
                    DieAnimation(g2);
                }
                g2.setFont(g2.getFont().deriveFont(Font.BOLD));
                g2.setColor(Color.BLACK);
                if (EntityType != 3) {
                    if (EntityType != 4) {
                        if (Type != Type_projectile){
                            g2.setFont(g2.getFont().deriveFont(10F));
                            g2.drawString("level: " + level, (int) screenX, (int) (screenY - 30));
                    }
                }
            }
                g2.drawImage(image, (int) screenX, (int) screenY, null);
                drawwalls(g2, this);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }
        }catch (Exception e){
            crash.main(e);
        }
    }
    //DIE ANIMATION
    protected void DieAnimation(Graphics2D g2d) {
        animationLength++;
        int startframe = 5;
        if(animationLength <= startframe){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
        if(animationLength > startframe && animationLength <= startframe*2){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
        if(animationLength > startframe*2 && animationLength <= startframe*3){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
        if(animationLength > startframe*3 && animationLength <= startframe*4){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
        if(animationLength > startframe*4 && animationLength <= startframe*5){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
        if(animationLength > startframe*5 && animationLength <= startframe*6){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
        if(animationLength > startframe*6 && animationLength <= startframe*7){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));}
        if(animationLength > startframe*7 && animationLength <= startframe*8){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));}
        if(animationLength > startframe*8){g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));alive = false;}
    }

    public BufferedImage BufferedRenderer(String imagePath, int width, int height){
        //OPTIMIZES THE RENDERER TO MAKE IT MORE EFFICIENT
        UtilityTool utool = new UtilityTool();
        BufferedImage ScaledImage = null;
        try{
            ScaledImage = ImageIO.read(getClass().getResourceAsStream("/entities/"+imagePath+".png"));
            ScaledImage = utool.scaleimage(ScaledImage, width, height);
        }catch (Exception e) {
            crash.main(e);
        }
        return ScaledImage;
    }

    /**PARTICLES**/
    public Color getparticleColor(){return null;}
    public void attackEntity(int attackindex, int dmg) {
        if (attackindex != 999) {
            if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].invincible == false) {
                gp.playsound(6);
                int damage = dmg - GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].defence;
                if (damage < 0) {
                    damage = 0;
                }
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].health -= damage;
                ParticleAttackManager( this, GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex]);
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].makemeHostile(this);
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].HostileTime = 0;
                GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].invincible = true;
            }
            if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].dying == false) {
                if (GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].health < 0) {
                    GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].dying = true;
                    UI.addMessages("Killed " + GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].name);
                    XP += GlobalGameThreadConfigs.Monsters[MainGame.currentmap][attackindex].XP;
                    levelUpAchiver();
                }
            }
        }
    }
    public void levelUpAchiver() {
        if (XP >= MaxXP) {
            level++;
            MaxXP = MaxXP * 2;
            maxhealth += 2;
            strength++;
            dexterity++;
            TrueAttackDamage = getAttackValues();
            defence = getDefenceValues();
            gp.playsound(8);

        }
    }
    public int getAttackValues(){return 0;}
    public int getDefenceValues(){return 0;}

    public int getparticleSize(){return 0;}
    public int getparticlespeed(){return 0;}
    public int getparticleMaxHealth(){return 0;}
    //SET PARTICLES AND THERE PROPERTIES
    public void ParticlePropertyManager(LivingEntity Source, LivingEntity target){Color texture = Source.getparticleColor(); int size = Source.getparticleSize(); int speed = Source.getparticlespeed(); int MaxHealth = Source.getparticleMaxHealth();
        Particle p1 = new Particle(gp, target, texture, size, speed, MaxHealth, -2, -1);
        Particle p2 = new Particle(gp, target, texture, size, speed, MaxHealth, 2, -1);
        Particle p3 = new Particle(gp, target, texture, size, speed, MaxHealth, -2, 1);
        Particle p4 = new Particle(gp, target, texture, size, speed, MaxHealth, 2, 1);
        GlobalGameThreadConfigs.particleList.add(p1);
        GlobalGameThreadConfigs.particleList.add(p2);
        GlobalGameThreadConfigs.particleList.add(p3);
        GlobalGameThreadConfigs.particleList.add(p4);
    }
    public void ParticleAttackManager(LivingEntity Source, LivingEntity target){Color texture = target.getparticleColor(); int size = target.getparticleSize(); int speed = target.getparticlespeed(); int MaxHealth = target.getparticleMaxHealth();
        Particle p1 = new Particle(gp, target, texture, size, speed, MaxHealth, -2, -1);
        Particle p2 = new Particle(gp, target, texture, size, speed, MaxHealth, 2, -1);
        Particle p3 = new Particle(gp, target, texture, size, speed, MaxHealth, -2, 1);
        Particle p4 = new Particle(gp, target, texture, size, speed, MaxHealth, 2, 1);
        GlobalGameThreadConfigs.particleList.add(p1);
        GlobalGameThreadConfigs.particleList.add(p2);
        GlobalGameThreadConfigs.particleList.add(p3);
        GlobalGameThreadConfigs.particleList.add(p4);
    }

    public void destroyTentity(int tileentityI) {
        if (tileentityI != 999 && GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].distructuble&& !GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].invincible) {
            if (GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].name.equals("Brick wall")) {
                if (GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].ItemRequirements(this)) {
                    GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].health-= TrueAttackDamage;
                    GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].playSE();
                    GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].invincible = true;
                    ParticlePropertyManager(GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI], GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI]);
                    if (GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].health <= 0) {
                        GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI] = GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].getDestroyedForm();
                        GlobalGameThreadConfigs.Tentity[MainGame.currentmap][tileentityI].HandleItems();
                    }
                }
            }
        }
    }
    public void DestroyOBJ(int tileentityI) {
        if (tileentityI != 999 && !GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].invincible) {
            if (GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].name.equals("Brick wall")) {
                if (GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].ItemRequirements(this)) {
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].health-= TrueAttackDamage;
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].playSE();
                    GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].invincible = true;
                    ParticlePropertyManager(GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI], GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI]);
                    if (GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].health <= 0) {
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI] = GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].getDestroyedForm();
                        GlobalGameThreadConfigs.obj[MainGame.currentmap][tileentityI].HandleItems();
                    }
                }
            }
        }
    }

    public boolean ItemRequirements(LivingEntity SourceEntity){return false;}
    public void playSE(){}
    public LivingEntity getDestroyedForm(){return null;}
    public void open(){}
    public void searchPath(double taskX, double taskY){
        if(path == null){
            path = new Path();
        }
        int startcol = (int) ((worldx+hitbox.x)/gp.tilesize);
        int startrow = (int) ((worldy+hitbox.y)/gp.tilesize);
        path.setNodes(startcol, startrow, (int) taskX, (int) taskY, (int) worldz, this);
        if(path.search()){
            int nextx = path.pathlist.get(0).col*gp.tilesize;
            int nexty = path.pathlist.get(0).row*gp.tilesize;
            int enleftx = (int) (worldx+hitbox.x);
            int enrightx = (int) (worldx+hitbox.x+hitbox.width);
            int enupy = (int) (worldy+hitbox.y);
            int endowny = (int) (worldy+hitbox.y+hitbox.height);
            if(enupy > nexty && enleftx >= nextx && enrightx < nextx +gp.tilesize){
                direction = "up";
            }else if(enupy < nexty && enleftx >= nextx && enrightx < nextx +gp.tilesize){
                direction = "down";
            } else if(enupy >= nexty && endowny < nexty + gp.tilesize){
                if(enleftx > nextx){
                    direction = "left";
                }else{
                    direction = "right";
                }
            }else if(enupy > nexty && enleftx > nextx){
                direction = "up";
                checkCollision();
                if(hitboxe){
                    direction = "left";
                }
            }else if(enupy > nexty && enleftx < nextx){
                direction = "up";
                checkCollision();
                if(hitboxe){
                    direction = "right";
                }
            }else if(enupy < nexty && enleftx > nextx){
                direction = "down";
                checkCollision();
                if(hitboxe){
                    direction = "left";
                }
            }else if(enupy < nexty && enleftx < nextx){
                direction = "down";
                checkCollision();
                if(hitboxe){
                    direction = "right";
                }
            }
            int nextcol = path.pathlist.get(0).col;
            int nextrow = path.pathlist.get(0).row;
            if(nextcol == taskX && nextrow == taskY) {
                if (forgiveondeath){
                    onpath = false;
                    Hostile = false;
            }
            }
        }else{
            actionLock++;
            Random random = new Random();
            int I = random.nextInt(100)+1;
            if (actionLock > 30) {
                    if (I > 50){
                        if (worldy < Math.round(taskY*gp.tilesize)) {
                            direction = "down";
                        } else {
                            direction = "up";
                        }
                }else{
                        if (worldx < Math.round(taskX*gp.tilesize)) {
                            direction = "right";
                        } else {
                            direction = "left";
                        }}
                    actionLock = 0;

        }}}
    public void checkCollision(){
        hitboxe = false;
        gp.hregister.checkTile(this);
        int npcindex = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.NPCS);
        if(EntityType == 1 && npcindex != 999){
            AttackNPC(TrueAttackDamage, npcindex);
        }
        gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Monsters);
        gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Tentity);
        int TileentityI = gp.hregister.EntityColide(this, GlobalGameThreadConfigs.Tentity);
        destroyTentity(TileentityI);
        DestroyOBJ(gp.hregister.EntityColide(this, GlobalGameThreadConfigs.obj));
        boolean ContactPLayer = gp.hregister.PlayerColide(this);
        if(EntityType == 1 && ContactPLayer){
            AttackPLayer(TrueAttackDamage);
        }
    }
    public BufferedImage scaleimage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
    public void drawwalls(Graphics2D g2, LivingEntity entity) {
        int worldcol = (int) (entity.worldx/MainGame.tilesize);
        int worldrow = (int) (entity.worldy / MainGame.tilesize);
        int worldlayer = 0;
        while (worldlayer < MainGame.maxworldlayer) {
            int tileID = gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer];
            int worldX = worldcol * MainGame.tilesize;
            int worldY = worldrow * MainGame.tilesize;
            double screenX = (worldX - MainGame.player.worldx + MainGame.player.screenX);
            double screenY = worldY - MainGame.player.worldy + MainGame.player.screenY;
            if (tileID != 46) {
                if (gp.tilemanager.mapRendererID[MainGame.currentmap][worldcol][worldrow][worldlayer + 1] == 46) {
                    if (worldlayer >= entity.worldz) {
                        g2.drawImage(gp.tilemanager.tile[tileID].image, (int) screenX, (int) screenY, null);

                    }
                }

            }
            worldlayer++;
        }
    }
}
