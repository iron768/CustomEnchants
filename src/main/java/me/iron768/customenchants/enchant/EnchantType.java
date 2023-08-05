package me.iron768.customenchants.enchant;

public enum EnchantType {

    NOTHING,
    DURABILITY_DAMAGE,
    MOB_DAMAGE,
    MOB_KILL,
    PLAYER_KILL,
    MOB_AND_PLAYER,
    HOLD_ITEM,
    BLOCK_BREAK,
    BOW_HIT,
    BOW_SHOOT,
    BOW_HIT_BLOCK,
    ARMOR_EQUIP,
    PASSIVE_DAMAGE,
    RECIEVE_DAMAGE,
    RECIEVE_DAMAGE_NON_STACK,
    SWORD_HIT,
    AXE_HIT,
    ALL_HIT,
    RAGE, //rage has it's own due to how it works with combos
    PASSIVE,
    BLOOD_LUST, //used with PlayerBleedEvent
    BLOOD_LINK,
    FRIENDLY_DEATH,

}
