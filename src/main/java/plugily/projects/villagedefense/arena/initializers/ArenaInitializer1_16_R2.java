/*
 * Village Defense - Protect villagers from hordes of zombies
 * Copyright (C) 2021  Plugily Projects - maintained by 2Wild4You, Tigerpanzer_02 and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package plugily.projects.villagedefense.arena.initializers;

import net.minecraft.server.v1_16_R2.GenericAttributes;
import net.minecraft.server.v1_16_R2.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import plugily.projects.villagedefense.Main;
import plugily.projects.villagedefense.arena.Arena;
import plugily.projects.villagedefense.arena.options.ArenaOption;
import plugily.projects.villagedefense.creatures.CreatureUtils;
import plugily.projects.villagedefense.creatures.v1_16_R2.BabyZombie;
import plugily.projects.villagedefense.creatures.v1_16_R2.FastZombie;
import plugily.projects.villagedefense.creatures.v1_16_R2.GolemBuster;
import plugily.projects.villagedefense.creatures.v1_16_R2.HardZombie;
import plugily.projects.villagedefense.creatures.v1_16_R2.PlayerBuster;
import plugily.projects.villagedefense.creatures.v1_16_R2.RidableIronGolem;
import plugily.projects.villagedefense.creatures.v1_16_R2.RidableVillager;
import plugily.projects.villagedefense.creatures.v1_16_R2.TankerZombie;
import plugily.projects.villagedefense.creatures.v1_16_R2.VillagerBuster;
import plugily.projects.villagedefense.creatures.v1_16_R2.VillagerSlayer;
import plugily.projects.villagedefense.creatures.v1_16_R2.WorkingWolf;
import plugily.projects.villagedefense.handlers.language.Messages;

import java.util.Random;

/**
 * @author Plajer
 * <p>
 * Created at 29.04.2019
 */
public class ArenaInitializer1_16_R2 extends Arena {

  private World world;
  private final Main plugin;

  public ArenaInitializer1_16_R2(String id, Main plugin) {
    super(id);
    this.plugin = plugin;
  }

  @Override
  public void setWorld(Location loc) {
    world = ((CraftWorld) loc.getWorld()).getHandle();
  }

  @Override
  public void spawnFastZombie(Random random) {
    Location location = getZombieSpawns().get(random.nextInt(getZombieSpawns().size()));
    FastZombie fastZombie = new FastZombie(world);
    fastZombie.setPosition(location.getX(), location.getY(), location.getZ());
    world.addEntity(fastZombie, CreatureSpawnEvent.SpawnReason.CUSTOM);
    Zombie zombie = (Zombie) fastZombie.getBukkitEntity();
    zombie.setRemoveWhenFarAway(false);
    CreatureUtils.applyAttributes(zombie, this);
    plugin.getHolidayManager().applyHolidayZombieEffects(zombie);
    addZombie((Zombie) fastZombie.getBukkitEntity());

    super.setOptionValue(ArenaOption.ZOMBIES_TO_SPAWN, getOption(ArenaOption.ZOMBIES_TO_SPAWN) - 1);
  }

  @Override
  public void spawnHalfInvisibleZombie(Random random) {
    Location location = getZombieSpawns().get(random.nextInt(getZombieSpawns().size()));
    FastZombie fastZombie = new FastZombie(world);
    fastZombie.setPosition(location.getX(), location.getY(), location.getZ());
    world.addEntity(fastZombie, CreatureSpawnEvent.SpawnReason.CUSTOM);
    Zombie zombie = (Zombie) fastZombie.getBukkitEntity();
    zombie.setRemoveWhenFarAway(false);
    zombie.getEquipment().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
    zombie.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
    CreatureUtils.applyAttributes(zombie, this);
    addZombie((Zombie) fastZombie.getBukkitEntity());

    super.setOptionValue(ArenaOption.ZOMBIES_TO_SPAWN, getOption(ArenaOption.ZOMBIES_TO_SPAWN) - 1);
  }

