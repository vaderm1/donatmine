package igorlink.donationexecutor;

import igorlink.donationexecutor.executionsstaff.ExecUtils;
import igorlink.service.MainConfig;
import igorlink.service.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static igorlink.service.Utils.*;
import static java.lang.Math.random;
import static java.lang.Math.round;
import static org.bukkit.Bukkit.getPlayerExact;

public class Executor {
    public static String nameOfStreamerPlayer;
    public static String nameOfSecondStreamerPlayer;
    public static List<String> executionsNamesList = new ArrayList<>(Arrays.asList("ShitToInventory", "Lesch", "DropActiveItem",
            "PowerKick", "SpawnCreeper", "GiveDiamonds", "GiveStackOfDiamonds", "GiveBread",
            "CallNKVD", "CallStalin", "TamedBecomesEnemies", "HalfHeart", "BigBoom", "Nekoglai", "SetNight", "SetDay", "GiveIronSet",
            "GiveIronSword", "GiveDiamondSet", "GiveDiamondSword", "SpawnTamedDog", "SpawnTamedCat", "HealPlayer", "GiveIronKirka", "GiveDiamondKirka",
            "TakeOffBlock", "spawnThreeBlazes", "spawnThreeGhasts", "spawnFiveSpidersJockeys", "spawnSevenBlazes", "spawnThreeVexes", 
            "spawnFiveRedZombies", "spawnWitherBoss", "spawnThreeCreepers", "spawnWithersBehindAll", "startEternalNight", "spawnTenSilverfish",
            "spawnThreeMagmaCubes", "spawnTenInvisibleCaveSpiders", "spawnTenAggressiveSpiders", "spawnFiveWitherSkeletons",
            "spawnThirtyWithers", "spawnTenAggressiveEndermen", "spawnThreeWardens", "spawnFollowerDragon", "giveEnchantedNetheriteSword",
            "giveEnchantedNetheriteSet", "kickAllPlayers", "banAllPlayers", "callStalin1", "setSixtyHealth", "spawnFiveCreakings",
            "spawnBedrockPrison", "teleportToTheEnd", "spawnWarden", "spawnThirtyWardens", "spawnTenPoweredCreepers", "spawnFiveImmortalPhantoms",
            "spawnBedrockPrison"));



