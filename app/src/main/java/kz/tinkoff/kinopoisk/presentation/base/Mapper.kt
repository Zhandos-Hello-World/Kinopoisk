package kz.tinkoff.kinopoisk.presentation.base

abstract class Mapper<FROM, TO> {

    abstract fun map(from: FROM): TO
}