package agh.ics.oop

fun main(args: Array<String>) {
    println("system wystartował!")
    run(arrayOf("f", "b", "l", "r"))
    println("system zakończył działanie")
}

fun run(str: Array<String>) {
    println("zwierzak idzie do przodu")
    for (i in 0 until str.size-1) {
        print(str[i])
        print(", ")
    }
    println(str[str.size-1])
}