package ru.otus.cars

/* Создаем класс горловины в котором все типы будут известны */
sealed class TankMouth {
    /* Создаем сборщика */
    interface Builder {
        fun create(tank: Tank): TankMouth
    }

    /* Добавляем горловину к баку */
    protected lateinit var tank: Tank

    /* Добавляем переменную определяющую открыт бак или закрыт */
    private var opened: Boolean = true

    /* Добавляем функции открытия и закрытия горловины */
    fun open() {
        opened = true
    }

    fun close() {
        opened = false
    }

    /* Добавляем внутреннюю функцию заливки топлива в горловину */
    protected fun putFuel(liters: Int) {
        /* Если открыта, то в бак заливается топливо */
        if (opened) {
            tank.FillFuel(liters)
        }
    }

    /* Создаем класс горловины для бензина */
    class PetrolMouth private constructor() : TankMouth() {
        companion object : Builder {
            override fun create(tank: Tank): TankMouth {
                /* Создаем экземпляр класса */
                return PetrolMouth().apply { this.tank = tank }
            }
            /* Функция заправки бензином */
        }

        fun fuelPetrol(liters: Int) {
            putFuel(liters)
        }

        /* По анналогии создаем для газа */
        class GasMouth private constructor() : TankMouth() {
            companion object : Builder {
                override fun create(tank: Tank): TankMouth {
                    return GasMouth().apply { this.tank = tank }
                }
            }

            fun fuelGas(liters: Int) {
                putFuel(liters)
            }
        }
    }
}