package com.islam97.android.apps.onx.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.islam97.android.apps.onx.R
import com.islam97.android.apps.onx.presentation.ui.composeables.CustomButton
import com.islam97.android.apps.onx.presentation.ui.composeables.CustomTextField
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme
import kotlinx.serialization.Serializable

@Serializable
object RouteLoginScreen

@Composable
fun LoginScreen(navController: NavHostController) {

    var userIdState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .offset(80.dp, (-80).dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.appColorScheme.error)
                    .align(Alignment.TopEnd)

            )
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                val (logoReference, languageButtonReference, deliveryVanReference, loginTitleReference, loginMessageReference, userIdReference, passwordReference, showMoreReference, loginButtonReference) = createRefs()
                Icon(
                    modifier = Modifier
                        .constrainAs(logoReference) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.percent(0.5f)
                            horizontalBias = 0f
                        }
                        .padding(top = 16.dp, start = 16.dp),
                    painter = painterResource(id = R.drawable.ic_onx_logo),
                    contentDescription = "",
                    tint = Color.Unspecified)
                IconButton(
                    modifier = Modifier
                        .constrainAs(languageButtonReference) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 16.dp, end = 16.dp), onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_language),
                        contentDescription = "",
                        tint = MaterialTheme.appColorScheme.white
                    )
                }
                Icon(
                    modifier = Modifier.constrainAs(deliveryVanReference) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        verticalBias = 0.95f
                    },
                    painter = painterResource(id = R.drawable.ic_delivery_van),
                    contentDescription = "",
                    tint = Color.Unspecified
                )

                Text(
                    modifier = Modifier
                        .constrainAs(loginTitleReference) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            verticalBias = 0.3f
                        }
                        .padding(top = 16.dp),
                    text = stringResource(R.string.title_login),
                    style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.appColorScheme.primary))

                Text(
                    modifier = Modifier
                        .constrainAs(loginMessageReference) {
                            top.linkTo(loginTitleReference.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 16.dp),
                    text = stringResource(R.string.message_login),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.appColorScheme.primary))

                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .constrainAs(userIdReference) {
                            top.linkTo(loginMessageReference.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 16.dp),
                    value = userIdState,
                    onValueChange = { userIdState = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Next
                    ),
                    placeholder = stringResource(R.string.placeholder_user_id),
                )

                CustomTextField(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .constrainAs(passwordReference) {
                            top.linkTo(userIdReference.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 16.dp),
                    value = passwordState,
                    onValueChange = { passwordState = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    placeholder = stringResource(R.string.placeholder_password),
                )

                Text(
                    modifier = Modifier
                        .constrainAs(showMoreReference) {
                            top.linkTo(passwordReference.bottom)
                            end.linkTo(passwordReference.end)
                        }
                        .padding(top = 16.dp),
                    text = stringResource(R.string.show_more),
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.appColorScheme.primary))

                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .constrainAs(loginButtonReference) {
                            top.linkTo(showMoreReference.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 16.dp), onClick = onClick@{

                    }) {
                    Text(
                        text = stringResource(R.string.login),
                        style = TextStyle(color = MaterialTheme.appColorScheme.white)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = NavHostController(LocalContext.current))
}