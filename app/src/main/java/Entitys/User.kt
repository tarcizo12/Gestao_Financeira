package Entitys

data class User(
    val email: String = "",
    val number: String = "",
    val name: String = "",
    val totalMoney: Double = 0.00,
    val listRevenues: HashMap<String, Revenue> = hashMapOf(),
    val listExpenses: HashMap<String, Expense> = hashMapOf(),
)
