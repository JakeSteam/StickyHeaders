package uk.co.jakelee.stickyheadersdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jay.widget.StickyHeadersLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.jakelee.stickyheadersdemo.ContentAdapter.Companion.getSampleRows

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.isNestedScrollingEnabled = true
        recyclerView.adapter = ContentAdapter(getSampleRows(10))
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = StickyHeadersLinearLayoutManager<ContentAdapter>(this)
    }
}
