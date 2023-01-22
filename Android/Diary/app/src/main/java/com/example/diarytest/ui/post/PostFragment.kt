package com.example.diarytest.ui.post

import android.app.Instrumentation
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.diarytest.R
import com.example.diarytest.databinding.FragmentPostBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.type.DateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.jar.Manifest

class PostFragment : Fragment() {
    private var _binding: FragmentPostBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var image: ActivityResult

    var requestGalleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult())
    {
        image = it
        try {
            // inSampleSize 비율 계산, 지정
            val calRatio = calculateInSampleSize(it!!.data!!.data!!,
                resources.getDimensionPixelSize(R.dimen.post_image),
                resources.getDimensionPixelSize(R.dimen.post_image))
            val option = BitmapFactory.Options()
            option.inSampleSize=calRatio
            // 이미지 로딩
            var inputStream = context?.contentResolver?.openInputStream(it!!.data!!.data!!)
            val bitmap = BitmapFactory.decodeStream(inputStream, null, option)
            inputStream!!.close()
            inputStream = null
            bitmap?. let {
                binding.postGalleryImageview.setImageBitmap(bitmap)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = Firebase.firestore
        val postColl = db.collection("Post")
        postColl.orderBy("createdAt")

        _binding!!.postGalleryImageview.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        _binding!!.postSavebutton.setOnClickListener {
            val title: String = _binding!!.postAddTitle.text.toString()
            val tag: String = _binding!!.postAddTag.text.toString()
            val text: String = _binding!!.postAddtext.text.toString()
            val image: Uri = image!!.data!!.data!!
            val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd||HH:mm:ss")).toString()
            var postdata = PostData(date, image.toString(), title, tag, text)
            db.collection("Post").document(date).set(postdata)
            binding.postAddTitle.text = null
            binding.postAddTag.text = null
            binding.postAddtext.text = null
            binding.postGalleryImageview.setImageURI(null)
        }
    }

    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = context?.contentResolver?.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        // inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2
            while (halfHeight / inSampleSize >= reqHeight &&
                halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}