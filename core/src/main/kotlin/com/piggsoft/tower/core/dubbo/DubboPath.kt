package com.piggsoft.tower.core.dubbo

class DubboPath {

    var paths = emptyArray<String>()
    val rootPath = "/dubbo"

    companion object {
        fun createPath(vararg _paths: String) : DubboPath {
            val dp = DubboPath()
            _paths.forEachIndexed({i, v ->
                if (i == 0) {
                    dp.paths += join(dp.rootPath, v)
                } else {
                    dp.paths += join(dp.paths[i - 1], v)
                }
            })
            return dp
        }

        private fun join(first: String?, last: String?) :String {
            return (first ?: "") + last?.padStart(last.length + 1, '/')
        }

    }

}

fun main(args: Array<String>) {
    var dp = DubboPath.createPath("1", "2")
    dp.paths.forEach(::println)
    println("dubbo path is : ${dp.paths.reduce({ x, y -> x + y })}")

}