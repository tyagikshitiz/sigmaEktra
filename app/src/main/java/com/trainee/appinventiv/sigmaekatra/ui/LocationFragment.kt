package com.trainee.appinventiv.sigmaekatra.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.trainee.appinventiv.sigmaekatra.R
import com.trainee.appinventiv.sigmaekatra.databinding.FragmentLocationBinding
import com.trainee.appinventiv.sigmaekatra.model.CurrentLocation
import kotlinx.android.synthetic.main.fragment_location.*
import java.io.IOException
import java.util.*

class LocationFragment : Fragment() , OnMapReadyCallback, LocationListener,
    GoogleMap.OnCameraMoveListener , GoogleMap.OnCameraMoveStartedListener ,
    GoogleMap.OnCameraIdleListener {
    private lateinit var addresses:List<Address>
    private var mmap: GoogleMap? = null
    private var location: Location? = null
    private var address: Address? = null
    private var currentLocation: Location? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val permissionCode = 101

    private val binding:FragmentLocationBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location , container , false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        var user=arguments?.getString("usertype")
        Log.d("takatak",user.toString())

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        fetchLocation()
       tv_current_location.setOnClickListener {
           val bundle=Bundle()
           var user=arguments?.getString("usertype")
           bundle.putString("usertype2",user)
           val cl = CurrentLocation(currentLocation?.latitude.toString(),
               currentLocation?.longitude.toString(),
               addresses.get(0)
                   .getAddressLine(0).toString(),
                user.toString()
           )
//
//           bundle.putString("lattitude",currentLocation?.latitude.toString())
//           bundle.putString("longitude",currentLocation?.longitude.toString())
           bundle.putString("location", addresses.get(0)
               .getAddressLine(0).toString())
//           Log.d("hello1",currentLocation?.latitude.toString())
           bundle.putParcelable("usertype",cl)
           Log.d("shubhendra",cl.toString())

           findNavController().navigate(R.id.action_locationFragment2_to_creatingAccountStep12,bundle)
       }


        binding?.etSearchLocation?.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                val location = binding.etSearchLocation.query.toString()
                var addressList: List<Address>? = null
                if (location != null || !location.equals("")) {
                    val geocoder = Geocoder(requireContext())
                    try {
                        addressList = geocoder.getFromLocationName(location , 1)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    val center = CameraUpdateFactory.newLatLng(LatLng(20.5937 , 78.9629))
                    mmap?.moveCamera(center)
                    val address = addressList?.get(0)
                    val latLng = LatLng(address!!.latitude , address!!.longitude)
                    mmap?.addMarker(MarkerOptions().position(latLng).title(location))
                    mmap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                    mmap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , 14f))
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext() , Manifest.permission.ACCESS_FINE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext() , Manifest.permission.ACCESS_COARSE_LOCATION
            ) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity() ,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION) , permissionCode
            )
        }
        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null) {
                currentLocation = location
                Toast.makeText(requireContext(), currentLocation!!.latitude.toString() + " " + currentLocation!!.longitude, Toast.LENGTH_SHORT).show()
                val supportMapFragment =
                    (childFragmentManager.findFragmentById(R.id.view_blankWhitelocation) as SupportMapFragment?)!!
                supportMapFragment.getMapAsync(this)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mmap = googleMap
        val latLng = LatLng(currentLocation!!.latitude , currentLocation!!.longitude)
        val markerOptions = MarkerOptions().position(latLng).title("I am here!").icon(
            BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
        )
        val center = CameraUpdateFactory.newLatLng(LatLng(20.5937 , 78.9629))
        googleMap?.moveCamera(center)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , 14f))
        googleMap?.addCircle(
            CircleOptions().center(latLng).radius(400.0).strokeWidth(3f).strokeColor(Color.BLUE)
                .fillColor(Color.argb(70 , 0 , 51 , 153))
        )
        googleMap?.addMarker(markerOptions)
        googleMap?.setOnCameraMoveListener(this)
        googleMap?.setOnCameraMoveStartedListener(this)
        googleMap?.setOnCameraIdleListener(this)
        val geocoder = Geocoder(activity, Locale.getDefault())
         addresses = geocoder.getFromLocation(currentLocation!!.latitude, currentLocation!!.longitude, 1)
        Log.d(
            "ArrayValues", addresses?.get(0)
                ?.getAddressLine(0).toString()
        )
        Log.d(
            "Location1",
            currentLocation!!.latitude.toString() + " " + currentLocation!!.longitude
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int ,
        permissions: Array<String?> ,
        grantResults: IntArray
    ) {
        when (requestCode) {
            permissionCode -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocation()
            }
        }
    }

    override fun onLocationChanged(glocation: Location) {
        location = glocation
        val geocoder = Geocoder(requireContext() , Locale.getDefault())
        var addresses: List<Address>? = null
        try {
            addresses = geocoder.getFromLocation(location!!.latitude , location!!.longitude , 1)
        } catch (e: IOException) {

        }
        setAddress(addresses!![0])
    }

    private fun setAddress(addressLL: Address) {
        address = addressLL
        if (address != null) {
            if (address?.getAddressLine(0) != null) {
                // getLocation.setText(address?.getAddressLine(0))
                Log.e("t1" , address?.getAddressLine(0).toString()  )
            }
            if (address?.getAddressLine(1) != null) {
                // getLocation.setText(getLocation.getText().toString() + address?.getAddressLine(1))
                Log.e("t1" , address?.getAddressLine(1).toString())
            }
        }
    }

    override fun onStatusChanged(provider: String? , status: Int , extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

    override fun onCameraMove() {
    }

    override fun onCameraMoveStarted(p0: Int) {
    }

    override fun onCameraIdle() {
        var addresses: List<Address>? = null
        val geocoder = Geocoder(requireContext() , Locale.getDefault())
        try {
            addresses = geocoder.getFromLocation(
                mmap!!.getCameraPosition().target.latitude ,
                mmap!!.getCameraPosition().target.longitude ,
                1
            )
            setAddress(addresses!![0])
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}