package com.example.weatherfit.domain.util

import com.example.weatherfit.R

class MannequinsRepository {
    val mannequins: List<Mannequin> = listOf(
        Mannequin(
            warmIndex = 0.1,
            image = R.drawable.mannequin_first,
            description = R.string.first_mannequin_description
        ),
        Mannequin(
            warmIndex = 0.2,
            image = R.drawable.mannequin_second,
            description = R.string.second_mannequin_description
        ),
        Mannequin(
            warmIndex = 0.3,
            image = R.drawable.mannequin_third,
            description = R.string.third_mannequin_description
        ),
        Mannequin(
            warmIndex = 0.5,
            image = R.drawable.mannequin_fourth,
            description = R.string.fourth_mannequin_description
        ),
        Mannequin(
            warmIndex = 0.8,
            image = R.drawable.mannequin_fifth,
            description = R.string.fifth_mannequin_description
        ),
        Mannequin(
            warmIndex = 1.1,
            image = R.drawable.mannequin_sixth,
            description = R.string.sixth_mannequin_description,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER
            )
        ),
        Mannequin(
            warmIndex = 1.4,
            image = R.drawable.mannequin_from_seventh_to_ninth,
            description = R.string.from_seventh_to_ninth_mannequin_description,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER
            )
        ),
        Mannequin(
            warmIndex = 1.7,
            image = R.drawable.mannequin_from_seventh_to_ninth,
            description = R.string.from_seventh_to_ninth_mannequin_description,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER,
                AccessoriesRepository.GLOVE,
                AccessoriesRepository.SCARF
            )
        ),
        Mannequin(
            warmIndex = 2.0,
            image = R.drawable.mannequin_from_seventh_to_ninth,
            description = R.string.from_seventh_to_ninth_mannequin_description,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER,
                AccessoriesRepository.GLOVE,
                AccessoriesRepository.SCARF,
                AccessoriesRepository.THERMAL_UNDERWEAR
            )
        ),
        Mannequin(
            warmIndex = 2.4,
            image = R.drawable.mannequin_tenth,
            description = R.string.tenth_mannequin_description,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER,
                AccessoriesRepository.GLOVE,
                AccessoriesRepository.SCARF,
                AccessoriesRepository.THERMAL_UNDERWEAR
            )
        )
    )
}