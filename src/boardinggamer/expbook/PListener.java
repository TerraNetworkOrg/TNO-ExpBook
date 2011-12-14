package boardinggamer.expbook;

import com.nisovin.bookworm.Book;
import com.nisovin.bookworm.BookWorm;
import org.bukkit.ChatColor;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

class PListener extends PlayerListener {

    expbook plugin;

    public PListener(expbook aThis) {
        this.plugin = aThis;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player plr = event.getPlayer();
            Block block = event.getClickedBlock();
            if (expbook.bookworm == false) {
                if (block.getTypeId() == expbook.altarblock) {
                    if (expbook.permission == null) {
                        if (plr.hasPermission("expbook.use")) {
                            if (block.getFace(BlockFace.UP).getTypeId() == 76) {
                                if (plr.getItemInHand().getTypeId() == 340) {
                                    plr.sendMessage(ChatColor.LIGHT_PURPLE + "You got " + plr.getItemInHand().getAmount() * expbook.exp + " exp!");
                                    ((ExperienceOrb) block.getWorld().spawn(plr.getLocation(), ExperienceOrb.class)).setExperience(plr.getItemInHand().getAmount() * expbook.exp);
                                    plr.setItemInHand(new ItemStack(Material.AIR));
                                }
                            }
                        } else {
                            plr.sendMessage(ChatColor.RED + "You do not have permission to use altars.");
                        }
                    } else if (expbook.permission != null) {
                        if (expbook.permission.has(plr, "expbook.use")) {
                            if (block.getFace(BlockFace.UP).getTypeId() == 76) {
                                if (plr.getItemInHand().getTypeId() == 340) {
                                    plr.sendMessage(ChatColor.LIGHT_PURPLE + "You got " + plr.getItemInHand().getAmount() * expbook.exp + " exp!");
                                    ((ExperienceOrb) block.getWorld().spawn(plr.getLocation(), ExperienceOrb.class)).setExperience(plr.getItemInHand().getAmount() * expbook.exp);
                                    plr.setItemInHand(new ItemStack(Material.AIR));
                                }
                            }
                        } else {
                            plr.sendMessage(ChatColor.RED + "You do not have permission to use altars.");
                        }
                    } else {
                        System.out.println("ERROR with config. Please check the permissions string.");
                        System.out.println("Shutting down the plugin.");
                        plr.getServer().getPluginManager().disablePlugin(plugin);
                    }
                    
                    
                    
                } else if (block.getType() == Material.IRON_BLOCK) {
                    if (expbook.permission == null) {
                        if (plr.hasPermission("expbook.use")) {
                            if (block.getFace(BlockFace.UP).getTypeId() == 76) {
                                if (plr.getExperience() >= expbook.exp){
                                    ((ExperienceOrb) block.getWorld().spawn(plr.getLocation(), ExperienceOrb.class)).setExperience(expbook.exp - (expbook.exp * 2));
                                    plr.getInventory().addItem(new ItemStack(Material.BOOK, 1));
                                    plr.sendMessage(ChatColor.LIGHT_PURPLE+"You spent "+expbook.exp+" to get a book.");
                                }
                            }
                        } else {
                            plr.sendMessage(ChatColor.RED + "You do not have permission to use altars.");
                        }
                    } else if (expbook.permission != null) {
                        if (expbook.permission.has(plr, "expbook.use")) {
                            if (block.getFace(BlockFace.UP).getTypeId() == 76) {
                                if (plr.getExperience() >= expbook.exp){
                                    ((ExperienceOrb) block.getWorld().spawn(plr.getLocation(), ExperienceOrb.class)).setExperience(expbook.exp - (expbook.exp * 2));
                                    plr.getInventory().addItem(new ItemStack(Material.BOOK, 1));
                                    plr.sendMessage(ChatColor.LIGHT_PURPLE+"You spent "+expbook.exp+" to get a book.");
                                }
                            }
                        } else {
                            plr.sendMessage(ChatColor.RED + "You do not have permission to use altars.");
                        }
                    } else {
                        System.out.println("ERROR with config. Please check the permissions string.");
                        System.out.println("Shutting down the plugin.");
                        plr.getServer().getPluginManager().disablePlugin(plugin);
                    }
                }
                
                
                
            } else if (expbook.bookworm == true) {
                Book book = BookWorm.getBook(plr);
                if (block.getTypeId() == expbook.altarblock) {
                    if (expbook.permission == null) {
                        if (plr.hasPermission("expbook.use")) {
                            if (block.getFace(BlockFace.UP).getTypeId() == 76) {
                                if (plr.getItemInHand().getTypeId() == 340) {
                                    if (book != null) {
                                        if (book.getTitle().equalsIgnoreCase(expbook.booktitle)) {
                                            plr.sendMessage(ChatColor.LIGHT_PURPLE + "You got " + plr.getItemInHand().getAmount() * expbook.exp + " exp!");
                                            ((ExperienceOrb) block.getWorld().spawn(plr.getLocation(), ExperienceOrb.class)).setExperience(plr.getItemInHand().getAmount() * expbook.exp);
                                            plr.setItemInHand(new ItemStack(Material.AIR));
                                        } else {
                                            plr.sendMessage(ChatColor.RED + "This is not the right book to get exp from.");
                                        }
                                    } else {
                                        plr.sendMessage(ChatColor.RED + "This is not the right book to get exp from.");
                                    }
                                }
                            }
                        } else {
                            plr.sendMessage(ChatColor.RED + "You do not have permission to use altars.");
                        }
                    } else if (expbook.permission != null) {
                        if (expbook.permission.has(plr, "expbook.use")) {
                            if (block.getFace(BlockFace.UP).getTypeId() == 76) {
                                if (plr.getItemInHand().getTypeId() == 340) {
                                    if (book != null) {
                                        if (book.getTitle().equalsIgnoreCase(expbook.booktitle)) {
                                            plr.sendMessage(ChatColor.LIGHT_PURPLE + "You got " + plr.getItemInHand().getAmount() * expbook.exp + " exp!");
                                            ((ExperienceOrb) block.getWorld().spawn(plr.getLocation(), ExperienceOrb.class)).setExperience(plr.getItemInHand().getAmount() * expbook.exp);
                                            plr.setItemInHand(new ItemStack(Material.AIR));
                                        } else {
                                            plr.sendMessage(ChatColor.RED + "This is not the right book to get exp from.");
                                        }
                                    } else {
                                        plr.sendMessage(ChatColor.RED + "This is not the right book to get exp from.");
                                    }
                                }
                            }
                        } else {
                            plr.sendMessage(ChatColor.RED + "You do not have permission to use altars.");
                        }
                    } else {
                        System.out.println("ERROR with config. Please check the permissions string.");
                        System.out.println("Shutting down the plugin.");
                        plr.getServer().getPluginManager().disablePlugin(plugin);
                    }
                }
            }
        }
    }
}