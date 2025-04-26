package racing.controller

import racing.model.Car
import racing.model.Race
import racing.view.WebView


class RaceController {
    fun run() {
        val carNames = WebView().inputCarNames()
        val tryCount = WebView().inputTryCount()
        val cars = carNames.map { Car(it) }

        val race = Race(cars)

        repeat(tryCount) {
            race.play()
            WebView().printRoundResult(race.getResults())
        }
        val winners = race.getWinners()
        WebView().printResults(winners)
    }
}