package com.jmanrique.loldataproject.data.network.model.championDetail

data class Chroma(
    val chromaPath: String,
    val colors: List<String>,
    val descriptions: List<Description>,
    val id: Int,
    val name: String,
    val rarities: List<Rarity>
)