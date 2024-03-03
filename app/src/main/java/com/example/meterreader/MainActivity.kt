package com.example.meterreader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.meterreader.ui.theme.CalculateRentTheme

data class PersonInfo(val name: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculateRentTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCardRead()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Clause(personInfo: PersonInfo) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row(
            modifier = Modifier.padding(all = 8.dp),
            Arrangement.SpaceBetween
        ){
            TextField(
                modifier = Modifier.height(40.dp),
                value = "text",
                onValueChange = { newText -> newText + 1 })
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Text(
                text = personInfo.name
            )
        }
    }
}

@Preview
@Composable
fun MessageCardRead() {
    Clause(
        personInfo = PersonInfo("Steve")
    )
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var x = mutableListOf(1, 2, 3, 4, 5)
    Text(
        text = "Hello ${name}!",
        modifier = modifier
    )
}

// @Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculateRentTheme {
        Greeting("Android")
    }
}


/*CalculateRentTheme {
    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Greeting("Android")
    }
}*/


/*Column() {
    Text(
        text = personInfo.author,
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.titleSmall
    )
    Spacer(modifier = Modifier.height(4.dp))
    Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
        Text(text = personInfo.text, modifier = Modifier.padding(all = 4.dp), color = MaterialTheme.colorScheme.tertiary, style = MaterialTheme.typography.bodyMedium)
    }
}*/