[![](https://jitpack.io/v/kingideayou/zxingPro.svg)](https://jitpack.io/#kingideayou/zxingPro)

# ZXingPro
An extension for [zxing/zxing](https://github.com/zxing/zxing) & [journeyapps/zxing-android-embedded](https://github.com/journeyapps/zxing-android-embedded) convert barcode image to text  

轻量级识别图片二维码插件，支持 [zxing/zxing](https://github.com/zxing/zxing) 和 [journeyapps/zxing-android-embedded](https://github.com/journeyapps/zxing-android-embedded)

## Sample
![](http://ww1.sinaimg.cn/mw690/6db4aff6ly1fx8sy8hut7g20d80lz1dx.gif)

## Download
Gradle:

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

dependencies {
    implementation 'com.github.kingideayou:zxingPro:0.0.1'
}
```

## ProGuard
If you use [zxing/zxing](https://github.com/zxing/zxing) , add rules as zxing's README says.  
And add extra rule:
```pro
-dontwarn com.journeyapps:zxing-android-embedded.**
```

If you use [journeyapps/zxing-android-embedded](https://github.com/journeyapps/zxing-android-embedded), add rules as zxing-android-embedded's README says.  
And add extra rule:
```pro
-dontwarn com.google.zxing:core.**
```
**Attention**: The above progurad rules are correct.

## Simple usage snippet

```kotlin
val decoder = BitmapDecoder(this@MainActivity)
val result:String = decoder.getRawResult(img)
// result is barcode text
```

## Thanks
This library is inspired by [open-android/Zxing](https://github.com/open-android/Zxing) and uses some of its source code.  
Readme file is inspired by [zhihu/Matisse](https://github.com/zhihu/Matisse).

## License

    Copyright 2018 NeXT.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
