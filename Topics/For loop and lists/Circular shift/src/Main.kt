fun main() {
    val n = readln().toInt()
    val l = MutableList(n) {readln().toInt()}
    val lastEle = l[l.lastIndex]
    l.removeAt(l.lastIndex)
    l.add(0, lastEle)
    println(l.joinToString(" "))
}
