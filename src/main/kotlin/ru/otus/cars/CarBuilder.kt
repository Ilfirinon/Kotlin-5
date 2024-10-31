package ru.otus.cars

/**
 * Сборщик машины
 */
sealed interface CarBuilder {
    /**
     * Собери машину
     */
    fun build(plates: Car.Plates): Car
    fun installFuelSystem(auto: Auto, tank: Tank, tankMouth: TankMouth)
}