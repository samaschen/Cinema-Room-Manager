fun main() {
    val n = readln().toInt()
    val earn = mutableListOf<Double>()
    val perc = mutableListOf<Double>()
    var tax = 0.0
    var taxIndex = 0
    repeat(n) {
        earn.add(readln().toDouble())
    }
    repeat(n) {
        perc.add(readln().toDouble())
    }
    for (i in 0 until n) {
        var curTax = earn[i] * perc[i]
        if (curTax > tax) {
            tax = curTax
            taxIndex = i
        }
    }
    print(taxIndex + 1)
}
