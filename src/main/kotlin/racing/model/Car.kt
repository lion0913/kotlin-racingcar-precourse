package racing.model

data class Car (
    val name: String,
    var score: Int = 0
) {
    fun move() {
        if((1..9).random() >= 4) {
            this.score++
        }
    }
}