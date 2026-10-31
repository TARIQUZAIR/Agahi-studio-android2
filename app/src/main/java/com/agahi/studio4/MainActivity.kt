package com.agahi.studio4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Navy = Color(0xFF071426)
private val Panel = Color(0xFF10243B)
private val Red = Color(0xFFC51E3A)
private val Gold = Color(0xFFD5B579)
private val Muted = Color(0xFFB5C5D7)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AgahiApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgahiApp() {
    var mode by remember { mutableStateOf("Headlines") }
    var headline by remember { mutableStateOf("آگاہی نیوز — اہم خبریں") }
    var script by remember { mutableStateOf("السلام علیکم! آپ دیکھ رہے ہیں آگاہی نیوز۔") }
    var selectedAudio by remember { mutableStateOf("No narration audio selected") }
    var status by remember { mutableStateOf("Android interface prototype — rendering engine not connected yet.") }

    val audioPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            selectedAudio = uri.lastPathSegment ?: uri.toString()
            status = "Narration file selected. Audio processing is part of the next integration milestone."
        }
    }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Red, secondary = Gold, background = Navy,
            surface = Panel, onSurface = Color.White
        )
    ) {
        Scaffold(containerColor = Navy, topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text("AGAHI STUDIO 4", fontWeight = FontWeight.Black, letterSpacing = 1.2.sp)
                        Text("ANDROID EDITION · DEVELOPMENT", color = Muted, fontSize = 10.sp, letterSpacing = 1.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Panel, titleContentColor = Color.White)
            )
        }) { padding ->
            Column(
                Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState()).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                    ModeChip("Headlines", mode == "Headlines", Modifier.weight(1f)) { mode = "Headlines" }
                    ModeChip("Breaking", mode == "Breaking News", Modifier.weight(1f)) { mode = "Breaking News" }
                    ModeChip("Vlog", mode == "Vlog", Modifier.weight(1f)) { mode = "Vlog" }
                }

                Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(18.dp)) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("$mode PREVIEW", color = Gold, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        if (mode == "Vlog") {
                            Image(
                                painter = painterResource(R.drawable.vlog_background),
                                contentDescription = "AGAHI Vlog studio scene",
                                modifier = Modifier.fillMaxWidth().height(300.dp).border(1.dp, Gold, RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Text("3-second opening cover planned for the exported Vlog.", color = Muted, fontSize = 12.sp)
                        } else {
                            Image(
                                painter = painterResource(R.drawable.anchor),
                                contentDescription = "AGAHI news studio",
                                modifier = Modifier.fillMaxWidth().height(300.dp).border(1.dp, Gold, RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )
                            Text(
                                if (mode == "Headlines") "Red headline strap · Urdu ticker · AGAHI logo" else "Breaking News strap · glowing title · Urdu ticker",
                                color = Muted, fontSize = 12.sp
                            )
                        }
                    }
                }

                Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(18.dp)) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text("PROJECT CONTENT", color = Gold, fontWeight = FontWeight.Bold)
                        OutlinedTextField(
                            value = headline, onValueChange = { headline = it },
                            label = { Text("News / Vlog title") }, modifier = Modifier.fillMaxWidth(),
                            minLines = 1, colors = fieldColors()
                        )
                        OutlinedTextField(
                            value = script, onValueChange = { script = it },
                            label = { Text("Urdu narration script") }, modifier = Modifier.fillMaxWidth(),
                            minLines = 4, colors = fieldColors()
                        )
                        Button(
                            onClick = { audioPicker.launch(arrayOf("audio/*")) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF263F5D))
                        ) { Text("Select narration audio") }
                        Text(selectedAudio, color = Muted, fontSize = 11.sp)
                    }
                }

                Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(18.dp)) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("OUTPUT PROFILE", color = Gold, fontWeight = FontWeight.Bold)
                        Text("Portrait · 720 × 1280 · MP4 · 30 fps target", color = Color.White)
                        Text("Optimized for Android 13 / 4 GB RAM. Export engine integration is pending.", color = Muted, fontSize = 12.sp)
                        Button(
                            onClick = { status = "Project draft saved in this prototype session. MP4 rendering is not enabled yet." },
                            enabled = false,
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Red)
                        ) { Text("GENERATE VIDEO — ENGINE NOT CONNECTED") }
                    }
                }

                Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF091A2B)), shape = RoundedCornerShape(14.dp)) {
                    Column(Modifier.padding(12.dp)) {
                        Text("SYSTEM STATUS", color = Gold, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        Text(status, color = Muted, fontSize = 12.sp)
                    }
                }
                Text("AGAHI Studio 4 DEV · Android prototype 0.1.0", color = Muted, fontSize = 10.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
private fun ModeChip(label: String, selected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick, modifier = modifier.heightIn(min = 44.dp),
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Red else Panel,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) { Text(label, fontSize = 11.sp, fontWeight = FontWeight.Bold) }
}

@Composable
private fun fieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = Color.White, unfocusedTextColor = Color.White,
    focusedBorderColor = Gold, unfocusedBorderColor = Color(0xFF34516D),
    focusedLabelColor = Gold, unfocusedLabelColor = Muted,
    cursorColor = Gold
)
