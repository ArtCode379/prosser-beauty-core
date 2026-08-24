package prosserco.cosmetics.prosserbeautycore.data.model

import androidx.annotation.StringRes
import prosserco.cosmetics.prosserbeautycore.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    SKINCARE(R.string.tsdik_category_skincare),
    MAKEUP(R.string.tsdik_category_makeup),
    HAIRCARE(R.string.tsdik_category_haircare),
    BODY(R.string.tsdik_category_body),
    FRAGRANCE(R.string.tsdik_category_fragrance)
}
