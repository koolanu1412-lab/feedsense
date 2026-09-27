package com.example.feedsense
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.Image
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import com.example.feedsense.ui.localization.AppLanguage
import com.example.feedsense.ui.localization.appLanguages
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feedsense.ui.theme.FeedSenseTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FeedSenseTheme {
                FeedSenseApp()
            }
        }
    }
}

@Composable
fun FeedSenseApp() {

    var currentScreen by remember { mutableStateOf("language") }
    var selectedLanguage by remember {
        mutableStateOf(appLanguages.first())
    }
    var selectedTest by remember { mutableStateOf("") }

    when (currentScreen) {

        "language" -> {
            LanguageScreen(
                languages = appLanguages,
                selectedLanguage = selectedLanguage,
                onLanguageSelected = {
                    selectedLanguage = it
                },
                onContinue = {
                    currentScreen = "home"
                }
            )
        }

        "home" -> {
            HomeScreen(
                language = selectedLanguage,
                onTest = {
                    currentScreen = "test"
                },
                onHardware = {
                    currentScreen = "hardware"
                },
                onQr = {
                    currentScreen = "qr"
                },
                onHistory = {
                    currentScreen = "history"
                },
                onReports = {
                    currentScreen = "reports"
                },
                onSettings = {
                    currentScreen = "settings"
                }
            )
        }

        "test" -> {
            TestSelectionScreen(
                language = selectedLanguage,
                onBack = {
                    currentScreen = "home"
                },
                onFeed = {
                    selectedTest = "Feed"
                    currentScreen = "sample"
                },
                onSilage = {
                    selectedTest = "Silage"
                    currentScreen = "sample"
                }
            )
        }

        "sample" -> {
            SampleScreen(
                language = selectedLanguage,
                testType = selectedTest,
                onBack = {
                    currentScreen = "test"
                },
                onAnalyze = {
                    currentScreen = "analysis"
                }
            )
        }

        "analysis" -> {
            AnalysisProgressScreen(
                language = selectedLanguage,
                testType = selectedTest,
                onComplete = {
                    currentScreen = "results"
                }
            )
        }

        "results" -> {
            ResultsScreen(
                language = selectedLanguage,
                testType = selectedTest,
                onBackHome = {
                    currentScreen = "home"
                },
                onQr = {
                    currentScreen = "qr"
                }
            )
        }

        "hardware" -> {
            HardwareScreen(
                language = selectedLanguage,
                onBack = {
                    currentScreen = "home"
                }
            )
        }

        "qr" -> {
            QrScreen(
                language = selectedLanguage,
                onBack = {
                    currentScreen = "home"
                }
            )
        }

        "history" -> {
            HistoryScreen(
                language = selectedLanguage,
                onBack = {
                    currentScreen = "home"
                }
            )
        }

        "reports" -> {
            ReportsScreen(
                language = selectedLanguage,
                onBack = {
                    currentScreen = "home"
                }
            )
        }

        "settings" -> {
            SettingsScreen(
                language = selectedLanguage,
                onLanguageChange = {
                    selectedLanguage = it
                },
                onBack = {
                    currentScreen = "home"
                }
            )
        }
    }
}


/* -------------------------------------------------------
   LANGUAGE SCREEN
------------------------------------------------------- */

@Composable
fun LanguageScreen(
    languages: List<AppLanguage>,
    selectedLanguage: AppLanguage,
    onLanguageSelected: (AppLanguage) -> Unit,
    onContinue: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        FeedSenseLogo()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "FeedSense",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Smart Feed & Silage Quality Testing",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = selectedLanguage.chooseLanguage,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        languages.forEach { language ->

            LanguageButton(
                text = language.displayName,
                selected = language.code == selectedLanguage.code,
                onClick = {
                    onLanguageSelected(language)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = selectedLanguage.continueText,
                fontSize = 17.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Offline-friendly • Farmer-focused",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}


/* -------------------------------------------------------
   LOGO
------------------------------------------------------- */

@Composable
fun FeedSenseLogo() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "🌿",
                fontSize = 52.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "FEEDSENSE",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )
    }
}


