package com.sdsu.assignment2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var btnDone: Button? = null
    private var btnMajor: Button? = null
    private var major_parent: String? = ""
    private var major_child: String? = ""
    private var etFirstName: EditText? = null
    private var etLastName: EditText? = null
    private var etAge: EditText? = null
    private var etEmail: EditText? = null
    private var etPhone: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        etAge = findViewById(R.id.etAge)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)

        btnDone = findViewById(R.id.btnDone)
        btnMajor = findViewById(R.id.btnMajor)
        btnDone!!.setOnClickListener(this)
        btnMajor!!.setOnClickListener(this)

        fillDataIfAvailable()
    }

    private fun fillDataIfAvailable() {
        val sp = getSharedPreferences("Data", Context.MODE_PRIVATE)

        if (sp.all.size > 0) {
            etFirstName!!.setText(sp.getString("fn", ""))
            etLastName!!.setText(sp.getString("ln", ""))
            etAge!!.setText(sp.getString("age", ""))
            etEmail!!.setText(sp.getString("email", ""))
            etPhone!!.setText(sp.getString("phone", ""))
            major_parent = sp.getString("major_parent", "")
            major_child = sp.getString("major_child", "")

            var acronymParent: String? = null
            if (major_parent!!.equals(getString(R.string.DoctorofPhilosophy), ignoreCase = true)) {
                acronymParent = getString(R.string.DoctorofPhilosophy_acr)
            } else if (major_parent!!.equals(getString(R.string.DoctorofEducation), ignoreCase = true)) {
                acronymParent = getString(R.string.DoctorofEducation_acr)
            } else if (major_parent!!.equals(getString(R.string.MasterofArts), ignoreCase = true)) {
                acronymParent = getString(R.string.MasterofArts_acr)
            } else if (major_parent!!.equals(getString(R.string.MasterofScience), ignoreCase = true)) {
                acronymParent = getString(R.string.MasterofScience_acr)
            } else if (major_parent!!.equals(getString(R.string.MasterofFineArts), ignoreCase = true)) {
                acronymParent = getString(R.string.MasterofFineArts_acr)
            } else if (major_parent!!.equals(getString(R.string.ProfessionalMastersDegrees), ignoreCase = true)) {
                acronymParent = getString(R.string.ProfessionalMastersDegrees_acr)
            }
            btnMajor!!.text = getString(R.string.major_val, acronymParent, major_child)
        }
    }

    private fun isEmailValid(text: String?): Boolean {
        return if (text == null) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(text).matches()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (data.extras != null) {
                if (data.extras!!.containsKey("major_parent")) {
                    major_parent = data.extras!!.getString("major_parent", "")
                    major_child = data.extras!!.getString("major_child", "")
                    var acronymParent: String? = null
                    if (major_parent!!.equals(getString(R.string.DoctorofPhilosophy), ignoreCase = true)) {
                        acronymParent = getString(R.string.DoctorofPhilosophy_acr)
                    } else if (major_parent!!.equals(getString(R.string.DoctorofEducation), ignoreCase = true)) {
                        acronymParent = getString(R.string.DoctorofEducation_acr)
                    } else if (major_parent!!.equals(getString(R.string.MasterofArts), ignoreCase = true)) {
                        acronymParent = getString(R.string.MasterofArts_acr)
                    } else if (major_parent!!.equals(getString(R.string.MasterofScience), ignoreCase = true)) {
                        acronymParent = getString(R.string.MasterofScience_acr)
                    } else if (major_parent!!.equals(getString(R.string.MasterofFineArts), ignoreCase = true)) {
                        acronymParent = getString(R.string.MasterofFineArts_acr)
                    } else if (major_parent!!.equals(getString(R.string.ProfessionalMastersDegrees), ignoreCase = true)) {
                        acronymParent = getString(R.string.ProfessionalMastersDegrees_acr)
                    }
                    btnMajor!!.text = getString(R.string.major_val, acronymParent, major_child)
                }
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnMajor -> {
                val intent = Intent(this, MajorSelectionActivity::class.java)
                startActivityForResult(intent, 1)
            }
            R.id.btnDone -> {
                val sp = getSharedPreferences("Data", Context.MODE_PRIVATE)
                val editor = sp.edit()
                editor.putString("fn", etFirstName!!.text.toString().trim { it <= ' ' })
                editor.putString("ln", etLastName!!.text.toString().trim { it <= ' ' })
                editor.putString("age", etAge!!.text.toString().trim { it <= ' ' })
                editor.putString("email", etEmail!!.text.toString().trim { it <= ' ' })
                editor.putString("phone", etPhone!!.text.toString().trim { it <= ' ' })
                editor.putString("major_parent", major_parent)
                editor.putString("major_child", major_child)
                editor.apply()
                Toast.makeText(this, "Profile data saved.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
