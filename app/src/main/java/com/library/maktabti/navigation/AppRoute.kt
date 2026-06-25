package com.library.maktabti.navigation

import kotlinx.serialization.Serializable


sealed class AppRoute {
    @Serializable
    object SplashRoute : AppRoute()

    @Serializable
    object HomeRoute : AppRoute()

    @Serializable
    object SearchRoute : AppRoute()

    @Serializable
    data class DetailsRoute(val productId: String) : AppRoute()

    @Serializable
    object FavoriteRoute : AppRoute()

    @Serializable
    object SectionsRoute : AppRoute()

    @Serializable
    object AboutRoute : AppRoute()

}