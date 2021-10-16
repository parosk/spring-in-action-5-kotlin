package hk.com.martiansapp.tacocloudkotlin.data

import hk.com.martiansapp.tacocloudkotlin.Taco

interface TacoRepository {
    fun save(design: Taco): Taco
}