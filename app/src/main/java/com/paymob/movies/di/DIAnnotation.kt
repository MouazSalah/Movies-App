package com.paymob.movies.di

import javax.inject.Qualifier

object DIAnnotation {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MoviesRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MoviesInterceptor
}