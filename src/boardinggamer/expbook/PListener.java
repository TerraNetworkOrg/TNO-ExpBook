package boardinggamer.expbook;

import com.nisovin.bookworm.Book;
import com.nisovin.bookworm.BookWorm;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
//import org.bukkit.block.Sign;
import org.bukkit.entity.ExperienceOrb;
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

    public void GiveEXP(Player player, int exp) {
    	int old_exp = player.getTotalExperience();
    	player.setExp(0);
    	player.setLevel(0);
    	player.setTotalExperience(0);
    	player.giveExp(old_exp + exp);
    }
    
    public void TakeEXP(Player player, int exp) {
    	int old_exp = player.getTotalExperience();
    	player.setExp(0);
    	player.setLevel(0);
    	player.setTotalExperience(0);
    	//player.giveExp(old_exp - exp);
    }
    
    public void SetItem(Player player, ItemStack item, int amount){
    	player.getWorld().dropItem(player.getLocation(), item);
		player.sendMessage(ChatColor.GREEN + "You converted a boook into exp!");
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public void onPlayerInteract(PlayerInteractEvent event) {
    	
    	Player plr = event.getPlayer();
        Block block = event.getClickedBlock();
        //Sign signBlock = (Sign) event.getClickedBlock().getState();
        //String[] signLines = signBlock.getLines();
        
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        	
        	expEditor e = new expEditor(plr);	
        	
            if (expbook.bookworm == false) {
                if (block.getTypeId() == expbook.expblock) {
                    if (plugin.permission == null) {
                        if (plr.isOp()) {
                            if (block.getFace(BlockFace.UP).getTypeId() == 76) {
                                if (plr.getItemInHand().getTypeId() == 340) {
                                    plr.sendMessage(ChatColor.LIGHT_PURPLE + "You got " + expbook.exp + " exp!");
                                    //int exp = plr.getItemInHand().getAmount() * expbook.exp;
                                    int exp = expbook.exp;
                                    //GiveEXP(plr, exp);
                                    if(e.getTotalExp() > exp) {
                                        //event.setNewExp(e.getTotalExp()-loss.intValue());
                                        plugin.expBuffer.put(plr.getName(), e.getTotalExp()+exp);
                                    } else {
                                    	plugin.expBuffer.put(plr.getName(), 0);
                                    }
                                    new expEditor(plr).giveExp(exp);
                                    ItemStack handItem = plr.getItemInHand();
                                    int handItemAmount = handItem.getAmount();
                                    ItemStack new_handItem;
									if (handItemAmount > 1) {
                                    	new_handItem = new ItemStack(340, handItemAmount - 1);
                                    }
                                    else {
                                    	new_handItem = new ItemStack(Material.AIR);
                                    }
                                    plr.setItemInHand(new_handItem);
                                }
                            }
                        } else {
                            plr.sendMessage(ChatColor.RED + "You do not have permission to use altars.");
                        }
                    } else if (plugin.permission != null) {
                        if (plugin.permission.has(plr, "expbook.use")) {
                            if (block.getFace(BlockFace.UP).getTypeId() == 76) {
                                if (plr.getItemInHand().getTypeId() == 340) {
                                    plr.sendMessage(ChatColor.LIGHT_PURPLE + "You got " + expbook.exp + " exp!");
                                    //int exp = plr.getItemInHand().getAmount() * expbook.exp;
                                    int exp = expbook.exp;
                                    //GiveEXP(plr, exp);
                                    if(e.getTotalExp() > exp) {
                                        //event.setNewExp(e.getTotalExp()-loss.intValue());
                                        plugin.expBuffer.put(plr.getName(), e.getTotalExp()+exp);
                                    } else {
                                    	plugin.expBuffer.put(plr.getName(), 0);
                                    }
                                    new expEditor(plr).giveExp(exp);
                                    ItemStack handItem = plr.getItemInHand();
                                    ItemStack new_handItem;
                                    int handItemAmount = handItem.getAmount();
                                    if (handItemAmount > 1) {
                                    	new_handItem = new ItemStack(340, handItemAmount - 1);
                                    }
                                    else {
                                    	new_handItem = new ItemStack(Material.AIR);
                                    }
                                    plr.setItemInHand(new_handItem);
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
                    
                } else if (block.getTypeId() == expbook.bookblock) {
                    if (plugin.permission == null) {
                        if (plr.isOp()) {
                            if (block.getFace(BlockFace.UP).getTypeId() == 76) {
                            	int exp = expbook.exp;
                            	if(plr.getTotalExperience() >= exp){
                            		//TakeEXP(plr, exp);
                            		plugin.expBuffer.put(plr.getName(), e.getTotalExp()-exp);
                            		Integer newExp = plugin.expBuffer.get(event.getPlayer().getName());
                            		plugin.expBuffer.remove(event.getPlayer().getName());
                            		TakeEXP(plr, 0);
                            		new expEditor(plr).giveExp(newExp);
                                    SetItem(plr, new ItemStack(Material.BOOK, 1), 1);
                            	}
                            	else {
                            		plr.sendMessage(ChatColor.RED + "You do not have enough books to convert.");
                            	}
                            }
                        } else {
                            plr.sendMessage(ChatColor.RED + "You do not have permission to use altars.");
                        }
                    } else if (plugin.permission != null) {
                        if (plugin.permission.has(plr, "expbook.use")) {
                        	int exp = expbook.exp;
                        	if(plr.getTotalExperience() >= exp){
                        		//TakeEXP(plr, exp);
                        		plugin.expBuffer.put(plr.getName(), e.getTotalExp()-exp);
                        		Integer newExp = plugin.expBuffer.get(event.getPlayer().getName());
                        		plugin.expBuffer.remove(event.getPlayer().getName());
                        		TakeEXP(plr, 0);
                        		new expEditor(plr).giveExp(newExp);
                        		SetItem(plr, new ItemStack(Material.BOOK, 1), 1);
                        	}
                        	else {
                        		plr.sendMessage(ChatColor.RED + "You do not have enough books to convert.");
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
                if (block.getTypeId() == expbook.expblock) {
                    if (plugin.permission == null) {
                        if (plr.isOp()) {
                            if (block.getFace(BlockFace.UP).getTypeId() == 76) {
                                if (plr.getItemInHand().getTypeId() == 340) {
                                    if (book != null) {
                                        if (book.getTitle().equalsIgnoreCase(expbook.booktitle)) {
                                        	plr.sendMessage(ChatColor.LIGHT_PURPLE + "You got " + plr.getItemInHand().getAmount() * expbook.exp + " exp!");
                                            int exp = plr.getItemInHand().getAmount() * expbook.exp;
                                            GiveEXP(plr, exp);
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
                    } else if (plugin.permission != null) {
                        if (plugin.permission.has(plr, "expbook.use")) {
                            if (block.getFace(BlockFace.UP).getTypeId() == 76) {
                                if (plr.getItemInHand().getTypeId() == 340) {
                                    if (book != null) {
                                        if (book.getTitle().equalsIgnoreCase(expbook.booktitle)) {
                                        	plr.sendMessage(ChatColor.LIGHT_PURPLE + "You got " + plr.getItemInHand().getAmount() * expbook.exp + " exp!");
                                            int exp = plr.getItemInHand().getAmount() * expbook.exp;
                                            GiveEXP(plr, exp);
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