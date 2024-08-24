package com.paymob.movies.core

import javax.inject.Qualifier

object DIAnnotation {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MoviesRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MoviesInterceptor
}