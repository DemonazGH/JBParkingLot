package parking

fun main() {
    var initx = 0
    var veharray = Array(0) {Car(0,"","")}
    val carcolnum = arrayOfNulls<String>(0)
    do {
        val x = readLine()!!
        var color = x.substringAfterLast(' ', "")
        var order = x.substringBefore(' ', "")
        val datacar: String = x.substringAfter(' ',"")
        val nr: String = datacar.substringBefore(' ', "")
        when {
            order == "create"  -> {
                veharray = Array(color.toInt()) {Car(0,"","")}
                println("Created a parking lot with ${color.toInt()} spots.")
            }
            order == "park" -> {
                if (veharray.count() > 0) {
                    (parklot(veharray, color, nr))
                } else errmsg()
            }
            order == "leave" -> {
                if (veharray.count() > 0) {
                    (unpark(veharray, color.toInt()))
                } else errmsg()
            }
            x == "status" -> {
                if (veharray.count() > 0) {
                    (stat(veharray))
                } else { errmsg() }
            }
            order == "reg_by_color" -> {
                if (veharray.count() >0) {
                    var over = veharray.filter { it.color.toLowerCase() == color.toLowerCase()}
                    var fix = over.map {Reg (num = it.num) }
                    if (fix.isEmpty()) {
                        println ("No cars with color ${color.toUpperCase()} were found.")
                    } else {
                        println(fix.joinToString(", "))
                    }
                } else { errmsg() }
            }
            order == "spot_by_color" -> {
                if (veharray.count() > 0) {
                    var over = veharray.filter { it.color.toLowerCase() == color.toLowerCase() }
                    var fix = over.map { Carspot(spot = it.spot) }
                    if (fix.isEmpty()) {
                        println("No cars with color ${color.toUpperCase()} were found.")
                    } else {
                        println(fix.joinToString(", "))
                    }
                } else {
                    errmsg()
                }
            }
            order == "spot_by_reg" -> {
                if (veharray.count() > 0) {
                    var over = veharray.filter { it.num == datacar }
                    var fix = over.map { Carspot(spot = it.spot) }
                    if (fix.isEmpty()) {
                        println("No cars with registration number $datacar were found.")
                    } else {
                        println(fix.joinToString(", "))
                    }
                } else { errmsg() }
            }
            x == "exit" -> {
                return
            }
        }
    } while (true)
}

fun parklot(array: Array<Car>, col: String, nr: String):Unit { //Attempt to park
    var freespot: Int = 0
    for (i in 0..array.lastIndex) {
        if (array[i].spot == 0) {
            array[i].spot = i + 1
            array[i].num = nr
            array[i].color = col
            println(message = "${array[i].color} car parked on the spot ${array[i].spot}.")
            freespot = i + 1
            break
        }
    }
    if (freespot == 0) {
        println("Sorry, parking lot is full.")
    }
}

fun unpark(array: Array<Car>, plac:Int):Unit { // Leave function
    if ((array[plac - 1].spot != 0)) {
        array[plac - 1] = Car (0, "", "")
        println("Spot $plac is free.")
    } else {
        println("There is no car in the spot $plac.")
    }
}

fun stat(array: Array<Car>):Unit { //Define status of parklot
    var temp = 0
    for (i in 0..array.lastIndex) {
        if (array[i].spot != 0) {
            println(message = "${i+1} ${array[i].num} ${array[i].color}")
            temp +=1
        }
    }
    if (temp == 0) emptymsg()
}

fun errmsg(): Unit {
    println("Sorry, parking lot is not created.")
}

fun emptymsg(): Unit {
    println("Parking lot is empty.")
}


data class Car(var spot: Int, var num: String, var color: String) {
    override fun toString(): String {
        return super.toString()

    }
}
data class Reg(var num: String) {
    override fun toString(): String {
        return num
    }
}
data class Carspot(var spot: Int) {
    override fun toString(): String {
        return "$spot"
    }
}
