package com.example.apiproject

data class Card(val name : String, val layout : String, val cmc : Double, val colors : Array<String>, val image_uris : Image, val prices : Amount, val oracle_text : String, val mana_cost : String)
