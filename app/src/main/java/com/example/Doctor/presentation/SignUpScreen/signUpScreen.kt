package com.example.Doctor.presentation.SignUpScreen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Doctor.R
import com.example.Doctor.presentation.NavGraph.HyperlinkText
import com.example.Doctor.presentation.NavGraph.Routes
import com.example.Doctor.ui.theme.MyDoctorTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun signUpScreen(
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
            .padding(vertical = 20.dp)) {
            Text(
                text = "Sign up",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            )
            Text(
                text = "Create your account",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        )
        {
            Text(modifier = Modifier.padding(vertical = 3.dp),
                text = "Email or phone",
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
                    unfocusedContainerColor = colorResource(id = R.color.textField)
                ),
                trailingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription =null ) },

            )
            Spacer(modifier = Modifier.height(30.dp))
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
            Spacer(modifier = Modifier.height(30.dp))
            Text(modifier = Modifier.padding(vertical = 3.dp),
                text = "Repeat password",
                fontWeight = FontWeight.SemiBold
            )
            var rePassword by remember { mutableStateOf("") }
            var reShowPassword by remember { mutableStateOf(false) }
            TextField(
                value = rePassword,
                onValueChange = { rePassword = it },
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
                visualTransformation = if (reShowPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }, trailingIcon = {
                    if (reShowPassword) {
                        IconButton(onClick = { reShowPassword = false }) {
                            Icon(imageVector = Icons.Filled.Visibility,contentDescription = null)
                        }
                    } else {
                        IconButton(onClick = { reShowPassword = true }) {
                            Icon(imageVector = Icons.Filled.VisibilityOff,contentDescription = null)
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                onClick = {
                          if (email.isNotEmpty()&&password.isNotEmpty()&&rePassword.isNotEmpty()){
                              if (password == rePassword){
                                  Firebase.auth
                                      .createUserWithEmailAndPassword(email,password)
                                      .addOnCompleteListener {
                                          if (it.isSuccessful){
                                              Firebase.auth.signOut()
                                              navController.navigate(Routes.CreatedEmailSuccessfully.route)
                                          }else{
                                              Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                                          }
                                      }
                              }else{
                                  Toast.makeText(context, "Password is not matching!", Toast.LENGTH_SHORT).show()
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
                Text(text = "Sign up", fontSize = 18.sp)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Already have an account?")
                Text(
                    text = "sign in",
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.ButtonColor),
                    modifier = Modifier.clickable(enabled = true){
                        navController.navigate(Routes.signIn.route)
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

@Preview(showBackground = true)
@Composable
fun signUpScreenPreview() {
    MyDoctorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            signUpScreen()
        }
    }
}