    public static void DoExecute(String streamerName, String donationUsername, String fullDonationAmount, String executionName) {

        Player streamerPlayer = getPlayerExact(streamerName);
        boolean canContinue = true;
        //Определяем игрока (если он оффлайн - не выполняем донат и пишем об этом в консоль), а также определяем мир, местоположение и направление игрока
        if (streamerPlayer == null) {
            canContinue = false;
        } else if (streamerPlayer.isDead()) {
            canContinue = false;
        }

        //Если имя донатера не указано - устанавливаем в качестве имени "Кто-то"
        String validDonationUsername;
        if (donationUsername.equals("")) {
            validDonationUsername = "Аноним";
        } else if (!isBlackListed(donationUsername)){
            validDonationUsername = donationUsername;
        } else {
            validDonationUsername = "Донатер";
            assert streamerPlayer != null;
            Utils.logToConsole("§eникнейм донатера §f" + donationUsername + "§e был скрыт, как подозрительный");
            streamerPlayer.sendActionBar("НИКНЕЙМ ДОНАТЕРА БЫЛ СКРЫТ");
        }


        if (!canContinue) {
            logToConsole("Донат от §b" + donationUsername + " §f в размере §b" + fullDonationAmount + "§f выполнен из-за того, что целевой стример был недоступен.");
            return;
        }


        switch (executionName) {
            case "ShitToInventory" -> shitToInventory(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "Lesch" -> lesch(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "DropActiveItem" -> dropActiveItem(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "PowerKick" -> powerKick(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "SpawnCreeper" -> spawnCreeper(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "GiveDiamonds" -> giveDiamonds(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "GiveStackOfDiamonds" -> giveStackOfDiamonds(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "GiveBread" -> giveBread(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "CallNKVD" -> callNKVD(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "CallStalin" -> callStalin(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "TamedBecomesEnemies" -> tamedBecomesEnemies(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "HalfHeart" -> halfHeart(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "BigBoom" -> bigBoom(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "Nekoglai" -> nekoglai(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "SetDay" -> setDay(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "SetNight" -> setNight(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "GiveIronSet" -> giveIronSet(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "GiveIronSword" -> giveIronSword(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "GiveDiamondSet" -> giveDiamondSet(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "GiveDiamondSword" -> giveDiamondSword(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "SpawnTamedDog" -> spawnTamedDog(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "SpawnTamedCat" -> spawnTamedCat(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "HealPlayer" -> healPlayer(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "GiveIronKirka" -> giveIronKirka(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "GiveDiamondKirka" -> giveDiamondKirka(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "TakeOffBlock" -> takeOffBlock(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "spawnSevenBlazes" -> spawnSevenBlazes(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "spawnThreeGhasts" -> spawnThreeGhasts(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "spawnFiveSpidersJockeys" -> spawnFiveSpidersJockeys(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "spawnThreeBreezes" -> spawnThreeBreezes(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "spawnThreeVexes" -> spawnThreeVexes(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "spawnFiveRedZombies" -> spawnFiveRedZombies(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "spawnWitherBoss" -> spawnWitherBoss(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "spawnThreeCreepers" -> spawnThreeCreepers(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "spawnWithersBehindAll" -> spawnWithersBehindAll(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnFiveWitherSkeletons" -> spawnFiveWitherSkeletons(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnTenAggressiveSpiders" -> spawnTenAggressiveSpiders(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnFiveInvisibleCaveSpiders" -> spawnTenInvisibleCaveSpiders(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnThreeMagmaCubes" -> spawnThreeMagmaCubes(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnTenSilverfish" -> spawnTenSilverfish(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnThirtyWithers" -> spawnThirtyWithers(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnTenAggressiveEndermen" -> spawnTenAggressiveEndermen(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnThreeWardens" -> spawnThreeWardens(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnThirtyWardens" -> spawnThirtyWardens(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "giveEnchantedNetheriteSword" -> giveEnchantedSet(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "giveEnchantedNetheriteSet" -> giveEnchantedNetheriteSet(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "banAllPlayers" -> banAllPlayers(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "kickAllPlayers" -> kickAllPlayers(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "CallStalin1" -> callStalin1(streamerPlayer, validDonationUsername,fullDonationAmount);
            case "setSixtyHealth" -> setSixtyHealth(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnFiveCreakings" -> spawnFiveCreakings(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "teleportToTheEnd" -> teleportToTheEnd(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnTenPoweredCreepers" -> spawnTenPoweredCreepers(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnFiveImmortalPhantoms" -> spawnFiveImmortalPhantoms(streamerPlayer, validDonationUsername, fullDonationAmount);
            case "spawnBedrockPrison" -> spawnBedrockPrison(streamerPlayer, validDonationUsername, fullDonationAmount);
            
        }

    }




    public static void shitToInventory (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "насрал тебе в инвентарь", "насрал в инвентарь", player, donationAmount, true);
        Material itemType = Material.DIRT;
        ItemStack itemStack = new ItemStack(itemType, 64);
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("§cГОВНО ОТ §f" + donationUsername.toUpperCase());
        meta.setLore(List.of("§7Это говно ужасно вонюче и занимает много места"));
        itemStack.setItemMeta(meta);

        for (int i = 0; i < MainConfig.getDirtAmount(); i++) {
            player.getInventory().addItem(itemStack);
        }
    
    }

    public static void teleportToTheEnd(Player player, String donationUsername, String donationAmount) {
    // 1. Ищем мир Энда
    org.bukkit.World endWorld = null;
    for (org.bukkit.World world : org.bukkit.Bukkit.getWorlds()) {
        if (world.getEnvironment() == org.bukkit.World.Environment.THE_END) {
            endWorld = world;
            break;
        }
    }

    if (endWorld == null) {
        player.sendMessage("§cМир Энда не найден на этом сервере!");
        return;
    }

    announce(donationUsername, "отправил тебя В ЭНД к дракону", "отправил в Энд", player, donationAmount, true);

    // 2. Стандартные координаты обсидиановой платформы в Энде: 100, 49, 0
    Location endPlatform = new Location(endWorld, 100.5, 49, 0.5);
    
    // 3. Создаем платформу 3x3 (на случай, если её нет)
    for (int x = -1; x <= 1; x++) {
        for (int z = -1; z <= 1; z++) {
            endWorld.getBlockAt(100 + x, 48, 0 + z).setType(Material.OBSIDIAN);
        }
    }

    // 4. Телепортируем
    player.teleport(endPlatform);
    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
    
    // 5. Сообщение в чат
    player.sendMessage("§d§lУдачи в сражении с Драконом!");
}

    public static void spawnBedrockPrison(Player player, String donationUsername, String donationAmount) {
    Location loc = player.getLocation().getBlock().getLocation().add(0.5, 0, 0.5); // Центрируем по сетке блоков
    announce(donationUsername, "посадил тебя в ТЮРЬМУ на 10 минут", "посадил в тюрьму", player, donationAmount, true);

    // Список для хранения состояний блоков
    java.util.List<org.bukkit.block.BlockState> oldBlocks = new java.util.ArrayList<>();

    // 1. Сначала телепортируем игрока точно в центр, чтобы его не вытолкнуло при постройке
    player.teleport(loc);

    // 2. Строим коробку
    for (int x = -1; x <= 1; x++) {
        for (int y = -1; y <= 3; y++) {
            for (int z = -1; z <= 1; z++) {
                // Оставляем пространство внутри (2 блока в высоту)
                if (x == 0 && (y == 0 || y == 1) && z == 0) {
                    loc.clone().add(x, y, z).getBlock().setType(Material.AIR); // На всякий случай чистим внутри
                    continue;
                }

                org.bukkit.block.Block block = loc.clone().add(x, y, z).getBlock();
                
                // ВАЖНО: не сохраняем блок, если это УЖЕ бедрок (защита от двойного доната)
                if (block.getType() != Material.BEDROCK) {
                    oldBlocks.add(block.getState());
                    block.setType(Material.BEDROCK);
                }
            }
        }
    }

    player.playSound(loc, Sound.BLOCK_ANVIL_LAND, 1.0f, 1.0f);

    // 3. Таймер (сократи 10 * 60 * 20L до 100L для теста!)
    new org.bukkit.scheduler.BukkitRunnable() {
        @Override
        public void run() {
            for (org.bukkit.block.BlockState state : oldBlocks) {
                // ПРОВЕРКА ЧАНКА: если чанк выгружен, принудительно грузим его
                if (!state.getChunk().isLoaded()) {
                    state.getChunk().load();
                }
                state.update(true, false); // true - форсировать, false - без обновления физики
            }
            
            if (player.isOnline()) {
                player.sendMessage("§a§lСвобода! §fСрок заключения истёк.");
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            }
        }
    }.runTaskLater(DonationExecutor.getInstance(), 10 * 60 * 20L);
}

    public static void nekoglai (Player player, String donationUsername, String donationAmount) {
        Vector direction = player.getLocation().getDirection();
        LivingEntity sheep;
        announce(donationUsername, "призвал ВАНЮТИБУ", "призвал Ванютибу", player, donationAmount, true);
        direction.setY(0);
        direction.normalize();
        Location newloc = player.getLocation().clone();
        Vector newdir = direction.clone().multiply(1.5);
        newloc.add(newdir);
        newloc.setDirection(player.getLocation().getDirection().clone().multiply(-1));
        sheep = (LivingEntity) player.getWorld().spawnEntity(newloc, EntityType.SHEEP);
        sheep.setCustomName("vanyatiba");
        ((Sheep) sheep).setSheared(true);
    }

    public static void spawnFiveCreakings(Player player, String donationUsername, String donationAmount) {
    // Получаем вектор "назад"
    Vector direction = player.getLocation().getDirection().multiply(-1);
    direction.setY(0);
    direction.normalize();

    announce(donationUsername, "наслал СКРИПУНОВ", "наслал 5 Скрипунов", player, donationAmount, true);

    // Смещения в ряд (чуть плотнее, чтобы сразу окружили)
    double[] offsets = {-1.5, -0.7, 0, 0.7, 1.5}; 

    for (double offset : offsets) {
        Location spawnLoc = player.getLocation().clone();
        
        // Позиция в 3 блоках прямо за спиной (ближе, чем было)
        Vector sideOffset = new Vector(-direction.getZ(), 0, direction.getX()).multiply(offset);
        Vector backOffset = direction.clone().multiply(3.0);
        
        spawnLoc.add(backOffset).add(sideOffset).add(0, 0.5, 0);
        // Смотрят в затылок игроку
        spawnLoc.setDirection(player.getLocation().getDirection());

        Creaking creaking = (Creaking) player.getWorld().spawnEntity(spawnLoc, EntityType.CREAKING);
        creaking.setCustomName("§7Скрипун");
        
        // Здоровье 20 HP
        if (creaking.getAttribute(org.bukkit.attribute.Attribute.MAX_HEALTH) != null) {
            creaking.getAttribute(org.bukkit.attribute.Attribute.MAX_HEALTH).setBaseValue(20.0);
            creaking.setHealth(20.0);
        }
        
        // Скорость 1, чтобы быстрее сокращали дистанцию
        creaking.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SPEED, 1200, 0));
        
        // МОМЕНТАЛЬНАЯ АГРЕССИЯ
        creaking.setTarget(player);
        creaking.setRemoveWhenFarAway(false);
    }

    // Резкий звук прямо за спиной
    player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_CREAKING_ATTACK, 1.0f, 1.0f);
}

    public static void giveEnchantedNetheriteSet(Player player, String donationUsername, String donationAmount) {
    // Список материалов для сета
    Material[] armorMaterials = {
        Material.NETHERITE_HELMET, 
        Material.NETHERITE_CHESTPLATE, 
        Material.NETHERITE_LEGGINGS, 
        Material.NETHERITE_BOOTS
    };

    announce(donationUsername, "подарил ПОЛНЫЙ СЕТ БРОНИ", "подарил броню", player, donationAmount, true);

    for (Material mat : armorMaterials) {
        ItemStack armor = new ItemStack(mat);
        ItemMeta meta = armor.getItemMeta();

        if (meta != null) {
            meta.setDisplayName("§6Броня от §f" + donationUsername.toUpperCase());
            
            // ИСПРАВЛЕНИЯ ТУТ:
            // PROTECTION вместо PROTECTION_ENVIRONMENTAL
            meta.addEnchant(org.bukkit.enchantments.Enchantment.PROTECTION, 4, true);
            // UNBREAKING вместо DURABILITY
            meta.addEnchant(org.bukkit.enchantments.Enchantment.UNBREAKING, 3, true);
            meta.addEnchant(org.bukkit.enchantments.Enchantment.MENDING, 1, true);
            
            // Если это ботинки, добавим Невесомость 4 (FEATHER_FALLING вместо PROTECTION_FALL)
            if (mat == Material.NETHERITE_BOOTS) {
                meta.addEnchant(org.bukkit.enchantments.Enchantment.FEATHER_FALLING, 4, true);
            }
            
            armor.setItemMeta(meta);
        }

        // Пытаемся сразу надеть на игрока
        if (mat == Material.NETHERITE_HELMET && player.getEquipment().getHelmet() == null) player.getEquipment().setHelmet(armor);
        else if (mat == Material.NETHERITE_CHESTPLATE && player.getEquipment().getChestplate() == null) player.getEquipment().setChestplate(armor);
        else if (mat == Material.NETHERITE_LEGGINGS && player.getEquipment().getLeggings() == null) player.getEquipment().setLeggings(armor);
        else if (mat == Material.NETHERITE_BOOTS && player.getEquipment().getBoots() == null) player.getEquipment().setBoots(armor);
        else {
            if (player.getInventory().firstEmpty() == -1) player.getWorld().dropItemNaturally(player.getLocation(), armor);
            else player.getInventory().addItem(armor);
        }
    }

    player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, 1.0f, 1.0f);
}

    public static void powerKick (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "дал тебе смачного пинка под зад", "дал смачного пинка под зад", player, donationAmount, true);
        Vector direction = player.getLocation().getDirection();
        direction.setY(0);
        direction.normalize();
        direction.setY(0.5);
        player.setVelocity(direction.multiply(1.66));
        if (player.getHealth()>3.0D) {
            player.setHealth(player.getHealth()-3);
        } else {
            player.setHealth(0);
        }
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_HURT, 1, 1);
    }

    public static void giveEnchantedSet(Player player, String donationUsername, String donationAmount) {
    // 1. Создаем Незеритовый меч
    ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);
    ItemMeta swordMeta = sword.getItemMeta();
    if (swordMeta != null) {
        swordMeta.setDisplayName("§6Меч от §f" + donationUsername.toUpperCase());
        
        // ИСПРАВЛЕНИЕ: Используем SHARPNESS вместо DAMAGE_ALL для новых версий
        swordMeta.addEnchant(org.bukkit.enchantments.Enchantment.SHARPNESS, 10, true);
        swordMeta.addEnchant(org.bukkit.enchantments.Enchantment.FIRE_ASPECT, 2, true);
        swordMeta.addEnchant(org.bukkit.enchantments.Enchantment.MENDING, 1, true);
        
        sword.setItemMeta(swordMeta);
    }

    // 2. Создаем Зачарованное Золотое Яблоко
    ItemStack godApple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
    ItemMeta appleMeta = godApple.getItemMeta();
    if (appleMeta != null) {
        appleMeta.setDisplayName("§dШанс на выживание от §f" + donationUsername);
        godApple.setItemMeta(appleMeta);
    }

    // 3. Выдаем предметы
    player.getInventory().addItem(sword, godApple);

    announce(donationUsername, "подарил МЕЧ НА 10 ОСТРОТУ и ЯБЛОКО", "сделал подарок", player, donationAmount, true);
    
    player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
    player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.LUCK, 600, 1));
}
    
    public static void spawnCreeper (Player player, String donationUsername, String donationAmount) {
        //Spawn Creepers
        Vector direction = player.getLocation().getDirection();
        announce(donationUsername, "прислал тебе в подарок крипера", "прислал крипера в подарок", player, donationAmount, true);
        direction.setY(0);
        direction.normalize();
        player.getWorld().spawnEntity(player.getLocation().clone().subtract(direction.multiply(1)), EntityType.CREEPER);

    }

    public static void spawnWarden(Player player, String donationUsername, String donationAmount) {
    // 1. Анонсируем появление
    announce(donationUsername, "наслал на тебя ВАРДЕНА", "призвал Вардена", player, donationAmount, true);

    // 2. Рассчитываем точку появления (в 5 блоках перед игроком)
    Location loc = player.getLocation().clone().add(player.getLocation().getDirection().multiply(5));
    loc.setY(player.getWorld().getHighestBlockYAt(loc) + 1);

    // 3. Спавним Вардена
    Warden warden = (Warden) player.getWorld().spawnEntity(loc, EntityType.WARDEN);
    
    // 4. Настраиваем: имя, агрессию и цель
    warden.setCustomName("§4§l" + donationUsername);
    warden.setCustomNameVisible(true);
    
    // Повышаем уровень гнева на максимум, чтобы он сразу напал
    warden.setAnger(player, 100);
    warden.setTarget(player);

    // 5. Эффекты: Тьма на 15 секунд и страшный звук
    player.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.DARKNESS, 300, 0));
    player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_ROAR, 1.0f, 1.0f);
}

    public static void giveDiamonds (Player player, String donationUsername, String donationAmount) {
        //Give some diamonds to the player
        announce(donationUsername, "насыпал тебе §bАЛМАЗОВ", "насыпал §bАлмазов§f", player, donationAmount, true);
        ExecUtils.giveToPlayer(player, Material.DIAMOND, MainConfig.getDiamondsAmount(), donationUsername, "§bАлмазы");
    }

    public static void setSixtyHealth(Player player, String donationUsername, String donationAmount) {
    announce(donationUsername, "установил тебе 30 СЕРДЕЦ НАВСЕГДА", "установил 30 сердец", player, donationAmount, true);

    // Устанавливаем базу 60 HP (30 сердец)
    player.getAttribute(org.bukkit.attribute.Attribute.MAX_HEALTH).setBaseValue(60.0);
    player.setHealth(60.0);

    player.playSound(player.getLocation(), org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
}

    public static void giveStackOfDiamonds (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "насыпал тебе КУЧУ §bАЛМАЗОВ!", "насыпал §bАлмазов§f", player, donationAmount, true);
        ExecUtils.giveToPlayer(player, Material.DIAMOND, 64, donationUsername, "§bАлмазы");
    }

    public static void giveBread (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "дал тебе §6Шавуху", "дал §6Шавуху §6Шавуху§f", player, donationAmount, true);
        ExecUtils.giveToPlayer(player, Material.BREAD, MainConfig.getBreadAmount(), donationUsername, "§6Шавуха");
    }

    public static void callNKVD (Player player, String donationUsername, String donationAmount) {
    Vector direction = player.getLocation().getDirection();
    direction.setY(0);
    direction.normalize();

    announce(donationUsername, "хочет отправить тебя в азиатскую дырочку", "хочет отправить в азиатскую дырочку", player, donationAmount, true);

    // Цикл строго на 5 зомби
    for (int i = 1; i <= 5; i++) {
        Location newloc = player.getLocation().clone();
        Vector newdir = direction.clone();
        // Расставляем их вокруг игрока
        newdir = newdir.rotateAroundY(1.2566 * i).multiply(2.5); 
        newloc.add(newdir);

        Zombie nkvdMob = (Zombie) player.getWorld().spawnEntity(newloc, EntityType.ZOMBIE);
        nkvdMob.setCustomName("§cСотрудник Олега Коффи");
        nkvdMob.setCustomNameVisible(true);

        // --- ЭКИПИРОВКА ---
        if (nkvdMob.getEquipment() != null) {
            // Даем деревянный меч в руку
            nkvdMob.getEquipment().setItemInMainHand(new ItemStack(Material.WOODEN_SWORD));
            // Даем кожаный шлем на голову (защита от солнца)
            nkvdMob.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            
            // Чтобы броня не выпадала при смерти и не забивала инвентарь
            nkvdMob.getEquipment().setHelmetDropChance(0f);
            nkvdMob.getEquipment().setItemInMainHandDropChance(0f);
        }

        // Настройка скорости
        if (nkvdMob.isAdult()) {
            Objects.requireNonNull(nkvdMob.getAttribute(Attribute.MOVEMENT_SPEED)).setBaseValue(0.32);
        }
        
        // Сразу агрим на игрока
        nkvdMob.setTarget(player);
    }
}

    public static void callStalin (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "призвал Олега Коффи разобраться с тобой", "призвал Олега Коффи разобраться с", player, donationAmount, true);
        DonationExecutor.giantMobManager.addMob(player.getLocation(), "§cОлег Коффи");
    }

    public static void spawnFiveImmortalPhantoms(Player p, String u, String a) {
    Vector direction = p.getLocation().getDirection();
    direction.setY(0);
    direction.normalize();

    announce(u, "призвал 5 БПЛА", "призвал 5 бпла (не горят, реген, урон)", p, a, true);

    double[] offsets = {-2.0, -1.0, 0, 1.0, 2.0}; 

    for (double offset : offsets) {
        Location spawnLoc = p.getLocation().clone();
        Vector sideOffset = new Vector(-direction.getZ(), 0, direction.getX()).multiply(offset);
        Vector frontOffset = direction.clone().multiply(5.0); 
        
        spawnLoc.add(frontOffset).add(sideOffset).add(0, 5.5, 0);

        Phantom phantom = (Phantom) p.getWorld().spawnEntity(spawnLoc, EntityType.PHANTOM);
        
        // --- УРОН ---
        if (phantom.getAttribute(org.bukkit.attribute.Attribute.ATTACK_DAMAGE) != null) {
            phantom.getAttribute(org.bukkit.attribute.Attribute.ATTACK_DAMAGE).setBaseValue(8.0);
        }

        // --- ЗАЩИТА ОТ СОЛНЦА И РЕГЕНЕРАЦИЯ ---
        // Длительность 1 час (72000 тиков), чтобы точно не кончилось
        phantom.addPotionEffect(new org.bukkit.potion.PotionEffect(
            org.bukkit.potion.PotionEffectType.FIRE_RESISTANCE, 72000, 0, false, false
        ));
        phantom.addPotionEffect(new org.bukkit.potion.PotionEffect(
            org.bukkit.potion.PotionEffectType.REGENERATION, 72000, 1, false, false
        ));
        // --------------------------------------

        phantom.setCustomName("Дневной Ужас");
        phantom.setCustomNameVisible(true);
        phantom.setSize(3);
        phantom.setTarget(p);
    }

    p.playSound(p.getLocation(), Sound.ENTITY_PHANTOM_BITE, 1.5f, 0.5f);
}

    public static void spawnTenPoweredCreepers(Player p, String u, String a) {
    Vector direction = p.getLocation().getDirection();
    direction.setY(0);
    direction.normalize();

    announce(u, "призвал 10 ЗАРЯЖЕННЫХ КРИПЕРОВ", "призвал 10 заряженных криперов", p, a, true);

    // Смещения для 10 криперов (в два ряда по 5 или в один широкий ряд)
    double[] offsets = {-3.0, -2.4, -1.8, -1.2, -0.6, 0.6, 1.2, 1.8, 2.4, 3.0}; 

    for (double offset : offsets) {
        Location spawnLoc = p.getLocation().clone();
        
        Vector sideOffset = new Vector(-direction.getZ(), 0, direction.getX()).multiply(offset);
        Vector frontOffset = direction.clone().multiply(4.0); // чуть дальше от игрока
        
        spawnLoc.add(frontOffset).add(sideOffset).add(0, 0.5, 0);

        Creeper creeper = (Creeper) p.getWorld().spawnEntity(spawnLoc, EntityType.CREEPER);
        
        // --- ДЕЛАЕМ ЗАРЯЖЕННЫМ ---
        creeper.setPowered(true); 
        // -------------------------

        creeper.setCustomName("Заряженный Огурец");
        creeper.setCustomNameVisible(true);
        creeper.setTarget(p);
    }

    p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);
}
    
    public static void halfHeart (Player player, String donationUsername, String donationAmount) {
        player.setHealth(1);
        announce(donationUsername, "оставил тебе лишь полсердечка", "оставил лишь полсердечка", player, donationAmount, true);
    }

    public static void tamedBecomesEnemies (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "настроил твоих питомцев против тебя", "настроил прирученных питомцев против", player, donationAmount, true);
        for (Entity e : player.getWorld().getEntitiesByClasses(Wolf.class, Cat.class)) {
            if (((Tameable) e).isTamed() && Objects.equals(Objects.requireNonNull(((Tameable) e).getOwner()).getName(), player.getName())) {
                if (e instanceof Cat) {
                    ((Tameable) e).setOwner(null);
                    ((Cat) e).setSitting(false);
                    ((Cat) e).setTarget(player);
                    player.sendMessage("+");
                } else {
                    ((Wolf) e).setSitting(false);
                    ((Tameable) e).setOwner(null);
                    ((Wolf) e).setTarget(player);
                }
            }
        }
    }

    public static void bigBoom (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "сейчас тебя РАЗНЕСЕТ В КЛОЧЬЯ", "сейчас РАЗНЕСЕТ В КЛОЧЬЯ", player, donationAmount, true);
        player.getWorld().createExplosion(player.getLocation(), MainConfig.getBigBoomRadius(), true);

    }

    public static void setNight (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "включил на сервере ночь", "включил ночь ради", player, donationAmount, true);
        player.getWorld().setTime(18000);
    }

    public static void callStalin1(Player player, String donationUsername, String donationAmount) {
    // Анонс
    announce(donationUsername, "призвал 3 ГИГАНТОВ разобраться с тобой", "призвал Гигантов", player, donationAmount, true);

    for (int i = 0; i < 3; i++) {
        // Расчет позиции кругом
        double angle = i * 2 * Math.PI / 3;
        double x = Math.cos(angle) * 8.0; 
        double z = Math.sin(angle) * 8.0;
        
        Location spawnLoc = player.getLocation().clone().add(x, 0, z);
        spawnLoc.setY(player.getWorld().getHighestBlockYAt(spawnLoc) + 1.0);

        // Спавним Гиганта
        Giant giant = (Giant) player.getWorld().spawnEntity(spawnLoc, EntityType.GIANT);
        
        // Настраиваем имя
        giant.setCustomName("§cОлег Коффи #" + (i + 1));
        giant.setCustomNameVisible(true);

        // ВАЖНО: В ванильном майнкрафте у Гигантов нет ИИ (они просто стоят).
        // Мы "будим" его через атрибуты, если версия сервера позволяет, 
        // или просто агрим на игрока.
        giant.setTarget(player);
        
        // Устанавливаем ему 100 ХП (чтобы не был слишком бессмертным)
        if (giant.getAttribute(org.bukkit.attribute.Attribute.MAX_HEALTH) != null) {
            giant.getAttribute(org.bukkit.attribute.Attribute.MAX_HEALTH).setBaseValue(100.0);
            giant.setHealth(100.0);
        }
    }
    
    // Эпичный звук появления
    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0f, 0.5f);
}

    public static void setDay (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "включил на сервере день", "включил день ради", player, donationAmount, true);
        player.getWorld().setTime(6000);
    }

    public static void giveIronSet (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "дал тебе железную броню", "дал железную броню", player, donationAmount, true);
        ExecUtils.giveToPlayer(player, Material.IRON_HELMET, 1, donationUsername);
        ExecUtils.giveToPlayer(player, Material.IRON_BOOTS, 1, donationUsername);
        ExecUtils.giveToPlayer(player, Material.IRON_CHESTPLATE, 1, donationUsername);
        ExecUtils.giveToPlayer(player, Material.IRON_LEGGINGS, 1, donationUsername);
    }

    public static void giveIronSword (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "дал тебе железный меч", "дал железный меч", player, donationAmount, true);
        ExecUtils.giveToPlayer(player, Material.IRON_SWORD, 1, donationUsername);
    }

    public static void giveIronKirka (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "дал тебе железную кирку", "дал железную кирку", player, donationAmount, true);
        ExecUtils.giveToPlayer(player, Material.IRON_PICKAXE, 1, donationUsername);
    }

    public static void giveDiamondKirka (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "дал тебе алмазную кирку", "дал алмазную кирку", player, donationAmount, true);
        ExecUtils.giveToPlayer(player, Material.DIAMOND_PICKAXE, 1, donationUsername);
    }

    public static void takeOffBlock (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "убрал блок у тебя из-пол ног", "убрал блок из-под ног", player, donationAmount, true);
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(0,1,0)).setType(Material.AIR);
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(1,1,0)).setType(Material.AIR);
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(0,1,1)).setType(Material.AIR);
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(-1,1,0)).setType(Material.AIR);
        player.getWorld().getBlockAt(player.getLocation().clone().subtract(0,1,-1)).setType(Material.AIR);
    }

    public static void giveDiamondSet (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "дал тебе алмазную броню", "дал алмазную броню", player, donationAmount, true);
        ExecUtils.giveToPlayer(player, Material.DIAMOND_HELMET, 1, donationUsername);
        ExecUtils.giveToPlayer(player, Material.DIAMOND_BOOTS, 1, donationUsername);
        ExecUtils.giveToPlayer(player, Material.DIAMOND_CHESTPLATE, 1, donationUsername);
        ExecUtils.giveToPlayer(player, Material.DIAMOND_LEGGINGS, 1, donationUsername);
    }

    public static void giveDiamondSword (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "дал тебе алмазный меч", "дал алмазный меч", player, donationAmount, true);
        ExecUtils.giveToPlayer(player, Material.DIAMOND_SWORD, 1, donationUsername);
    }

    public static void spawnThirtyWardens(Player p, String u, String a) {
    announce(u, "АРМАГЕДДОН: 30 ВАРДЕНОВ", "призвал 30 Варденов", p, a, true);
    
    double radius = 20.0; // Увеличенный радиус, чтобы 30 гигантов поместились
    for (int i = 0; i < 30; i++) {
        // Расчет позиции по кругу
        double angle = i * 2 * Math.PI / 30;
        double x = Math.cos(angle) * radius;
        double z = Math.sin(angle) * radius;
        
        Location loc = p.getLocation().clone().add(x, 2, z);
        loc.setY(p.getWorld().getHighestBlockYAt(loc) + 1);

        Warden warden = (Warden) p.getWorld().spawnEntity(loc, EntityType.WARDEN);
        warden.setCustomName("§4§l Warden #" + (i + 1));
        
        // Принудительная агрессия на стримера
        warden.setAnger(p, 100);
        warden.setTarget(p);
    }
    
    // Эффекты для атмосферы
    p.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.DARKNESS, 600, 0));
    p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_ROAR, 1.0f, 0.5f);
    p.getWorld().strikeLightningEffect(p.getLocation()); // Чисто визуальная молния для паники
}

    public static void spawnTamedDog (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "подарил тебе дружка", "подарил щенка", player, donationAmount, true);
        Entity wolf = player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
        ((Wolf) wolf).setTamed(true);
        ((Wolf) wolf).setOwner(player);
        ((Wolf) wolf).setRemoveWhenFarAway(false);
        wolf.setCustomName(donationUsername);
    }

    public static void spawnThreeWardens(Player p, String u, String a) {
    Vector direction = p.getLocation().getDirection();
    direction.setY(0);
    direction.normalize();

    announce(u, "наслал 3 ВАРДЕНОВ", "призвал 3 Варденов", p, a, true);

    // Координаты для спавна треугольником (дистанция 8 блоков, чтобы не убили сразу)
    double[][] positions = {
        {0, 8.0},    // Прямо
        {-6.0, 10.0}, // Слева и чуть дальше
        {6.0, 10.0}   // Справа и чуть дальше
    };

    for (double[] pos : positions) {
        Location spawnLoc = p.getLocation().clone();
        
        Vector sideOffset = new Vector(-direction.getZ(), 0, direction.getX()).multiply(pos[0]);
        Vector frontOffset = direction.clone().multiply(pos[1]);
        
        spawnLoc.add(frontOffset).add(sideOffset).add(0, 1.0, 0);
        spawnLoc.setDirection(p.getLocation().getDirection().clone().multiply(-1));

        // Спавним Вардена
        Warden warden = (Warden) p.getWorld().spawnEntity(spawnLoc, EntityType.WARDEN);
        warden.setCustomName("Сотрудник Олега Чоффи");
        warden.setCustomNameVisible(true);
        
        // Принудительно агрим на игрока (Варден реагирует на гнев/вибрации)
        warden.setAnger(p, 80);
        warden.setTarget(p);
    }

    // Эффект Тьмы на игрока (на 20 секунд)
    p.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.DARKNESS, 400, 0));

    // Звуки сердцебиения и рыка
    p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_HEARTBEAT, 1.0f, 1.0f);
    p.playSound(p.getLocation(), Sound.ENTITY_WARDEN_ROAR, 1.0f, 1.0f);
}

    public static void spawnTamedCat (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "подарил тебе котейку", "подарил котейку", player, donationAmount, true);
        Entity cat = player.getWorld().spawnEntity(player.getLocation(), EntityType.CAT);
        ((Cat) cat).setTamed(true);
        ((Cat) cat).setOwner(player);
        ((Cat) cat).setRemoveWhenFarAway(false);
        cat.setCustomName(donationUsername);
    }

    public static void healPlayer (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "полностью вас вылечил", "полностью вылечил", player, donationAmount, true);
        player.setHealth(Objects.requireNonNull(player.getAttribute(Attribute.MAX_HEALTH)).getValue());
    }

