package com.applaunch.paginationdemo.camera

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applaunch.paginationdemo.R
import com.applaunch.paginationdemo.camera.model.Picture
import com.applaunch.paginationdemo.databinding.ItemListGalleryBinding
import com.applaunch.paginationdemo.pagination.interfaces.listener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class GalleryAdapter(val context: Context, val singleSelection: Boolean):RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    var count = 0
    private var pictures: List<Picture> = ArrayList()
    private val picturesSelected: MutableList<Picture> = ArrayList()
    private var lastCheckedPosition = -1
    inner class ViewHolder(var binding: ItemListGalleryBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListGalleryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picture = pictures[position]
        holder.binding.apply {


            Glide.with(context).load(picture.path).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true).into(ivGalleryPhoto)
            itemCountView(holder, picture)

            if (singleSelection) {
                if (position == lastCheckedPosition) {
                    tvCount.text = "1"
                    tvCount.setBackgroundResource(R.drawable.gallery_selected)
                    picturesSelected.clear()
                    picture.selectCount = 1
                    picturesSelected.add(picture)
                } else {
                    tvCount.text = ""
                    tvCount.setBackgroundResource(R.drawable.bg_circular)
                }
            }

            galleryConstraint.setOnClickListener {
                picture.position = position

                if (singleSelection) {
                    val copyOfLastCheckedPosition = lastCheckedPosition
                    lastCheckedPosition = holder.bindingAdapterPosition
                    notifyItemChanged(copyOfLastCheckedPosition)
                    notifyItemChanged(lastCheckedPosition)

                } else {
                    if (picture.selectCount > 0) {
                        count--
                        tvCount.text = ""
                        tvCount.setBackgroundResource(R.drawable.bg_circular)
                        picturesSelected.remove(picture)
                        for (pictureUpdate in pictures) {
                            if (pictureUpdate.selectCount > picture.selectCount) {
                                pictureUpdate.selectCount = pictureUpdate.selectCount - 1
                                notifyItemChanged(pictureUpdate.position)
                            }
                        }
                        picture.selectCount = 0
                    } else {
                        count++
                        picture.selectCount = count
                        picturesSelected.add(picture)
                        tvCount.text = picture.selectCount.toString()
                        tvCount.setBackgroundResource(R.drawable.gallery_selected)
                    }
                }
                listener!!.invoke(holder.itemView, picture, picturesSelected.size)
            }
        }
    }

    override fun getItemCount(): Int = R.layout.item_list_gallery
    private fun itemCountView(holder: ViewHolder,picture:Picture) {
        holder.binding.apply {
            if (picture.selectCount > 0) {
                tvCount.text = picture.selectCount.toString()
                tvCount.setBackgroundResource(R.drawable.gallery_selected)
            } else {
                tvCount.text = ""
                tvCount.setBackgroundResource(R.drawable.bg_circular)
            }
        }
    }
}
