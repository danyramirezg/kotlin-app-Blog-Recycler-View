package com.dany.appblogrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_blog_list_item.view.*

class BlogRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<BlogPost> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // These lines of code are always the same in all codes (except the layout name, ex: Layout_blog_list_item)
        val layoutInflater = LayoutInflater.from(parent.context)
        return BlogViewHolder(layoutInflater.inflate(R.layout.layout_blog_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // When the holder is the type of the class BlogViewHolder:
        when (holder) {
            is BlogViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    fun submitList(blogList: List<BlogPost>) {
        items = blogList
    }

    class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val blogImage = itemView.blog_image
        val blogTitle = itemView.blog_title
        val blogAuthor = itemView.blog_author

        fun bind(blogPost: BlogPost) {

            blogAuthor.setText(blogPost.username)
            blogTitle.setText(blogPost.title)

            val requestOptions = RequestOptions()

                // I'm telling Glide (library images) What to display if is an error
                // or if for any reason the image can't display
                .placeholder(R.drawable.ic_launcher_background)
                .error((R.drawable.ic_launcher_background))

            // Setting the image with the Glide library:
            Glide
                .with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(blogPost.image)
                .into(blogImage)
        }
    }
}

