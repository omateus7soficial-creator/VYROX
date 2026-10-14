package com.vyrox.editor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Fundo = Color(0xFF101116)
private val Cartao = Color(0xFF1C1D26)
private val Roxo = Color(0xFF9B7BFF)
private val Texto = Color(0xFFF5F3FF)
private val Secundario = Color(0xFFAAA9BA)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    background = Fundo,
                    surface = Cartao,
                    primary = Roxo
                )
            ) {
                TelaInicial()
            }
        }
    }
}

@Composable
fun TelaInicial() {
    var videoSelecionado by remember { mutableStateOf<String?>(null) }

    val escolherVideo = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        videoSelecionado = uri?.lastPathSegment
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Fundo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(22.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "VYROX",
                        color = Texto,
                        fontSize = 30.sp,
                        letterSpacing = 3.sp
                    )
                    Text(
                        text = "VIDEO EDITOR",
                        color = Roxo,
                        fontSize = 11.sp,
                        letterSpacing = 2.sp
                    )
                }

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configurações",
                    tint = Secundario
                )
            }

            Spacer(Modifier.height(36.dp))

            Text(
                text = "Crie algo incrível.",
                color = Texto,
                fontSize = 25.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Suas ideias merecem ganhar vida.",
                color = Secundario,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    escolherVideo.launch(arrayOf("video/*"))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Roxo,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
                Spacer(Modifier.width(8.dp))
                Text("Novo projeto", fontSize = 16.sp)
            }

            Spacer(Modifier.height(32.dp))

            Text(
                text = "Seus projetos",
                color = Texto,
                fontSize = 19.sp
            )

            Spacer(Modifier.height(14.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(
                        Cartao,
                        RoundedCornerShape(20.dp)
                    )
                    .padding(22.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Movie,
                    contentDescription = null,
                    tint = Roxo,
                    modifier = Modifier.size(48.dp)
                )

                Spacer(Modifier.height(14.dp))

                Text(
                    text = if (videoSelecionado == null)
                        "Seu próximo projeto começa aqui"
                    else
                        "Vídeo selecionado!",
                    color = Texto,
                    fontSize = 16.sp
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = if (videoSelecionado == null)
                        "Escolha um vídeo para começar."
                    else
                        "Agora vamos preparar as ferramentas de edição.",
                    color = Secundario,
                    fontSize = 13.sp
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "VYROX  •  CRIATIVIDADE SEM LIMITES",
                color = Secundario,
                fontSize = 10.sp,
                letterSpacing = 1.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}
