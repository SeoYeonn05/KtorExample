package com.example.ktorexample.ktor

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RestFulViewModel constructor(
    private val restFulRepository: RestFulRepository) : ViewModel() {

    companion object {
        private const val TAG = "RestFulViewModel"
    }

    private val _pictureData = MutableStateFlow(Picture("", "", ""))
    val pictureData: StateFlow<Picture> = _pictureData

    fun requestPicture() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = restFulRepository.getPictureByGet(0)
//                val response = restFulRepository.getPictureByPost(PictureRequest(1))
                Log.i(TAG, "requestRestFul() - success:$response")
                _pictureData.emit(response)
            } catch (th: Throwable) {
                Log.e(TAG, "Error:Code: ${restFulRepository.getErrorStatus(th)}")
            }
        }
    }
}