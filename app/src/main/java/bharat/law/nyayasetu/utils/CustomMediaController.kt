package bharat.law.nyayasetu.utils

import android.content.Context
import android.view.View
import android.widget.MediaController

class CustomMediaController(context: Context) : MediaController(context) {

    override fun setAnchorView(view: View?) {
        // Do nothing to prevent the anchor view from being set
    }

    override fun hide() {
        // Override the hide method to prevent the controller from being hidden
    }

    override fun show(timeout: Int) {
        // Override the show method to prevent the controller from being shown
    }

    // Add any other customization you may need
}
