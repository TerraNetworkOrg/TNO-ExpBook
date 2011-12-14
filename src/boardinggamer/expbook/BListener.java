package boardinggamer.expbook;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

class BListener extends BlockListener {
    expbook plugin;
    public BListener(expbook aThis) {
        this.plugin=aThis;
    }

    @Override
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player plr = event.getPlayer();
        if (block.getTypeId()==76){
            if (block.getFace(BlockFace.DOWN).getTypeId()==expbook.altarblock){
                if (expbook.permission == null){
                if (plr.hasPermission("expbook.create")){
                plr.sendMessage(ChatColor.LIGHT_PURPLE+"You have created an altar!");
                } else {
                    block.setType(Material.AIR);
                    block.getFace(BlockFace.DOWN).setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.REDSTONE_TORCH_ON, 1));
                    block.getWorld().dropItemNaturally(block.getLocation().add(0, -1, 0), new ItemStack(expbook.altarblock, 1));
                    plr.sendMessage(ChatColor.DARK_RED+"You can not create an altar.");
                }
            } else if (expbook.permission != null){
                if (expbook.permission.has(plr, "expbook.create")){
                plr.sendMessage(ChatColor.LIGHT_PURPLE+"You have created an altar!");
                } else {
                    block.setType(Material.AIR);
                    block.getFace(BlockFace.DOWN).setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.REDSTONE_TORCH_ON, 1));
                    block.getWorld().dropItemNaturally(block.getLocation().add(0, -1, 0), new ItemStack(expbook.altarblock, 1));
                    plr.sendMessage(ChatColor.DARK_RED+"You can not create an altar.");
                }
                }
            }
            } else if (block.getTypeId()==expbook.altarblock){
            if (block.getFace(BlockFace.UP).getTypeId()==76){
                if (plr.isOp()){
                plr.sendMessage(ChatColor.LIGHT_PURPLE+"You have created an alter!");
                } else {
                    block.setType(Material.AIR);
                    block.getFace(BlockFace.UP).setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation().add(0, 1, 0), new ItemStack(Material.REDSTONE_TORCH_ON, 1));
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(expbook.altarblock, 1));
                    plr.sendMessage(ChatColor.DARK_RED+"You can not create an alter.");
                  }
             }
             } 
            else if (block.getFace(BlockFace.DOWN).getType()==Material.IRON_BLOCK){
                if (expbook.permission == null){
                if (plr.hasPermission("expbook.create")){
                plr.sendMessage(ChatColor.LIGHT_PURPLE+"You have created an altar!");
                } else {
                    block.setType(Material.AIR);
                    block.getFace(BlockFace.DOWN).setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.REDSTONE_TORCH_ON, 1));
                    block.getWorld().dropItemNaturally(block.getLocation().add(0, -1, 0), new ItemStack(Material.IRON_BLOCK, 1));
                    plr.sendMessage(ChatColor.DARK_RED+"You can not create an altar.");
                }
            } else if (expbook.permission != null){
                if (expbook.permission.has(plr, "expbook.create")){
                plr.sendMessage(ChatColor.LIGHT_PURPLE+"You have created an altar!");
                } else {
                    block.setType(Material.AIR);
                    block.getFace(BlockFace.DOWN).setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.REDSTONE_TORCH_ON, 1));
                    block.getWorld().dropItemNaturally(block.getLocation().add(0, -1, 0), new ItemStack(Material.IRON_BLOCK, 1));
                    plr.sendMessage(ChatColor.DARK_RED+"You can not create an altar.");
                }
                }
            } else if (block.getType()==Material.IRON_BLOCK){
            if (block.getFace(BlockFace.UP).getTypeId()==76){
                if (plr.isOp()){
                plr.sendMessage(ChatColor.LIGHT_PURPLE+"You have created an alter!");
                } else {
                    block.setType(Material.AIR);
                    block.getFace(BlockFace.UP).setType(Material.AIR);
                    block.getWorld().dropItemNaturally(block.getLocation().add(0, 1, 0), new ItemStack(Material.REDSTONE_TORCH_ON, 1));
                    block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.IRON_BLOCK, 1));
                    plr.sendMessage(ChatColor.DARK_RED+"You can not create an alter.");
                  }
             }
              }
        }
    
    
    
    
    
    
    
    
    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player plr = event.getPlayer();
        if (expbook.permission == null) {
        if (!(plr.hasPermission("expbook.create"))){
           if (block.getTypeId()==76){
               if (block.getFace(BlockFace.DOWN).getTypeId()==expbook.altarblock || block.getFace(BlockFace.DOWN).getType()==Material.IRON_BLOCK){
                   event.setCancelled(true);
                   plr.sendMessage(ChatColor.DARK_RED+"You can not break an altar.");
               }
           } else if (block.getTypeId()==expbook.altarblock || block.getType()==Material.IRON_BLOCK){
               if (block.getFace(BlockFace.UP).getTypeId()==76){
                   event.setCancelled(true);
                   plr.sendMessage(ChatColor.DARK_RED+"You can not break an altar.");
               }
             }
        }
        } else if (expbook.permission != null) {
        if (!(expbook.permission.has(plr, "expbook.create"))){
           if (block.getTypeId()==76){
               if (block.getFace(BlockFace.DOWN).getTypeId()==expbook.altarblock || block.getFace(BlockFace.DOWN).getType()==Material.IRON_BLOCK){
                   event.setCancelled(true);
                   plr.sendMessage(ChatColor.DARK_RED+"You can not break an altar.");
               }
           } else if (block.getTypeId()==expbook.altarblock || block.getType()==Material.IRON_BLOCK){
               if (block.getFace(BlockFace.UP).getTypeId()==76){
                   event.setCancelled(true);
                   plr.sendMessage(ChatColor.DARK_RED+"You can not break an altar.");
               }
             }
        }
    }
}
}
