package com.jmanrique.loldataproject.data.network.model.championDetail

data class Spell(
    val abilityIconPath: String,
    val abilityVideoImagePath: String,
    val abilityVideoPath: String,
    val ammo: Ammo,
    val coefficients: Coefficients,
    val cooldown: String,
    val cooldownCoefficients: List<Double>,
    val cost: String,
    val costCoefficients: List<Double>,
    val description: String,
    val dynamicDescription: String,
    val effectAmounts: EffectAmounts,
    val maxLevel: Int,
    val name: String,
    val range: List<Double>,
    val spellKey: String
)