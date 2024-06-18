package cu.xetid.dtvc.androidtrainingapp.activity


import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.insertion.navigation.insertGraph
import com.example.update.navigation.updateGraph
import cu.xetid.dtvc.androidtrainingapp.home.navigation.homeGraph
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigatorHandler
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.routes.home.HomeRoute
import cu.xetid.dtvc.androidtrainingapp.ui.theme.AndroidTrainingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    //private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val content = findViewById<View>(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun onPreDraw(): Boolean {
                  //  viewModel.isReady?.let {
                        println("entre")
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        setContent {
                            AndroidTrainingAppTheme {
                                Surface(
                                    modifier = Modifier.fillMaxSize(),
                                    color = MaterialTheme.colorScheme.surface
                                ) {
                                    MainContainer(
                                        navigator = navigator
                                    )
                                }
                            }
                        }
                        return true
                 //   }
                   // return false
                }
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
private fun MainContainer(
    navigator: Navigator
) {
    val navController = rememberNavController()


    Column {
        NavHost(navController = navController, startDestination = HomeRoute.RootRoute.route) {
            homeGraph()
            insertGraph()
            updateGraph()
        }

    }
    //Listener of the Navigator
    NavigatorHandler(navigator = navigator, navController = navController)
}