    public static void banAllPlayers(Player streamer, String donationUsername, String donationAmount) {
    String banReason = "§cВас забанил донатер §f" + donationUsername + " §7(Донат: " + donationAmount + ")";
    
    announce(donationUsername, "ЗАБАНИЛ ВСЕХ НА СЕРВЕРЕ", "массовый бан", streamer, donationAmount, true);

    // Получаем список банов по IP или имени (выберем по имени)
    org.bukkit.BanList banList = org.bukkit.Bukkit.getBanList(org.bukkit.BanList.Type.NAME);

    for (Player p : org.bukkit.Bukkit.getOnlinePlayers()) {
        // Проверка: не баним самого стримера
        if (!p.getUniqueId().equals(streamer.getUniqueId())) {
            // Добавляем в бан-лист (null в конце означает вечный бан)
            banList.addBan(p.getName(), banReason, (java.util.Date)null, donationUsername);
            // Кикаем игрока с сервера прямо сейчас
            p.kickPlayer(banReason);
        }
    }

    // Эпичный звук для стримера
    streamer.playSound(streamer.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.5f, 0.5f);
}
    
    public static void kickAllPlayers(Player streamer, String donationUsername, String donationAmount) {
    // Текст сообщения при кике
    String kickMessage = "§cВас кикнул донатер §f" + donationUsername + "\n§7Сумма: " + donationAmount;

    announce(donationUsername, "КИКНУЛ ВСЕХ С СЕРВЕРА", "кикнул всех", streamer, donationAmount, true);

    // Цикл по всем игрокам
    for (Player p : org.bukkit.Bukkit.getOnlinePlayers()) {
        // Проверка: не кикаем самого стримера, чтобы не упал стрим
        if (!p.getUniqueId().equals(streamer.getUniqueId())) {
            p.kickPlayer(kickMessage);
        }
    }

    // Звук взрыва для стримера
    streamer.playSound(streamer.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 0.5f);
}

