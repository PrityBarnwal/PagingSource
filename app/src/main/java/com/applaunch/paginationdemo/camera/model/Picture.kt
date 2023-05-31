package com.applaunch.paginationdemo.camera.model

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore

open class Picture : Parcelable {
    var path: String? = null
    var selectCount = 0
    var position = 0

    constructor() {}
    protected constructor(`in`: Parcel) {
        path = `in`.readString()
        selectCount = `in`.readInt()
        position = `in`.readInt()
    }

    override fun equals(obj: Any?): Boolean {
        return selectCount == (obj as Picture?)!!.selectCount
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(path)
        dest.writeInt(selectCount)
        dest.writeInt(position)
    }

    companion object CREATOR : Parcelable.Creator<Picture> {
        override fun createFromParcel(parcel: Parcel): Picture {
            return Picture(parcel)
        }

        override fun newArray(size: Int): Array<Picture?> {
            return arrayOfNulls(size)
        }
    }

    fun getGalleryPhotos(context: Context): List<Picture?> {
        val pictures = ArrayList<Picture?>()
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID)
        val orderBy = MediaStore.Images.Media._ID
        context.contentResolver.query(uri, columns, null, null, orderBy).use { cursorPhotos ->
            if (cursorPhotos != null && cursorPhotos.count > 0) {
                while (cursorPhotos.moveToNext()) {
                    val picture =
                        Picture()
                    val indexPath =
                        cursorPhotos.getColumnIndex(MediaStore.MediaColumns.DATA)
                    picture.path = cursorPhotos.getString(indexPath)
                    pictures.add(picture)
                }
            }
        }
        pictures.reverse()
        return pictures
    }
}