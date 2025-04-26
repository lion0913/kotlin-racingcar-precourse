package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import racing.model.Car
import racing.model.Race

class RaceTest {
    @Test
    fun `play 호출 시 0 또는 1만큼 이동한다`() {
        val car1 = Car("car1")
        val car2 = Car("car2")
        val car3 = Car("car3")

        val race = Race(listOf(car1, car2, car3))
        val currentScore = car1.score + car2.score + car3.score
        race.play()
        val afterScore = car1.score + car2.score + car3.score

        assertThat(afterScore).isGreaterThanOrEqualTo(currentScore)
    }

    @Test
    fun `getWinner 호출 시 자동차 목록 중 하나를 반환해야 한다`() {
        val car1 = Car("car1")
        val car2 = Car("car2")
        val car3 = Car("car3")

        val race = Race(listOf(car1, car2, car3))
        race.play()
        assertThat(race.getResults()).contains(car1, car2, car3)
    }

    @Test
    fun `getWinner 호출 시 score이 가장 큰 Car를 응답한다 - 단건`() {
        val car1 = Car("car1", 1)
        val car2 = Car("car2", 2)
        val car3 = Car("car3", 3)
        val race = Race(listOf(car1, car2, car3))

        assertThat(race.getWinners().size).isEqualTo(1)
        assertThat(race.getWinners()).isEqualTo(listOf(car3))
    }

    @Test
    fun `getWinner 호출 시 score이 가장 큰 Car를 응답한다 - 복수건`() {
        val car1 = Car("car1", 1)
        val car2 = Car("car2", 5)
        val car3 = Car("car3", 5)
        val race = Race(listOf(car1, car2, car3))

        assertThat(race.getWinners().size).isEqualTo(2)
        assertThat(race.getWinners()).isEqualTo(listOf(car2, car3))
    }
}