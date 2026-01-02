package com.mohamed.kedra.listingdemo.presentation.navigation

import android.content.Context
import com.mohamed.kedra.listingdemo.R


sealed class Screens(val route: String,val titleRes: Int,val hasBack : Boolean) {

    abstract fun createTitle(context: Context) : String
    object HomeScreen : Screens("home", R.string.home_screen_title,false){
        override fun createTitle(context: Context): String {
            return context.getString(titleRes,"100")
        }
    }
    object DetailsScreen : Screens("details/{launchId}",R.string.details_screen_title,true) {
        fun createRoute(launchId: String) = "details/$launchId"

        override fun createTitle(context: Context) : String {
            return context.getString(titleRes)
        }
    }
}

fun getNavBarDetails(route: String?): Screens{
    return when(route){
        Screens.HomeScreen.route -> Screens.HomeScreen
        Screens.DetailsScreen.route -> Screens.DetailsScreen
        else -> Screens.HomeScreen
    }
}