package org.example.project

import App
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.example.project.platform.Permission


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }

    override fun onStart() {
        super.onStart()
        Permission.checkPermission(this)
    }

    override fun onResume() {
        super.onResume()
        if (Permission.isPermissionGranted(this)) {
            Log.i("PERMISSION", "Granted successfully.")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Permission.REQUEST_CODE) {
            for (grantResult in grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission", "Granted failed.")
                    return
                }
            }
        }
    }
}

