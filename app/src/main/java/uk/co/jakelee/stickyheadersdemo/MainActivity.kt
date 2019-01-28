package uk.co.jakelee.stickyheadersdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.jay.widget.StickyHeadersLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.adapter = ContentAdapter(getSampleRows(10))
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getSampleRows(numSections: Int): List<ContentAdapter.IRow> {
        val rows = mutableListOf<ContentAdapter.IRow>()
        for (i in 1..numSections) {
            rows.add(ContentAdapter.HeaderRow(Randomiser.date(), Randomiser.word()))
            val numChildren = Randomiser.int(0, 10)
            for (j in 1..numChildren) {
                if(Randomiser.int(0, 1) > 0) {
                    rows.add(ContentAdapter.MessageRow(Randomiser.message()))
                } else {
                    rows.add(ContentAdapter.ColourRow(Randomiser.colour()))
                }
            }
        }
        return rows
    }
}
