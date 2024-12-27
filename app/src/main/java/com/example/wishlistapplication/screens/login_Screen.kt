package com.example.wishlistapplication.screens

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapplication.R
import com.example.wishlistapplication.routes.Routes
import com.example.wishlistapplication.viewmodel.AuthState
import com.example.wishlistapplication.viewmodel.WishViewModel
import com.example.wishlistapplication.viewmodel.firebase_auth_viewmodel
import kotlinx.coroutines.delay


@Composable
fun LoginScreen(modifier: Modifier,
                navController: NavController,
                authViewModel : firebase_auth_viewmodel
){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val authState = authViewModel.authState.observeAsState()

    var backPressedOnce by remember{ mutableStateOf(false) }

    LaunchedEffect(authState.value) {
        when(authState.value) {
            is AuthState.Authenticated -> {
                navController.navigate(Routes.homeScreen)
            } is AuthState.Error -> {
            Toast.makeText(context, (authState.value as AuthState.Error).message, Toast.LENGTH_SHORT).show()
        } else -> {
            Unit
        }
        }
    }

    BackHandler {
        if (backPressedOnce) {
            // Close the app
            (context as? android.app.Activity)?.finish()
        } else {
            backPressedOnce = true
            // Show a toast message
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
    }

    // Reset backPressedOnce after 2 seconds
    if (backPressedOnce) {
        LaunchedEffect(backPressedOnce) {
            delay(2000)
            backPressedOnce = false
        }
    }

    Column(
        modifier = modifier.then(Modifier.fillMaxSize()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Log In Heading
        Text("Log In", fontSize = 40.sp)

        Spacer(modifier = Modifier.height(32.dp))

        //Email Text Field
        TextField(value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        //Password Text Field
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val passwordVisibilityIcon = if (passwordVisible) {
                    Icons.Default.Visibility // Icon for visible password
                } else {
                    Icons.Default.VisibilityOff // Icon for hidden password
                }
                val passwordVisibilityDescription = if (passwordVisible) {
                    "Hide password"
                } else {
                    "Show password"
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = passwordVisibilityIcon,
                        contentDescription = passwordVisibilityDescription
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {

            authViewModel.login(email,password)

            Log.d("LogIn","Successfully Login !")
        },
            enabled = authState.value != AuthState.Loading) {
            Text("Log in")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Don't have an Account ?")
            TextButton(onClick = {
                navController.navigate(Routes.signUpScreen)
            }) {
                Text("Sign Up")
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically){
            Image(painter = painterResource(R.drawable.google),"Google Login",
                modifier = Modifier.height(24.dp).width(24.dp).clickable {
                    Toast.makeText(context,"Google Log In", Toast.LENGTH_SHORT).show()
                })

            VerticalDivider(modifier = Modifier.height(48.dp).padding(8.dp))

            Image(painter = painterResource(R.drawable.facebook),"Facebook Login",
                modifier = Modifier.height(24.dp).width(24.dp).clickable {
                    Toast.makeText(context,"Facebook Log In", Toast.LENGTH_SHORT).show()
                })

        }

    }

}