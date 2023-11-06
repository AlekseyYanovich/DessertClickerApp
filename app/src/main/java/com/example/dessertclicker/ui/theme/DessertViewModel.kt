package com.example.dessertclicker.ui.theme

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.data.DessertClickerUiState
import com.example.dessertclicker.model.Dessert

class DessertClickerViewModel : ViewModel() {
    internal var uiState = DessertClickerUiState()

    fun handleDessertClicked() {
        val dessertToShow = determineDessertToShow(Datasource.dessertList, uiState.dessertSold + 1)
        uiState = uiState.copy(
            revenue = uiState.revenue + dessertToShow.price,
            dessertSold = uiState.dessertSold + 1,
            currentDessertImageId = dessertToShow.imageId,
            currentDessertPrice = dessertToShow.price
        )
    }

    private fun determineDessertToShow(desserts: List<Dessert>, dessertsSold: Int): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                break
            }
        }
        return dessertToShow
    }
}