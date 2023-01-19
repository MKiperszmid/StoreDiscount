package com.mkiperszmid.storediscount.core.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AirplaneTicket
import androidx.compose.material.icons.outlined.Checkroom
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.ui.graphics.vector.ImageVector

data class ProductItem(
    val code: ProductCode,
    val name: String,
    val price: Double
)

enum class ProductCode(val icon: ImageVector) {
    VOUCHER(Icons.Outlined.AirplaneTicket),
    TSHIRT(Icons.Outlined.Checkroom),
    MUG(Icons.Outlined.Coffee)
}
