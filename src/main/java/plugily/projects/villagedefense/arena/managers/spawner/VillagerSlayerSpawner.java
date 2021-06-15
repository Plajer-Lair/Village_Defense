package plugily.projects.villagedefense.arena.managers.spawner;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import pl.plajerlair.commonsbox.minecraft.compat.VersionUtils;
import pl.plajerlair.commonsbox.minecraft.compat.xseries.XMaterial;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.creatures.CreatureUtils;

public class VillagerSlayerSpawner implements SimpleZombieSpawner {
  @Override
  public int getMinWave() {
    return 23;
  }

  @Override
  public double getSpawnRate(Arena arena, int wave, int phase, int spawnAmount) {
    return 1D / 6;
  }

  @Override
  public int getFinalAmount(Arena arena, int wave, int phase, int spawnAmount) {
    return spawnAmount;
  }

  @Override
  public boolean checkPhase(Arena arena, int wave, int phase, int spawnAmount) {
    return phase == 5;
  }

  @Override
  public Zombie spawnZombie(Location location) {
    Zombie villagerSlayer = CreatureUtils.getCreatureInitializer().spawnVillagerSlayer(location);
    VersionUtils.setItemInHand(villagerSlayer, XMaterial.EMERALD.parseItem());
    VersionUtils.setItemInHandDropChance(villagerSlayer, 0F);
    villagerSlayer.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
    villagerSlayer.getEquipment().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
    villagerSlayer.getEquipment().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
    villagerSlayer.getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
    return villagerSlayer;
  }

  @Override
  public String getName() {
    return "VillagerSlayer";
  }
}