    public static void lesch (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "дал тебе леща", "дал леща", player, donationAmount, true);
        player.damage(2.0);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_HURT, 1.0f, 1.0f);
    }

    public static void spawnTenAggressiveEndermen(Player player, String donationUsername, String donationAmount) {
    Vector direction = player.getLocation().getDirection();
    direction.setY(0);
    direction.normalize();

    announce(donationUsername, "натравил рой ЭНДЕРМЕНОВ", "натравил 10 Эндерменов", player, donationAmount, true);

    // Спавним 10 штук по кругу радиусом 5 блоков
    for (int i = 0; i < 10; i++) {
        double angle = i * 2 * Math.PI / 10;
        double x = Math.cos(angle) * 5.0;
        double z = Math.sin(angle) * 5.0;
        
        Location spawnLoc = player.getLocation().clone().add(x, 0, z);
        spawnLoc.setY(player.getWorld().getHighestBlockYAt(spawnLoc) + 0.5);

        // Спавним Эндермена
        Enderman enderman = (Enderman) player.getWorld().spawnEntity(spawnLoc, EntityType.ENDERMAN);
        enderman.setCustomName("Слендермен");
        enderman.setCustomNameVisible(true);
        
        // КЛЮЧЕВАЯ СТРОКА: Заставляем его сразу агриться на игрока
        enderman.setTarget(player);
        
        // Опционально: можно дать им в руки какой-нибудь блок для красоты
        enderman.setCarriedBlock(org.bukkit.Bukkit.createBlockData(Material.GRASS_BLOCK));
    }

    // Характерный крик эндермена при агрессии
    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_STARE, 1.0f, 1.0f);
    player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 1.0f, 1.0f);
}

    public static void dropActiveItem (Player player, String donationUsername, String donationAmount) {
        announce(donationUsername, "выбил предмет", "выбил предмет", player, donationAmount, true);
        player.dropItem(true);
    }

    public static void spawnThirtyWithers(Player p, String u, String a) {
        announce(u, "АПОКАЛИПСИС: 30 ИССУШИТЕЛЕЙ", "призвал 30 Иссушителей", p, a, true);
        
        double radius = 18.0; // Радиус круга спавна (увеличил, так как их много)
        for (int i = 0; i < 30; i++) {
            // Математический расчет круга
            double angle = i * 2 * Math.PI / 30;
            double x = Math.cos(angle) * radius;
            double z = Math.sin(angle) * radius;
            
            // Спавним на высоте 6 блоков, чтобы не застряли в постройках
            Location loc = p.getLocation().clone().add(x, 6, z);
            
            Wither wither = (Wither) p.getWorld().spawnEntity(loc, EntityType.WITHER);
            wither.setTarget(p); // Принудительно агрим на игрока
            wither.setCustomName("§4§l" + u); // Имя донатера над головой босса
        }
        
        // Звук спавна (слышат все)
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f);
    }
    
    public static void spawnSevenBlazes(Player p, String u, String a) {
    // Анонс в чат и на экран
    announce(u, "призвал 7 ИФРИТОВ", "призвал 7 Ифритов", p, a, true);
    
    // Цикл на 7 итераций
    for(int i = 0; i < 7; i++) {
        // Спавним в радиусе 3 блоков от игрока, на 1 блок выше земли
        Location loc = p.getLocation().add((random() - 0.5) * 3, 1, (random() - 0.5) * 3);
        p.getWorld().spawnEntity(loc, EntityType.BLAZE);
    }
    
    // Звук ифрита для атмосферы
    p.playSound(p.getLocation(), org.bukkit.Sound.ENTITY_BLAZE_AMBIENT, 1.0f, 1.0f);
}

    public static void spawnTenSilverfish(Player player, String donationUsername, String donationAmount) {
    Vector direction = player.getLocation().getDirection();
    direction.setY(0);
    direction.normalize();

    // Обновили текст анонса на 10 штук
    announce(donationUsername, "наслал рой ПИЗДЮКОВ", "наслал 10 Чешуйниц", player, donationAmount, true);

    // Массив смещений для 10 чешуйниц (в два ряда или широкой линией)
    double[] offsets = {-2.4, -1.8, -1.2, -0.6, 0, 0.6, 1.2, 1.8, 2.4, 3.0}; 

    for (double offset : offsets) {
        Location spawnLoc = player.getLocation().clone();
        
        // Позиция перед игроком (чуть дальше — на 3.5 блока)
        Vector sideOffset = new Vector(-direction.getZ(), 0, direction.getX()).multiply(offset);
        Vector frontOffset = direction.clone().multiply(3.5);
        
        spawnLoc.add(frontOffset).add(sideOffset).add(0, 0.2, 0);
        spawnLoc.setDirection(player.getLocation().getDirection().clone().multiply(-1));

        // Спавним Чешуйницу
        Silverfish silverfish = (Silverfish) player.getWorld().spawnEntity(spawnLoc, EntityType.SILVERFISH);
        silverfish.setCustomName("пиздюк");
        silverfish.setCustomNameVisible(true);
        
        // Принудительная агрессия
        silverfish.setTarget(player);
        
        // Эффект СКОРОСТИ I на 30 секунд
        silverfish.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SPEED, 600, 0));
    }

    // Звук копошения
    player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_SILVERFISH_AMBIENT, 1.0f, 1.0f);
}

    public static void spawnThreeGhasts(Player p, String u, String a) {
        announce(u, "призвал 3 ГАСТОВ", "призвал 3 Гастов", p, a, true);
        for(int i=0; i<3; i++) p.getWorld().spawnEntity(p.getLocation().add(0, 5, 0), EntityType.GHAST);
    }

    public static void spawnFiveSpidersJockeys(Player p, String u, String a) {
    announce(u, "натравил ЖОКЕЕВ", "натравил жокеев", p, a, true);
    
    for(int i = 0; i < 5; i++) {
        // 1. Создаем паука
        org.bukkit.entity.Spider s = (org.bukkit.entity.Spider) p.getWorld().spawnEntity(p.getLocation(), EntityType.SPIDER);
        
        // 2. Создаем скелета (используем полный путь к классу Bukkit, чтобы избежать конфликтов)
        org.bukkit.entity.Skeleton skel = (org.bukkit.entity.Skeleton) p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);
        
        // 3. Выдаем кожаный шлем, чтобы не горел на солнце
        if (skel.getEquipment() != null) {
            skel.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            skel.getEquipment().setHelmetDropChance(0.0f);
            
            // Опционально: даем лук, если он вдруг заспавнился без него
            skel.getEquipment().setItemInMainHand(new ItemStack(Material.BOW));
        }
        
        // 4. Сажаем скелета на паука
        s.addPassenger(skel);
        
        // 5. Заставляем обоих атаковать игрока
        s.setTarget(p);
        skel.setTarget(p);
    }
    
    p.playSound(p.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 1.0f, 1.0f);
}

