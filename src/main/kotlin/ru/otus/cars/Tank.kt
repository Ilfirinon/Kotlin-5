package ru.otus.cars
/* Интерфейс бака доступный для пользователя */
interface Tank {
/* Горловина */
    val mouth: TankMouth
/* Функция получения кол-ва топлива */
    fun FuelLevel(): Int
/* Функция заправки топливом */
    fun FillFuel(liters: Int)
}
/* Класс рабочего бака */
class WorkingTank private constructor(): Tank {
    /* Создаем сборщика баков */
    companion object {
        /* Функция сборки рабочего бака */
        fun create(mouth: TankMouth.Builder): Tank = WorkingTank().apply {
            this.mouth = mouth.create(this) /* На этом моменте ошибка - this.mouth: Val cannot be reassigned */
        }
    }
    /* Переменная для хранения топлива, на данный момент бак пуст */
    private var FuelV: Int = 0
    /* Переопределяем свойство mouth, делаем его приватным */
    override lateinit var mouth: TankMouth /* Зачем тут Lateinit мне пока не совсем ясно, но студио ругался и предложил его вставить */
        private set
    /* Переопределяем функцию FuelLevel, чтобы она выдавала объем топлива */
    override fun FuelLevel(): Int = FuelV
    /* Переопределяем функцию FillFuel, чтобы она прибавляла топливо к FuelV */
    override fun FillFuel(liters: Int) {
        FuelV += liters
    }
}

