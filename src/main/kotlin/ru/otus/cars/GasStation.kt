package ru.otus.cars

class GasStation {

    fun refuel(car: Car, liters: Int, fuelType: FuelType) {
        try {
            car.refuel(liters, fuelType) /* Используем новую функцию refuel у Car */
        } catch (e: Exception) { /* Ловим исключения */
            println("Ошибка заправки: ${e.message}")
        }
    }


    fun refuelAll(cars: List<Car>) {
        cars.forEach { car ->
            when (car) {  /* Используем car, а не car::class */
                is Vaz2107 -> refuel(car, 10, FuelType.GAS)
                is Vaz2108 -> refuel(car, 10, FuelType.PETROL)
                is Taz -> refuel(car, 10, FuelType.PETROL)
                else -> println("Неизвестный тип машины")
            }
        }
    }
}
