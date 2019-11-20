class HomePageController < ApplicationController
  PAGE = 4
  before_action :authenticate_user!
  def feeds
    @users = current_user.followings.ids
    @photos_feeds = Photo.where(user_id: @users).published_photo.paginate(page: params[:page_photos], per_page: PAGE)
    @albums_feeds = Album.where(user_id: @users).published_album.paginate(page: params[:page_albums], per_page: PAGE)
  end

  def discovers
    @photos_discovers = Photo.published_photo.where.not(user_id: current_user).paginate(page_photos: params[:page], per_page: PAGE)
    @albums_discovers = Album.published_album.where.not(user_id: current_user).paginate(page_albums: params[:page], per_page: PAGE)
  end
end