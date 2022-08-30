package net.justcoded.mc_core.components.commands;

import net.justcoded.mc_core.components.collections.CLinkedMap;
import net.justcoded.mc_core.components.commands.arguments.CCommandArg;
import net.justcoded.mc_core.components.commands.arguments.CCommandArgument;
import net.justcoded.mc_core.components.commands.suggestions.CCommandSuggestion;
import net.justcoded.mc_core.components.commands.suggestions.CSimpleSuggestion;
import net.justcoded.mc_core.components.commands.suggestions.StaticSuggestions;
import net.justcoded.mc_core.utilities.CString;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CCommand extends BukkitCommand {
    private boolean isMain;
    private List<Integer> argsNumber = new ArrayList<>(List.of(-1));
    private final String name;
    private final List<CCommand> list = new ArrayList<>();
    private final CLinkedMap<String, CCommandArg> args = new CLinkedMap<>();
    private BiConsumer<CCommandSender, CCommandArgument> biConsumer;
    private final List<CCommandSuggestion> suggestions = new ArrayList<>();
    private final List<CSimpleSuggestion> simpleSuggestions = new ArrayList<>();
    private boolean hasToBePlayer, removeDefaultCommandSuggestion;
    private CCommandPermission seeTabComplete = new CCommandPermission(null, null, CCommandPermission.PERMISSION_TYPE.SEE_TAB_COMPLETE), useCommand = new CCommandPermission(null, null, CCommandPermission.PERMISSION_TYPE.USE_COMMAND);

    public CCommand(String name) {
        super(name);
        this.name = name;
        this.list.add(this);
        this.isMain = true;
        addArgNumber();
    }

    public CCommand addCommand(CCommand subCommand) {
        this.list.add(subCommand);
        subCommand.isMain = false;
        subCommand.addArgNumber();
        return this;
    }

    public CCommand addArgSuggestion(CCommandArg arg, CSimpleSuggestion suggestion) {
        return addArg(arg).addSuggestion(suggestion);
    }

    public CCommand addArgSuggestion(CCommandArg arg, CCommandSuggestion suggestion) {
        return addArg(arg).addSuggestion(suggestion);
    }

    public CCommand addArgSuggestion(CCommandArg arg, StaticSuggestions.SUGGESTION_TYPE suggestion) {
        return addArg(arg).addSuggestion(suggestion);
    }

    public CCommand addArgSuggestion(CCommandArg arg, String suggestion) {
        return addArg(arg).addSuggestion(suggestion);
    }

    public CCommand addArgSuggestion(CCommandArg arg, List<String> suggestion) {
        return addArg(arg).addSuggestion(suggestion);
    }

    public CCommand addSuggestion(CCommandSuggestion suggestion) {
        suggestions.add(suggestion);
        return addArgNumber();
    }

    public CCommand addSuggestion(CSimpleSuggestion suggestion) {
        simpleSuggestions.add(suggestion);
        return addArgNumber();
    }

    public CCommand addSuggestion(StaticSuggestions.SUGGESTION_TYPE suggestion) {
        return addSuggestion(StaticSuggestions.get(suggestion));
    }

    public CCommand addSuggestion(String suggestion) {
        return addSuggestion(new CSimpleSuggestion(suggestion));
    }

    public CCommand addSuggestion(List<String> suggestions) {
        return addSuggestion(new CSimpleSuggestion(suggestions));
    }

    public List<CCommand> getCommands() {
        return list;
    }

    public CCommand addArg(CCommandArg arg) {
        args.set(arg.getName(), arg);
        return this;
    }

    public List<CCommand> getCommandsForArg(int arg) {
        return list.stream().filter(command -> command.getArgsNumber().stream().anyMatch(integer -> integer.equals(arg - 1))).toList();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (hasToBePlayer && !(sender instanceof Player)) {
            return false;
        }
        if (args.length > 1) {
            for (CCommand command : getCommandsForArg(args.length)) {
                if (command.getName().equals(args[0]) || list.size() == 1) {
                    if (command.getUseCommand().hasPermission(sender)) {
                        command.executeFunction(sender, args);
                    }
                }
            }
        } else {
            executeFunction(sender, args);
        }
        return false;
    }

    public void executeFunction(CommandSender sender, String[] args) {
        try {
            biConsumer.accept(new CCommandSender(sender), new CCommandArgument(this.args, args, isMain ? -1 : 0));
        } catch (NullPointerException ignore) {}
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        List<String> strings = new ArrayList<>();
        for (CCommand command : getCommandsForArg(args.length)) {
            if (command.getSeeTabComplete().hasPermission(sender)) {
                strings.addAll(command.tabCompleteFunction(sender, args));
            }
        }
        if (isMain) {
            strings.addAll(tabCompleteFunction(sender, args));
        }
        return CString.copyPartialMatches(args[args.length - 1], strings);
    }

    public List<String> tabCompleteFunction(CommandSender sender, String [] args) {
        List<String> completions = new ArrayList<>();
        if (args.length > getHighestArgNumber()) {
            return completions;
        }
        if (isMain) {
            if (simpleSuggestions.size() != 0) {
                completions.addAll(simpleSuggestions.get(args.length - 1).getSuggestions());
            } else if (suggestions.size() != 0) {
                completions.addAll(suggestions.get(args.length - 1).apply(new CCommandSender(sender), args));
            }

            if (completions.size() == 0) {
                completions.addAll(list.stream()
                        .map(CCommand::getName)
                        .filter(s -> !(s.equals(name))).toList());
            }
        } else if (args.length > 1) {
            if (getName().equals(args[0])) {
                if (simpleSuggestions.size() + 1 >= args.length) {
                    completions.addAll(simpleSuggestions.get(args.length - 2).getSuggestions());
                }
                if (suggestions.size() + 1 >= args.length) {
                    completions.addAll(suggestions.get(args.length - 2).apply(new CCommandSender(sender), args));
                }
            }
        }
        return completions;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args,  Location location) throws IllegalArgumentException {
        return tabComplete(sender, alias, args);
    }

    public CCommand register() {
        if (isRegistered()) {
            return this;
        }
        if (!removeDefaultCommandSuggestion) {
            List<String> names = new ArrayList<>();
            for (CCommand command : list) {
                String name = command.name;
                if (!name.equals(this.name)) {
                    names.add(name);
                }
            }
            if (names.size() != 0) {
                addSuggestion(names);
            }
        }
        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            Command command = commandMap.getCommand(this.getName());
            if (command == null || !command.isRegistered()) {
                commandMap.register(name, this);
                CCommandRegister.addCommand(this);
            } else {
                throw new IllegalStateException("This command is already registered.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    @SuppressWarnings("unchecked")
    public CCommand unregister() {
        try {
            Field commandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            Field knownCommands = SimpleCommandMap.class.getDeclaredField("knownCommands");
            commandMap.setAccessible(true);
            knownCommands.setAccessible(true);
            ((Map<String, Command>) knownCommands.get(commandMap.get(Bukkit.getServer()))).remove(name);
            this.unregister((CommandMap) commandMap.get(Bukkit.getServer()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public BiConsumer<CCommandSender, CCommandArgument> getConsumer() {
        return biConsumer;
    }

    public CCommand execute(BiConsumer<CCommandSender, CCommandArgument> biConsumer) {
        this.biConsumer = biConsumer;
        return this;
    }

    public CCommand executeAsPlayer(BiConsumer<CCommandSender, CCommandArgument> biConsumer) {
        setHasToBePlayer(true);
        return execute(biConsumer);
    }

    public CCommand executeAsPlayerWithArgLength(BiConsumer<CCommandSender, CCommandArgument> biConsumer, int argLength) {
        setHasToBePlayer(true);
        return executeWithArgLength(biConsumer, argLength);
    }

    public CCommand execute(Consumer<CCommandSender> consumer) {
        this.biConsumer = (sender, args) -> consumer.accept(sender);
        return this;
    }

    public CCommand executeWithArgLength(BiConsumer<CCommandSender, CCommandArgument> biConsumer, int argLength) {
        this.biConsumer = ((sender, args) -> {
            if (args.getLength() == argLength) {
                biConsumer.accept(sender, args);
            }
        });
        return this;
    }

    public CCommand executeWithArgLength(Consumer<CCommandSender> consumer, int argLength) {
        this.biConsumer = ((sender, args) -> {
            if (args.getLength() == argLength) {
                consumer.accept(sender);
            }
        });
        return this;
    }

    public List<Integer> getArgsNumber() {
        return argsNumber;
    }

    public CCommand setArgsNumber(List<Integer> argsNumber) {
        this.argsNumber = argsNumber;
        return this;
    }

    public CCommand setArgsNumber(Integer... argsNumber) {
        this.argsNumber = List.of(argsNumber);
        return this;
    }

    public CCommand addArgNumber() {
        List<Integer> list = getArgsNumber();
        int last = -1;
        if (list.size() != 0) {
            last = list.get(list.size() - 1);
        }
        if (last == -1) {
            if (isMain) {
                list.set(0, 0);
            } else {
                list.set(0, 1);
            }
        } else {
            list.add(last + 1);
        }
        return setArgsNumber(list);
    }

    public int getHighestArgNumber() {
        int highest = -1;
        for (int number : getArgsNumber()) {
            if (number > highest) {
                highest = number;
            }
        }
        return highest;
    }

    public boolean hasToBePlayer() {
        return hasToBePlayer;
    }

    public CCommand setHasToBePlayer(boolean hasToBePlayer) {
        this.hasToBePlayer = hasToBePlayer;
        return this;
    }

    public boolean isRemoveDefaultCommandSuggestion() {
        return removeDefaultCommandSuggestion;
    }

    public CCommand setRemoveDefaultCommandSuggestion(boolean removeDefaultCommandSuggestion) {
        this.removeDefaultCommandSuggestion = removeDefaultCommandSuggestion;
        return this;
    }

    public CCommandPermission getSeeTabComplete() {
        return seeTabComplete;
    }

    public CCommand setSeeTabComplete(String permission) {
        this.seeTabComplete = new CCommandPermission(permission, "", CCommandPermission.PERMISSION_TYPE.SEE_TAB_COMPLETE);
        return this;
    }

    public CCommandPermission getUseCommand() {
        return useCommand;
    }

    public CCommand setUseCommand(String permission, String noPermissionMessage) {
        this.useCommand = new CCommandPermission(permission, noPermissionMessage, CCommandPermission.PERMISSION_TYPE.USE_COMMAND);
        return this;
    }
}