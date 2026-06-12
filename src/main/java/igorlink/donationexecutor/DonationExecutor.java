package igorlink.donationexecutor;

import igorlink.command.DonationExecutorCommand;
import igorlink.donationexecutor.executionsstaff.executionsmanagement.executions.inventory.ShitToInventory;
import igorlink.donationexecutor.executionsstaff.giantmobs.GiantMobManager;
import igorlink.donationexecutor.playersmanagement.StreamerPlayersManager;
import igorlink.service.MainConfig;
import igorlink.service.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import static igorlink.service.Utils.*;

public final class DonationExecutor extends JavaPlugin {
    private static DonationExecutor instance;
    public static GiantMobManager giantMobManager;
    private static Boolean isRunningStatus = true;
    public StreamerPlayersManager streamerPlayersManager;


    @Override
    public void onEnable() {
        instance = this;
        try {
            MainConfig.loadMainConfig();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (CheckNameAndToken()) {
            streamerPlayersManager = new StreamerPlayersManager();
            giantMobManager = new GiantMobManager(this);
            new DonationExecutorCommand();
            Utils.fillTheSynonimousCharsHashMap();
        }


        Bukkit.getPluginManager().registerEvents(new GeneralEventListener(),this);

    }

    @Override
public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("donationreload")) {
        // Проверка прав (опционально)
        if (!sender.hasPermission("donationexecutor.admin")) {
            sender.sendMessage("§cУ вас нет прав на эту команду!");
            return true;
        }

        try {
            // Вызываем метод из твоего MainConfig
            igorlink.service.MainConfig.reloadMainConfig();
            sender.sendMessage("§a[DonationExecutor] Конфигурация успешно перезагружена!");
        } catch (InterruptedException e) {
            sender.sendMessage("§cОшибка при перезагрузке конфига!");
            e.printStackTrace();
        }
        return true;
    }
    return false;
}

@org.bukkit.event.EventHandler
public void onPlayerRespawn(org.bukkit.event.player.PlayerRespawnEvent event) {
    Player player = event.getPlayer();
    
    // Проверяем текущий базовый атрибут. 
    // Если мы уже выдавали 60 HP, то после смерти ставим их снова.
    double maxHealth = player.getAttribute(org.bukkit.attribute.Attribute.MAX_HEALTH).getBaseValue();
    
    if (maxHealth > 20.0) {
        // Делаем небольшую задержку в 1 тик, чтобы сервер успел "возродить" игрока
        new org.bukkit.scheduler.BukkitRunnable() {
            @Override
            public void run() {
                player.getAttribute(org.bukkit.attribute.Attribute.MAX_HEALTH).setBaseValue(60.0);
                player.setHealth(60.0);
            }
        }.runTaskLater(this, 1L);
    }
}    
    
    @Override
    public void onDisable() {
        try {
            isRunningStatus = false;
            if (CheckNameAndToken()) {
                streamerPlayersManager.stop();
            }
        } catch (InterruptedException e) {
            logToConsole("Какая-то ебаная ошибка, похуй на нее вообще");
        }
    }

    public static DonationExecutor getInstance() {
        return instance;
    }


    public static Boolean isRunning() {
        return isRunningStatus;
    }


}
