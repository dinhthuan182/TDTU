class AdminController < ApplicationController
  before_action :authenticate_user!
  before_action :ensure_admin_user!

  def photo
    @photos = Photo.all.paginate(page: params[:page], per_page: 10)
  end

  def album
    @albums = Album.all.paginate(page: params[:page], per_page: 10)
  end

  def user
    @users = User.all.paginate(page: params[:page], per_page: 10)
  end

  def ensure_admin_user!
    unless current_user and current_user.admin?
      redirect_to root_path
    end
  end
end
