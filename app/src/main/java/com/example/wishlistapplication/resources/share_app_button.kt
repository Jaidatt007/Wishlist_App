import android.content.Context
import android.content.Intent

fun shareContent(context: Context, title: String, description: String) {
    val message = "$title\n$description" // Combine title and description with formatting
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    context.startActivity(Intent.createChooser(intent, "Share via"))
}
