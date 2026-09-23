
@Serializable
object Home

@Serializable
data class Detail(val itemId: Int)

@Composable
fun MyApp() {
val navController = rememberNavController()
NavHost(navController = navController, startDestination = Home) {
composable<Home> {
HomeScreen(onNavigateToDetail = { itemId ->
navController.navigate(Detail(itemId))
})
}
composable<Detail> { backStackEntry ->
val detail = backStackEntry.toRoute<Detail>()
DetailScreen(itemId = detail.itemId, onBack = { navController.popBackStack() })
}
}
}