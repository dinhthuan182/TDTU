class Photos::LikesController < LikesController
  before_action :authenticate_user!
  before_action :set_photo

  def create
    @photo = Photo.find(params[:photo_id])
    @like = @photo.likes.new
    @like.user = current_user
    @like.save
    respond_to do |format|
      format.html { redirect_to @like }
      format.js { render :file => "likes/create_photo.js.erb" }
    end
  end

  def destroy
    @like = Like.find(params[:id])
    @like.destroy
    respond_to do |format|
      format.html { redirect_to @like }
      format.js { render :file => "/likes/destroy_photo.js.erb" }
    end
  end

  private
  def set_photo
    @photo = Photo.find(params[:photo_id]) if params[:id]
  end
end