AGAHI STUDIO 4 DEV — ANDROID PROTOTYPE 0.1.0
============================================

This is the first Android Studio project scaffold, not a completed video editor
and not yet a production APK.

CURRENTLY INCLUDED
- Native Android/Jetpack Compose project.
- Three mode selectors: Headlines, Breaking News, Vlog.
- Vlog background and original news-studio preview images.
- Editable headline/title and Urdu narration script fields.
- Android document picker for selecting narration audio.
- 720x1280 portrait output target in the UI.
- Android 8+ minimum compatibility; intended test device Android 13.

NOT YET IMPLEMENTED
- FFmpeg/MediaCodec video-render pipeline.
- Full animation and overlay compositor.
- Urdu Nastaleeq shaping in rendered video frames.
- Local Piper/Kaani speech synthesis port.
- Music ducking and synchronized caption timing.
- MP4 export.
The Generate Video control is intentionally disabled until those components
are ported and tested. This avoids presenting a non-working button as complete.

SOURCE AUDIT NOTES
- The supplied 177 MB Windows archive contains a Piper Fasih model, music,
  anchor image, server and an earlier video_engine.py.
- A newer video_engine.py was uploaded separately and must be treated as the
  current rendering reference.
- The Windows renderer uses Pillow + FFmpeg and expects a Nastaleeq font.
- The current archive's README describes Kaani as an optional separately
  prepared model; do not assume it is ready to redistribute in an APK.
- The existing app's voice settings can include online Edge/Azure voices;
  a truly standalone build must either port a local engine or explicitly mark
  online voice options as internet-dependent.

BUILD
1. Open this folder in Android Studio (Ladybug or newer).
2. Install Android SDK Platform 35 and JDK 17.
3. Allow Gradle sync.
4. Connect an Android 13 phone with USB debugging, or create an emulator.
5. Run the app configuration.

No Windows project files are modified by this Android project.
