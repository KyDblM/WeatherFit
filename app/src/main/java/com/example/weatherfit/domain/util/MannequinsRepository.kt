package com.example.weatherfit.domain.util

import com.example.weatherfit.R

class MannequinsRepository {
    val mannequins: List<Mannequin> = listOf(
        Mannequin(
            warmIndex = 0.1,
            maleImage = R.drawable.mannequin_first,
            femaleImage = R.drawable.mannequin_naked
        ),
        Mannequin(
            warmIndex = 0.2,
            maleImage = R.drawable.mannequin_second,
            femaleImage = R.drawable.mannequin_naked
        ),
        Mannequin(
            warmIndex = 0.3,
            maleImage = R.drawable.mannequin_third,
            femaleImage = R.drawable.mannequin_naked
        ),
        Mannequin(
            warmIndex = 0.5,
            maleImage = R.drawable.mannequin_fourth,
            femaleImage = R.drawable.mannequin_naked
        ),
        Mannequin(
            warmIndex = 0.8,
            maleImage = R.drawable.mannequin_fifth,
            femaleImage = R.drawable.mannequin_naked
        ),
        Mannequin(
            warmIndex = 1.1,
            maleImage = R.drawable.mannequin_sixth,
            femaleImage = R.drawable.mannequin_naked,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER
            )
        ),
        Mannequin(
            warmIndex = 1.4,
            maleImage = R.drawable.mannequin_from_seventh_to_ninth,
            femaleImage = R.drawable.mannequin_naked,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER
            )
        ),
        Mannequin(
            warmIndex = 1.7,
            maleImage = R.drawable.mannequin_from_seventh_to_ninth,
            femaleImage = R.drawable.mannequin_naked,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER,
                AccessoriesRepository.GLOVE,
                AccessoriesRepository.SCARF
            )
        ),
        Mannequin(
            warmIndex = 2.0,
            maleImage = R.drawable.mannequin_from_seventh_to_ninth,
            femaleImage = R.drawable.mannequin_naked,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER,
                AccessoriesRepository.GLOVE,
                AccessoriesRepository.SCARF,
                AccessoriesRepository.THERMAL_UNDERWEAR
            )
        ),
        Mannequin(
            warmIndex = 2.4,
            maleImage = R.drawable.mannequin_tenth,
            femaleImage = R.drawable.mannequin_naked,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER,
                AccessoriesRepository.GLOVE,
                AccessoriesRepository.SCARF,
                AccessoriesRepository.THERMAL_UNDERWEAR
            )
        )
    )
}