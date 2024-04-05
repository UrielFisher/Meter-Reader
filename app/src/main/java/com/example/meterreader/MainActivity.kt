package com.example.meterreader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.meterreader.ui.theme.MeterReaderTheme
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

data class PersonInfo(val name: String)

class MainActivity : ComponentActivity() {
    private var person = mutableStateOf(PersonInfo("loading"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)

        processImage(recognizer) { result ->
//            person.value = PersonInfo(result)
            println(result)
        }

        setContent {
            MeterReaderTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCardRead(person)
                }
            }
        }
    }

    fun processImage(recognizer: TextRecognizer, callback: (String) -> Unit) {
        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.test)
        val image = InputImage.fromBitmap(bitmap, 0)

        val result = recognizer.process(image)
            .addOnSuccessListener { visionText ->
                callback(visionText.text)
            }
            .addOnFailureListener { e ->
                callback("error processing image")
                e.stackTrace
            }
    }
}

// @Preview
@Composable
fun MessageCardRead(person: MutableState<PersonInfo>) {
    repeat(1) {index ->
        Clause(
            index = index,
            personInfo = person
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Clause(index: Int, personInfo: MutableState<PersonInfo>) {
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .padding(top = index * 64.dp)
    ) {
        Row(
            modifier = Modifier.padding(all = 8.dp),
            Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
        ){
            TextField(
                modifier = Modifier.height(40.dp).width(80.dp),
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
                text = personInfo.value.name
            )
        }
    }
}