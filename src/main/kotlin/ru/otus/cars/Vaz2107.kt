package ru.otus.cars

import kotlin.random.Random

/**
 * Семёрочка
 */
/* Добавляем в сборщик установку бака */
class Vaz2107 private constructor(color: String,private val tank: Tank) : VazPlatform(color) {
    /**
     * Сам-себе-сборщик ВАЗ 2107.
     */
    companion object : CarBuilder {
        private fun getRandomEngine(): VazEngine {
            return when (Random.nextInt(0, 2)) {
                0 -> VazEngine.LADA_2107(1300)
                else -> VazEngine.LADA_2107(1600)
            }
        }
        /* Добавляем в переопределяемую функцию создание бака */
        override fun build(plates: Car.Plates): Vaz2107 = Vaz2107("Зеленый", WorkingTank.create(
            TankMouth.PetrolMouth.GasMouth
        )).apply {
            this.engine = getRandomEngine()
            this.plates = plates
        }


        /**
         * Проверь, ездит или нет
         */
        fun test(vaz2107: Vaz2107) {
            println("Проверяем, едет ли ВАЗ 2107...")
            vaz2107.currentSpeed = Random.nextInt(0, 60)
        }

        /**
         * Используем вместо STATIC
         */
        const val MODEL = "2107"
    }

    // Переопределяем свойство родителя
    override lateinit var engine: VazEngine
        private set

    /**
     * Семерка едет так
     */
    fun drdrdrdrdr() {
        println("Помчали на $MODEL:")
        println("Др-др-др-др....")
    }

    private var currentSpeed: Int = 0 // Скока жмёт

    /**
     * Доступно сборщику
     * @see [build]
     */
    override lateinit var plates: Car.Plates
        private set

    // Выводим состояние машины
    override fun toString(): String {
        return "Vaz2107(plates=$plates, wheelAngle=$wheelAngle, currentSpeed=$currentSpeed)"
    }

    /**
     * Делегируем приборы внутреннему классу
     */
    override val carOutput: CarOutput = VazOutput()

    /**
     * Имеет доступ к внутренним данным ЭТОГО ВАЗ-2107!
     */
    inner class VazOutput : CarOutput {
        /* Переопределяем функцию получения уровня топлива */
        override fun getFuelV(): Int {
            return this@Vaz2107.tank.FuelLevel()
        }
        override fun getCurrentSpeed(): Int {
            return this@Vaz2107.currentSpeed
        }
    }
}