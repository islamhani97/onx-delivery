package com.islam97.android.apps.onx.presentation.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.islam97.android.apps.onx.R
import com.islam97.android.apps.onx.domain.models.Order
import com.islam97.android.apps.onx.domain.models.OrderStatus
import com.islam97.android.apps.onx.domain.models.toValue
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme

@Composable
fun OrderItem(order: Order) {
    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(50),
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
                    start.linkTo(parent.start)
                },
                text = "#${order.id}",
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.appColorScheme.neutralDark)
            )

            OrderItemField(
                modifier = Modifier.constrainAs(statusReference) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(statusDividerReference.start)
                    bottom.linkTo(parent.bottom)
                }, stringResource(R.string.status), order.status.toValue(), statusColor
            )

            VerticalDivider(modifier = Modifier
                .constrainAs(statusDividerReference) {
                    top.linkTo(parent.top)
                    start.linkTo(statusReference.end)
                    end.linkTo(totalPriceReference.start)
                    bottom.linkTo(parent.bottom)
                }
                .width(2.dp))

            OrderItemField(
                modifier = Modifier.constrainAs(totalPriceReference) {
                    top.linkTo(parent.top)
                    start.linkTo(statusDividerReference.end)
                    end.linkTo(totalPriceDividerReference.start)
                    bottom.linkTo(parent.bottom)
                },
                stringResource(R.string.total_price),
                order.totalPrice,
                MaterialTheme.appColorScheme.primary
            )

            VerticalDivider(modifier = Modifier
                .constrainAs(totalPriceDividerReference) {
                    top.linkTo(parent.top)
                    start.linkTo(totalPriceDividerReference.end)
                    end.linkTo(dateReference.start)
                    bottom.linkTo(parent.bottom)
                }
                .width(2.dp))

            OrderItemField(
                modifier = Modifier.constrainAs(dateReference) {
                    top.linkTo(parent.top)
                    start.linkTo(totalPriceDividerReference.end)
                    end.linkTo(orderDetailsButtonReference.start)
                    bottom.linkTo(parent.bottom)
                }, stringResource(R.string.date), order.date, MaterialTheme.appColorScheme.primary
            )
            Column(modifier = Modifier.constrainAs(orderDetailsButtonReference) {
                top.linkTo(parent.top)
                start.linkTo(dateReference.end)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderItem() {
    OrderItem(Order("", OrderStatus.NEW, "", ""))
}