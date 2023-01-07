package com.kira.composecodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kira.composecodelab.ui.theme.ComposeCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCodelabTheme {
                MyApp()
            }
        }
    }
}

val reusableModifier = Modifier
    .fillMaxWidth()
    .background(Color.Red)

val anotherReusableModifier = Modifier
    .fillMaxWidth()
    .background(color = Color.Magenta, shape = RectangleShape)


@Preview(showBackground = true)
@Composable
fun useThisPreview() {
    Greetings()
}

@Composable
fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
    } else {
        Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = List(1000) { "$it"}) {
    Column(
        Modifier
            .padding(5.dp)
            .fillMaxWidth()) {
        LazyColumn {
            item { Text(text = "Header")}
            items(names) { name ->
                Greeting(name = name)
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeCodelabTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Composable
fun Greeting(name: String) {
    var isExpanded by remember { mutableStateOf(false) }
    val extendedPadding by animateDpAsState(
        targetValue = if (isExpanded) 50.dp else 0.dp,
        animationSpec = tween(durationMillis = 2000)
    )
    Surface(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
        color = colorResource(R.color.purple_500)
    ) {
        Row(
            modifier = Modifier.padding(24.dp)
        ) {
            Column(
                Modifier
                    .weight(1f)
                    .padding(bottom = extendedPadding)
            ) {
                Text(
                    "Hello,",
                    color = Color.White
                )
                displayHelloName(name)
            }
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(Color.White),
                onClick = { isExpanded = !isExpanded },
            ) {
                Text(
                    text = if (isExpanded) "Show less" else "Show More",
                    color = Color.Magenta
                )
            }
        }
    }
}

@Composable
fun displayHelloName(name: String) {
    Text(
        text = name,
        color = Color.White
    )
}
