package cu.xetid.dtvc.androidtrainingapp.common

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

fun checkPermissionActive(applicationContext: Context): Boolean {
    val result = ContextCompat.checkSelfPermission(applicationContext, permissionLocation)
    val result1 = ContextCompat.checkSelfPermission(applicationContext, permissionLocation2)
    return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
}

fun checkPermissionActiveOfPhoneState(applicationContext: Context): Boolean {
    val result = ContextCompat.checkSelfPermission(applicationContext, permissionPhoneState)
    return result == PackageManager.PERMISSION_GRANTED
}

const val permissionLocation = Manifest.permission.ACCESS_FINE_LOCATION
const val permissionLocation2 = Manifest.permission.ACCESS_COARSE_LOCATION
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
const val permissionNotification = Manifest.permission.POST_NOTIFICATIONS
const val permissionPhoneState = Manifest.permission.READ_PHONE_STATE
