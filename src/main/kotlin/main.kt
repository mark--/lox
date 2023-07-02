import java.io.File
import java.util.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size > 1) {
        println("""Usage klox [script]""")
        exitProcess(64)
    } else if (args.size == 1) {
        runFile(args[0])
    } else {
        runPrompt()
    }

    if (hadError) exitProcess(65)
}

fun runFile(path: String) {
    run(File(path).readText())
}

fun runPrompt() {
    while (true) {
        print("> ")
        val line = readln()
        if (line.isEmpty()) break
        run(line)
        hadError = false
    }
}

fun run(source: String) {

    val scanner = Scanner(source)
    val tokens = scanner.tokens()

    tokens.forEach { println(it) }
}

fun error(line: Int, message: String) {
    report(line, "", message)
}

var hadError = false

fun report(line: Int, where: String, message: String) {
    System.err.println("[line $line] Error$where: $message")
    hadError = true
}
