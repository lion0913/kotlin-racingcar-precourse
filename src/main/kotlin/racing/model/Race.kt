package racing.model

class Race (
    private val cars: List<Car>
) {
    fun play() {
        cars.forEach { it.move()}
    }

    fun getResults(): List<Car> = cars

    fun getWinners(): List<Car> {
        val max = cars.maxOfOrNull { it.score } ?: 0
        return cars.filter { it.score == max }
    }
}