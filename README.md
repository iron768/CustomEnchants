# CustomEnchants
A custom enchants plugin for 1.20.1. This started as a recode/port of PixelyMC's Custom Enchants to fix a bug with players getting multiple copies of enchants they already have. The direction of this plugin can change on a whim as I decide what to work on.

> [!IMPORTANT]
> This plugin is very early in development and will most likely have many bugs, THIS SHOULD NOT BE RAN IN PRODUCTION ENVIRONMENTS! If you encounter any bugs please make an issue and follow the bug report format, thanks.

## Requirements:
These plugins must be ran on the server alongside the custom enchants plugin.
  
[NBTAPI](https://www.spigotmc.org/resources/nbt-api.7939/) 
[ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/)

## Special thanks

Tabbin's original codebase for PixelyMC's custom enchant.

Armor equipping [library](https://www.spigotmc.org/threads/arnuhs-armorequipevent.545188/) 

## Features:

### Completed:
```
All enchants currently implemented are 100% complete and working.

Enchant types:
Hold item,
Armor equip

Enchants:
Drunk,
Gears,
Haste,
Overload,
Springs
```

### To do:
```
GUI handling/api

implement more enchant types
 - weapons, tools, etc

better performance / code review
 - things that can be handled off the main thread should be done off main thread
 - look for stupid mistakes

language / better message formatting
 - handle potion effects properly, ex. "FAST_DIGGING" should be "Haste"

enchantment books
 - right click the book to get a random enchantment of that books rarity
 - drag books onto weapons to apply enchants (these books have apply chances)

particles
 - display particles

sound
 - play sounds

more enchants 
 - try to have at least 20-30 enchants

other features?
```
