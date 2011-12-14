package boardinggamer.expbook;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

@SuppressWarnings("deprecation")
public class expbook extends JavaPlugin {

    public static int altarblock = Integer.SIZE;
    public static int exp = Integer.SIZE;
    public static boolean bookworm;
    public static String booktitle;
    Configuration config;
    public static Permission permission = null;

    private Boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }    
    
    @Override
    public void onDisable() {
        System.out.println(this + " has been disabled");
    }

    @Override
    public void onEnable() {
        System.out.println(this + " has been enabled");
        PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvent(Type.PLAYER_INTERACT, new PListener(this), Priority.Normal, this);
        pm.registerEvent(Type.BLOCK_PLACE, new BListener(this), Priority.Lowest, this);
        pm.registerEvent(Type.BLOCK_BREAK, new BListener(this), Priority.Normal, this);

        config = getConfiguration();
        config.load();
        altarblock = config.getInt("Block for altar", 22);
        exp = config.getInt("Exp per book", 10);
        bookworm = config.getBoolean("Bookworm", false);
        booktitle = config.getString("Book title", "Book of exp");
        config.save();
        
        if (!setupPermissions()) {
            System.out.println("Null perm");
           //use these if you require econ
          //getServer().getPluginManager().disablePlugin(this);
          //return;
        }

        if (expbook.bookworm == true) {
            System.out.println(this + " is using Bookworm and is using the book title, " + expbook.booktitle + ".");
        } else {
            System.out.println(this + " is not using bookworm.");
        }
        if (expbook.permission != null) {
            System.out.println(this + " is using Permissions");
        } else {
            System.out.println(this + " is not using Permissions.");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLayout, String args[]) {
        if (cmd.getName().toLowerCase().equals("expbook")) {
            if (args.length < 3) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender instanceof Player) {
                        if (expbook.permission == null) {
                            Player plr = (Player) sender;
                            if (plr.hasPermission("expbook.command.reload")) {
                                this.getServer().getPluginManager().disablePlugin(this);
                                this.getServer().getPluginManager().enablePlugin(this);
                                plr.sendMessage(ChatColor.LIGHT_PURPLE + "expbook has been reloaded.");
                            } else {
                                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                            }
                        } else if (expbook.permission != null) {
                            Player plr = (Player) sender;
                            if (expbook.permission.has(plr, "expbook.command.reload")) {
                                this.getServer().getPluginManager().disablePlugin(this);
                                this.getServer().getPluginManager().enablePlugin(this);
                                plr.sendMessage(ChatColor.LIGHT_PURPLE + "expbook has been reloaded.");
                            } else {
                                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                            }
                        } else {
                            Player plr = (Player) sender;
                            System.out.println("ERROR with config. Please check the permissions string.");
                            System.out.println("Shutting down the plugin.");
                            plr.getServer().getPluginManager().disablePlugin(this);
                        }
                    } else {
                        this.getServer().getPluginManager().disablePlugin(this);
                        this.getServer().getPluginManager().enablePlugin(this);
                    }
                } else if (args[0].equalsIgnoreCase("help")) {
                    if (sender instanceof Player) {
                        if (expbook.permission == null) {
                            Player plr = (Player) sender;
                            if (plr.hasPermission("expbook.command.help")) {
                                plr.sendMessage(ChatColor.GREEN + "-----expbook Help-----");
                                plr.sendMessage(ChatColor.LIGHT_PURPLE + "/expbook reload");
                                plr.sendMessage(ChatColor.AQUA + "Reloads expbook");
                                plr.sendMessage(ChatColor.LIGHT_PURPLE + "/expbook help");
                                plr.sendMessage(ChatColor.AQUA + "Shows this message.");
                            } else {
                                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                            }
                        } else if (expbook.permission != null) {
                            Player plr = (Player) sender;
                            if (expbook.permission.has(plr, "expbook.command.help")) {
                                plr.sendMessage(ChatColor.GREEN + "-----expbook Help-----");
                                plr.sendMessage(ChatColor.LIGHT_PURPLE + "/expbook reload");
                                plr.sendMessage(ChatColor.AQUA + "Reloads expbook");
                                plr.sendMessage(ChatColor.LIGHT_PURPLE + "/expbook help");
                                plr.sendMessage(ChatColor.AQUA + "Shows this message.");
                            } else {
                                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                            }
                        } else {
                            Player plr = (Player) sender;
                            System.out.println("ERROR with config. Please check the permissions string.");
                            System.out.println("Shutting down the plugin.");
                            plr.getServer().getPluginManager().disablePlugin(this);
                        }
                    } else {
                        System.out.println("This is for player use only.");
                    }
                } else {
                    if (sender instanceof Player) {
                        Player plr = (Player) sender;
                        plr.sendMessage(ChatColor.RED + "Use /expbook [help | reload].");
                    } else {
                        System.out.println("the command is expbook [reload].");
                    }
                }
            } else {
                if (sender instanceof Player) {
                    Player plr = (Player) sender;
                    plr.sendMessage(ChatColor.RED + "Use /expbook [help | disable | reload | bookworm [true | false]].");
                } else {
                    System.out.println("the command is expbook [disable | reload].");
                }
            }
        }
        return true;
    }
}
//M0nk3yc0d3r
//no tricks
//no secrets