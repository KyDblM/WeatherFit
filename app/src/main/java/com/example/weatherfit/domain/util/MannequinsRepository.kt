package com.example.weatherfit.domain.util

import com.example.weatherfit.R

class MannequinsRepository {
    val mannequins: List<Mannequin> = listOf(
        Mannequin(
            warmIndex = 0.1,
            maleImage = R.drawable.mannequin_first_male,
            femaleImage = R.drawable.mannequin_first_female
        ),
        Mannequin(
            warmIndex = 0.2,
            maleImage = R.drawable.mannequin_second_male,
            femaleImage = R.drawable.mannequin_second_female
        ),
        Mannequin(
            warmIndex = 0.3,
            maleImage = R.drawable.mannequin_third_male,
            femaleImage = R.drawable.mannequin_third_female
        ),
        Mannequin(
            warmIndex = 0.5,
            maleImage = R.drawable.mannequin_fourth_male,
            femaleImage = R.drawable.mannequin_fourth_female
        ),
        Mannequin(
            warmIndex = 0.8,
            maleImage = R.drawable.mannequin_fifth_male,
            femaleImage = R.drawable.mannequin_fifth_female
        ),
        Mannequin(
            warmIndex = 1.1,
            maleImage = R.drawable.mannequin_sixth_male,
            femaleImage = R.drawable.mannequin_sixth_female,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER
            )
        ),
        Mannequin(
            warmIndex = 1.4,
            maleImage = R.drawable.mannequin_from_seventh_to_ninth_male,
            femaleImage = R.drawable.mannequin_from_seventh_to_ninth_female,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER
            )
        ),
        Mannequin(
            warmIndex = 1.7,
            maleImage = R.drawable.mannequin_from_seventh_to_ninth_male,
            femaleImage = R.drawable.mannequin_from_seventh_to_ninth_female,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER,
                AccessoriesRepository.GLOVE,
                AccessoriesRepository.SCARF
            )
        ),
        Mannequin(
            warmIndex = 2.0,
            maleImage = R.drawable.mannequin_from_seventh_to_ninth_male,
            femaleImage = R.drawable.mannequin_from_seventh_to_ninth_female,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER,
                AccessoriesRepository.GLOVE,
                AccessoriesRepository.SCARF,
                AccessoriesRepository.THERMAL_UNDERWEAR
            )
        ),
        Mannequin(
            warmIndex = 2.4,
            maleImage = R.drawable.mannequin_tenth_male,
            femaleImage = R.drawable.mannequin_tenth_female,
            baseAccessories = mutableListOf(
                AccessoriesRepository.HEADER,
                AccessoriesRepository.GLOVE,
                AccessoriesRepository.SCARF,
                AccessoriesRepository.THERMAL_UNDERWEAR
            )
        )
    )
}