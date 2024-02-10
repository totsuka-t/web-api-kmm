import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

class NumbersView(private val onClick: () -> Unit) : Screen {

    @Composable
    override fun Content() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("NumbersAPI") },
                    navigationIcon = {
                        IconButton(onClick = onClick) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                    },
                )
            }
        ) {
            Text("NumbersView")
        }
    }
}