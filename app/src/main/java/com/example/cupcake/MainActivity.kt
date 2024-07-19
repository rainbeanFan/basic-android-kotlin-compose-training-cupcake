/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cupcake.ui.theme.CupcakeTheme

class MainActivity : ComponentActivity() {

    private val mShortcutManager by lazy { getSystemService(ShortcutManager::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeTheme {
                CupcakeApp()

                initShortcuts()

            }
        }
    }

    private fun initShortcuts() {
        mShortcutManager?.let { shortcutManager ->
            val shortcutList = mutableListOf<ShortcutInfo>()
            val intent = if (false) Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.baidu.com")
            ) else Intent(Intent.ACTION_MAIN, null, this, MainActivity::class.java)
            shortcutList.add(
                ShortcutInfo.Builder(this, "shortcut_id_2")
                    .setShortLabel(getString(R.string.shortcut_label))
                    .setLongLabel(getString(R.string.shortcut_long_label))
                    .setIcon(Icon.createWithResource(this, R.drawable.cupcake))
                    .setIntent(intent)
                    .build()
            )
            shortcutList.add(
                ShortcutInfo.Builder(this, "shortcut_id_1")
                    .setShortLabel(getString(R.string.shortcut_label))
                    .setLongLabel(getString(R.string.shortcut_long_label))
                    .setIcon(Icon.createWithResource(this, R.drawable.cupcake))
                    .setIntent(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com")))
                    .build()
            )

            shortcutManager.setDynamicShortcuts(shortcutList)
        }
    }

}
