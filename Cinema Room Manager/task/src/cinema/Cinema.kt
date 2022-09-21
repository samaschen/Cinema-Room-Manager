const val BIG_ROOM = 60
const val FEE_HIGHER = 10
const val FEE_LOWER = 8
fun main() {
    Cinema().process()
}   

class Cinema {
    fun process() {
        val (rowNum, seatNum, cinema) = cinemaSize()
        var op = cinemaOptions()
        while (op != 0) {
            when (op) {
                1 -> cinemaShowSeats(rowNum, cinema)
                2 -> cinemaBuyTicket(rowNum, seatNum, cinema)
                3 -> cinemaStats(rowNum, seatNum, cinema)
                else -> println("Wrong option!")
            }
            op = cinemaOptions()
        }
    }

    fun cinemaSize(): Triple<Int, Int, MutableList<MutableList<String>>> {
        println("Enter the number of rows:")
        val rowNum = readln().toInt()
        println("Enter the number of seats in each row:")
        val seatNum = readln().toInt()
        val cinema = mutableListOf(MutableList(seatNum + 1) { "S" })
        repeat(rowNum) {
            cinema.add(MutableList(seatNum + 1) { "S" })
        }
        cinema[0][0] = " "
        for (i in 0..rowNum) {
            for (j in 0..seatNum) {
                if (i != 0 || j != 0) {
                    if (i == 0) {
                    cinema[0][j] = j.toString()
                    }
                    if (j == 0) {
                        cinema[i][0] = i.toString()
                    }   
                }
            }
        }
        return Triple(rowNum, seatNum, cinema)
    }

    private fun cinemaOptions(): Int {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        val op = readln().toInt()
        return op
    }
        
    private fun cinemaShowSeats(rowNum: Int, cinema: MutableList<MutableList<String>>) {
        println("Cinema:")
        for (i in 0..rowNum) {
            println(cinema[i].joinToString(" "))
        }
    }
    
    private fun cinemaBuyTicket(rowNum: Int, seatNum: Int, cinema: MutableList<MutableList<String>>) {
        println("Enter a row number:")
        val theRow = readln().toInt()
        println("Enter a seat number in that row:")
        val theSeat = readln().toInt()

        try {
            if (cinema[theRow][theSeat] == "B") {
                println("\nThat ticket has already been purchased!\n")
                cinemaBuyTicket(rowNum, seatNum, cinema)
            }
            println("Ticket price: $" + if (rowNum * seatNum > BIG_ROOM && theRow > rowNum / 2) FEE_LOWER else FEE_HIGHER)
            cinema[theRow][theSeat] = "B"            
        } catch (e: Exception) {
            println("Wrong input!")
            cinemaBuyTicket(rowNum, seatNum, cinema)
        }

    }

    private fun cinemaStats(rowNum: Int, seatNum: Int, cinema: MutableList<MutableList<String>>) {
        val totalSeats = rowNum * seatNum
        var purchasedTicket = 0
        var currentIncome = 0

        for (i in 1..rowNum) {
            purchasedTicket += cinema[i].count { it == "B" }
            if (totalSeats > BIG_ROOM && i > rowNum / 2) {
                currentIncome += FEE_LOWER * cinema[i].count { it == "B" }
            } else {
                currentIncome += FEE_HIGHER * cinema[i].count { it == "B" }
            }
        }
        println("Number of purchased tickets: $purchasedTicket")
        println("Percentage: ${String.format("%.2f", purchasedTicket.toDouble() * 100 / totalSeats.toDouble())}%")
        println("Current income: \$$currentIncome")
        println("Total income: \$${cinemaMaxIncome(rowNum, seatNum)}")
    }

    private fun cinemaMaxIncome(rowNum: Int, seatNum: Int): Int {
        if (rowNum * seatNum <= 60) {
            return rowNum * seatNum * 10
        } else {
            if (rowNum % 2 == 0){
                return rowNum * seatNum * (10 + 8) / 2
            } else {
                return (rowNum - 1)* seatNum * (10 + 8) / 2 + seatNum * 8
            }
        }
    }
}