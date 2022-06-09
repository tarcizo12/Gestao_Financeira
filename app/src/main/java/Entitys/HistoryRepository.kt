package Entitys

object HistoryRepository {

    fun findAll():List<HistoryData>{
        return listOf(
            HistoryData(1, "Despesa", "burguer king", 30.0),
            HistoryData(2, "Receita", "tarcizo", 100.0),
            HistoryData(3, "Despesa", "unifor", 1450.0),
            HistoryData(4, "Receita", "salario", 5000.0),
            HistoryData(5, "Despesa", "netflix", 40.0)
        )
    }
}