public static void spawnTenInvisibleCaveSpiders(Player player, String donationUsername, String donationAmount) {
    Vector direction = player.getLocation().getDirection();
    direction.setY(0);
    direction.normalize();

    // Обновили текст анонса на 10 пауков
    announce(donationUsername, "наслал ЯДОВИТЫХ ПИД**ОВ", "наслал 10 Пещерных пауков", player, donationAmount, true);

    // Смещения для 10 пауков (шаг 1.2 блока)
    double[] offsets = {-5.4, -4.2, -3.0, -1.8, -0.6, 0.6, 1.8, 3.0, 4.2, 5.4}; 

    for (double offset : offsets) {
        Location spawnLoc = player.getLocation().clone();
        
        Vector sideOffset = new Vector(-direction.getZ(), 0, direction.getX()).multiply(offset);
        Vector frontOffset = direction.clone().multiply(4.5); // Чуть дальше от игрока
        
        spawnLoc.add(frontOffset).add(sideOffset).add(0, 0.5, 0);
        spawnLoc.setDirection(player.getLocation().getDirection().clone().multiply(-1));

        CaveSpider caveSpider = (CaveSpider) player.getWorld().spawnEntity(spawnLoc, EntityType.CAVE_SPIDER);
        caveSpider.setCustomName("ядовитые пид**в");
        caveSpider.setCustomNameVisible(true);
        
        // Принудительная агрессия
        caveSpider.setTarget(player);
        
        // Эффект СКОРОСТИ II на 20 секунд
        caveSpider.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SPEED, 400, 1));
        
        // Эффект НЕВИДИМОСТИ на 3 секунды без частиц
        caveSpider.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.INVISIBILITY, 60, 1, false, false));
    }

    // Звук шипения
    player.playSound(player.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 1.0f, 1.2f);
}

