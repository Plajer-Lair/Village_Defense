package plugily.projects.villagedefense.arena.managers;

import plugily.projects.villagedefense.Main;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.managers.spawner.*;
import plugily.projects.villagedefense.arena.options.ArenaOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ZombieSpawnerRegistry {
    private final List<ZombieSpawner> zombieSpawnerList = new ArrayList<>();
    private final Main plugin;

    public ZombieSpawnerRegistry(Main plugin) {
        this.plugin = plugin;

        zombieSpawnerList.add(new FastZombieSpawner());
        zombieSpawnerList.add(new VillagerSlayerSpawner());
        zombieSpawnerList.add(new KnockbackResistantZombieSpawner());
        zombieSpawnerList.add(new HardZombieSpawner());
        zombieSpawnerList.add(new SoftHardZombieSpawner());
        zombieSpawnerList.add(new PlayerBusterSpawner());
        zombieSpawnerList.add(new GolemBusterSpawner());
        zombieSpawnerList.add(new VillagerBusterSpawner());
        zombieSpawnerList.add(new BabyZombieSpawner());
        zombieSpawnerList.add(new HalfInvisibleZombieSpawner());
    }

    public void spawnZombies(Random random, Arena arena) {
        int wave = arena.getWave();
        int spawn = arena.getWave();
        int zombiesLimit = plugin.getConfig().getInt("Zombies-Limit", 75);
        if(zombiesLimit < wave) {
            spawn = (int) Math.ceil(zombiesLimit / 2.0);
        }

        for (ZombieSpawner zombieSpawner : zombieSpawnerList) {
            zombieSpawner.spawnZombie(random, arena, spawn);
        }

        arena.addOptionValue(ArenaOption.ZOMBIE_SPAWN_COUNTER, 1);
        if(arena.getOption(ArenaOption.ZOMBIE_SPAWN_COUNTER) == 20) {
            arena.setOptionValue(ArenaOption.ZOMBIE_SPAWN_COUNTER, 0);
        }
    }

    public List<ZombieSpawner> getZombieSpawnerList() {
        return zombieSpawnerList;
    }
}
