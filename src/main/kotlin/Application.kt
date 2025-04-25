fun main() {
    var scoreMap: MutableMap<String, Int>
    var cars: Set<String>
    var tryCount: Int

    while(true) {
        println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
        val input1 = readLine()
        try {
            if(input1.isNullOrBlank()) {
                throw IllegalArgumentException("[ERROR] 입력이 없습니다.")
            }
            cars = input1.split(",")
                .map { it.trim() }
                .toSet()
            val invalid = cars.firstOrNull { it.length > 5 }
            if(invalid != null) {
                throw IllegalArgumentException("[ERROR] 5자리를 초과한 이름이 있습니다: $invalid")
            }
            scoreMap = cars.associateWith { 0 }.toMutableMap()
            break
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
    while(true) {
        println("시도할 회수는 몇회인가요?")
        try {
            val input2 = readLine()
            if(input2.isNullOrBlank()) {
                throw IllegalArgumentException("[ERROR] 입력이 없습니다.")
            }
            tryCount = input2.toIntOrNull()
                ?: throw IllegalArgumentException("[ERROR] 횟수는 정수로만 입력하세요.")
            break
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

    for(game in 1..tryCount) {
        for(car in cars) {
            val random = (0..9).random()
            var currentScore = scoreMap[car]!!
            if(random >= 4) {
                currentScore++
                scoreMap[car] = currentScore
            }
            println("$car: ${ "-".repeat(currentScore)}")
        }
        println("")
    }

    val maxScore = scoreMap.values.maxOrNull()
    val winners = scoreMap.filterValues { it == maxScore }
        .keys
        .joinToString(", ")
    print("최종 우승자 : $winners")
}