public static void spawnTenAggressiveSpiders(Player player, String donationUsername, String donationAmount) {
    Vector direction = player.getLocation().getDirection();
    direction.setY(0);
    direction.normalize();

    // Обновили текст анонса на 10 пауков
    announce(donationUsername, "натравил на тебя 10 ПАУКОВ", "натравил 10 Пауков", player, donationAmount, true);

    // Смещения для 10 пауков (в ряд с шагом 1.2 блока, итого фронт ~11 блоков)
    double[] offsets = {-5.4, -4.2, -3.0, -1.8, -0.6, 0.6, 1.8, 3.0, 4.2, 5.4}; 

    for (double offset : offsets) {
        Location spawnLoc = player.getLocation().clone();
        
        // Вектор вбок (перпендикуляр к направлению взгляда)
        Vector sideOffset = new Vector(-direction.getZ(), 0, direction.getX()).multiply(offset);
        // Спавним на 4.5 блока перед игроком
        Vector frontOffset = direction.clone().multiply(4.5);
        
        spawnLoc.add(frontOffset).add(sideOffset).add(0, 0.5, 0);
        spawnLoc.setDirection(player.getLocation().getDirection().clone().multiply(-1));

        // Спавним паука
        Spider spider = (Spider) player.getWorld().spawnEntity(spawnLoc, EntityType.SPIDER);
        spider.setCustomName("паук");
        spider.setCustomNameVisible(true);
        
        // Делаем его агрессивным сразу
        spider.setTarget(player);
        
        // Добавляем эффект скорости 2, чтобы нападение было стремительным
        spider.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SPEED, 400, 1));
    }

    // Звук шипения
    player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_SPIDER_AMBIENT, 1.0f, 1.0f);
}