/* -------------------------------------------------------
   LANGUAGE BUTTON
------------------------------------------------------- */

@Composable
fun LanguageButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor =
                if (selected)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.surface
        )
    ) {

        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = if (selected)
                    FontWeight.Bold
                else
                    FontWeight.Normal
            )
        }
    }
}


/* -------------------------------------------------------
   HOME / DASHBOARD
------------------------------------------------------- */

@Composable
fun HomeScreen(
    language: AppLanguage,
    onTest: () -> Unit,
    onHardware: () -> Unit,
    onQr: () -> Unit,
    onHistory: () -> Unit,
    onReports: () -> Unit,
    onSettings: () -> Unit
) {

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "🌿",
                            fontSize = 26.sp
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "FeedSense",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Smart Feed & Silage Testing",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                Text(
                    text = language.displayName,
                    style = MaterialTheme.typography.labelMedium
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = language.greeting,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = language.homeQuestion,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = language.quickActions,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                DashboardCard(
                    emoji = "🌾",
                    title = language.testFeed,
                    subtitle = language.feedSubtitle,
                    modifier = Modifier.weight(1f),
                    onClick = onTest
                )

                DashboardCard(
                    emoji = "🌱",
                    title = language.testSilage,
                    subtitle = language.silageSubtitle,
                    modifier = Modifier.weight(1f),
                    onClick = onTest
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                DashboardCard(
                    emoji = "📡",
                    title = language.hardware,
                    subtitle = language.hardwareSubtitle,
                    modifier = Modifier.weight(1f),
                    onClick = onHardware
                )

                DashboardCard(
                    emoji = "▦",
                    title = language.qrVerify,
                    subtitle = language.qrSubtitle,
                    modifier = Modifier.weight(1f),
                    onClick = onQr
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                DashboardCard(
                    emoji = "📋",
                    title = language.history,
                    subtitle = language.historySubtitle,
                    modifier = Modifier.weight(1f),
                    onClick = onHistory
                )

                DashboardCard(
                    emoji = "📄",
                    title = language.reports,
                    subtitle = language.reportsSubtitle,
                    modifier = Modifier.weight(1f),
                    onClick = onReports
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "📴 ${language.offlineReady}",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = language.offlineDescription
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "☁️ ${language.cloudSyncDescription}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "📡 ${language.deviceStatus}",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = language.connectHardware,
                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = language.ready,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            OutlinedButton(
                onClick = onSettings,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("⚙️ ${language.settings}")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "AI/ML • IoT • NIR • Computer Vision • QR • Offline",
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


/* -------------------------------------------------------
   DASHBOARD CARD
------------------------------------------------------- */

@Composable
fun DashboardCard(
    emoji: String,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .height(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = emoji,
                fontSize = 32.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}


/* -------------------------------------------------------
   TEST SELECTION
------------------------------------------------------- */

@Composable
fun TestSelectionScreen(
    language: AppLanguage,
    onBack: () -> Unit,
    onFeed: () -> Unit,
    onSilage: () -> Unit
) {

    SimpleScreen(
        title = "🔬 ${language.testFeed} / ${language.testSilage}",
        onBack = onBack
    ) {

        Text(
            text = language.homeQuestion,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(18.dp))

        LargeChoiceCard(
            emoji = "🌾",
            title = language.testFeed,
            description = language.feedSubtitle,
            onClick = onFeed
        )

        Spacer(modifier = Modifier.height(12.dp))

        LargeChoiceCard(
            emoji = "🌱",
            title = language.testSilage,
            description = language.silageSubtitle,
            onClick = onSilage
        )
    }
}

/* -------------------------------------------------------
   SAMPLE SCREEN
------------------------------------------------------- */

@Composable
fun SampleScreen(
    language: AppLanguage,
    testType: String,
    onBack: () -> Unit,
    onAnalyze: () -> Unit
) {

    val context = LocalContext.current

    var previewBitmap by remember {
        mutableStateOf<Bitmap?>(null)
    }

    var galleryUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var imageSource by remember {
        mutableStateOf("")
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { bitmap ->

            if (bitmap != null) {
                previewBitmap = bitmap
                imageSource = "Camera"
            }
        }

    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->

            galleryUri = uri

            if (uri != null) {
                imageSource = "Gallery"
            }
        }

    LaunchedEffect(galleryUri) {

        galleryUri?.let { uri ->

            previewBitmap = withContext(Dispatchers.IO) {

                context.contentResolver
                    .openInputStream(uri)
                    ?.use { inputStream ->
                        BitmapFactory.decodeStream(inputStream)
                    }
            }
        }
    }

    SimpleScreen(
        title = if (testType == "Feed") {
            "🌾 ${language.feedTypeTitle}"
        } else {
            "🌱 ${language.silageTypeTitle}"
        },
        onBack = onBack,
        backText = language.back
    ) {

        Text(
            text = language.chooseTest,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        /*
         * CAMERA
         */

        LargeChoiceCard(
            emoji = "📷",
            title = language.visualInspection,
            description = language.visualInspectionDescription,
            onClick = {
                cameraLauncher.launch(null)
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        /*
         * GALLERY
         */

        LargeChoiceCard(
            emoji = "🖼️",
            title = "Choose Sample Image",
            description = "Select an existing feed or silage image.",
            onClick = {
                galleryLauncher.launch("image/*")
            }
        )

        /*
         * IMAGE PREVIEW
         */

        previewBitmap?.let { bitmap ->

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp)
            ) {

                Column(
                    modifier = Modifier.padding(12.dp)
                ) {

                    Text(
                        text = "✅ $imageSource sample",
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Selected sample image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(14.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Used only for visible/external inspection.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        /*
         * SPECTROMETER
         */

        LargeChoiceCard(
            emoji = "🔬",
            title = language.spectrometer,
            description = language.spectrometerDescription,
            onClick = {
                // Hardware connection will be added later.
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        /*
         * SENSORS
         */

        LargeChoiceCard(
            emoji = "📡",
            title = language.sensors,
            description = language.sensorsDescription,
            onClick = {
                // Sensor connection will be added later.
            }
        )

        Spacer(modifier = Modifier.height(18.dp))

        /*
         * SCIENTIFIC EXPLANATION
         */

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.secondaryContainer
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "⚠️ ${language.verifiedDataTitle}",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = language.cameraOnlyNote
                )

                Text(
                    text = language.spectrometerNote
                )

                Text(
                    text = language.sensorsNote
                )

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = language.verifiedDataDescription,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = onAnalyze,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(16.dp)
        ) {

            Text(
                text = "🤖 ${language.analyze}",
                fontSize = 17.sp
            )
        }
    }
}

@Composable
fun AnalysisProgressScreen(
    language: AppLanguage,
    testType: String,
    onComplete: () -> Unit
) {

    var currentStep by remember {
        mutableStateOf(0)
    }

    LaunchedEffect(Unit) {

        delay(800)
        currentStep = 1

        delay(1000)
        currentStep = 2

        delay(1000)
        currentStep = 3

        delay(1000)
        onComplete()
    }

    SimpleScreen(
        title = "🤖 ${language.analysisResults}",
        onBack = {
            onComplete()
        },
        backText = language.back
    ) {

        Text(
            text = if (testType == "Feed") {
                language.feedTypeTitle
            } else {
                language.silageTypeTitle
            },
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        AnalysisStepCard(
            emoji = "📷",
            title = language.visualInspection,
            active = currentStep >= 0,
            complete = currentStep >= 1
        )

        AnalysisStepCard(
            emoji = "🔬",
            title = language.spectrometer,
            active = currentStep >= 1,
            complete = currentStep >= 2
        )

        AnalysisStepCard(
            emoji = "📡",
            title = language.sensors,
            active = currentStep >= 2,
            complete = currentStep >= 3
        )

        AnalysisStepCard(
            emoji = "🤖",
            title = "AI / ML Processing",
            active = currentStep >= 3,
            complete = false
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Preparing verified result...",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text =
                "No nutritional value is generated until validated measurement data is available.",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun AnalysisStepCard(
    emoji: String,
    title: String,
    active: Boolean,
    complete: Boolean
) {

    val containerColor =
        if (active)
            MaterialTheme.colorScheme.primaryContainer
        else
            MaterialTheme.colorScheme.surfaceVariant

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = emoji,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = when {
                        complete -> "✅ Complete"
                        active -> "⏳ Processing..."
                        else -> "Waiting"
                    },
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


/* -------------------------------------------------------
   RESULTS
------------------------------------------------------- */

@Composable
fun ResultsScreen(
    language: AppLanguage,
    testType: String,
    onBackHome: () -> Unit,
    onQr: () -> Unit
) {

    SimpleScreen(
        title = "📊 ${language.analysisResults}",
        onBack = onBackHome,
        backText = language.back
    ) {

        Text(
            text = if (testType == "Feed") {
                language.feedTypeTitle
            } else {
                language.silageTypeTitle
            },
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = language.verifiedResult,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = language.awaitingMeasurement,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = language.connectHardware,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        PendingResultRow(
            label = language.protein,
            source = "🔬 NIR"
        )

        PendingResultRow(
            label = language.moisture,
            source = "🔬 NIR / 📡 Sensor"
        )

        PendingResultRow(
            label = language.fiber,
            source = "🔬 NIR"
        )

        PendingResultRow(
            label = language.energy,
            source = "🔬 NIR + Model"
        )

        PendingResultRow(
            label = language.mineralStatus,
            source = "🔬 NIR / Validated Model"
        )

        PendingResultRow(
            label = language.ureaRisk,
            source = "🔬 Spectral Model"
        )

        PendingResultRow(
            label = language.silicaRisk,
            source = "🔬 Spectral / Visual"
        )

        PendingResultRow(
            label = language.mycotoxinRisk,
            source = "Validated module required"
        )

        PendingResultRow(
            label = language.fungalRisk,
            source = "📷 Visual + Model"
        )

        if (testType == "Silage") {

            PendingResultRow(
                label = language.ph,
                source = "🧪 pH Sensor"
            )

            PendingResultRow(
                label = language.fermentation,
                source = "📡 Sensor + Model"
            )

            PendingResultRow(
                label = language.spoilage,
                source = "📷 + 📡"
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {

            Column(
                modifier = Modifier.padding(18.dp)
            ) {

                Text(
                    text = "💡 ${language.farmerAdvisory}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = language.advisoryPending
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedButton(
            onClick = onQr,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("▦ ${language.qrVerify}")
        }
    }
}

/* -------------------------------------------------------
   RESULT CARD
------------------------------------------------------- */

@Composable
fun ResultCard(
    title: String,
    value: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


/* -------------------------------------------------------
   HARDWARE
------------------------------------------------------- */

@Composable
fun HardwareScreen(
    language: AppLanguage,
    onBack: () -> Unit
) {

    SimpleScreen(
        title = "📡 ${language.hardware}",
        onBack = onBack,
        backText = language.back
    ) {

        DeviceCard(
            emoji = "🔬",
            title = language.spectrometer,
            status = language.notConnected
        )

        DeviceCard(
            emoji = "🌡️",
            title = "Temperature Sensor",
            status = language.notConnected
        )

        DeviceCard(
            emoji = "💧",
            title = "Moisture Sensor",
            status = language.notConnected
        )

        DeviceCard(
            emoji = "🧪",
            title = "pH Sensor",
            status = language.notConnected
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = language.connectHardware,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("🔄 ${language.scanDevices}")
        }
    }
}


/* -------------------------------------------------------
   QR SCREEN
------------------------------------------------------- */

@Composable
fun QrScreen(
    language: AppLanguage,
    onBack: () -> Unit
) {

    SimpleScreen(
        title = "▦ ${language.qrVerify}",
        onBack = onBack,
        backText = language.back
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp)
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "▦",
                    fontSize = 90.sp,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = language.noVerifiedReport,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = language.qrPendingDescription,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


/* -------------------------------------------------------
   HISTORY
------------------------------------------------------- */

@Composable
fun HistoryScreen(
    language: AppLanguage,
    onBack: () -> Unit
) {

    SimpleScreen(
        title = "📋 ${language.history}",
        onBack = onBack,
        backText = language.back
    ) {

        EmptyStateCard(
            emoji = "📋",
            title = language.noVerifiedTests,
            description = language.noVerifiedTestsDescription
        )
    }
}

/* -------------------------------------------------------
   REPORTS
------------------------------------------------------- */

@Composable
fun ReportsScreen(
    language: AppLanguage,
    onBack: () -> Unit
) {

    SimpleScreen(
        title = "📄 ${language.reports}",
        onBack = onBack,
        backText = language.back
    ) {

        EmptyStateCard(
            emoji = "📄",
            title = language.noVerifiedReports,
            description = language.noVerifiedReportsDescription
        )
    }
}


/* -------------------------------------------------------
   SETTINGS
------------------------------------------------------- */

@Composable
fun SettingsScreen(
    language: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit,
    onBack: () -> Unit
) {

    SimpleScreen(
        title = "⚙️ ${language.settings}",
        onBack = onBack,
        backText = language.back
    ) {

        Text(
            text = language.language,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        appLanguages.forEach { item ->

            LanguageButton(
                text = item.displayName,
                selected = item.code == language.code,
                onClick = {
                    onLanguageChange(item)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(18.dp))

        LargeChoiceCard(
            emoji = "📴",
            title = language.offlineStorage,
            description = language.offlineStorageDescription,
            onClick = { }
        )

        Spacer(modifier = Modifier.height(12.dp))

        LargeChoiceCard(
            emoji = "☁️",
            title = language.cloudSync,
            description = language.cloudSyncDescription,
            onClick = { }
        )
    }
}


/* -------------------------------------------------------
   GENERIC SCREENS
------------------------------------------------------- */

@Composable
fun SimpleScreen(
    title: String,
    onBack: () -> Unit,
    backText: String = "Back",
    content: @Composable () -> Unit
) {

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {

            Text(
                text = "← $backText",
                modifier = Modifier.clickable { onBack() },
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(22.dp))

            content()
        }
    }
}


/* -------------------------------------------------------
   LARGE CHOICE CARD
------------------------------------------------------- */

@Composable
fun LargeChoiceCard(
    emoji: String,
    title: String,
    description: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp)
    ) {

        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = emoji,
                fontSize = 34.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


/* -------------------------------------------------------
   DEVICE CARD
------------------------------------------------------- */

@Composable
fun DeviceCard(
    emoji: String,
    title: String,
    status: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = emoji,
                    fontSize = 28.sp
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = title,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = status,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}


/* -------------------------------------------------------
   HISTORY CARD
------------------------------------------------------- */

@Composable
fun HistoryCard(
    sampleId: String,
    type: String,
    score: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        shape = RoundedCornerShape(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = sampleId,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = type,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = score,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun PendingResultRow(
    label: String,
    source: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(15.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = label,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = source,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Text(
                text = "—",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
@Composable
fun EmptyStateCard(
    emoji: String,
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    ) {

        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = emoji,
                fontSize = 46.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}