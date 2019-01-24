package uk.co.jakelee.stickyheadersdemo

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jay.widget.StickyHeaders

internal class ContentAdapter(private val rows: List<IRow>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaders {

    interface IRow
    class HeaderRow(val date: String, val title: String) : IRow
    class MessageRow(val message: String) : IRow
    class ColourRow(val colour: Int) : IRow

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateView: TextView = itemView.findViewById(R.id.date)
        val titleView: TextView = itemView.findViewById(R.id.title)
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageView: TextView = itemView.findViewById(R.id.message)
    }

    class ColourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val colourView: TextView = itemView.findViewById(R.id.colour)
    }

    override fun getItemCount() = rows.count()

    override fun isStickyHeader(position: Int) = rows[position] is HeaderRow

    override fun getItemViewType(position: Int): Int =
        when (rows[position]) {
            is HeaderRow -> TYPE_HEADER
            is MessageRow -> TYPE_MESSAGE
            is ColourRow -> TYPE_COLOUR
            else -> throw IllegalArgumentException()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_HEADER -> {
                return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_header, parent, false))
            }
            TYPE_MESSAGE -> {
                return MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_message, parent, false))
            }
            TYPE_COLOUR -> {
                return ColourViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_colour, parent, false))
            }
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            TYPE_HEADER -> onBindHeader(holder, rows[position] as HeaderRow)
            TYPE_MESSAGE -> onBindMessage(holder, rows[position] as MessageRow)
            TYPE_COLOUR -> onBindColour(holder, rows[position] as ColourRow)
            else -> throw IllegalArgumentException()
        }

    private fun onBindHeader(holder: RecyclerView.ViewHolder, row: HeaderRow) {
        val headerRow = holder as HeaderViewHolder
        headerRow.titleView.text = row.title
        headerRow.dateView.text = row.date
    }

    private fun onBindMessage(holder: RecyclerView.ViewHolder, row: MessageRow) {
        val messageRow = holder as MessageViewHolder
        messageRow.messageView.text = row.message
    }

    private fun onBindColour(holder: RecyclerView.ViewHolder, row: ColourRow) {
        val colourRow = holder as ColourViewHolder
        colourRow.colourView.setBackgroundColor(row.colour)
    }

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_MESSAGE = 1
        private const val TYPE_COLOUR = 2

        fun getSampleRows(numSections: Int): List<IRow> {
            val rows = mutableListOf<IRow>()
            for (i in 1..numSections) {
                rows.add(HeaderRow(Randomiser.date(), Randomiser.message()))
                val numChildren = Randomiser.int(0, 10)
                for (j in 1..numChildren) {
                    if(Randomiser.int(0, 1) > 0) {
                        rows.add(MessageRow(Randomiser.message()))
                    } else {
                        rows.add(ColourRow(Randomiser.colour()))
                    }
                }
            }
            return rows
        }
    }
}