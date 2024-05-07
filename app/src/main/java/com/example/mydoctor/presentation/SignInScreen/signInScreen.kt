package com.example.mydoctor.presentation.SignInScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mydoctor.R
import com.example.mydoctor.presentation.NavGraph.HyperlinkText
import com.example.mydoctor.presentation.NavGraph.Routes
import com.example.mydoctor.presentation.onBOardingScreen.OnBoardingScreen
import com.example.mydoctor.ui.theme.MyDoctorTheme
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun signInScreen(
    modifier: Modifier = Modifier,
    navController  : NavHostController
) {
    val context = LocalContext.current
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
       Image(
           modifier = Modifier
               .size(110.dp)
               .clip(RoundedCornerShape(5.dp)),
           painter = painterResource(id = R.drawable.medicalteam),
           contentDescription = null,
           contentScale = ContentScale.Crop
       )
        Text(text = "My Doctor", fontWeight = FontWeight.Bold)
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp)) {
            Text(
                text = "Sign in",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            )
            Text(
                text = "Enter your account details",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
        )
        {
            Text(modifier = Modifier.padding(vertical = 3.dp),
                text = "Username or Email",
                fontWeight = FontWeight.SemiBold
            )
            var email by remember { mutableStateOf("") }
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(25.dp)),

                colors = TextFieldDefaults.colors(
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = colorResource(id = R.color.focusedtextField),
                    unfocusedContainerColor = colorResource(id = R.color.textField),
                ),
                trailingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription =null )}
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(modifier = Modifier.padding(vertical = 3.dp),
                text = "Password",
                fontWeight = FontWeight.SemiBold
            )
            var password by remember { mutableStateOf("") }
            var showPassword by remember { mutableStateOf(false) }
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(25.dp)),

                colors = TextFieldDefaults.colors(
                    errorIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = colorResource(id = R.color.focusedtextField),
                    unfocusedContainerColor = colorResource(id = R.color.textField)
                ),
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }, trailingIcon = {
                    if (showPassword) {
                        IconButton(onClick = { showPassword = false }) {
                            Icon(imageVector = Icons.Filled.Visibility,contentDescription = null)
                        }
                    } else {
                        IconButton(onClick = { showPassword = true }) {
                            Icon(imageVector = Icons.Filled.VisibilityOff,contentDescription = null)
                        }
                    }
                }
            )
            Text(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(vertical = 3.dp)
                    .clickable(enabled = true) {
                          navController.navigate(Routes.ForgotPassword.route)
                    },
                text = "Forgot password?",
                textAlign = TextAlign.End,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                    if (email.isNotEmpty()&&password.isNotEmpty()){
                            FirebaseAuth.getInstance()
                                .signInWithEmailAndPassword(email,password)
                                .addOnCompleteListener {
                                    if (it.isSuccessful){
                                        navController.navigate(Routes.App_Home.route)
                                    }else{
                                        Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                                    }
                                }
                    }else{
                        Toast.makeText(context, "Empty fields are not allowed!", Toast.LENGTH_SHORT).show()
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(25.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.ButtonColor),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Sign in", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(25.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.ButtonColor),
                    contentColor = Color.White
                )
            ) {
                Row(modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = "Sign in with  ", fontSize = 18.sp)
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = null
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Have no account?")
                Text(
                    text = "sign up",
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.ButtonColor),
                    modifier = Modifier.clickable(enabled = true){
                        navController.navigate(Routes.SignUp.route)
                    }
                )
            }
            HyperlinkText(
                modifier = Modifier.fillMaxWidth(),
                fullText = "Need help?",
                hyperLinks = mutableMapOf(
                    "Need help?" to "https://dribbble.com/shots/16482360-Sehat-kan-Mobile-Apps/attachments/11304571?mode=media"
                ),
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                ),
                linkTextFontWeight = FontWeight.SemiBold,
                linkTextColor = Color.Unspecified,
                linkTextDecoration = TextDecoration.Underline
            )

        }
    }
}

@Preview
@Composable
fun signInScreenPreview(){
    MyDoctorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            signInScreen()
        }
    }
}