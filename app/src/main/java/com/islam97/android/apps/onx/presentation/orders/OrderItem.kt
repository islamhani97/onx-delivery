package com.islam97.android.apps.onx.presentation.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.islam97.android.apps.onx.R
import com.islam97.android.apps.onx.domain.models.Order
import com.islam97.android.apps.onx.domain.models.OrderStatus
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme

@Composable
fun OrderItem(modifier: Modifier = Modifier, order: Order) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors()
            .copy(containerColor = MaterialTheme.appColorScheme.white),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        ConstraintLayout(modifier = Modifier) {
            val (idReference, statusReference, statusDividerReference, totalPriceReference, totalPriceDividerReference, dateReference, orderDetailsButtonReference) = createRefs()

            val statusColor = when (order.status) {
                OrderStatus.NEW -> MaterialTheme.appColorScheme.secondary
                OrderStatus.DELIVERING -> MaterialTheme.appColorScheme.primary
                OrderStatus.DELIVERED -> MaterialTheme.appColorScheme.neutralExtraDark
                OrderStatus.RETURNED -> MaterialTheme.appColorScheme.error
            }
            Text(
                modifier = Modifier.constrainAs(idReference) {
                    top.linkTo(parent.top)
                    start.linkTo(statusReference.start)
                    end.linkTo(statusReference.end)
                },
                text = "#${order.id}",
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.appColorScheme.neutralDark)
            )

            OrderItemField(
                modifier = Modifier
                    .constrainAs(statusReference) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(statusDividerReference.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth(0.27f)
                    .padding(vertical = 20.dp),
                stringResource(R.string.status),
                stringResource(order.status.textResourceId),
                statusColor)

            VerticalDivider(
                modifier = Modifier
                    .constrainAs(statusDividerReference) {
                        top.linkTo(parent.top)
                        start.linkTo(statusReference.end)
                        end.linkTo(totalPriceReference.start)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
                    .width(2.dp)
                    .padding(vertical = 8.dp),
                color = MaterialTheme.appColorScheme.neutral)

            OrderItemField(
                modifier = Modifier
                    .constrainAs(totalPriceReference) {
                        top.linkTo(parent.top)
                        start.linkTo(statusDividerReference.end)
                        end.linkTo(totalPriceDividerReference.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth(0.27f)
                    .padding(vertical = 20.dp),
                stringResource(R.string.total_price),
                order.totalPrice,
                MaterialTheme.appColorScheme.primary)

            VerticalDivider(
                modifier = Modifier
                    .constrainAs(totalPriceDividerReference) {
                        top.linkTo(parent.top)
                        start.linkTo(totalPriceDividerReference.end)
                        end.linkTo(dateReference.start)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
                    .width(2.dp)
                    .padding(vertical = 8.dp),
                color = MaterialTheme.appColorScheme.neutral)

            OrderItemField(
                modifier = Modifier
                    .constrainAs(dateReference) {
                        top.linkTo(parent.top)
                        start.linkTo(totalPriceDividerReference.end)
                        end.linkTo(orderDetailsButtonReference.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .fillMaxWidth(0.27f)
                    .padding(vertical = 20.dp),
                stringResource(R.string.date),
                order.date,
                MaterialTheme.appColorScheme.primary)
            Column(
                modifier = Modifier
                    .constrainAs(orderDetailsButtonReference) {
                        top.linkTo(parent.top)
                        start.linkTo(dateReference.end)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        height = Dimension.fillToConstraints
                    }
                    .fillMaxWidth(0.19f)
                    .background(statusColor),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Text(
                    modifier = Modifier,
                    text = stringResource(R.string.order_details),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.appColorScheme.white, textAlign = TextAlign.Center
                    )
                )

                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .padding(top = 4.dp),
                    painter = painterResource(id = R.drawable.ic_arrow),
                    contentDescription = "",
                    tint = MaterialTheme.appColorScheme.white
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderItem() {
    OrderItem(modifier = Modifier, order = Order("", OrderStatus.NEW, "", ""))
}