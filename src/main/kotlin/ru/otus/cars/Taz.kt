package ru.otus.cars

object Taz: Car {
    /**
     * Номерной знак
     */
    override val plates: Car.Plates
        get() = throw NotImplementedError("Номера сняты")

    /**
     * Цвет машины
     */
    override val color: String = "Ржавый"

    /**
     * Следит за машиной
     */
    override val carOutput: CarOutput
        get() = throw NotImplementedError("Приборов нет")

    /**
     * Получить оборудование
     */
    override fun getEquipment(): String = "Крыса"

    /**
     * Руль вправо на [degrees] градусов
     */
    override fun wheelToRight(degrees: Int) {
        throw NotImplementedError("Руля нет")
    }

    /**
     * Руль влево на [degrees] градусов
     */
    override fun wheelToLeft(degrees: Int) {
        throw NotImplementedError("Руля нет")
    }
    /* Создаем для Таза взрывающийся бак */
    private val tank = object : Tank {
        override val mouth: TankMouth = TankMouth.PetrolMouth.create(this)
        override fun FuelLevel(): Int = 0
        /* Взрывается при заправке */
        override fun FillFuel(liters: Int) {
            throw IllegalStateException("Взрыв бака!")
        }
    }
    /* Переопределяем функцию refuel */
    override fun refuel(liters: Int, fuelType: FuelType) {
        try {
            tank.FillFuel(liters) // Вызовет взрыв
        } catch (e: IllegalStateException) {
            println("Ошибка заправки Taz: ${e.message}")
        }
    }
}