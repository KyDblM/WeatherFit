package com.example.weatherfit.domain.util

import com.example.weatherfit.R

enum class AccessoriesRepository(val accessory: Accessory) {
    HEADER(
        Accessory(
            image = R.drawable.accessory_header,
            description = R.string.header_description
        )
    ),
    GLOVE(
        Accessory(
            image = R.drawable.accessory_glove,
            description = R.string.glove_description
        )
    ),
    SCARF(
        Accessory(
            image = R.drawable.accessory_scarf,
            description = R.string.scarf_description
        )
    ),
    THERMAL_UNDERWEAR(
        Accessory(
            image = R.drawable.accessory_thermal_underwear,
            description = R.string.thermal_underwear_description
        )
    ),
    UMBRELLA(
        Accessory(
            image = R.drawable.accessory_umbrella,
            description = R.string.umbrella_description
        )
    ),
    SUNGLASSES(
        Accessory(
            image = R.drawable.accessory_sunglasses,
            description = R.string.sunglasses_description
        )
    ),
    CAP(
        Accessory(
            image = R.drawable.accessory_cap,
            description = R.string.cap_description
        )
    ),
    SUNSCREEN(
        Accessory(
            image = R.drawable.accessory_sunscreen,
            description = R.string.sunscreen_description
        )
    )
}