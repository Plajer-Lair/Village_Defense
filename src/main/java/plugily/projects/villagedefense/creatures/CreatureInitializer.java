package plugily.projects.villagedefense.creatures;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Golem;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import pl.plajerlair.commonsbox.minecraft.misc.MiscUtils;

public interface CreatureInitializer {
    Villager spawnVillager(Location location);

    Wolf spawnWolf(Location location);

    Golem spawnGolem(Location location);

    Zombie spawnFastZombie(Location location);

    Zombie spawnBabyZombie(Location location);

    Zombie spawnHardZombie(Location location);

    Zombie spawnPlayerBuster(Location location);

    Zombie spawnGolemBuster(Location location);

    Zombie spawnVillagerBuster(Location location);

    Zombie spawnKnockbackResistantZombies(Location location);

    Zombie spawnVillagerSlayer(Location location);

    default void applyFollowRange(Zombie zombie) {
        MiscUtils.getEntityAttribute(zombie, Attribute.GENERIC_FOLLOW_RANGE).ifPresent(ai -> ai.setBaseValue(200.0D));
    }
}