class PhotosController < ApplicationController
  before_action :authenticate_user!
  before_action :set_photo, only: [:show, :edit, :update, :destroy]

  def index
    @photos = Photo.all
  end

  def show
    @photo = Photo.find(params[:id])
    respond_to do |format|
      format.html
      format.js
    end
  end

  def new
    @photo = Photo.new
    @photo.user = current_user
  end

  def edit
  end

  def create
    @photo = Photo.new(photo_params)
    @photo.user = current_user

    respond_to do |format|
      if @photo.save
        format.html { redirect_to current_user, notice: 'Photo was successfully created.' }
        format.json { render :show, status: :created, location: current_user }
      else
        format.html { render :new }
        format.json { render json: @photo.errors, status: :unprocessable_entity }
      end
    end
  end

  def update
    respond_to do |format|
      if @photo.update(photo_params)
        format.html { redirect_to current_user, notice: 'Photo was successfully updated.' }
        format.json { render :show, status: :ok, location: current_user }
      else
        format.html { render :edit }
        format.json { render json: @photo.errors, status: :unprocessable_entity }
      end
    end
  end

  def destroy
    @photo.destroy
    respond_to do |format|
      format.html { redirect_to current_user, notice: 'Photo was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  def delete_image_attachment
    image = ActiveStorage::Attachment.find(params[:id])
    image.purge
    redirect_back(fallback_location: request.referer)
  end

  private
    def set_photo
      @photo = Photo.find(params[:id])
    end

    def photo_params
      params.require(:photo).permit(:title, :description, :sharing_mode, :image)
    end
end
