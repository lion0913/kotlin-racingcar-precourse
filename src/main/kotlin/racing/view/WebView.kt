package racing.view

import racing.model.Car

class WebView {
    fun printResults(winners: List<Car>) {
        println("최종 우승자 : ${winners.joinToString(", ") { it.name }}")
    }

    fun printRoundResult(cars: List<Car>) {
        cars.forEach { car ->
            println("${car.name}: ${"-".repeat(car.score)}")
        }
    }

    fun inputCarNames():  List<String> {
        while (true) {
            println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
            val input1 = readLine()
            try {
                if (input1.isNullOrBlank()) {
                    throw IllegalArgumentException("[ERROR] 입력이 없습니다.")
                }
                val cars = input1.split(",")
                    .map { it.trim() }
                    .filter { it.isNotBlank() }
                    .toSet()
                val invalid = cars.firstOrNull { it.length > 5 }
                if (invalid != null) {
                    throw IllegalArgumentException("[ERROR] 5자리를 초과한 이름이 있습니다: $invalid")
                }
                return cars.toSet().toList()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun inputTryCount(): Int {
        while(true) {
            println("시도할 회수는 몇회인가요?")
            try {
                val input = readLine()
                if(input.isNullOrBlank()) {
                    throw IllegalArgumentException("[ERROR] 입력이 없습니다.")
                }
                return input.toIntOrNull()
                    ?: throw IllegalArgumentException("[ERROR] 횟수는 정수로만 입력하세요.")
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}