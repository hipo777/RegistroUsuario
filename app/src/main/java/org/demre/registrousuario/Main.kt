package org.demre.registrousuario

data class Usuario(
    val nombre: String,
    val apellido: String,
    val edad: Int,
    val correo: String,
    val sistemaSalud: String
)

fun main() {
    val usuarios = validarCantidad()

    println("\nUsuarios registrados y ordenados:")
    for (usuario in usuarios) {
        println("Nombre: ${usuario.nombre} ${usuario.apellido}")
        println("Edad: ${usuario.edad}")
        println("Correo: ${usuario.correo}")
        println("Sistema de salud: ${usuario.sistemaSalud}")
        println("")
    }
}

fun validarCantidad(): List<Usuario> {
    val usuarios = mutableListOf<Usuario>()
    var cantidad: Int? = null

    do {
        print("Ingrese la cantidad de usuarios a registrar: ")
        val input = readLine()

        if (input.isNullOrEmpty()) {
            println("Debe ingresar un número.")
            continue
        }
        try {
            cantidad = input.toInt()
            if (cantidad <= 0) {
                println("Debe ingresar un número mayor a cero.")
            }
        } catch (e: NumberFormatException) {
            println("Debe ingresar un número válido.")
        }

    } while (cantidad == null || cantidad <= 0)
    for (i in 1..cantidad) {
        println("Usuario $i:")

        val nombre = validarNombre()
        val apellido = validarApellido()
        val edad = validarEdad()
        val correo = validarCorreo()
        val sistemaSalud = validarSistemaSalud()
        val usuario = Usuario(nombre, apellido, edad, correo, sistemaSalud)
        usuarios.add(usuario)
        println("")
    }
    return usuarios.sortedBy { it.edad }
}

fun validarNombre(): String {
    println("Ingrese el nombre (debe tener entre 1 y 20 caracteres):")
    var nombre = readLine()?.trim() ?: ""
    while (nombre.length !in 1..20) {
        println("Nombre inválido. Ingrese nuevamente:")
        nombre = readLine()?.trim() ?: ""
    }
    return nombre
}

fun validarApellido(): String {
    println("Ingrese el apellido (solo letras):")
    var apellido = readLine()?.trim() ?: ""
    while (!apellido.matches(Regex("[a-zA-Z]+"))) {
        println("Apellido inválido. Ingrese nuevamente:")
        apellido = readLine()?.trim() ?: ""
    }
    return apellido
}

fun validarEdad(): Int {
    println("Ingrese la edad:")
    var edad = readLine()?.toIntOrNull()
    while (edad == null || edad <= 0) {
        println("Edad inválida. Ingrese nuevamente:")
        edad = readLine()?.toIntOrNull()
    }
    return edad
}

fun validarCorreo(): String {
    println("Ingrese el correo (debe tener formato válido):")
    var correo = readLine()?.trim() ?: ""
    while (!correo.matches(Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"))) {
        println("Correo inválido. Ingrese nuevamente:")
        correo = readLine()?.trim() ?: ""
    }
    return correo
}

fun validarSistemaSalud(): String {
    println("Ingrese el sistema de salud (Fonasa/Isapre/Particular):")
    var sistemaSalud = readLine()?.trim()?.lowercase() ?: ""
    while (sistemaSalud !in listOf("fonasa", "isapre", "particular")) {
        println("Sistema de salud inválido. Ingrese nuevamente:")
        sistemaSalud = readLine()?.trim()?.lowercase() ?: ""
    }
    return sistemaSalud
}
