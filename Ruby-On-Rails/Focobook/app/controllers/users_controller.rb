class UsersController < ApplicationController
  before_action :authenticate_user!
  before_action :set_user, only: [:edit, :update, :show, :destroy ]
  def index
    @users = User.all
  end

  def edit
  end


   def update
     if @user.update(user_params)
       redirect_to :admin
     else
       render 'edit'
     end
   end

  def show
    @get_photos = Photo.where(user_id: @user.id)
    @get_albums = Album.where(user_id: @user.id)
    @get_followings = @user.followings
    @get_followers = @user.followers
  end

  def destroy
    if @user.present?
      @user.destroy
    end
    redirect_to :admin
  end

  def followings
    @title = "Followings"
    @user  = User.find(params[:id])
    @users = @user.followings.paginate(page: params[:page])
  end

  def followers
    @title = "Followers"
    @user  = User.find(params[:id])
    @users = @user.followers.paginate(page: params[:page])
  end

  def set_user
    @user = User.find(params[:id])
  end

  def user_params
      params.require(:user).permit(:first_name, :last_name, :email, :password, :activated)
    end
end
