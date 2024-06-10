package cu.xetid.dtvc.androidtrainingapp.activity

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import cu.xetid.dtvc.androidtrainingapp.common.permissionLocation
import cu.xetid.dtvc.androidtrainingapp.common.permissionLocation2
import cu.xetid.dtvc.androidtrainingapp.common.permissionNotification
import cu.xetid.dtvc.androidtrainingapp.common.permissionPhoneState
import cu.xetid.dtvc.androidtrainingapp.home.navigation.homeGraph
import cu.xetid.dtvc.androidtrainingapp.login.navigation.logInGraph
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.Navigator
import cu.xetid.dtvc.androidtrainingapp.ui.navigation.NavigatorHandler
import cu.xetid.dtvc.androidtrainingapp.ui.theme.AndroidTrainingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        val content = findViewById<View>(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                @RequiresApi(Build.VERSION_CODES.TIRAMISU)
                override fun onPreDraw(): Boolean {
                    viewModel.isReady?.let {
                        println("entre")
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        setContent {
                            AndroidTrainingAppTheme {
                                Surface(
                                    modifier = Modifier.fillMaxSize(),
                                    color = MaterialTheme.colorScheme.surface
                                ) {
                                    MainContainer(
                                        startDestination = viewModel.firstScreen,
                                        navigator = navigator
                                    )
                                }
                            }
                        }
                        return true
                    }
                    return false
                }
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun MainContainer(
    startDestination: String, navigator: Navigator
) {
    val navController = rememberNavController()

    val context = LocalContext.current
    val activity = context.findActivity()
    val permissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            permissionLocation,
            permissionLocation2,
            permissionNotification,
            permissionPhoneState
        )
    )
    val lifecycleOwner = LocalLifecycleOwner.current

    //Permission Request
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                permissionsState.launchMultiplePermissionRequest()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    //Setting application
//    val registerResponse = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.StartActivityForResult(),
//        onResult = {},
//    )

    Column {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.weight(1.0f)
        ) {
            logInGraph()

            homeGraph()
        }
    }

    //Listener of the Navigator
    NavigatorHandler(navigator = navigator, navController = navController)
}

/**
 * Navigate to App Information
 * @param response contain a registerForActivityResult function,
 * with StartActivityForResult() as ActivityResultContracts
 * */

fun Activity.toAppInformation(response: ActivityResultLauncher<Intent>) {
    response.launch(
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        ).apply {
            data = Uri.fromParts("package", packageName, null)
        })
}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("No activity")
}