package it.localhostsoftware.maps.google

import android.graphics.Point
import com.google.android.gms.maps.CameraUpdateFactory
import it.localhostsoftware.maps.model.CameraPosition
import it.localhostsoftware.maps.model.LatLng
import it.localhostsoftware.maps.model.LatLngBounds

class GoogleCameraUpdateFactory : it.localhostsoftware.maps.CameraUpdateFactory {
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

    override fun newCameraPosition(var0: CameraPosition<*>) =
        GoogleCameraUpdate(CameraUpdateFactory.newCameraPosition((var0.cp as com.google.android.gms.maps.model.CameraPosition)))

    override fun newLatLng(var0: LatLng<*>) =
        GoogleCameraUpdate(CameraUpdateFactory.newLatLng((var0.ll as com.google.android.gms.maps.model.LatLng)))

    override fun newLatLngZoom(var0: LatLng<*>, var1: Float) =
        GoogleCameraUpdate(CameraUpdateFactory.newLatLngZoom((var0.ll as com.google.android.gms.maps.model.LatLng), var1))

    override fun newLatLngBounds(var0: LatLngBounds<*>, var1: Int) =
        GoogleCameraUpdate(CameraUpdateFactory.newLatLngBounds((var0.lb as com.google.android.gms.maps.model.LatLngBounds), var1))

    override fun newLatLngBounds(var0: LatLngBounds<*>, var1: Int, var2: Int, var3: Int) =
        GoogleCameraUpdate(CameraUpdateFactory.newLatLngBounds((var0.lb as com.google.android.gms.maps.model.LatLngBounds), var1, var2, var3))
}