class ImagesController < ApplicationController
  before_action :set_album

  def create
    if images_params[:images]
      add_more_images(images_params[:images])
    end
    @album.save
    redirect_to current_user
  end

  def destroy
    remove_image_at_index(params[:id].to_i)
    @album.save
    redirect_back(fallback_location: root_path)
  end

  private

  def set_album
    @album = Album.find(params[:album_id])
  end

  def add_more_images(new_images)
    images = @album.images
    images += new_images
    @album.images = images
  end

  def remove_image_at_index(index)
     remain_images = @album.images
     if index == 0 && @album.images.size == 1
       @album.remove_images!
     else
       deleted_image = remain_images.delete_at(index)
       deleted_image.try(:remove!)
       @album.images = remain_images
     end
  end

  def images_params
    params.require(:album).permit({images: []})
  end
end
