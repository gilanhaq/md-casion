package com.example.casion.views.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.casion.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HistoryBottomSheet: BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false)
    }

}