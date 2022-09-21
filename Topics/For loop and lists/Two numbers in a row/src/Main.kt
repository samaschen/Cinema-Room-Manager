fun main() {
    val n = readln().toInt()
    val l = List(n) { readln().toInt() }
    val (p, m) = readln().split(" ")
    val lString = l.joinToString("")
    println(lString)
    if ((p + m) in lString || (p + m) in lString) {
        p
        println("NO")
    } else {
        println("YES")
    }
}
