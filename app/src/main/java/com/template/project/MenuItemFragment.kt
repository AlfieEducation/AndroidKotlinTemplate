package com.template.project

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment


class MenuItemFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_menuitem, container, false)
    }
}