package com.islam97.android.apps.onx.presentation.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.islam97.android.apps.onx.R
import com.islam97.android.apps.onx.presentation.ui.composeables.CustomSingleChoiceSegmentedRow
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme
import kotlinx.serialization.Serializable

@Serializable
object RouteOrdersScreen

@Composable
fun OrdersScreen(navController: NavHostController) {

    val viewModel: OrdersViewModel = hiltViewModel()
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            val (topBannerReference, topCornerShapeReference, languageButtonReference, userNameReference, deliveryManReference, loadingIndicatorReference, filterReference, ordersListReference, emptyViewReference) = createRefs()
            Box(
                modifier = Modifier
                    .constrainAs(
                        topBannerReference
                    ) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .height(144.dp)
                    .background(MaterialTheme.appColorScheme.error))
            Box(
                modifier = Modifier
                    .constrainAs(
                        topCornerShapeReference
                    ) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .size(240.dp)
                    .offset(96.dp, (-96).dp)
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.appColorScheme.primary))

            IconButton(
                modifier = Modifier
                    .constrainAs(languageButtonReference) {
                        top.linkTo(topBannerReference.top)
                        end.linkTo(topBannerReference.end)
                        bottom.linkTo(topBannerReference.bottom)
                    }

                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.appColorScheme.white)
                    .size(32.dp), onClick = {

            }) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.ic_language),
                    contentDescription = "",
                    tint = MaterialTheme.appColorScheme.primary
                )
            }
            Text(
                modifier = Modifier
                    .constrainAs(userNameReference) {
                        top.linkTo(topBannerReference.top)
                        start.linkTo(topBannerReference.start)
                        bottom.linkTo(topBannerReference.bottom)
                        width = Dimension.percent(0.5f)
                        verticalBias = 0.6f
                    }
                    .padding(start = 16.dp),
                text = "",
                style = MaterialTheme.typography.headlineLarge.copy(color = MaterialTheme.appColorScheme.white))
            Icon(
                modifier = Modifier.constrainAs(deliveryManReference) {
                    start.linkTo(topBannerReference.start)
                    end.linkTo(topBannerReference.end)
                    bottom.linkTo(topBannerReference.bottom)
                    horizontalBias = 0.75f
                },
                painter = painterResource(id = R.mipmap.ic_delivery_man),
                contentDescription = "",
                tint = Color.Unspecified
            )

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .constrainAs(
                            loadingIndicatorReference
                        ) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .size(60.dp))
            } else {
                val filters = listOf("", "")
                var selectedFilterIndex by remember { mutableIntStateOf(0) }
                CustomSingleChoiceSegmentedRow(
                    modifier = Modifier
                        .constrainAs(
                            filterReference
                        ) {
                            top.linkTo(topBannerReference.bottom)
                            start.linkTo(topBannerReference.start)
                            end.linkTo(topBannerReference.end)
                        }
                        .padding(16.dp),
                    options = filters,
                    selectedOptionIndex = selectedFilterIndex) {
                    selectedFilterIndex = it
                }

                LazyColumn(
                    modifier = Modifier
                        .constrainAs(
                            ordersListReference
                        ) {
                            top.linkTo(filterReference.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                        }
                        .fillMaxWidth()
                        .padding(top = 16.dp)) {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrdersScreen() {
    OrdersScreen(navController = NavHostController(LocalContext.current))
}