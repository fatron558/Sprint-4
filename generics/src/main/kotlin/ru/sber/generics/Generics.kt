package ru.sber.generics

fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

fun <E : Comparable<E>> countGreaterThan(anArray: Array<E>, elem: E): Int {
    return anArray.filter { it > elem }.size
}

class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

class Stack<T> {
    var stack = ArrayDeque<T>()

    fun isEmpty(): Boolean = stack.isEmpty()

    fun push(value: T) = stack.add(value)

    fun pop(): T? = if (stack.isNotEmpty()) stack.removeLast() else null
}