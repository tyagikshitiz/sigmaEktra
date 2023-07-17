package com.trainee.appinventiv.sigmaekatra.ui

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.storage.FirebaseStorage
import com.trainee.appinventiv.sigmaekatra.MainActivity
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.databinding.FragmentImageUploadBinding
import com.trainee.appinventiv.sigmaekatra.picUpload
import com.trainee.appinventiv.sigmaekatra.util.Preference
import com.trainee.appinventiv.sigmaekatra.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_image_upload.*
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*


class ImageUploadFragment : Fragment() {
    private  var link:String? =null
    private  var viewModel: MainActivityViewModel?=null
    lateinit var imageUri: Uri
    private var firebaseUrl:String=""
    private lateinit var binding: FragmentImageUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {

        startActivity(Intent(Settings.ACTION_SETTINGS))
        return super.shouldShowRequestPermissionRationale(permission)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
binding= FragmentImageUploadBinding.inflate(inflater,container,false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_image_upload, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uploadImage()

        tv_details_next_upload_image.setOnClickListener {
//            api()
              findNavController().navigate(R.id.action_imageUploadFragment_to_createAccountWorkFragment)
        }
    }

    // self check if camera & gallery permission is granted
    // open camera and gallery
    private fun camera(){
        if (ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED)
        {
            openCamera()

        }else{

            requestPermissions(arrayOf(Manifest.permission.CAMERA),10)
            Toast.makeText(activity,"Permission Granted", Toast.LENGTH_LONG).show()
        }
    }

    private fun openCamera() {
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLaunch.launch(camera)
    }

    private fun gallery(){
        if (ContextCompat.checkSelfPermission(requireActivity(),android.Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
        {
            openGallery()

        }else{
             requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),12)
            Toast.makeText(activity,"Permission Granted", Toast.LENGTH_LONG).show()
        }
    }

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        resultLauncher.launch(gallery)
    }

    // set image in imageview from gallery
    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.e("pick","pick")
        if (result.resultCode == RESULT_OK) {
            Log.e("image","image")
            val data: Intent? = result.data
            imageUri = data!!.data!!
            binding.placeholder.visibility=View.INVISIBLE
            binding.ivUploadImage.setImageURI(imageUri)
            addImagetoFirebase()
        }
    }

    // set image in imageview from camera
    var resultLaunch = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        Log.e("pick","pick")
        if (result.resultCode == RESULT_OK) {
            Log.e("image","image")
            val data: Intent? = result.data
            val bmp: Bitmap =data?.extras?.get("data") as Bitmap
//            imageUri = data.data!!
            val a=getImageUri(bmp, Bitmap.CompressFormat.JPEG,25)
            imageUri=a!!
            binding.placeholder.visibility=View.INVISIBLE
            binding.ivUploadImage.setImageBitmap(bmp)
            addImagetoFirebase()
        }
    }


    // dialog for Select camera or gallery
    private fun uploadImage(){
        binding.tvTakePhoto.setOnClickListener {

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Select Image")
            builder.setMessage("Choose Your Option?")
            builder.setPositiveButton("Gallery"){ dialog, _ ->
                dialog.dismiss()
                gallery()
            }
            builder.setNegativeButton("Camera"){ dialog, _ ->
                dialog.dismiss()
                camera()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        requireActivity().onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode== 10){
            if (grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                openCamera()
                Toast.makeText(activity,"Camera Granted", Toast.LENGTH_LONG).show()
            }else{
                shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)

                Toast.makeText(activity,"Camera Denied", Toast.LENGTH_LONG).show()
            }
        }else {
            if (requestCode== 12){
                if (grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    openGallery()
                    Toast.makeText(activity,"Gallery Granted", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(activity,"Gallery Denied", Toast.LENGTH_LONG).show()
                }
            }

        }

    }

    fun getImageUri(src: Bitmap, format: Bitmap.CompressFormat, quality: Int): Uri? {
        val os = ByteArrayOutputStream()
        src.compress(format, quality, os)
        val path = MediaStore.Images.Media.insertImage(activity?.contentResolver, src, "title", null)
        return Uri.parse(path)
    }

    private fun addImagetoFirebase() {
/*        val progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Uploading...")
        progressDialog.setCancelable(false)
        progressDialog.show()*/

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(imageUri).
        addOnSuccessListener {
//            binding.ivUserimage.setImageURI(null)
//            Toast.makeText(requireContext(),"Successfully Uploaded", Toast.LENGTH_SHORT).show()
            Log.e("upload1", it.metadata.toString())
            Log.e("upload2", it.uploadSessionUri?.encodedPath.toString())
//            if (progressDialog.isShowing) progressDialog.dismiss()

            val modify = it.metadata!!.path
            link = modify.substring(7)

            firebaseUrl = "https://firebasestorage.googleapis.com/v0/b/sigmaektara.appspot.com/o/images%2F${link}?alt=media&token=e0d7723b-8630-4c4b-b36b-df75257147c7"
//            Log.e("link",it.metadata!!.path) // images/s_d_sd
            Log.e("link",link.toString())
            Log.e("link","https://firebasestorage.googleapis.com/v0/b/sigmaektara.appspot.com/o/images%2F${link}?alt=media&token=e0d7723b-8630-4c4b-b36b-df75257147c7")

//            val link = it.toString()
/*            val link = it.metadata!!.reference!!.downloadUrl.addOnCompleteListener{task ->
                imageUri = task.result!!
            }*/



        }.addOnFailureListener {
            Toast.makeText(requireContext(),"Not Uploaded", Toast.LENGTH_SHORT).show()
        }
    }
    fun api(){
        addImagetoFirebase()

        val picUpload=picUpload("https://firebasestorage.googleapis.com/v0/b/sigmaektara.appspot.com/o/images%2F${link}?alt=media&token=e0d7723b-8630-4c4b-b36b-df75257147c7")
        Log.e("imagelink",link.toString())
        val pref= Preference(requireActivity())
        val token = pref.getPreference().toString()
                viewModel?.picUpload(token, MainActivity.deviceId,picUpload)

        viewModel?.profilePicResponseLiveData?.observe(requireActivity(),androidx.lifecycle.Observer {
            if (it.body()?.statusCode==200){

//                    CoroutineScope(Dispatchers.Main).launch {
//                        delay(2000)
                lifecycleScope.launchWhenResumed {
                    findNavController().navigate(R.id.action_imageUploadFragment_to_createAccountWorkFragment)
//                    }
                }

            }
            else{
                Toast.makeText(requireActivity(), "Invalid Otp", Toast.LENGTH_SHORT).show()
            }
        })
    }


}