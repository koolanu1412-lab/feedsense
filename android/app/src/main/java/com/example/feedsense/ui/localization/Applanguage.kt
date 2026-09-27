package com.example.feedsense.ui.localization

data class AppLanguage(
    val code: String,
    val displayName: String,

    val chooseLanguage: String,
    val continueText: String,

    val greeting: String,
    val homeQuestion: String,
    val quickActions: String,

    val testFeed: String,
    val testSilage: String,
    val feedSubtitle: String,
    val silageSubtitle: String,

    val hardware: String,
    val hardwareSubtitle: String,

    val qrVerify: String,
    val qrSubtitle: String,

    val history: String,
    val historySubtitle: String,

    val reports: String,
    val reportsSubtitle: String,

    val advisory: String,
    val settings: String,

    val newTest: String,
    val chooseTest: String,

    val visualInspection: String,
    val visualInspectionDescription: String,

    val spectrometer: String,
    val spectrometerDescription: String,

    val sensors: String,
    val sensorsDescription: String,

    val analyze: String,

    val cameraOnlyNote: String,
    val spectrometerNote: String,
    val sensorsNote: String,

    val verifiedDataTitle: String,
    val verifiedDataDescription: String,

    val analysisResults: String,
    val verifiedResult: String,
    val awaitingMeasurement: String,
    val connectHardware: String,

    val protein: String,
    val moisture: String,
    val fiber: String,
    val energy: String,

    val mineralStatus: String,
    val ureaRisk: String,
    val silicaRisk: String,
    val mycotoxinRisk: String,
    val fungalRisk: String,

    val ph: String,
    val fermentation: String,
    val spoilage: String,

    val farmerAdvisory: String,
    val advisoryPending: String,

    val offlineReady: String,
    val offlineDescription: String,
    val cloudSyncDescription: String,

    val deviceStatus: String,
    val ready: String,

    val scanDevices: String,
    val notConnected: String,

    val sampleReport: String,
    val noVerifiedReport: String,
    val qrPendingDescription: String,
    val viewReport: String,

    val noVerifiedTests: String,
    val noVerifiedTestsDescription: String,

    val noVerifiedReports: String,
    val noVerifiedReportsDescription: String,

    val language: String,
    val offlineStorage: String,
    val offlineStorageDescription: String,
    val cloudSync: String,
    val back: String,

    val feedTypeTitle: String,
    val silageTypeTitle: String
)

