package kz.ukteplo.uktsgisviewer

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class AlertDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val view = layoutInflater.inflate(R.layout.alert_dialog_fragment, null)
        view.findViewById<TextView>(R.id.code).setText(arguments?.getString("code"))
        builder.setView(view)
            .setTitle("Внимание")
            .setMessage(arguments?.getString("msg"))
            .setPositiveButton("OK", {dialog, which -> dialog.dismiss()})
        return builder.create()
    }

    companion object {
        const val TAG = "AlertDialog"
    }
}