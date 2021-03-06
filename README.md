# Wasambo News Technical Test App

Gradle + Android Studio + Robolectric + Espresso + JaCoCo

## Technologies used:
#### Build Tools:
|Name|Description|
|---|---|
| [Gradle](http://gradle.org/docs/current/release-notes) | Gradle build system |
| [Android Gradle Build Tools](http://tools.android.com/tech-docs/new-build-system) | Official Gradle Plugin |
| [Android SDK](http://developer.android.com/tools/revisions/platforms.html#5.1) | Official SDK |
| [Android SDK Build Tools](http://developer.android.com/tools/revisions/build-tools.html) | Official Build Tools |
| [Android Studio](http://tools.android.com/recent) or | Official IDE |
| [Intellij](https://www.jetbrains.com/idea/download/) | Intellij IDE |

#### Android Libraries:
|Name|Description|
|---|---|
| [Android Support-v4 ](http://developer.android.com/tools/support-library/features.html#v4) | Support Library API 4+|
| [Android AppCompat-v7](http://developer.android.com/tools/support-library/features.html#v7-appcompat) | Support Library API 7+|

#### Testing Frameworks:
|Name|Description|
|---|---|
| [Espresso](https://google.github.io/android-testing-support-library/) | Instrumentation Framework |

#### Reporting Plugins:
|Name|Description|
|---|---|
| [JaCoCo](http://www.eclemma.org/jacoco/) | JaCoCo Test Coverage |

# Getting Started:

## `Android Studio` or `Intellij` Support(Simple):
 - **Import/Open this project with Android Studio/Intellij(click on `build.gradle`)**

 - **Instrumentation Tests:**
  - Change the Build Variant Test Artifact to `Instrumentation Tests`
  - Right click an instrumentation test located in `src/main/androidTest` and click test

 - **Unit Tests:**
  - Change the Build Variant Test Artifact to `Unit Tests`
  - Right click a unit test located in `src/main/test` and click test

## Building and Running


This project builds with [Gradle](www.gradle.org) and the Android Build [tools](http://tools.android.com/tech-docs/new-build-system).


**Build the APK:**

    $ ./gradlew assembleDebug

**Install the APK:**

    $ ./gradlew installDebug

**Run the App:**

    $ ./gradlew runDebug

## Testing


**Running the Unit Tests:**


The [Junit](http://junit.org/junit4/) tests run on the JVM, no need for emulators or real devices.


    $ ./gradlew testDebug

    
**Running the Instrumentation Tests:**


The [Espresso](https://developer.android.com/training/testing/ui-testing/espresso-testing.html) instrumentation tests run on the device.

    $ ./gradlew connectedDebugAndroidTest
    

## Reports


**Generate Lint Reports:**


The [Lint](http://developer.android.com/tools/help/lint.html) plugin generates reports based off the source code.


    $ ./gradlew lintDebug


**Generate Jacoco Test Coverage:**


The [Jacoco](http://www.eclemma.org/jacoco/) plugin generates coverage reports based off the unit tests.


    $ ./gradlew jacocoDebugReport