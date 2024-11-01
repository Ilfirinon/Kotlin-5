package ru.otus.cars

fun main() {
    println("\n===> drive cars...")
    driveCars()
    println("\n===> inner test...")
    innerNestedCheck()
    println("\n===> garage make...")
    garageMake()
    println("\n===> model special...")
    println("\n===> get equipment...")
    getEquipment()
    println("\n===> get color...")
    getColor()
    println("\n===> tech checks...")
    techChecks()
    println("\n===> Taz...")
    println(Taz.color)
    /* Добавляем вывод уровня топлива */
    println("\n===> get fuel...")
    getFuel()
}

fun driveCars() {
    val vaz1 = Togliatti.buildCar(Vaz2107, Car.Plates("123", 77))
    val vaz2 = Togliatti.buildCar(Vaz2108, Car.Plates("321", 78))

    println("Экземпляры класса имеют разное внутреннее состояние:")
    vaz1.wheelToRight(10)
    println(vaz1.toString()) // Выводит 10 и случайную скорость
    vaz2.wheelToLeft(20)
    println(vaz2.toString()) // Выводит -20 и случайную скорость
}

fun innerNestedCheck() {
    val vaz = Vaz2107.build(Car.Plates("123", 77))
    val output = vaz.VazOutput() // Создаем новый объект ИЗ ЭКЗЕМПЛЯРА МАШИНЫ

    println("Скорость до проверки: ${output.getCurrentSpeed()}") // Выводит 0
    Vaz2107.test(vaz) // Газуем...
    println("Скорость после проверки: ${output.getCurrentSpeed()}") // Выводит случайную скорость
}

fun garageMake() {
    val maker = "Дядя Вася"
    val garage = object : CarFactory {
        override fun buildCar(builder: CarBuilder, plates: Car.Plates): Car {
            println("Запил Жигулей у: $maker...")
            println("Машину не проверяем... и в продакшн...")
            return builder.build(plates)
        }
    }

    val vaz = garage.buildCar(Vaz2107, Car.Plates("500", 50))
    println(vaz.toString())
}

fun getEquipment() {
    val cars = listOf(
        Vaz2107.build(Car.Plates("123", 77)),
        Vaz2108.build(Car.Plates("321", 78))
    )

    cars.forEach { car ->
        println("Оборудование: ${car.getEquipment()}")
    }
}

fun getColor() {
    val cars = listOf(
        Vaz2107.build(Car.Plates("123", 77)),
        Vaz2108.build(Car.Plates("321", 78))
    )

    cars.forEach { car ->
        println("Цвет: ${car.color}")
    }
}

fun techChecks() {
    val vaz1 = Vaz2107.build(Car.Plates("123", 77))
    val vaz2 = Vaz2108.build(Car.Plates("321", 78))

    repairEngine(vaz1)
    repairEngine(vaz2)
}

fun repairEngine(car: VazPlatform) {
    // Проверяем тип двигателя
    // В зависимости от типа двигателя выполняем разные действия
    // when обеспечивает обход всех вариантов перечисления
    when (car.engine) {
        is VazEngine.LADA_2107 -> println("Чистка карбюратора у двигателя объемом ${car.engine.volume} куб.см у машины $car")
        is VazEngine.SAMARA_2108 -> println("Угол зажигания у двигателя объемом ${car.engine.volume} куб.см у машины $car")
    }
}
/* Добавляем фукцию вывода информации о баках */
fun getFuel() {
    val vaz1 = Vaz2107.build(Car.Plates("123", 77))
    val vaz2 = Vaz2108.build(Car.Plates("321", 78))
    println("Бензобаки:")
    println("2107: ${vaz1.carOutput.getFuelV()}")
    println("2108: ${vaz2.carOutput.getFuelV()}")
    /* Создаем коллекцию и выводим информацию о топливе до и после заправки */
    val cars = listOf(vaz1, vaz2, Taz)

    println("Бензобаки до заправки:")
    cars.forEach {
        try {
            println("${it::class.simpleName}: ${it.carOutput.getFuelV()}")
        } catch (e: NotImplementedError) {
            println("${it::class.simpleName}: Ошибка заправки")
        }
    }

    val gasStation = GasStation()
    /*  фильтруем список, чтобы заправлять только те машины, у которых есть carOutput */
    val refuelableCars = cars.filter {
        try {
            it.carOutput.getFuelV()
            true /* Если getFuelV() не выбросил исключение, то машина заправляемая */
        } catch (e: NotImplementedError) {
            false /* Если выбросил исключение, то не заправляемая */
        }
    }
    gasStation.refuelAll(refuelableCars)

    println("Бензобаки после заправки:")
    cars.forEach {
        try {
            println("${it::class.simpleName}: ${it.carOutput.getFuelV()}")
        } catch (e: NotImplementedError) {
            println("${it::class.simpleName}: Уровень топлива недоступен: Машина взорвалась!")
        }
    }
}




