package it.localhostsoftware.maps.google

import android.graphics.Point
import com.google.android.gms.maps.CameraUpdateFactory
import it.localhostsoftware.maps.google.model.GoogleCameraPosition
import it.localhostsoftware.maps.google.model.GoogleLatLng
import it.localhostsoftware.maps.google.model.GoogleLatLngBounds

class GoogleCameraUpdateFactory : it.localhostsoftware.maps.CameraUpdateFactory<GoogleCameraUpdate, GoogleCameraPosition, GoogleLatLng, GoogleLatLngBounds> {
    override fun zoomIn() =
        GoogleCameraUpdate(CameraUpdateFactory.zoomIn())

    override fun zoomOut() =
        GoogleCameraUpdate(CameraUpdateFactory.zoomOut())

    override fun scrollBy(var0: Float, var1: Float) =
        GoogleCameraUpdate(CameraUpdateFactory.scrollBy(var0, var1))

    override fun zoomTo(var0: Float) =
        GoogleCameraUpdate(CameraUpdateFactory.zoomTo(var0))

    override fun zoomBy(var0: Float) =
        GoogleCameraUpdate(CameraUpdateFactory.zoomBy(var0))

    override fun zoomBy(var0: Float, var1: Point) =
        GoogleCameraUpdate(CameraUpdateFactory.zoomBy(var0, var1))

    override fun newCameraPosition(var0: GoogleCameraPosition) =
        GoogleCameraUpdate(CameraUpdateFactory.newCameraPosition(var0.cp))

    override fun newLatLng(var0: GoogleLatLng) =
        GoogleCameraUpdate(CameraUpdateFactory.newLatLng(var0.ll))

    override fun newLatLngZoom(var0: GoogleLatLng, var1: Float) =
        GoogleCameraUpdate(CameraUpdateFactory.newLatLngZoom(var0.ll, var1))

    override fun newLatLngBounds(var0: GoogleLatLngBounds, var1: Int) =
        GoogleCameraUpdate(CameraUpdateFactory.newLatLngBounds(var0.lb, var1))

    override fun newLatLngBounds(var0: GoogleLatLngBounds, var1: Int, var2: Int, var3: Int) =
        GoogleCameraUpdate(CameraUpdateFactory.newLatLngBounds(var0.lb, var1, var2, var3))
}