val appLanguages = listOf(

    AppLanguage(
        code = "en",
        displayName = "🇬🇧 English",

        chooseLanguage = "Choose your language",
        continueText = "Continue",

        greeting = "Namaste! 👋",
        homeQuestion = "What would you like to do today?",
        quickActions = "Quick Actions",

        testFeed = "Test Feed",
        testSilage = "Test Silage",
        feedSubtitle = "Nutrition & safety",
        silageSubtitle = "pH & spoilage",

        hardware = "Hardware",
        hardwareSubtitle = "NIR + IoT",

        qrVerify = "QR Verify",
        qrSubtitle = "Traceability",

        history = "History",
        historySubtitle = "Saved tests",

        reports = "Reports",
        reportsSubtitle = "PDF reports",

        advisory = "Farmer Advisory",
        settings = "Settings",

        newTest = "New Test",
        chooseTest = "Choose what you want to test",

        visualInspection = "Visual Inspection",
        visualInspectionDescription =
            "Checks visible dust, dirt, foreign particles and visible mould.",

        spectrometer = "NIR Spectrometer",
        spectrometerDescription =
            "Used for internal composition measurements such as protein, moisture, fibre and energy estimation.",

        sensors = "IoT Sensors",
        sensorsDescription =
            "Provides pH, moisture, temperature and other supported physical measurements.",

        analyze = "Analyze Sample",

        cameraOnlyNote =
            "Camera = visible inspection only",

        spectrometerNote =
            "Spectrometer = internal composition",

        sensorsNote =
            "Sensors = physical measurements",

        verifiedDataTitle = "Verified-data rule",
        verifiedDataDescription =
            "FeedSense will not invent nutritional values. Results appear only when validated hardware, sensor or backend data is available.",

        analysisResults = "Analysis Results",
        verifiedResult = "VERIFIED RESULT",
        awaitingMeasurement = "Awaiting measurement",
        connectHardware =
            "Connect the required hardware or backend source to receive validated values.",

        protein = "Crude Protein",
        moisture = "Moisture",
        fiber = "Fiber",
        energy = "Energy",

        mineralStatus = "Mineral Status",
        ureaRisk = "Urea Adulteration Risk",
        silicaRisk = "Sand / Silica Risk",
        mycotoxinRisk = "Mycotoxin Risk",
        fungalRisk = "Fungal Risk",

        ph = "pH",
        fermentation = "Fermentation Quality",
        spoilage = "Spoilage Risk",

        farmerAdvisory = "Farmer Advisory",
        advisoryPending =
            "Advisory will be generated after verified measurements are received.",

        offlineReady = "Offline Mode",
        offlineDescription =
            "Saved settings and locally stored information can remain available without internet.",
        cloudSyncDescription =
            "Cloud synchronization can occur when connectivity is available.",

        deviceStatus = "Device Status",
        ready = "READY",

        scanDevices = "Scan for Devices",
        notConnected = "Not Connected",

        sampleReport = "Sample Report",
        noVerifiedReport = "No verified report yet",
        qrPendingDescription =
            "A real QR code will be generated after a verified sample report is created.",
        viewReport = "View Report",

        noVerifiedTests = "No verified tests yet",
        noVerifiedTestsDescription =
            "Completed validated tests will appear here.",

        noVerifiedReports = "No verified reports yet",
        noVerifiedReportsDescription =
            "PDF reports will appear here after validated analysis.",

        language = "Language",
        offlineStorage = "Offline Storage",
        offlineStorageDescription =
            "Saved tests and reports will be stored locally on the device.",
        cloudSync = "Cloud Sync",
        back = "Back",

        feedTypeTitle = "Feed Testing",
        silageTypeTitle = "Silage Testing"
    ),

    AppLanguage(
        code = "hi",
        displayName = "🇮🇳 हिन्दी",

        chooseLanguage = "अपनी भाषा चुनें",
        continueText = "आगे बढ़ें",

        greeting = "नमस्ते! 👋",
        homeQuestion = "आज आप क्या करना चाहते हैं?",
        quickActions = "त्वरित विकल्प",

        testFeed = "चारा जाँचें",
        testSilage = "साइलेज जाँचें",
        feedSubtitle = "पोषण और सुरक्षा",
        silageSubtitle = "pH और खराब होने की जाँच",

        hardware = "हार्डवेयर",
        hardwareSubtitle = "NIR + IoT",

        qrVerify = "QR सत्यापन",
        qrSubtitle = "ट्रेसबिलिटी",

        history = "इतिहास",
        historySubtitle = "सहेजी गई जाँच",

        reports = "रिपोर्ट",
        reportsSubtitle = "PDF रिपोर्ट",

        advisory = "किसान सलाह",
        settings = "सेटिंग्स",

        newTest = "नई जाँच",
        chooseTest = "आप क्या जाँचना चाहते हैं?",

        visualInspection = "दृश्य निरीक्षण",
        visualInspectionDescription =
            "धूल, गंदगी, बाहरी कण और दिखाई देने वाली फफूंद की जाँच।",

        spectrometer = "NIR स्पेक्ट्रोमीटर",
        spectrometerDescription =
            "प्रोटीन, नमी, फाइबर और ऊर्जा जैसे आंतरिक गुणों के मापन के लिए।",

        sensors = "IoT सेंसर",
        sensorsDescription =
            "pH, नमी, तापमान और अन्य समर्थित भौतिक माप।",

        analyze = "नमूने का विश्लेषण करें",

        cameraOnlyNote = "कैमरा = केवल दृश्य निरीक्षण",
        spectrometerNote = "स्पेक्ट्रोमीटर = आंतरिक संरचना",
        sensorsNote = "सेंसर = भौतिक माप",

        verifiedDataTitle = "सत्यापित डेटा नियम",
        verifiedDataDescription =
            "FeedSense बिना सत्यापित डेटा के पोषण संबंधी मान नहीं बनाएगा।",

        analysisResults = "विश्लेषण परिणाम",
        verifiedResult = "सत्यापित परिणाम",
        awaitingMeasurement = "माप की प्रतीक्षा",
        connectHardware =
            "सत्यापित मान प्राप्त करने के लिए हार्डवेयर या बैकएंड कनेक्ट करें।",

        protein = "कच्चा प्रोटीन",
        moisture = "नमी",
        fiber = "फाइबर",
        energy = "ऊर्जा",

        mineralStatus = "खनिज स्थिति",
        ureaRisk = "यूरिया मिलावट जोखिम",
        silicaRisk = "रेत / सिलिका जोखिम",
        mycotoxinRisk = "माइकोटॉक्सिन जोखिम",
        fungalRisk = "फफूंद जोखिम",

        ph = "pH",
        fermentation = "किण्वन गुणवत्ता",
        spoilage = "खराब होने का जोखिम",

        farmerAdvisory = "किसान सलाह",
        advisoryPending =
            "सत्यापित माप मिलने के बाद सलाह तैयार की जाएगी।",

        offlineReady = "ऑफलाइन मोड",
        offlineDescription =
            "सहेजी गई सेटिंग्स और स्थानीय जानकारी इंटरनेट के बिना उपलब्ध रह सकती है।",
        cloudSyncDescription =
            "इंटरनेट उपलब्ध होने पर क्लाउड सिंक किया जा सकता है।",

        deviceStatus = "डिवाइस स्थिति",
        ready = "तैयार",

        scanDevices = "डिवाइस खोजें",
        notConnected = "कनेक्ट नहीं है",

        sampleReport = "नमूना रिपोर्ट",
        noVerifiedReport = "अभी कोई सत्यापित रिपोर्ट नहीं",
        qrPendingDescription =
            "सत्यापित रिपोर्ट बनने के बाद वास्तविक QR कोड बनाया जाएगा।",
        viewReport = "रिपोर्ट देखें",

        noVerifiedTests = "अभी कोई सत्यापित जाँच नहीं",
        noVerifiedTestsDescription =
            "सत्यापित जाँच यहाँ दिखाई देंगी।",

        noVerifiedReports = "अभी कोई सत्यापित रिपोर्ट नहीं",
        noVerifiedReportsDescription =
            "सत्यापित विश्लेषण के बाद PDF रिपोर्ट यहाँ दिखाई देंगी।",

        language = "भाषा",
        offlineStorage = "ऑफलाइन स्टोरेज",
        offlineStorageDescription =
            "सहेजी गई जाँच और रिपोर्ट डिवाइस पर स्थानीय रूप से रखी जाएंगी।",
        cloudSync = "क्लाउड सिंक",
        back = "वापस",

        feedTypeTitle = "चारा जाँच",
        silageTypeTitle = "साइलेज जाँच"
    ),

    AppLanguage(
        code = "mr",
        displayName = "🇮🇳 मराठी",

        chooseLanguage = "तुमची भाषा निवडा",
        continueText = "पुढे चला",

        greeting = "नमस्कार! 👋",
        homeQuestion = "आज तुम्हाला काय करायचे आहे?",
        quickActions = "जलद पर्याय",

        testFeed = "चारा तपासा",
        testSilage = "सायलेज तपासा",
        feedSubtitle = "पोषण आणि सुरक्षितता",
        silageSubtitle = "pH आणि खराब होण्याची तपासणी",

        hardware = "हार्डवेअर",
        hardwareSubtitle = "NIR + IoT",

        qrVerify = "QR पडताळणी",
        qrSubtitle = "ट्रेसिबिलिटी",

        history = "इतिहास",
        historySubtitle = "साठवलेल्या चाचण्या",

        reports = "अहवाल",
        reportsSubtitle = "PDF अहवाल",

        advisory = "शेतकरी सल्ला",
        settings = "सेटिंग्ज",

        newTest = "नवीन चाचणी",
        chooseTest = "तुम्हाला काय तपासायचे आहे?",

        visualInspection = "दृश्य तपासणी",
        visualInspectionDescription =
            "धूळ, घाण, बाहेरील कण आणि दिसणारी बुरशी तपासते.",

        spectrometer = "NIR स्पेक्ट्रोमीटर",
        spectrometerDescription =
            "प्रथिने, ओलावा, फायबर आणि ऊर्जेसारख्या अंतर्गत गुणधर्मांसाठी.",

        sensors = "IoT सेन्सर",
        sensorsDescription =
            "pH, ओलावा, तापमान आणि इतर समर्थित भौतिक मोजमाप.",

        analyze = "नमुन्याचे विश्लेषण करा",

        cameraOnlyNote = "कॅमेरा = फक्त दृश्य तपासणी",
        spectrometerNote = "स्पेक्ट्रोमीटर = अंतर्गत रचना",
        sensorsNote = "सेन्सर = भौतिक मोजमाप",

        verifiedDataTitle = "सत्यापित डेटा नियम",
        verifiedDataDescription =
            "सत्यापित डेटा नसताना FeedSense पोषणमूल्ये तयार करणार नाही.",

        analysisResults = "विश्लेषण परिणाम",
        verifiedResult = "सत्यापित परिणाम",
        awaitingMeasurement = "मोजमापाची प्रतीक्षा",
        connectHardware =
            "सत्यापित मूल्यांसाठी हार्डवेअर किंवा बॅकएंड कनेक्ट करा.",

        protein = "क्रूड प्रोटीन",
        moisture = "ओलावा",
        fiber = "फायबर",
        energy = "ऊर्जा",

        mineralStatus = "खनिज स्थिती",
        ureaRisk = "युरिया भेसळ धोका",
        silicaRisk = "वाळू / सिलिका धोका",
        mycotoxinRisk = "मायकोटॉक्सिन धोका",
        fungalRisk = "बुरशीचा धोका",

        ph = "pH",
        fermentation = "फर्मेंटेशन गुणवत्ता",
        spoilage = "खराब होण्याचा धोका",

        farmerAdvisory = "शेतकरी सल्ला",
        advisoryPending =
            "सत्यापित मोजमाप मिळाल्यानंतर सल्ला तयार केला जाईल.",

        offlineReady = "ऑफलाइन मोड",
        offlineDescription =
            "साठवलेल्या सेटिंग्ज आणि स्थानिक माहिती इंटरनेटशिवाय उपलब्ध राहू शकते.",
        cloudSyncDescription =
            "इंटरनेट उपलब्ध झाल्यावर क्लाउड सिंक केले जाऊ शकते.",

        deviceStatus = "डिव्हाइस स्थिती",
        ready = "तयार",

        scanDevices = "डिव्हाइस शोधा",
        notConnected = "कनेक्ट केलेले नाही",

        sampleReport = "नमुना अहवाल",
        noVerifiedReport = "अजून सत्यापित अहवाल नाही",
        qrPendingDescription =
            "सत्यापित नमुना अहवाल तयार झाल्यानंतर वास्तविक QR कोड तयार केला जाईल.",
        viewReport = "अहवाल पहा",

        noVerifiedTests = "अजून सत्यापित चाचण्या नाहीत",
        noVerifiedTestsDescription =
            "पूर्ण झालेल्या सत्यापित चाचण्या येथे दिसतील.",

        noVerifiedReports = "अजून सत्यापित अहवाल नाहीत",
        noVerifiedReportsDescription =
            "सत्यापित विश्लेषणानंतर PDF अहवाल येथे दिसतील.",

        language = "भाषा",
        offlineStorage = "ऑफलाइन स्टोरेज",
        offlineStorageDescription =
            "साठवलेल्या चाचण्या आणि अहवाल डिव्हाइसवर जतन केले जातील.",
        cloudSync = "क्लाउड सिंक",
        back = "मागे",

        feedTypeTitle = "चारा तपासणी",
        silageTypeTitle = "सायलेज तपासणी"
    ),

    AppLanguage(
        code = "ta",
        displayName = "🇮🇳 தமிழ்",

        chooseLanguage = "உங்கள் மொழியைத் தேர்ந்தெடுக்கவும்",
        continueText = "தொடரவும்",

        greeting = "வணக்கம்! 👋",
        homeQuestion = "இன்று நீங்கள் என்ன செய்ய விரும்புகிறீர்கள்?",
        quickActions = "விரைவு விருப்பங்கள்",

        testFeed = "தீவனத்தை சோதிக்கவும்",
        testSilage = "சைலேஜை சோதிக்கவும்",
        feedSubtitle = "ஊட்டச்சத்து மற்றும் பாதுகாப்பு",
        silageSubtitle = "pH மற்றும் கெடுதல்",

        hardware = "வன்பொருள்",
        hardwareSubtitle = "NIR + IoT",

        qrVerify = "QR சரிபார்ப்பு",
        qrSubtitle = "தடமறிதல்",

        history = "வரலாறு",
        historySubtitle = "சேமித்த சோதனைகள்",

        reports = "அறிக்கைகள்",
        reportsSubtitle = "PDF அறிக்கைகள்",

        advisory = "விவசாயி ஆலோசனை",
        settings = "அமைப்புகள்",

        newTest = "புதிய சோதனை",
        chooseTest = "நீங்கள் எதை சோதிக்க விரும்புகிறீர்கள்?",

        visualInspection = "காட்சி ஆய்வு",
        visualInspectionDescription =
            "தூசி, அழுக்கு, வெளிப்புற துகள்கள் மற்றும் தெரியும் பூஞ்சையைச் சரிபார்க்கும்.",

        spectrometer = "NIR ஸ்பெக்ட்ரோமீட்டர்",
        spectrometerDescription =
            "புரதம், ஈரப்பதம், நார்ச்சத்து மற்றும் ஆற்றல் போன்ற உள் பண்புகளுக்கு.",

        sensors = "IoT சென்சார்கள்",
        sensorsDescription =
            "pH, ஈரப்பதம், வெப்பநிலை மற்றும் பிற ஆதரிக்கப்படும் அளவீடுகள்.",

        analyze = "மாதிரியை பகுப்பாய்வு செய்யவும்",

        cameraOnlyNote = "கேமரா = காட்சி ஆய்வு மட்டும்",
        spectrometerNote = "ஸ்பெக்ட்ரோமீட்டர் = உள் அமைப்பு",
        sensorsNote = "சென்சார்கள் = உடல் அளவீடுகள்",

        verifiedDataTitle = "சரிபார்க்கப்பட்ட தரவு விதி",
        verifiedDataDescription =
            "சரிபார்க்கப்பட்ட தரவு இல்லாமல் FeedSense ஊட்டச்சத்து மதிப்புகளை உருவாக்காது.",

        analysisResults = "பகுப்பாய்வு முடிவுகள்",
        verifiedResult = "சரிபார்க்கப்பட்ட முடிவு",
        awaitingMeasurement = "அளவீட்டை எதிர்பார்க்கிறது",
        connectHardware =
            "சரிபார்க்கப்பட்ட மதிப்புகளுக்கு வன்பொருள் அல்லது backend-ஐ இணைக்கவும்.",

        protein = "கச்சா புரதம்",
        moisture = "ஈரப்பதம்",
        fiber = "நார்ச்சத்து",
        energy = "ஆற்றல்",

        mineralStatus = "கனிம நிலை",
        ureaRisk = "யூரியா கலப்பட ஆபத்து",
        silicaRisk = "மணல் / சிலிகா ஆபத்து",
        mycotoxinRisk = "மைக்கோடாக்சின் ஆபத்து",
        fungalRisk = "பூஞ்சை ஆபத்து",

        ph = "pH",
        fermentation = "நொதித்தல் தரம்",
        spoilage = "கெடுதல் ஆபத்து",

        farmerAdvisory = "விவசாயி ஆலோசனை",
        advisoryPending =
            "சரிபார்க்கப்பட்ட அளவீடுகள் கிடைத்த பிறகு ஆலோசனை உருவாக்கப்படும்.",

        offlineReady = "ஆஃப்லைன் முறை",
        offlineDescription =
            "சேமிக்கப்பட்ட அமைப்புகள் மற்றும் உள்ளூர் தகவல்களை இணையமின்றி பயன்படுத்தலாம்.",
        cloudSyncDescription =
            "இணையம் கிடைக்கும் போது கிளவுட் ஒத்திசைவு செய்யப்படும்.",

        deviceStatus = "சாதன நிலை",
        ready = "தயார்",

        scanDevices = "சாதனங்களைத் தேடவும்",
        notConnected = "இணைக்கப்படவில்லை",

        sampleReport = "மாதிரி அறிக்கை",
        noVerifiedReport = "சரிபார்க்கப்பட்ட அறிக்கை இல்லை",
        qrPendingDescription =
            "சரிபார்க்கப்பட்ட அறிக்கை உருவான பிறகு உண்மையான QR குறியீடு உருவாக்கப்படும்.",
        viewReport = "அறிக்கையைப் பார்க்கவும்",

        noVerifiedTests = "சரிபார்க்கப்பட்ட சோதனைகள் இல்லை",
        noVerifiedTestsDescription =
            "சரிபார்க்கப்பட்ட சோதனைகள் இங்கே தோன்றும்.",

        noVerifiedReports = "சரிபார்க்கப்பட்ட அறிக்கைகள் இல்லை",
        noVerifiedReportsDescription =
            "சரிபார்க்கப்பட்ட பகுப்பாய்வுக்குப் பிறகு PDF அறிக்கைகள் இங்கே தோன்றும்.",

        language = "மொழி",
        offlineStorage = "ஆஃப்லைன் சேமிப்பு",
        offlineStorageDescription =
            "சேமிக்கப்பட்ட சோதனைகள் மற்றும் அறிக்கைகள் சாதனத்தில் சேமிக்கப்படும்.",
        cloudSync = "கிளவுட் ஒத்திசைவு",
        back = "பின்செல்",

        feedTypeTitle = "தீனவன சோதனை",
        silageTypeTitle = "சைலேஜ் சோதனை"
    ),

    AppLanguage(
        code = "te",
        displayName = "🇮🇳 తెలుగు",

        chooseLanguage = "మీ భాషను ఎంచుకోండి",
        continueText = "కొనసాగించండి",

        greeting = "నమస్కారం! 👋",
        homeQuestion = "ఈరోజు మీరు ఏమి చేయాలనుకుంటున్నారు?",
        quickActions = "త్వరిత ఎంపికలు",

        testFeed = "మేతను పరీక్షించండి",
        testSilage = "సైలేజ్‌ను పరీక్షించండి",
        feedSubtitle = "పోషణ & భద్రత",
        silageSubtitle = "pH & చెడిపోవడం",

        hardware = "హార్డ్‌వేర్",
        hardwareSubtitle = "NIR + IoT",

        qrVerify = "QR ధృవీకరణ",
        qrSubtitle = "ట్రేసబిలిటీ",

        history = "చరిత్ర",
        historySubtitle = "సేవ్ చేసిన పరీక్షలు",

        reports = "నివేదికలు",
        reportsSubtitle = "PDF నివేదికలు",

        advisory = "రైతు సలహా",
        settings = "సెట్టింగ్స్",

        newTest = "కొత్త పరీక్ష",
        chooseTest = "మీరు ఏది పరీక్షించాలనుకుంటున్నారు?",

        visualInspection = "దృశ్య పరిశీలన",
        visualInspectionDescription =
            "దుమ్ము, మురికి, బాహ్య కణాలు మరియు కనిపించే పూజును పరిశీలిస్తుంది.",

        spectrometer = "NIR స్పెక్ట్రోమీటర్",
        spectrometerDescription =
            "ప్రోటీన్, తేమ, ఫైబర్ మరియు శక్తి వంటి అంతర్గత లక్షణాల కోసం.",

        sensors = "IoT సెన్సర్లు",
        sensorsDescription =
            "pH, తేమ, ఉష్ణోగ్రత మరియు ఇతర మద్దతు ఉన్న భౌతిక కొలతలు.",

        analyze = "నమూనాను విశ్లేషించండి",

        cameraOnlyNote = "కెమెరా = దృశ్య పరిశీలన మాత్రమే",
        spectrometerNote = "స్పెక్ట్రోమీటర్ = అంతర్గత నిర్మాణం",
        sensorsNote = "సెన్సర్లు = భౌతిక కొలతలు",

        verifiedDataTitle = "ధృవీకరించిన డేటా నియమం",
        verifiedDataDescription =
            "ధృవీకరించిన డేటా లేకుండా FeedSense పోషక విలువలను సృష్టించదు.",

        analysisResults = "విశ్లేషణ ఫలితాలు",
        verifiedResult = "ధృవీకరించిన ఫలితం",
        awaitingMeasurement = "కొలత కోసం వేచి ఉంది",
        connectHardware =
            "ధృవీకరించిన విలువల కోసం హార్డ్‌వేర్ లేదా backend ను కనెక్ట్ చేయండి.",

        protein = "క్రూడ్ ప్రోటీన్",
        moisture = "తేమ",
        fiber = "ఫైబర్",
        energy = "శక్తి",

        mineralStatus = "ఖనిజ స్థితి",
        ureaRisk = "యూరియా కల్తీ ప్రమాదం",
        silicaRisk = "ఇసుక / సిలికా ప్రమాదం",
        mycotoxinRisk = "మైకోటాక్సిన్ ప్రమాదం",
        fungalRisk = "ఫంగస్ ప్రమాదం",

        ph = "pH",
        fermentation = "ఫెర్మెంటేషన్ నాణ్యత",
        spoilage = "చెడిపోవడం ప్రమాదం",

        farmerAdvisory = "రైతు సలహా",
        advisoryPending =
            "ధృవీకరించిన కొలతలు వచ్చిన తర్వాత సలహా రూపొందించబడుతుంది.",

        offlineReady = "ఆఫ్‌లైన్ మోడ్",
        offlineDescription =
            "సేవ్ చేసిన సెట్టింగ్స్ మరియు స్థానిక సమాచారం ఇంటర్నెట్ లేకుండా అందుబాటులో ఉంటాయి.",
        cloudSyncDescription =
            "ఇంటర్నెట్ అందుబాటులో ఉన్నప్పుడు క్లౌడ్ సింక్ చేయబడుతుంది.",

        deviceStatus = "పరికరం స్థితి",
        ready = "సిద్ధంగా ఉంది",

        scanDevices = "పరికరాలను స్కాన్ చేయండి",
        notConnected = "కనెక్ట్ కాలేదు",

        sampleReport = "నమూనా నివేదిక",
        noVerifiedReport = "ధృవీకరించిన నివేదిక లేదు",
        qrPendingDescription =
            "ధృవీకరించిన నమూనా నివేదిక వచ్చిన తర్వాత నిజమైన QR కోడ్ రూపొందించబడుతుంది.",
        viewReport = "నివేదికను చూడండి",

        noVerifiedTests = "ధృవీకరించిన పరీక్షలు లేవు",
        noVerifiedTestsDescription =
            "ధృవీకరించిన పరీక్షలు ఇక్కడ కనిపిస్తాయి.",

        noVerifiedReports = "ధృవీకరించిన నివేదికలు లేవు",
        noVerifiedReportsDescription =
            "ధృవీకరించిన విశ్లేషణ తర్వాత PDF నివేదికలు ఇక్కడ కనిపిస్తాయి.",

        language = "భాష",
        offlineStorage = "ఆఫ్‌లైన్ నిల్వ",
        offlineStorageDescription =
            "సేవ్ చేసిన పరీక్షలు మరియు నివేదికలు పరికరంలో నిల్వ చేయబడతాయి.",
        cloudSync = "క్లౌడ్ సింక్",
        back = "వెనుకకు",

        feedTypeTitle = "మేత పరీక్ష",
        silageTypeTitle = "సైలేజ్ పరీక్ష"
    ),

    AppLanguage(
        code = "kn",
        displayName = "🇮🇳 ಕನ್ನಡ",

        chooseLanguage = "ನಿಮ್ಮ ಭಾಷೆಯನ್ನು ಆಯ್ಕೆಮಾಡಿ",
        continueText = "ಮುಂದುವರಿಸಿ",

        greeting = "ನಮಸ್ಕಾರ! 👋",
        homeQuestion = "ಇಂದು ನೀವು ಏನು ಮಾಡಲು ಬಯಸುತ್ತೀರಿ?",
        quickActions = "ತ್ವರಿತ ಆಯ್ಕೆಗಳು",

        testFeed = "ಆಹಾರ ಪರೀಕ್ಷಿಸಿ",
        testSilage = "ಸೈಲೇಜ್ ಪರೀಕ್ಷಿಸಿ",
        feedSubtitle = "ಪೋಷಣೆ ಮತ್ತು ಸುರಕ್ಷತೆ",
        silageSubtitle = "pH ಮತ್ತು ಹಾಳಾಗುವಿಕೆ",

        hardware = "ಹಾರ್ಡ್‌ವೇರ್",
        hardwareSubtitle = "NIR + IoT",

        qrVerify = "QR ಪರಿಶೀಲನೆ",
        qrSubtitle = "ಟ್ರೇಸಬಿಲಿಟಿ",

        history = "ಇತಿಹಾಸ",
        historySubtitle = "ಉಳಿಸಿದ ಪರೀಕ್ಷೆಗಳು",

        reports = "ವರದಿಗಳು",
        reportsSubtitle = "PDF ವರದಿಗಳು",

        advisory = "ರೈತ ಸಲಹೆ",
        settings = "ಸೆಟ್ಟಿಂಗ್‌ಗಳು",

        newTest = "ಹೊಸ ಪರೀಕ್ಷೆ",
        chooseTest = "ನೀವು ಯಾವುದನ್ನು ಪರೀಕ್ಷಿಸಲು ಬಯಸುತ್ತೀರಿ?",

        visualInspection = "ದೃಶ್ಯ ಪರಿಶೀಲನೆ",
        visualInspectionDescription =
            "ಧೂಳು, ಕೊಳಕು, ಹೊರಗಿನ ಕಣಗಳು ಮತ್ತು ಕಾಣುವ ಹುಳೆಯನ್ನು ಪರಿಶೀಲಿಸುತ್ತದೆ.",

        spectrometer = "NIR ಸ್ಪೆಕ್ಟ್ರೋಮೀಟರ್",
        spectrometerDescription =
            "ಪ್ರೋಟೀನ್, ತೇವಾಂಶ, ಫೈಬರ್ ಮತ್ತು ಶಕ್ತಿಯಂತಹ ಆಂತರಿಕ ಗುಣಲಕ್ಷಣಗಳಿಗಾಗಿ.",

        sensors = "IoT ಸೆನ್ಸರ್‌ಗಳು",
        sensorsDescription =
            "pH, ತೇವಾಂಶ, ತಾಪಮಾನ ಮತ್ತು ಇತರ ಬೆಂಬಲಿತ ಭೌತಿಕ ಅಳತೆಗಳು.",

        analyze = "ಮಾದರಿಯನ್ನು ವಿಶ್ಲೇಷಿಸಿ",

        cameraOnlyNote = "ಕ್ಯಾಮೆರಾ = ದೃಶ್ಯ ಪರಿಶೀಲನೆ ಮಾತ್ರ",
        spectrometerNote = "ಸ್ಪೆಕ್ಟ್ರೋಮೀಟರ್ = ಆಂತರಿಕ ಸಂಯೋಜನೆ",
        sensorsNote = "ಸೆನ್ಸರ್‌ಗಳು = ಭೌತಿಕ ಅಳತೆಗಳು",

        verifiedDataTitle = "ಪರಿಶೀಲಿತ ಡೇಟಾ ನಿಯಮ",
        verifiedDataDescription =
            "ಪರಿಶೀಲಿತ ಡೇಟಾ ಇಲ್ಲದೆ FeedSense ಪೌಷ್ಟಿಕ ಮೌಲ್ಯಗಳನ್ನು ಸೃಷ್ಟಿಸುವುದಿಲ್ಲ.",

        analysisResults = "ವಿಶ್ಲೇಷಣೆ ಫಲಿತಾಂಶಗಳು",
        verifiedResult = "ಪರಿಶೀಲಿತ ಫಲಿತಾಂಶ",
        awaitingMeasurement = "ಅಳತೆಯನ್ನು ನಿರೀಕ್ಷಿಸಲಾಗುತ್ತಿದೆ",
        connectHardware =
            "ಪರಿಶೀಲಿತ ಮೌಲ್ಯಗಳಿಗಾಗಿ ಹಾರ್ಡ್‌ವೇರ್ ಅಥವಾ backend ಅನ್ನು ಸಂಪರ್ಕಿಸಿ.",

        protein = "ಕ್ರೂಡ್ ಪ್ರೋಟೀನ್",
        moisture = "ತೇವಾಂಶ",
        fiber = "ಫೈಬರ್",
        energy = "ಶಕ್ತಿ",

        mineralStatus = "ಖನಿಜ ಸ್ಥಿತಿ",
        ureaRisk = "ಯೂರಿಯಾ ಕಲಬೆರಕೆ ಅಪಾಯ",
        silicaRisk = "ಮರಳು / ಸಿಲಿಕಾ ಅಪಾಯ",
        mycotoxinRisk = "ಮೈಕೋಟಾಕ್ಸಿನ್ ಅಪಾಯ",
        fungalRisk = "ಶಿಲೀಂಧ್ರ ಅಪಾಯ",

        ph = "pH",
        fermentation = "ಫರ್ಮೆಂಟೇಶನ್ ಗುಣಮಟ್ಟ",
        spoilage = "ಹಾಳಾಗುವಿಕೆ ಅಪಾಯ",

        farmerAdvisory = "ರೈತ ಸಲಹೆ",
        advisoryPending =
            "ಪರಿಶೀಲಿತ ಅಳತೆಗಳು ಬಂದ ನಂತರ ಸಲಹೆ ರಚಿಸಲಾಗುತ್ತದೆ.",

        offlineReady = "ಆಫ್‌ಲೈನ್ ಮೋಡ್",
        offlineDescription =
            "ಉಳಿಸಿದ ಸೆಟ್ಟಿಂಗ್‌ಗಳು ಮತ್ತು ಸ್ಥಳೀಯ ಮಾಹಿತಿಯನ್ನು ಇಂಟರ್ನೆಟ್ ಇಲ್ಲದೆ ಬಳಸಬಹುದು.",
        cloudSyncDescription =
            "ಇಂಟರ್ನೆಟ್ ಲಭ್ಯವಾದಾಗ ಕ್ಲೌಡ್ ಸಿಂಕ್ ಮಾಡಲಾಗುತ್ತದೆ.",

        deviceStatus = "ಸಾಧನ ಸ್ಥಿತಿ",
        ready = "ಸಿದ್ಧವಾಗಿದೆ",

        scanDevices = "ಸಾಧನಗಳನ್ನು ಸ್ಕ್ಯಾನ್ ಮಾಡಿ",
        notConnected = "ಸಂಪರ್ಕಗೊಂಡಿಲ್ಲ",

        sampleReport = "ಮಾದರಿ ವರದಿ",
        noVerifiedReport = "ಪರಿಶೀಲಿತ ವರದಿ ಇಲ್ಲ",
        qrPendingDescription =
            "ಪರಿಶೀಲಿತ ಮಾದರಿ ವರದಿ ಸಿದ್ಧವಾದ ನಂತರ ನಿಜವಾದ QR ಕೋಡ್ ರಚಿಸಲಾಗುತ್ತದೆ.",
        viewReport = "ವರದಿ ನೋಡಿ",

        noVerifiedTests = "ಪರಿಶೀಲಿತ ಪರೀಕ್ಷೆಗಳಿಲ್ಲ",
        noVerifiedTestsDescription =
            "ಪರಿಶೀಲಿತ ಪರೀಕ್ಷೆಗಳು ಇಲ್ಲಿ ಕಾಣಿಸುತ್ತವೆ.",

        noVerifiedReports = "ಪರಿಶೀಲಿತ ವರದಿಗಳಿಲ್ಲ",
        noVerifiedReportsDescription =
            "ಪರಿಶೀಲಿತ ವಿಶ್ಲೇಷಣೆಯ ನಂತರ PDF ವರದಿಗಳು ಇಲ್ಲಿ ಕಾಣಿಸುತ್ತವೆ.",

        language = "ಭಾಷೆ",
        offlineStorage = "ಆಫ್‌ಲೈನ್ ಸಂಗ್ರಹಣೆ",
        offlineStorageDescription =
            "ಉಳಿಸಿದ ಪರೀಕ್ಷೆಗಳು ಮತ್ತು ವರದಿಗಳನ್ನು ಸಾಧನದಲ್ಲಿ ಸಂಗ್ರಹಿಸಲಾಗುತ್ತದೆ.",
        cloudSync = "ಕ್ಲೌಡ್ ಸಿಂಕ್",
        back = "ಹಿಂದೆ",

        feedTypeTitle = "ಆಹಾರ ಪರೀಕ್ಷೆ",
        silageTypeTitle = "ಸೈಲೇಜ್ ಪರೀಕ್ಷೆ"
    ),

    AppLanguage(
        code = "ml",
        displayName = "🇮🇳 മലയാളം",

        chooseLanguage = "നിങ്ങളുടെ ഭാഷ തിരഞ്ഞെടുക്കുക",
        continueText = "തുടരുക",

        greeting = "നമസ്കാരം! 👋",
        homeQuestion = "ഇന്ന് നിങ്ങൾ എന്ത് ചെയ്യാൻ ആഗ്രഹിക്കുന്നു?",
        quickActions = "ദ്രുത ഓപ്ഷനുകൾ",

        testFeed = "തീറ്റ പരിശോധിക്കുക",
        testSilage = "സൈലേജ് പരിശോധിക്കുക",
        feedSubtitle = "പോഷണവും സുരക്ഷയും",
        silageSubtitle = "pH & കേടുപാട് പരിശോധന",

        hardware = "ഹാർഡ്‌വെയർ",
        hardwareSubtitle = "NIR + IoT",

        qrVerify = "QR പരിശോധന",
        qrSubtitle = "ട്രേസബിലിറ്റി",

        history = "ചരിത്രം",
        historySubtitle = "സംരക്ഷിച്ച പരിശോധനകൾ",

        reports = "റിപ്പോർട്ടുകൾ",
        reportsSubtitle = "PDF റിപ്പോർട്ടുകൾ",

        advisory = "കർഷക ഉപദേശം",
        settings = "ക്രമീകരണങ്ങൾ",

        newTest = "പുതിയ പരിശോധന",
        chooseTest = "നിങ്ങൾ എന്താണ് പരിശോധിക്കാൻ ആഗ്രഹിക്കുന്നത്?",

        visualInspection = "ദൃശ്യ പരിശോധന",
        visualInspectionDescription =
            "പൊടി, അഴുക്ക്, പുറം കണങ്ങൾ, ദൃശ്യമായ പൂപ്പൽ എന്നിവ പരിശോധിക്കുന്നു.",

        spectrometer = "NIR സ്പെക്ട്രോമീറ്റർ",
        spectrometerDescription =
            "പ്രോട്ടീൻ, ഈർപ്പം, ഫൈബർ, ഊർജ്ജം തുടങ്ങിയ ആന്തരിക ഗുണങ്ങൾക്കായി.",

        sensors = "IoT സെൻസറുകൾ",
        sensorsDescription =
            "pH, ഈർപ്പം, താപനില, മറ്റ് പിന്തുണയുള്ള ഭൗതിക അളവുകൾ.",

        analyze = "സാമ്പിൾ വിശകലനം ചെയ്യുക",

        cameraOnlyNote = "ക്യാമറ = ദൃശ്യ പരിശോധന മാത്രം",
        spectrometerNote = "സ്പെക്ട്രോമീറ്റർ = ആന്തരിക ഘടന",
        sensorsNote = "സെൻസറുകൾ = ഭൗതിക അളവുകൾ",

        verifiedDataTitle = "സ്ഥിരീകരിച്ച ഡാറ്റാ നിയമം",
        verifiedDataDescription =
            "സ്ഥിരീകരിച്ച ഡാറ്റ ഇല്ലാതെ FeedSense പോഷക മൂല്യങ്ങൾ സൃഷ്ടിക്കില്ല.",

        analysisResults = "വിശകലന ഫലങ്ങൾ",
        verifiedResult = "സ്ഥിരീകരിച്ച ഫലം",
        awaitingMeasurement = "അളവ് കാത്തിരിക്കുന്നു",
        connectHardware =
            "സ്ഥിരീകരിച്ച മൂല്യങ്ങൾക്ക് ഹാർഡ്‌വെയർ അല്ലെങ്കിൽ backend കണക്റ്റ് ചെയ്യുക.",

        protein = "ക്രൂഡ് പ്രോട്ടീൻ",
        moisture = "ഈർപ്പം",
        fiber = "ഫൈബർ",
        energy = "ഊർജ്ജം",

        mineralStatus = "ഖനിജ സ്ഥിതി",
        ureaRisk = "യൂറിയ കലർപ്പ് അപകടസാധ്യത",
        silicaRisk = "മണൽ / സിലിക്ക അപകടസാധ്യത",
        mycotoxinRisk = "മൈക്കോടോക്സിൻ അപകടസാധ്യത",
        fungalRisk = "ഫംഗസ് അപകടസാധ്യത",

        ph = "pH",
        fermentation = "ഫെർമെന്റേഷൻ ഗുണനിലവാരം",
        spoilage = "കേടുപാട് അപകടസാധ്യത",

        farmerAdvisory = "കർഷക ഉപദേശം",
        advisoryPending =
            "സ്ഥിരീകരിച്ച അളവുകൾ ലഭിച്ചതിന് ശേഷം ഉപദേശം സൃഷ്ടിക്കും.",

        offlineReady = "ഓഫ്‌ലൈൻ മോഡ്",
        offlineDescription =
            "സംരക്ഷിച്ച ക്രമീകരണങ്ങളും പ്രാദേശിക വിവരങ്ങളും ഇന്റർനെറ്റ് ഇല്ലാതെയും ഉപയോഗിക്കാം.",
        cloudSyncDescription =
            "ഇന്റർനെറ്റ് ലഭിക്കുമ്പോൾ ക്ലൗഡ് സിങ്ക് ചെയ്യും.",

        deviceStatus = "ഉപകരണ നില",
        ready = "തയ്യാർ",

        scanDevices = "ഉപകരണങ്ങൾ സ്കാൻ ചെയ്യുക",
        notConnected = "കണക്റ്റ് ചെയ്തിട്ടില്ല",

        sampleReport = "സാമ്പിൾ റിപ്പോർട്ട്",
        noVerifiedReport = "സ്ഥിരീകരിച്ച റിപ്പോർട്ട് ഇല്ല",
        qrPendingDescription =
            "സ്ഥിരീകരിച്ച സാമ്പിൾ റിപ്പോർട്ട് തയ്യാറായ ശേഷം യഥാർത്ഥ QR കോഡ് സൃഷ്ടിക്കും.",
        viewReport = "റിപ്പോർട്ട് കാണുക",

        noVerifiedTests = "സ്ഥിരീകരിച്ച പരിശോധനകൾ ഇല്ല",
        noVerifiedTestsDescription =
            "സ്ഥിരീകരിച്ച പരിശോധനകൾ ഇവിടെ കാണിക്കും.",

        noVerifiedReports = "സ്ഥിരീകരിച്ച റിപ്പോർട്ടുകൾ ഇല്ല",
        noVerifiedReportsDescription =
            "സ്ഥിരീകരിച്ച വിശകലനത്തിന് ശേഷം PDF റിപ്പോർട്ടുകൾ ഇവിടെ കാണിക്കും.",

        language = "ഭാഷ",
        offlineStorage = "ഓഫ്‌ലൈൻ സംഭരണം",
        offlineStorageDescription =
            "സംരക്ഷിച്ച പരിശോധനകളും റിപ്പോർട്ടുകളും ഉപകരണത്തിൽ സൂക്ഷിക്കും.",
        cloudSync = "ക്ലൗഡ് സിങ്ക്",
        back = "തിരികെ",

        feedTypeTitle = "തീറ്റ പരിശോധന",
        silageTypeTitle = "സൈലേജ് പരിശോധന"
    ),

    AppLanguage(
        code = "bho",
        displayName = "🟠 भोजपुरी",

        chooseLanguage = "अपना भाषा चुनीं",
        continueText = "आगे बढ़ीं",

        greeting = "नमस्कार! 👋",
        homeQuestion = "आज रउआ का करे के चाहत बानी?",
        quickActions = "जल्दी वाला विकल्प",

        testFeed = "चारा जाँचीं",
        testSilage = "साइलेज जाँचीं",
        feedSubtitle = "पोषण आ सुरक्षा",
        silageSubtitle = "pH आ खराब होखे के जाँच",

        hardware = "हार्डवेयर",
        hardwareSubtitle = "NIR + IoT",

        qrVerify = "QR जाँच",
        qrSubtitle = "ट्रेसबिलिटी",

        history = "इतिहास",
        historySubtitle = "सहेजल जाँच",

        reports = "रिपोर्ट",
        reportsSubtitle = "PDF रिपोर्ट",

        advisory = "किसान सलाह",
        settings = "सेटिंग्स",

        newTest = "नया जाँच",
        chooseTest = "रउआ का जाँचे के चाहत बानी?",

        visualInspection = "दृश्य जाँच",
        visualInspectionDescription =
            "धूल, गंदगी, बाहरी कण आ दिखाई देत फफूंदी जाँची.",

        spectrometer = "NIR स्पेक्ट्रोमीटर",
        spectrometerDescription =
            "प्रोटीन, नमी, फाइबर आ ऊर्जा जइसन अंदरूनी गुण खातिर.",

        sensors = "IoT सेंसर",
        sensorsDescription =
            "pH, नमी, तापमान आ बाकी समर्थित भौतिक माप खातिर.",

        analyze = "नमूना के विश्लेषण करीं",

        cameraOnlyNote = "कैमरा = खाली दृश्य जाँच",
        spectrometerNote = "स्पेक्ट्रोमीटर = अंदरूनी संरचना",
        sensorsNote = "सेंसर = भौतिक माप",

        verifiedDataTitle = "सत्यापित डेटा नियम",
        verifiedDataDescription =
            "सत्यापित डेटा बिना FeedSense पोषण मान ना बनाइ.",

        analysisResults = "विश्लेषण परिणाम",
        verifiedResult = "सत्यापित परिणाम",
        awaitingMeasurement = "माप के इंतजार",
        connectHardware =
            "सत्यापित मान खातिर हार्डवेयर या backend कनेक्ट करीं.",

        protein = "क्रूड प्रोटीन",
        moisture = "नमी",
        fiber = "फाइबर",
        energy = "ऊर्जा",

        mineralStatus = "खनिज स्थिति",
        ureaRisk = "यूरिया मिलावट खतरा",
        silicaRisk = "रेत / सिलिका खतरा",
        mycotoxinRisk = "माइकोटॉक्सिन खतरा",
        fungalRisk = "फफूंदी के खतरा",

        ph = "pH",
        fermentation = "फर्मेंटेशन गुणवत्ता",
        spoilage = "खराब होखे के खतरा",

        farmerAdvisory = "किसान सलाह",
        advisoryPending =
            "सत्यापित माप मिलला के बाद सलाह बनावल जाई.",

        offlineReady = "ऑफलाइन मोड",
        offlineDescription =
            "सहेजल सेटिंग आ स्थानीय जानकारी इंटरनेट बिना भी उपलब्ध रह सकेला.",
        cloudSyncDescription =
            "इंटरनेट मिलला पर क्लाउड सिंक होई.",

        deviceStatus = "डिवाइस स्थिति",
        ready = "तैयार",

        scanDevices = "डिवाइस खोजीं",
        notConnected = "कनेक्ट नइखे",

        sampleReport = "नमूना रिपोर्ट",
        noVerifiedReport = "अभी सत्यापित रिपोर्ट नइखे",
        qrPendingDescription =
            "सत्यापित रिपोर्ट बनला के बाद असली QR कोड बनावल जाई.",
        viewReport = "रिपोर्ट देखीं",

        noVerifiedTests = "अभी सत्यापित जाँच नइखे",
        noVerifiedTestsDescription =
            "सत्यापित जाँच इहाँ देखाई.",

        noVerifiedReports = "अभी सत्यापित रिपोर्ट नइखे",
        noVerifiedReportsDescription =
            "सत्यापित विश्लेषण के बाद PDF रिपोर्ट इहाँ देखाई.",

        language = "भाषा",
        offlineStorage = "ऑफलाइन स्टोरेज",
        offlineStorageDescription =
            "सहेजल जाँच आ रिपोर्ट डिवाइस पर रखल जाई.",
        cloudSync = "क्लाउड सिंक",
        back = "पीछे",

        feedTypeTitle = "चारा जाँच",
        silageTypeTitle = "साइलेज जाँच"
    )
)