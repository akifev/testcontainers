package daniil.akifev.testcontainers

import daniil.akifev.testcontainers.entity.Stock

fun Iterable<Stock>.totalCost() = sumBy { it.company.stockPrice }