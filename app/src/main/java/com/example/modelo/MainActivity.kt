    package com.example.modelo


    import android.os.Build
    import android.os.Bundle

    import android.util.Log

    import androidx.appcompat.app.AppCompatActivity;
    import android.view.Menu
    import android.view.MenuItem
    import android.webkit.*

    import kotlinx.android.synthetic.main.activity_main.*
    import android.annotation.TargetApi
    import android.content.Intent

    import android.net.Uri


    class MainActivity : AppCompatActivity() {

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
               // setSupportActionBar(toolbar)

                webView.settings.setAppCachePath(applicationContext.cacheDir.absolutePath)
                webView.settings.javaScriptEnabled = true
                webView.settings.allowFileAccessFromFileURLs = true
                webView.settings.allowUniversalAccessFromFileURLs = true
                webView.settings.mediaPlaybackRequiresUserGesture = false
                webView.settings.domStorageEnabled = true
                webView.settings.setAppCacheEnabled(true)
               // webView.settings.setAppCachePath("image/*")

                webView.webChromeClient = object : WebChromeClient(){
                    override fun onPermissionRequest(request: PermissionRequest ) {
                      //  val permissions = ArrayList<String>()
                      //  permissions.add(Manifest.permission.CAMERA)

                        @TargetApi(Build.VERSION_CODES.M)
                         fun run() {
                            Log.d("Log2", request.origin.toString())
                            request.grant(request.resources)
                        }
                    }

                    override fun onShowFileChooser(
                        webView: WebView?,
                        filePathCallback: ValueCallback<Array<Uri>>?,
                        fileChooserParams: FileChooserParams?
                    ): Boolean {
                        val contentSelection = Intent(Intent.ACTION_GET_CONTENT)
                        contentSelection.addCategory(Intent.CATEGORY_OPENABLE)
                        contentSelection.type = "*/*"

                        val chooserIntent = Intent(Intent.ACTION_CHOOSER)
                        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelection)
                        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Choose your file")
                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(contentSelection))
                        startActivityForResult(chooserIntent, 100)
                        return true
                    }

                }

            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    // view?.loadUrl(url)
                    return false
                }



            }
                webView.loadUrl("https://redegiza.com.br/mobile.html")
                webView.clearCache(true)




    //            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    //                WebView.setWebContentsDebuggingEnabled(true)
    //            }

            }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            // Inflate the menu; this adds items to the action bar if it is present.
            menuInflater.inflate(R.menu.menu_main, menu)
            return true
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            return when (item.itemId) {
                R.id.action_settings -> true
                else -> super.onOptionsItemSelected(item)
            }
        }
    }
