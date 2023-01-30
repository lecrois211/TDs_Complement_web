package edu.spring.td1.models
    data class Category(var label:String) {
        private val items = HashSet<Item>()
        fun get(itemName:String):Item?{
            return items.find { it.nom == itemName }
        }

        fun addItem(item: Item){
            items.add(item)
        }
    }