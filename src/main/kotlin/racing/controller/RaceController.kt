package racing.controller

import racing.model.Car
import racing.model.Race
import racing.view.WebView


class RaceController {
    fun run() {
        val carNames = validateCarNames()
        val tryCount = validateTryCount()
        val cars = carNames.map { Car(it) }

        val race = Race(cars)

        repeat(tryCount) {
            race.play()
            WebView().printRoundResult(race.getResults())
        }
        val winners = race.getWinners()
        WebView().printResults(winners)
    }

    fun validateCarNames(): List<String> {
        while(true) {
            try {
                val cars = WebView().inputCarNames()
                val invalid = cars.firstOrNull { it.length > 5 }
                if (invalid != null) {
                    throw IllegalArgumentException("[ERROR] 5자리를 초과한 이름이 있습니다: $invalid")
                }
                return cars.toSet().toList()
            } catch(e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun validateTryCount(): Int {
        while(true) {
            try {
                return WebView().inputTryCount()
            } catch(e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}