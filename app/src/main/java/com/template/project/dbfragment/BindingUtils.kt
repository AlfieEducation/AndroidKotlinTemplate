package com.template.project.dbfragment

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.template.project.database.TemplateEntity

@BindingAdapter("stringField")
fun TextView.setSleepQualityString(item: TemplateEntity?) {
    item?.let {
        text = item.stringField
    }
}