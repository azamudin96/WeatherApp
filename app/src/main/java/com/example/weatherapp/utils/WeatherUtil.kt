package com.example.weatherapp.utils

import com.example.weatherapp.R

object WeatherUtil {

    fun getWeatherIcon(weatherCode : Int) : Int {
        return when(weatherCode) {
            1000 -> R.drawable.day_113
            1003 -> R.drawable.day_116
            1006 -> R.drawable.day_119
            1009 -> R.drawable.day_122
            1030 -> R.drawable.day_143
            1063 -> R.drawable.day_176
            1066 -> R.drawable.day_179
            1069 -> R.drawable.day_182
            1072 -> R.drawable.day_185
            1087 -> R.drawable.day_200
            1114 -> R.drawable.day_227
            1117 -> R.drawable.day_230
            1135 -> R.drawable.day_248
            1147 -> R.drawable.day_260
            1150 -> R.drawable.day_263
            1153 -> R.drawable.day_266
            1168 -> R.drawable.day_281
            1171 -> R.drawable.day_284
            1180 -> R.drawable.day_293
            1183 -> R.drawable.day_296
            1186 -> R.drawable.day_299
            1189 -> R.drawable.day_302
            1192 -> R.drawable.day_305
            1195 -> R.drawable.day_308
            1198 -> R.drawable.day_311
            1201 -> R.drawable.day_314
            1204 -> R.drawable.day_317
            1207 -> R.drawable.day_320
            1210 -> R.drawable.day_323
            1213 -> R.drawable.day_326
            1216 -> R.drawable.day_329
            1219 -> R.drawable.day_332
            1222 -> R.drawable.day_335
            1225 -> R.drawable.day_338
            1237 -> R.drawable.day_350
            1240 -> R.drawable.day_353
            1243 -> R.drawable.day_356
            1246 -> R.drawable.day_359
            1249 -> R.drawable.day_362
            1252 -> R.drawable.day_365
            1255 -> R.drawable.day_368
            1258 -> R.drawable.day_371
            1261 -> R.drawable.day_374
            1264 -> R.drawable.day_377
            1273 -> R.drawable.day_386
            1276 -> R.drawable.day_389
            1279 -> R.drawable.day_392
            1282 -> R.drawable.day_395
            else -> R.drawable.ic_launcher_background
        }
    }
}