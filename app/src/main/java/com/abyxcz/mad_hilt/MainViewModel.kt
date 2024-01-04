package com.abyxcz.mad_hilt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abyxcz.mad_hilt.util.CoroutineContextProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coroutineContextProvider: CoroutineContextProvider
) : ViewModel() {


    init {
        viewModelScope.launch(
            coroutineContextProvider.io
        ){
            //Use Injected Dependency as usual.
            //Test with explicit provider params
        }
    }
}