package com.example.kopring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Problem1

// 과제1
class KStack<T> (){
    // private + MutableList형으로
    private val stack: MutableList<T>

    /**
     * MutableList vs List
     *
     ** MutableList
     * val mutableList = mutableListOf<Int>()
     * mutableList.add(1)
     * mutableList.removeLast()

     ** List
     * val list = listOf<Int>()
     * list.add(1) // add 불가능
     * list.removeLast() // remove 불가능

     * val listInit = listOf<Int>(1, 2, 3, 4, 5)
     */

    fun push(element: T){
        stack.add(element)
    }

    fun pop(): ArrayList<T> {
        // stack의 가장 마지막 요소를 빼서 반환
        return stack.removeLast()
    }

    // more simple
    fun size() = stack.size

    fun find(element: T): Boolean {
        return stack.contains(element)
    }
}

fun main() {

    /**
     * 과제1
     */
    var kstack: KStack<Int> = KStack() // INT형만 가능
/*
    // push
    kstack.push(1)
    kstack.push(2)
    kstack.push(3)

    // 스택 상태 => [1, 2, 3]
    println(kstack.stack)

    // pop : 결과 출력 => [1, 2]
    println(kstack.pop())

    // size : 결과 출력 => 2
    println(kstack.size())

    // find : 결과 출력 => true
    println(kstack.find(1)) */

    /**
     * 과제2
     */
    val num = readLine()!!.toInt()

    for(i in 1..num) {
        var vps: KStack<Int> = KStack()
        var case = readLine()!!.toString()

        for(i in 0 .. case.length-1) {
            if (case[i] == '(') {
                // 왼쪽 괄호이면 push
                vps.push(1)
            } else {
                // 오른쪽 괄호이면 스택이 비어있지 않으면 pop
                if (vps.size() == 0) {
                    break
                }
                vps.pop()
            }
        }

        // vps.size가 왼쪽과 오른쪽 괄호 수의 차이이므로 0일 때만 vps 인정
        if (vps.isNotEmpty()) { // isNotEmpty 활용
            println("NO")
            continue
        } else {
            println("YES")
            continue
        }

    }

}
