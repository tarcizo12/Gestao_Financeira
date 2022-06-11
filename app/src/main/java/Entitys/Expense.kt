package Entitys

data class Expense(
    var indexOf: Int,
    var id: String = "",
    var name: String = "",
    var value: String = "",
    val typeValue:Boolean = false
)
