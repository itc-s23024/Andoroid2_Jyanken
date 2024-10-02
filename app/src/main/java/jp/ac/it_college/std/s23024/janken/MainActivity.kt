package jp.ac.it_college.std.s23024.janken

import android.media.metrics.PlaybackErrorEvent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.ac.it_college.std.s23024.janken.ui.theme.JankenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JankenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    var myHand by remember { mutableIntStateOf(-1) }
    var comHand by remember { mutableIntStateOf(-1) }
    var result by remember { mutableIntStateOf(-1) }
    // 移譲

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.app_name))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                myHand = 0
                comHand = (0..2).random()
                result = (comHand - myHand + 3) % 3
            }) {
            Text(text = stringResource(id = R.string.gu))
        }
            Button(onClick = {
                myHand = 1
                comHand = (0..2).random()
                result = (comHand - myHand + 3) % 3
            }) {
                Text(text = stringResource(id = R.string.choki))
            }
            Button(onClick = {
                myHand = 2
                comHand = (0..2).random()
                result = (comHand - myHand + 3) % 3
            }) {
                Text(text = stringResource(id = R.string.pa))
            }
        }
        PlayerView(hand = myHand)
        ResultView(result = result)
        ComputerView(comHand = comHand)
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, locale = "ja")
@Composable
private fun ContentPreview() {
    Content()
}

@Composable
fun PlayerView(modifier: Modifier = Modifier, hand: Int) {
    if (hand == 0) {
        Image(
            painter = painterResource(id = R.drawable.gu),
            contentDescription = null
        )
    } else if (hand == 1) {
        Image(
            painter = painterResource(id = R.drawable.choki),
            contentDescription = null
        )
    } else if (hand == 2) {
        Image(
            painter = painterResource(id = R.drawable.pa),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun PlayerViewPreview0() {
    PlayerView(hand = 0)
}

@Preview
@Composable
private fun PlayViewPreview1() {
    PlayerView(hand = 1)
}

@Preview
@Composable
private fun PlayViewPreview2() {
    PlayerView(hand = 2)
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF00FF00,
    widthDp = 100, heightDp = 200
)
@Composable
private fun PlayerViewPreview() {
    PlayerView(hand = 1)
}

@Composable
fun ComputerView(modifier: Modifier = Modifier, comHand: Int) {
    when(comHand) {
        0 -> Image(
            painter = painterResource(id = R.drawable.com_gu),
            contentDescription = null
        )
        1 -> Image(
            painter = painterResource(id = R.drawable.com_choki),
            contentDescription = null
        )
        2 -> Image(
            painter = painterResource(id = R.drawable.com_pa),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun ComputerViewPreview0() {
    ComputerView(comHand = 0)
}

@Preview
@Composable
private fun ComputerViewPreview1() {
    ComputerView(comHand = 1)
}

@Preview
@Composable
private fun ComputerViewPreview2() {
    ComputerView(comHand = 2)
}

@Composable
fun ResultView(modifier: Modifier = Modifier, result: Int) {
    when (result) {
        0 -> Text(
            text = stringResource(R.string.draw),
            fontSize = 32.sp
            )
        1 -> Text(text = stringResource(R.string.player_win),
            fontSize = 32.sp,
            color = Color(0xFFD81B60)
        )
        2 -> Text(text = stringResource(R.string.computer_wins),
            fontSize = 32.sp,
            color = Color.Blue
        )
    }
}

@Preview(
    showBackground = true
)
@Preview(
    showBackground = true,
    locale = "ja"
)
@Composable
private fun ResultViewPreview() {
    Column {
        ResultView(result = 0)
        ResultView(result = 1)
        ResultView(result = 2)
    }
}

