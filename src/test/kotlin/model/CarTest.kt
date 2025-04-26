package model


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import racing.model.Car

class CarTest {
    @Test
    fun `Car 생성시 기본 score은 0이다`() {
        val car = Car("a")
        assertEquals(0, car.score)
    }

    @RepeatedTest(10)
    fun `move 함수 호출 시 score이 증가할수도 안할 수도 있다`() {
        val car = Car("a")
        assertEquals(0, car.score)
        car.move()
        assertThat(car.score).isBetween(0,1)
    }
}