  @Override
  public void spawnKnockbackResistantZombies(Random random) {
    Location location = getZombieSpawns().get(random.nextInt(getZombieSpawns().size()));
    TankerZombie tankerZombie = new TankerZombie(world);
    tankerZombie.getAttributeInstance(GenericAttributes.KNOCKBACK_RESISTANCE).setValue(Double.MAX_VALUE);
    tankerZombie.setPosition(location.getX(), location.getY(), location.getZ());
    world.addEntity(tankerZombie, CreatureSpawnEvent.SpawnReason.CUSTOM);
    Zombie zombie = (Zombie) tankerZombie.getBukkitEntity();
    InitializerHelper.prepareKnockbackResistantZombie(zombie, this);
    addZombie(zombie);

    super.setOptionValue(ArenaOption.ZOMBIES_TO_SPAWN, getOption(ArenaOption.ZOMBIES_TO_SPAWN) - 1);
  }

  @Override
  public void spawnBabyZombie(Random random) {
    Location location = getZombieSpawns().get(random.nextInt(getZombieSpawns().size()));
    BabyZombie babyZombie = new BabyZombie(world);
    babyZombie.setPosition(location.getX(), location.getY(), location.getZ());
    Zombie zombie = (Zombie) babyZombie.getBukkitEntity();
    CreatureUtils.applyAttributes(zombie, this);
    plugin.getHolidayManager().applyHolidayZombieEffects(zombie);
    zombie.setRemoveWhenFarAway(false);
    world.addEntity(babyZombie, CreatureSpawnEvent.SpawnReason.CUSTOM);
    addZombie((Zombie) babyZombie.getBukkitEntity());

    super.setOptionValue(ArenaOption.ZOMBIES_TO_SPAWN, getOption(ArenaOption.ZOMBIES_TO_SPAWN) - 1);
  }

  @Override
  public void spawnHardZombie(Random random) {
    Location location = getZombieSpawns().get(random.nextInt(getZombieSpawns().size()));
    HardZombie hardZombie = new HardZombie(world);
    hardZombie.setPosition(location.getX(), location.getY(), location.getZ());
    world.addEntity(hardZombie, CreatureSpawnEvent.SpawnReason.CUSTOM);
    Zombie zombie = (Zombie) hardZombie.getBukkitEntity();
    InitializerHelper.prepareHardZombie(zombie, this);
    addZombie(zombie);
    super.setOptionValue(ArenaOption.ZOMBIES_TO_SPAWN, getOption(ArenaOption.ZOMBIES_TO_SPAWN) - 1);
  }

  @Override
  public void spawnSoftHardZombie(Random random) {
    Location location = getZombieSpawns().get(random.nextInt(getZombieSpawns().size()));
    HardZombie hardZombie = new HardZombie(world);
    hardZombie.setPosition(location.getX(), location.getY(), location.getZ());
    world.addEntity(hardZombie, CreatureSpawnEvent.SpawnReason.CUSTOM);
    Zombie zombie = (Zombie) hardZombie.getBukkitEntity();
    InitializerHelper.prepareSoftHardZombie(zombie, this);
    addZombie(zombie);
    super.setOptionValue(ArenaOption.ZOMBIES_TO_SPAWN, getOption(ArenaOption.ZOMBIES_TO_SPAWN) - 1);
  }

  @Override
  public void spawnGolemBuster(Random random) {
    Location location = getZombieSpawns().get(random.nextInt(getZombieSpawns().size()));
    GolemBuster golemBuster = new GolemBuster(world);
    golemBuster.setPosition(location.getX(), location.getY(), location.getZ());
    world.addEntity(golemBuster, CreatureSpawnEvent.SpawnReason.CUSTOM);
    Zombie zombie = (Zombie) golemBuster.getBukkitEntity();
    InitializerHelper.prepareGolemBusterZombie(zombie, this);
    zombie.setRemoveWhenFarAway(false);
    CreatureUtils.applyAttributes(zombie, this);
    addZombie(zombie);

    super.setOptionValue(ArenaOption.ZOMBIES_TO_SPAWN, getOption(ArenaOption.ZOMBIES_TO_SPAWN) - 1);
  }

