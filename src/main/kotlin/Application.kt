fun main() {
    val cars = getCarNames()
    val tryCount = getTryCount()
    val scoreMap = cars.associateWith { 0 }.toMutableMap()

    runRace(cars, tryCount, scoreMap)
    printResults(scoreMap)
}

fun getCarNames(): Set<String> {
    while(true) {
        println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
        val input1 = readLine()
        try {
            if(input1.isNullOrBlank()) {
                throw IllegalArgumentException("[ERROR] 입력이 없습니다.")
            }
            val cars = input1.split(",")
                .map { it.trim()}
                .filter { it.isNotBlank() }
                .toSet()
            val invalid = cars.firstOrNull { it.length > 5 }
            if(invalid != null) {
                throw IllegalArgumentException("[ERROR] 5자리를 초과한 이름이 있습니다: $invalid")
            }
            return cars
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun getTryCount(): Int {
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

fun runRace(cars: Set<String>, tryCount: Int, scoreMap: MutableMap<String, Int>) {
    for(round in 1..tryCount) {
        for(car in cars) {
            val random = (0..9).random()
            if(random >= 4) {
                scoreMap[car] = scoreMap[car]!! + 1
            }
            println("$car: ${ "-".repeat(scoreMap[car]!!)}")
        }
        println("")
    }
}

fun printResults(scoreMap: Map<String, Int>) {
    val maxScore = scoreMap.values.maxOrNull()
    val winners = scoreMap.filterValues { it == maxScore }
        .keys
        .joinToString(", ")
    print("최종 우승자 : $winners")
}