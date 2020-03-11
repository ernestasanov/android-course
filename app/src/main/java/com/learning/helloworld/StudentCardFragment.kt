package com.learning.helloworld

import androidx.fragment.app.Fragment

class StudentCardFragment : Fragment() {


    companion object {
        fun newInstance(): StudentCardFragment {
            val fragment = StudentCardFragment()
            return fragment
        }
    }
}