  @Override
  public void spawnPlayerBuster(Random random) {
    Location location = getZombieSpawns().get(random.nextInt(getZombieSpawns().size()));
    PlayerBuster playerBuster = new PlayerBuster(world);
    playerBuster.setPosition(location.getX(), location.getY(), location.getZ());
    world.addEntity(playerBuster, CreatureSpawnEvent.SpawnReason.CUSTOM);
    Zombie zombie = (Zombie) playerBuster.getBukkitEntity();
    InitializerHelper.preparePlayerBusterZombie(zombie, this);
    addZombie(zombie);

    super.setOptionValue(ArenaOption.ZOMBIES_TO_SPAWN, getOption(ArenaOption.ZOMBIES_TO_SPAWN) - 1);
  }

  @Override
  public void spawnVillagerBuster(Random random) {
    Location location = getZombieSpawns().get(random.nextInt(getZombieSpawns().size()));
    VillagerBuster villagerBuster = new VillagerBuster(world);
    villagerBuster.setPosition(location.getX(), location.getY(), location.getZ());
    world.addEntity(villagerBuster, CreatureSpawnEvent.SpawnReason.CUSTOM);
    Zombie zombie = (Zombie) villagerBuster.getBukkitEntity();
    InitializerHelper.prepareVillagerBusterZombie(zombie, this);
    addZombie(zombie);

    super.setOptionValue(ArenaOption.ZOMBIES_TO_SPAWN, getOption(ArenaOption.ZOMBIES_TO_SPAWN) - 1);
  }

  @Override
  public void spawnVillagerSlayer(Random random) {
    Location location = getZombieSpawns().get(random.nextInt(getZombieSpawns().size() - 1));
    VillagerSlayer villagerSlayer = new VillagerSlayer(world);
    villagerSlayer.setPosition(location.getX(), location.getY(), location.getZ());
    world.addEntity(villagerSlayer, CreatureSpawnEvent.SpawnReason.CUSTOM);
    Zombie zombie = (Zombie) villagerSlayer.getBukkitEntity();
    InitializerHelper.prepareVillagerSlayerZombie(zombie, this);
    addZombie(zombie);

    super.setOptionValue(ArenaOption.ZOMBIES_TO_SPAWN, getOption(ArenaOption.ZOMBIES_TO_SPAWN) - 1);
  }

  @Override
  public void spawnVillager(Location location) {
    RidableVillager ridableVillager = new RidableVillager(location.getWorld());
    ridableVillager.setPosition(location.getX(), location.getY(), location.getZ());
    world.addEntity(ridableVillager, CreatureSpawnEvent.SpawnReason.CUSTOM);
    Villager villager = (Villager) ridableVillager.getBukkitEntity();
    villager.setRemoveWhenFarAway(false);
    addVillager((Villager) ridableVillager.getBukkitEntity());
  }

  @Override
  public void spawnGolem(Location location, Player player) {
    if(!canSpawnMobForPlayer(player, org.bukkit.entity.EntityType.IRON_GOLEM)) {
      return;
    }

    RidableIronGolem ironGolem = new RidableIronGolem(location.getWorld());
    ironGolem.setPosition(location.getX(), location.getY(), location.getZ());
    ironGolem.getBukkitEntity().setCustomName(plugin.getChatManager().colorMessage(Messages.SPAWNED_GOLEM_NAME).replace("%player%", player.getName()));
    ironGolem.setCustomNameVisible(true);
    world.addEntity(ironGolem, CreatureSpawnEvent.SpawnReason.CUSTOM);

    addIronGolem((org.bukkit.entity.IronGolem) ironGolem.getBukkitEntity());
  }

  @Override
  public void spawnWolf(Location location, Player player) {
    if(!canSpawnMobForPlayer(player, org.bukkit.entity.EntityType.WOLF)) {
      return;
    }

    WorkingWolf wolf = new WorkingWolf(location.getWorld());
    wolf.setPosition(location.getX(), location.getY(), location.getZ());
    world.addEntity(wolf, CreatureSpawnEvent.SpawnReason.CUSTOM);
    wolf.getBukkitEntity().setCustomName(plugin.getChatManager().colorMessage(Messages.SPAWNED_WOLF_NAME).replace("%player%", player.getName()));
    wolf.setCustomNameVisible(true);
    wolf.setInvisible(false);
    ((Wolf) wolf.getBukkitEntity()).setOwner(player);

    addWolf((Wolf) wolf.getBukkitEntity());
  }

}

