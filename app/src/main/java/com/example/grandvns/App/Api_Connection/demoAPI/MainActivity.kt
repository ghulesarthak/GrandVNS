//package net.genericapps.imageuploader
//
//
////
////
////import retrofit2.Call
////import retrofit2.Callback
////import retrofit2.Response
//import android.net.Uri
//import android.os.Bundle
//import android.view.WindowInsetsAnimation
//import androidx.appcompat.app.AppCompatActivity
//import com.example.grandvns.R
//import okhttp3.Call
//import okhttp3.MediaType
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MultipartBody
//import okhttp3.RequestBody
//import okhttp3.Response
//import java.io.File
//import java.io.FileInputStream
//import java.io.FileOutputStream
//import java.text.SimpleDateFormat
//import java.util.Date
//class MainActivity : AppCompatActivity(), UploadRequestBody.UploadCallback {
//
//    private var selectedImageUri: Uri? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        image_view.setOnClickListener {
//            opeinImageChooser()
//        }
//
//        button_upload.setOnClickListener {
//            uploadImage()
//        }
//    }
//
//
//
//    private fun uploadImage() {
//      //  var context= LocalContext.current
//        if (selectedImageUri == null) {
//            layout_root.snackbar("Select an Image First")
//            return
//        }
//
//        val parcelFileDescriptor = contentResolver.openFileDescriptor(
//            selectedImageUri!!, "r", null
//        ) ?: return
//
//        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
//        val file = File(cacheDir,contentResolver.getFileName(selectedImageUri!!))
//        val outputStream = FileOutputStream(file)
//        inputStream.copyTo(outputStream)
//        //progress_bar.progress = 0
//
//        val body = UploadRequestBody(file,"image",this)
//
//        MyApi().uploadImage(
//            MultipartBody.Part.createFormData(
//            "image",
//            file.name,
//            body
//        ),
//            RequestBody.create("multipart/form-data".toMediaTypeOrNull(),"json")
//        ).enqueue(object : WindowInsetsAnimation.Callback<imagegetaipItem> {
//            override fun onResponse(
//                call: Call<imagegetaipItem>,
//                response: Response<imagegetaipItem>
//            ) {
//                response.body()?.let {
//                    layout_root.snackbar(it.img)
//                    progress_bar.progress = 100
//                }
//            }
//
//            override fun onFailure(call: Call<imagegetaipItem>, t: Throwable) {
//                layout_root.snackbar(t.message!!)
//                progress_bar.progress = 0
//
//
//                //Toast.makeText(context, "Network failure: ${t.message}", Toast.LENGTH_SHORT).show()
//                Log.e("API_FAILURE", "Failure: ${t.message}", t)
//            }
//
//        })
//
//
//    }
//
//    private fun opeinImageChooser() {
//
//        Intent(Intent.ACTION_PICK).also {
//            it.type = "image/*"
//            val mimeTypes = arrayOf("image/jpeg", "image/png")
//            it.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
//            startActivityForResult(it, REQUEST_CODE_IMAGE)
//        }
//    }
//
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK) {
//            when (requestCode) {
//                REQUEST_CODE_IMAGE -> {
//                    selectedImageUri = data?.data
//                    image_view.setImageURI(selectedImageUri)
//                }
//            }
//        }
//    }
//
//    companion object {
//        const val REQUEST_CODE_IMAGE = 101
//    }
//
//    override fun onProgressUpdate(percentage: Int) {
//       progress_bar.progress = percentage
//    }
//}
//
//
//private fun ContentResolver.getFileName(selectedImageUri: Uri): String {
//    var name = ""
//    val returnCursor = this.query(selectedImageUri,null,null,null,null)
//    if (returnCursor!=null){
//        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//        returnCursor.moveToFirst()
//        name = returnCursor.getString(nameIndex)
//        returnCursor.close()
//    }
//
//    return name
//}
//
//private fun View.snackbar(message: String) {
//    Snackbar.make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
//        snackbar.setAction("OK") {
//            snackbar.dismiss()
//        }
//    }.show()
//
//}
