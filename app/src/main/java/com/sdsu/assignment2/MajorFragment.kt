package com.sdsu.assignment2

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter

class MajorFragment : ListFragment(), AdapterView.OnItemClickListener {
    private var arr: Array<String>? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_major_parent, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arr = resources.getStringArray(R.array.AdvancedDegrees)
        val adapter = ArrayAdapter.createFromResource(activity!!,
                R.array.AdvancedDegrees, android.R.layout.simple_list_item_1)
        listAdapter = adapter
        listView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        //Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
        val fm = activity!!.supportFragmentManager
        val ft = fm.beginTransaction()
        val majorChildFragment = MajorChildFragment()
        val bundle = Bundle()
        bundle.putString("advDegree", arr!![position])
        majorChildFragment.arguments = bundle
        ft.hide(fm.findFragmentById(R.id.fl_container))
        ft.add(R.id.fl_container, majorChildFragment,
                MajorChildFragment::class.java.canonicalName)
        ft.addToBackStack(MajorChildFragment::class.java.canonicalName)
        ft.commit()
    }
}
