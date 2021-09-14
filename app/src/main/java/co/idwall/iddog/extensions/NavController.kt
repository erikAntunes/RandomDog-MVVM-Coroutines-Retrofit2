package co.idwall.iddog.extensions

import androidx.navigation.NavController

fun NavController.alternateStartDestination(
    condition: Boolean,
    caseTrueFragment: Int,
    caseFalseFragment: Int,
    graphResId: Int
) {
    val inflater = navInflater
    val navGraph = inflater.inflate(graphResId)
    val destination = if (condition) caseTrueFragment else caseFalseFragment
    navGraph.startDestination = destination
    graph = navGraph
}