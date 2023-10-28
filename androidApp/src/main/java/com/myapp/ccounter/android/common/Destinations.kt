package com.myapp.ccounter.android.common

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
    val routeWithArgs: String
}

object Search: Destination {
    override val route: String
        get() = "search"

    override val routeWithArgs: String
        get() = route
}

object Detail: Destination {
    override val route: String
        get() = "detail"

    override val routeWithArgs: String
        get() = "$route/{productId}"

    val arguments = listOf(
        navArgument(name = "productId") { type = NavType.IntType }
    )
}

object Saved: Destination {
    override val route: String
        get() = "saved"

    override val routeWithArgs: String
        get() = route
}

object Main: Destination {
    override val route: String
        get() = "main"
    override val routeWithArgs: String
        get() = route

}
