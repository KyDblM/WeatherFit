package com.example.weatherfit.domain.util

import com.example.weatherfit.R

enum class AccessoriesRepository(val accessory: Accessory) {
    HEADER(
        Accessory(
            image = R.drawable.dev_accessories_example,
            description = R.string.header_description
        )
    ),
    GLOVE(
        Accessory(
            image = R.drawable.dev_accessories_example,
            description = R.string.glove_description
        )
    ),
    SCARF(
        Accessory(
            image = R.drawable.dev_accessories_example,
            description = R.string.scarf_description
        )
    ),
    THERMAL_UNDERWEAR(
        Accessory(
            image = R.drawable.dev_accessories_example,
            description = R.string.thermal_underwear_description
        )
    ),
    UMBRELLA(
        Accessory(
            image = R.drawable.dev_accessories_example,
            description = R.string.umbrella_description
        )
    ),
    SUNGLASSES(
        Accessory(
            image = R.drawable.dev_accessories_example,
            description = R.string.sunglasses_description
        )
    ),
    CAP(
        Accessory(
            image = R.drawable.dev_accessories_example,
            description = R.string.cap_description
        )
    ),
    SUNSCREEN(
        Accessory(
            image = R.drawable.dev_accessories_example,
            description = R.string.sunscreen_description
        )
    )
}