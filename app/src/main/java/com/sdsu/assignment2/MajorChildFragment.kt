package com.sdsu.assignment2

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MajorChildFragment : ListFragment(), AdapterView.OnItemClickListener, View.OnClickListener {
    private var advDegree = ""
    private var selectedItem = -1
    private var array: Array<String>? = null
    private var btnDone: Button? = null
    private var btnClear: Button? = null
    private var tvTitle: TextView? = null
    private var adapter: ArrayAdapter<*>? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_major_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDone = view.findViewById(R.id.btnDone)
        btnClear = view.findViewById(R.id.btnClear)
        tvTitle = view.findViewById(R.id.tvTitle)
        btnDone!!.setOnClickListener(this)
        btnClear!!.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (arguments != null) {
            if (arguments!!.containsKey("advDegree")) {
                advDegree = arguments!!.getString("advDegree", "")

            }
        }
        setData()
    }

    private fun setData() {
        var arrVal = -1
        if (advDegree.equals(getString(R.string.DoctorofPhilosophy), ignoreCase = true)) {
            arrVal = R.array.DrOfPhilosophy
        } else if (advDegree.equals(getString(R.string.DoctorofEducation), ignoreCase = true)) {
            arrVal = R.array.DrOfEducation
        } else if (advDegree.equals(getString(R.string.MasterofArts), ignoreCase = true)) {
            arrVal = R.array.MasterOfArts
        } else if (advDegree.equals(getString(R.string.MasterofScience), ignoreCase = true)) {
            arrVal = R.array.MasterofScience
        } else if (advDegree.equals(getString(R.string.MasterofFineArts), ignoreCase = true)) {
            arrVal = R.array.MasterofFineArts
        } else if (advDegree.equals(getString(R.string.ProfessionalMastersDegrees), ignoreCase = true)) {
            arrVal = R.array.ProfessionalMastersDegrees
        }
        tvTitle!!.text = advDegree
        if (arrVal != -1) {
            array = resources.getStringArray(arrVal)
            adapter = ArrayAdapter.createFromResource(activity!!,
                    arrVal, android.R.layout.simple_list_item_single_choice)
            listAdapter = adapter
            listView.choiceMode = ListView.CHOICE_MODE_SINGLE
            listView.onItemClickListener = this
        }
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        selectedItem = position
        if (btnClear!!.visibility == View.GONE) {
            btnClear!!.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnDone -> if (selectedItem != -1) {
                val intent = activity!!.intent
                intent.putExtra("major_parent", advDegree)
                intent.putExtra("major_child", array!![selectedItem])
                activity!!.setResult(1, intent)
                activity!!.finish()
            } else {
                Toast.makeText(activity, "Please select Major.", Toast.LENGTH_SHORT).show()
            }
            R.id.btnClear -> {
                setData()
                btnClear!!.visibility = View.GONE
            }
        }
    }
}