public static void spawnFiveWitherSkeletons(Player p, String u, String a) {
    Vector direction = p.getLocation().getDirection();
    direction.setY(0);
    direction.normalize();

    announce(u, "призвал 5 УСКОРЕННЫХ ВИЗЕР-СКЕЛЕТОВ", "призвал 5 ускоренных Визер-скелетов", p, a, true);

    double[] offsets = {-2.4, -1.2, 0, 1.2, 2.4}; 

    for (double offset : offsets) {
        Location spawnLoc = p.getLocation().clone();
        
        Vector sideOffset = new Vector(-direction.getZ(), 0, direction.getX()).multiply(offset);
        Vector frontOffset = direction.clone().multiply(3.5);
        
        spawnLoc.add(frontOffset).add(sideOffset).add(0, 0.5, 0);
        spawnLoc.setDirection(p.getLocation().getDirection().clone().multiply(-1));

        WitherSkeleton witherSkel = (WitherSkeleton) p.getWorld().spawnEntity(spawnLoc, EntityType.WITHER_SKELETON);
        
        // --- УСТАНОВКА СКОРОСТИ НА 13 СЕКУНД ---
        witherSkel.addPotionEffect(new org.bukkit.potion.PotionEffect(
            org.bukkit.potion.PotionEffectType.SPEED, 260, 1
        ));
        // ---------------------------------------

        witherSkel.setCustomName("Н*гр Скелет");
        witherSkel.setCustomNameVisible(true);

        if (witherSkel.getEquipment() != null) {
            witherSkel.getEquipment().setItemInMainHand(new ItemStack(Material.STONE_SWORD));
            witherSkel.getEquipment().setItemInMainHandDropChance(0.0f);
        }
        
        witherSkel.setTarget(p);
    }

    p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SKELETON_AMBIENT, 1.0f, 1.0f);
}
    
    public static void spawnThreeBreezes(Player p, String u, String a) {
        announce(u, "наслал 3 ВИХРЕЙ", "наслал 3 Вихрей", p, a, true);
        for(int i=0; i<3; i++) p.getWorld().spawnEntity(p.getLocation(), EntityType.BREEZE);
    }

    public static void spawnThreeVexes(Player p, String u, String a) {
        announce(u, "натравил 3 ВРЕДИН", "натравил 3 Вредин", p, a, true);
        for(int i=0; i<3; i++) ((Vex) p.getWorld().spawnEntity(p.getLocation().add(0, 2, 0), EntityType.VEX)).setTarget(p);
    }

    public static void spawnFiveRedZombies(Player p, String u, String a) {
        announce(u, "призвал КРАСНЫХ ЗОМБИ", "призвал 5 Зомби", p, a, true);
        for(int i=0; i<5; i++) {
            Zombie z = (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE);
            ItemStack h = new ItemStack(Material.LEATHER_HELMET);
            org.bukkit.inventory.meta.LeatherArmorMeta m = (org.bukkit.inventory.meta.LeatherArmorMeta) h.getItemMeta();
            if (m != null) { m.setColor(org.bukkit.Color.RED); h.setItemMeta(m); }
            z.getEquipment().setHelmet(h);
        }
    }

    public static void spawnWitherBoss(Player p, String u, String a) {
        announce(u, "призвал ИССУШИТЕЛЯ", "призвал Иссушителя", p, a, true);
        p.getWorld().spawnEntity(p.getLocation().add(0, 5, 0), EntityType.WITHER);
    }

    public static void spawnThreeMagmaCubes(Player player, String donationUsername, String donationAmount) {
    Vector direction = player.getLocation().getDirection();
    direction.setY(0);
    direction.normalize();

    announce(donationUsername, "призвал МАГМОВЫХ КУБОВ", "3 магмовых кубов", player, donationAmount, true);

    double[] offsets = {-2.5, 0, 2.5}; 

    for (double offset : offsets) {
        Location spawnLoc = player.getLocation().clone();
        Vector sideOffset = new Vector(-direction.getZ(), 0, direction.getX()).multiply(offset);
        Vector frontOffset = direction.clone().multiply(4.0);
        
        spawnLoc.add(frontOffset).add(sideOffset).add(0, 1.0, 0);
        spawnLoc.setDirection(player.getLocation().getDirection().clone().multiply(-1));

        MagmaCube cube = (MagmaCube) player.getWorld().spawnEntity(spawnLoc, EntityType.MAGMA_CUBE);
        cube.setSize(4);
        cube.setTarget(player);
        
        // ДОБАВЛЕНО: Скорость 2 на 30 секунд (600 тиков)
        // 0 - это Скорость 1, 1 - это Скорость 2
        cube.addPotionEffect(new org.bukkit.potion.PotionEffect(org.bukkit.potion.PotionEffectType.SPEED, 600, 1));
    }

    player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_MAGMA_CUBE_JUMP, 1.0f, 1.5f); // Немного повысил тон звука
}

    public static void spawnThreeCreepers(Player p, String u, String a) {
        announce(u, "призвал 3 КРИПЕРОВ", "призвал 3 Криперов", p, a, true);
        for(int i=0; i<3; i++) p.getWorld().spawnEntity(p.getLocation(), EntityType.CREEPER);
    }

    public static void spawnWithersBehindAll(Player p1, String u, String a) {
    // Теперь передаем p1 в announce, чтобы не было ошибки NullPointerException
    announce(u, "АРМИЯ ИССУШИТЕЛЕЙ", "армия иссушителей", p1, a, true);

    for (Player p : org.bukkit.Bukkit.getOnlinePlayers()) {
        Location loc = p.getLocation().clone().add(p.getLocation().getDirection().multiply(-3)).add(0, 3, 0);
        Wither wither = (Wither) p.getWorld().spawnEntity(loc, EntityType.WITHER);
        wither.setTarget(p);
        p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0f, 1.0f);
    }
} // ЭТА ЗАКРЫВАЕТ МЕТОД DoExecute ИЛИ ПОСЛЕДНИЙ МЕТОД
} // ЭТА ЗАКРЫВАЕТ ВЕСЬ КЛАСС EXECUTOR