package com.enesokurterzi.cinemaroommanager

var numberOfPurchasedTickets = 0
    var percentage = 0.00
    var formatPercentage = "%.2f".format(percentage)
    var currentIncome = 0
    var totalIncome = 0
    var rows = 0
    var seats = 0
    var grid = mutableListOf(mutableListOf<String>())

    fun main() {
        println("Enter the number of rows:")
        rows = readln().toInt()
        println("Enter the number of seats in each row:")
        seats = readln().toInt()
        grid = MutableList(rows) { MutableList(seats) { "S" } }

        val totSeat = rows * seats
        val frontSeat = rows / 2
        totalIncome = if (totSeat < 60 ) totSeat * 10 else {
            ((frontSeat * seats) * 10) + (((rows - frontSeat) * seats) * 8)
        }
        menu()
    }

    fun menu() {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        when (readln().toInt()) {
            1 -> showTheSeats()
            2 -> try {
                buyTicket()
            } catch (e: Exception) {
                println("Wrong input")
                buyTicket()
            }
            3 -> statistics()
            0 -> return
        }
    }

    fun showTheSeats() {
        println("\nCinema:")
        print(" ")
        for (i in 1..seats) print(" $i")
        println()
        for (i in grid.indices) {
            println("${i + 1} ${grid[i].joinToString(" ")}")
        }
        menu()
    }

    fun buyTicket() {
        println("\nEnter a row number:")
        val rowNum = readln().toInt()
        println("Enter a seat number in that row:")
        val seatNum = readln().toInt()

        if (grid[rowNum - 1][seatNum - 1] == "B"){
            println("That ticket has already been purchased")
            buyTicket()
        } else {
            grid[rowNum - 1][seatNum - 1] = "B"
            numberOfPurchasedTickets += 1

            val totSeat = rows * seats
            percentage = (numberOfPurchasedTickets.toDouble() / totSeat.toDouble()) * 100
            formatPercentage = "%.2f".format(percentage)

            val frontSeat = rows / 2
            if (totSeat < 60 ) {
                println("\nTicket price: $10")
                currentIncome += 10
            }
            else {
                currentIncome = if (rowNum > frontSeat){
                    println("\nTicket price: $8")
                    currentIncome + 8
                } else {
                    println("\nTicket price: $10")
                    currentIncome + 10
                }
            }
            menu()
        }
    }

    fun statistics() {
        println("Number of purchased tickets: $numberOfPurchasedTickets")
        println("Percentage: $formatPercentage%")
        println("Current income: $$currentIncome")
        println("Total income: $$totalIncome")
        menu()
    }
