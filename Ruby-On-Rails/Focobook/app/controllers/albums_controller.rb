class AlbumsController < ApplicationController
  before_action :authenticate_user!
  before_action :set_album, except: [:index, :new, :create, :delete_image_attachment]
  before_action :set_album, only: [:show, :edit, :update, :destroy]

  def index
    @albums = Album.all
  end

  def show
    @album = Album.find(params[:id])
    respond_to do |format|
      format.html
      format.js
    end
  end

  def new
    @album = Album.new
    @album.user = current_user
  end

  def edit
  end

  def create
    @album = Album.new(album_params)
    @album.images = params[:images]
    @album.user = current_user

    respond_to do |format|
      if @album.save
        format.html { redirect_to current_user, notice: 'Album was successfully created.' }
        format.json { render :show, status: :created, location: current_user }
      else
        format.html { render :new }
        format.json { render json: @album.errors, status: :unprocessable_entity }
      end
    end
  end

  def update
    respond_to do |format|
      if @album.update(album_params)
        format.html { redirect_to current_user, notice: 'Album was successfully updated.' }
        format.json { render :show, status: :ok, location: current_user }
      else
        format.html { render :edit }
        format.json { render json: @album.errors, status: :unprocessable_entity }
      end
    end
  end

  def destroy
    @album.destroy
    respond_to do |format|
      format.html { redirect_to current_user, notice: 'Album was successfully destroyed.' }
      format.json { head :no_content }
    end
  end

  private
    def set_album
      @album = Album.find(params[:id])
    end

    def album_params
      params.require(:album).permit(:title, :description, :sharing_mode, {images: []})
    end
end
